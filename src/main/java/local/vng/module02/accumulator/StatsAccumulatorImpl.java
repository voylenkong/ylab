package local.vng.module02.statsaccumulator;

/**
 * StatsAccumulator
 *
 * @author VoylenkoNG
 * 11.03.2023
 */
public class StatsAccumulatorImpl implements StatsAccumulator{

  private int min;
  private int max;
  private int count;

  private int sum;
  private Double avg;

  public StatsAccumulatorImpl() {

  }

  @Override
  public void add(int value) {
    if (this.min > value) {
      this.min = value;
    }

    if (this.max < value) {
      this.max = value;
    }

    this.count++;

    this.sum += value;

    this.avg = (double)this.sum / this.count;

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
