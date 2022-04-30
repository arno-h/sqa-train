public interface StatsOperator {
    void reset();

    void addYear(YearStats yearStats);

    YearStats getStats();
}
