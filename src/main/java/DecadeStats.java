import java.util.ArrayList;
import java.util.List;

public class DecadeStats {
    private final TrainData trainData;

    public DecadeStats(TrainData trainData) {
        this.trainData = trainData;
    }

    private List<YearStats> calculate(StatsOperator statistics) {
        ArrayList<YearStats> result = new ArrayList<>();
        int old_decade = -1;
        for (int i = 0; i < trainData.numRows(); i++) {
            YearStats yearStats = trainData.getRow(i);
            Integer new_decade = yearStats.getDecade();
            if (new_decade.compareTo(old_decade) != 0) {
                if (old_decade != -1) {
                    result.add(statistics.getStats());
                }
                old_decade = new_decade;
                statistics.reset();
            }
            statistics.addYear(yearStats);
        }
        result.add(statistics.getStats());
        return result;
    }

    /**
     * Calculates sum of properties group by decade.
     * NA values are treated as zero.
     */
    public List<YearStats> total() {
        return calculate(new StatsSum());
    }

    /**
     * Calculates average of properties grouped by decade.
     * NA values are treated as zero.
     * Average of NA-only values is also set to zero (for consistency).
     */
    public List<YearStats> average() {
        return calculate(new StatsAvg());
    }
}
