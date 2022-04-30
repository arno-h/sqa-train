import java.util.ArrayList;
import java.util.List;

public class DecadeStats {
    private final TrainData trainData;

    public DecadeStats(TrainData trainData) {
        this.trainData = trainData;
    }

    private List<YearStats> calculate(StatsOperator statistics) {
        ArrayList<YearStats> result = new ArrayList<>();
        int oldDecade = -1;
        for (int i = 0; i < trainData.numRows(); i++) {
            YearStats currentYear = trainData.getRow(i);
            int newDecade = currentYear.getDecade();
            if (newDecade != oldDecade) {
                if (oldDecade != -1) {
                    result.add(statistics.getStats());
                    statistics.reset();
                }
                oldDecade = newDecade;
            }
            statistics.addYear(currentYear);
        }
        result.add(statistics.getStats());
        return result;
    }

    public List<YearStats> total() {
        return calculate(new StatsSum());
    }

    public List<YearStats> average() {
        return calculate(new StatsAvg());
    }
}
