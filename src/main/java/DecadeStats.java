import java.util.ArrayList;
import java.util.List;

public class DecadeStats {
    private final TrainData trainData;

    public DecadeStats(TrainData trainData) {
        this.trainData = trainData;
    }

    public List<YearStats> total() {
        ArrayList<YearStats> result = new ArrayList<>();
        YearStats sumStats = new YearStats();
        int old_decade = -1;
        for (int i = 0; i < trainData.numRows(); i++) {
            YearStats yearStats = trainData.getRow(i);
            Integer new_decade = yearStats.getDecade();
            if (new_decade.compareTo(old_decade) != 0) {
                if (old_decade != -1) {
                    result.add(sumStats);
                }
                old_decade = new_decade;
                sumStats = new YearStats();
            }
            sumStats.addStats(yearStats);
        }
        result.add(sumStats);
        return result;
    }

    public List<YearStats> average() {
        int old_decade = -1;
        ArrayList<YearStats> result = new ArrayList<>();
        YearStats sumStats = new YearStats();
        YearStats countStats = new YearStats();
        for (int i = 0; i < trainData.numRows(); i++) {
            YearStats yearStats = trainData.getRow(i);
            Integer new_decade = yearStats.getDecade();
            if (new_decade.compareTo(old_decade) != 0) {
                if (old_decade != -1) {
                    result.add(calcAverage(sumStats, countStats));
                }
                old_decade = new_decade;
                sumStats = new YearStats();
                countStats = new YearStats();
            }
            sumStats.addStats(yearStats);
            countStats.countStats(yearStats);
        }
        result.add(calcAverage(sumStats, countStats));
        return result;
    }

    private YearStats calcAverage(YearStats sum, YearStats count) {
        YearStats result = new YearStats();
        result.year = sum.year;
        result.ridesMkm = sum.ridesMkm / count.ridesMkm;
        result.collAcc = sum.collAcc / count.collAcc;
        result.collFatal = sum.collFatal / count.collFatal;
        result.roadAcc = sum.roadAcc / count.roadAcc;
        result.roadFatal = sum.roadFatal / count.roadFatal;
        result.moveAcc = sum.moveAcc / count.moveAcc;
        result.moveFatal = sum.moveFatal / count.moveFatal;

        return result;
    }
}
