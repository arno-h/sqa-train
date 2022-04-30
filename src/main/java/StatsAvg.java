public class StatsAvg implements StatsOperator {
    private YearStats average = new YearStats();
    private int count;

    @Override
    public void reset() {
        average = new YearStats();
        count = 0;
    }

    @Override
    public void addYear(YearStats yearStats) {
        average.addStats(yearStats);
        count++;
    }

    @Override
    public YearStats getStats() {
        average.divideBy(count);    // Attention: as this overwrites 'average', you must not call addStats() afterwards
        return average;
    }
}
