package local.vng.module02.printer;

import local.vng.module02.accumulator.StatsAccumulator;

/**
 * RateLimitedPrinterImpl
 * <p>
 * Имплементация {@link RateLimitedPrinter}
 *
 * @author VoylenkoNG 11.03.2023
 */
public class RateLimitedPrinterImpl implements RateLimitedPrinter {

    /**
     * Интервал, через который возможна печать
     */
    private int interval;
    /**
     * Время последней печати в милисекундах
     */
    private long lastPrintTime;

    /**
     * Конструктор с параметром
     *
     * @param interval Интервал, через который возможна печать
     */
    public RateLimitedPrinterImpl(int interval) {
        this.interval = interval;
        this.lastPrintTime = 0;
    }

    @Override
    public void print(String message) {
        if (System.currentTimeMillis() - this.lastPrintTime >= interval) {
            System.out.println(message);
            this.lastPrintTime = System.currentTimeMillis();
        }
    }
}
