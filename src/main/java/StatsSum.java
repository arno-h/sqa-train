public class StatsSum implements StatsOperator {
    private YearStats sum = new YearStats();

    @Override
    public void reset() {
        sum = new YearStats();
    }

    @Override
    public void addYear(YearStats yearStats) {
        sum.addStats(yearStats);
    }

    @Override
    public YearStats getStats() {
        return sum;
    }
}
