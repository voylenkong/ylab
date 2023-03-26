package local.vng.lesson04.movie;

import local.vng.lesson04.movie.parser.CsvParser;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;

import static java.util.Objects.nonNull;
import static local.vng.lesson04.movie.parser.CsvHeaders.*;

/**
 * MovieLoaderImpl
 * <p>
 * Имплементация {@link MovieLoader}
 *
 * @author VoylenkoNG
 * 25.03.2023
 */
public class MovieLoaderImpl implements MovieLoader {
    private DataSource dataSource;
    private static final String INSERT_MOVIE = "INSERT INTO movie (year, length, title, subject, actors, actress, director, popularity, awards) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";

    public MovieLoaderImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void loadData(File file) {
        List<Movie> movies = getMovies(file);

        saveToDb(movies);
    }

    /**
     * Получение фильмов {@link Movie} из файла
     *
     * @param file Файл
     * @return Список фильмов {@link Movie}
     */
    private static List<Movie> getMovies(File file) {
        CsvParser csvParser = new CsvParser();

        var records = csvParser.parseCsv(file, ";");

        List<Movie> movies = new ArrayList<>();
        for (Map<String, String> record : records) {
            Movie movie = getMovie(record);
            movies.add(movie);
        }
        return movies;
    }

    /**
     * Получение фильма {@link Movie} из входящей записи
     *
     * @param record Входящая запись
     * @return Фильм {@link Movie}
     */
    private static Movie getMovie(Map<String, String> record) {
        Movie movie = new Movie();
        if (!record.get(HEADER_YEAR.getDescription()).isEmpty()) {
            movie.setYear(Integer.valueOf(record.get(HEADER_YEAR.getDescription())));
        }
        if (!record.get(HEADER_LENGTH.getDescription()).isEmpty()) {
            movie.setLength(Integer.valueOf(record.get(HEADER_LENGTH.getDescription())));
        }
        movie.setTitle(record.get(HEADER_TITLE.getDescription()));
        movie.setSubject(record.get(HEADER_SUBJECT.getDescription()));
        movie.setActors(record.get(HEADER_ACTOR.getDescription()));
        movie.setActress(record.get(HEADER_ACTRESS.getDescription()));
        movie.setDirector(record.get(HEADER_DIRECTOR.getDescription()));

        if (!record.get(HEADER_POPULARITY.getDescription()).isEmpty()) {
            movie.setPopularity(Integer.valueOf(record.get(HEADER_POPULARITY.getDescription())));
        }
        if (!record.get(HEADER_AWARDS.getDescription()).isEmpty()) {
            movie.setAwards(record.get(HEADER_AWARDS.getDescription()).equals("Yes"));
        }

        return movie;
    }

    /**
     * Вставка БД листа фильмов
     *
     * @param movies Фильм
     */
    private void saveToDb(List<Movie> movies) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_MOVIE);
        ) {
            for (Movie movie : movies) {
                setIntParameter(preparedStatement,1,  movie.getYear());
                setIntParameter(preparedStatement, 2, movie.getLength());
                setStrParameter(preparedStatement, 3, movie.getTitle());
                setStrParameter(preparedStatement, 4, movie.getSubject());
                setStrParameter(preparedStatement, 5, movie.getActors());
                setStrParameter(preparedStatement, 6, movie.getActress());
                setStrParameter(preparedStatement, 7, movie.getDirector());
                setIntParameter(preparedStatement, 8, movie.getPopularity());
                setBooleanParameter(preparedStatement, 9, movie.getAwards());

                preparedStatement.addBatch();
            }

            preparedStatement.executeBatch();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Утсановка типа Integer
     *
     * @param preparedStatement Prepared Statement
     * @param index Индекс Prepared Statement
     * @param value Значение
     * @throws SQLException
     */
    private void setIntParameter(PreparedStatement preparedStatement, int index, Integer value) throws SQLException{
        if (nonNull(value)) {
            preparedStatement.setInt(index, value);
        } else {
            preparedStatement.setNull(index, Types.INTEGER);
        }
    }

    /**
     * Утсановка типа String
     *
     * @param preparedStatement Prepared Statement
     * @param index Индекс Prepared Statement
     * @param value Значение
     * @throws SQLException
     */
    private void setStrParameter(PreparedStatement preparedStatement, int index, String value) throws SQLException{
        if (nonNull(value)) {
            preparedStatement.setString(index, value);
        } else {
            preparedStatement.setNull(index, Types.VARCHAR);
        }
    }

    /**
     * Утсановка типа Boolean
     *
     * @param preparedStatement Prepared Statement
     * @param index Индекс Prepared Statement
     * @param value Значение
     * @throws SQLException
     */
    private void setBooleanParameter(PreparedStatement preparedStatement, int index, Boolean value) throws SQLException{
        if (nonNull(value)) {
            preparedStatement.setBoolean(index, value);
        } else {
            preparedStatement.setNull(index, Types.BOOLEAN);
        }
    }
}
