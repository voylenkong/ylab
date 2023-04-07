package io.ylab.intensive.lesson02.accumulator;

/**
 * StatsAccumulatorImpl
 * <p>
 * Имплементация {@link StatsAccumulator}
 *
 * @author VoylenkoNG 11.03.2023
 */
public class StatsAccumulatorImpl implements StatsAccumulator {

    /**
     * Минимальное переданное число
     */
    private int min;
    /**
     * Максимаольное переданное число
     */
    private int max;
    /**
     * Количество всех переданных чисел
     */
    private int count;
    /**
     * Среднее арифметическое всех переданных чисел
     */
    private Double avg = 0.0;

    /**
     * Конструктор
     */
    public StatsAccumulatorImpl() {
    }

    @Override
    public void add(int value) {
        if (this.count == 0) {
            this.min = value;
            this.max = value;
        } else if (this.min > value) {
            this.min = value;
        } else if (this.max < value) {
            this.max = value;
        }

        this.avg = (this.avg * this.count + value) / (this.count + 1);

        this.count++;
    }

    @Override
    public int getMin() {
        return this.min;
    }

    @Override
    public int getMax() {
        return this.max;
    }

    @Override
    public int getCount() {
        return this.count;
    }

    @Override
    public Double getAvg() {
        return this.avg;
    }
}
