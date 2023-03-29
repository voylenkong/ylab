package local.vng.lesson04.movie;

import java.io.File;
import java.sql.SQLException;
import java.util.Scanner;
import javax.sql.DataSource;

import local.vng.lesson04.DbUtil;

/**
 * MovieTest
 *
 * @author VoylenkoNG
 * 25.03.2023
 */
public class MovieTest {
    public static void main(String[] args) throws SQLException {
        DataSource dataSource = initDb();
        MovieLoader movieLoader = new MovieLoaderImpl(dataSource);

        Scanner scanner = new Scanner(System.in);

        System.out.print("Путь к файлу БД фильмов *.csv: ");
        String filePath = scanner.next();
        File dataFile = new File(filePath);

        movieLoader.loadData(dataFile);

        scanner.close();
        /**
         * Тут написать в комментариях запрос, выводящий количество фильмов
         * каждого жанра (GROUP BY).
         *
         * select subject, COUNT(subject)
         * from movie
         * group by subject
         */
    }

    /**
     * Инициализация DB
     *
     * @return Datasource
     * @throws SQLException
     */
    private static DataSource initDb() throws SQLException {
        String createMovieTable = "drop table if exists movie;"
                + "CREATE TABLE IF NOT EXISTS movie (\n"
                + "\tid bigserial NOT NULL,\n"
                + "\t\"year\" int4,\n"
                + "\tlength int4,\n"
                + "\ttitle varchar,\n"
                + "\tsubject varchar,\n"
                + "\tactors varchar,\n"
                + "\tactress varchar,\n"
                + "\tdirector varchar,\n"
                + "\tpopularity int4,\n"
                + "\tawards bool,\n"
                + "\tCONSTRAINT movie_pkey PRIMARY KEY (id)\n"
                + ");";
        DataSource dataSource = DbUtil.buildDataSource();
        DbUtil.applyDdl(createMovieTable, dataSource);
        return dataSource;
    }
}
