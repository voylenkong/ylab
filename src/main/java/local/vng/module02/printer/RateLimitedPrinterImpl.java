package local.vng.module02.ratelimitedprinter;

/**
 * RateLimitedPrinter
 *
 * @author VoylenkoNG
 * 11.03.2023
 */
public class RateLimitedPrinter {

  private int interval;

  private long lastPrintTime;

  public RateLimitedPrinter(int interval) {
    this.interval = interval;
    this.lastPrintTime = 0;
  }

  public void print(String message) {
    if (System.currentTimeMillis() - this.lastPrintTime >= interval) {
      System.out.println(message);
      this.lastPrintTime = System.currentTimeMillis();
    }

  }

}
