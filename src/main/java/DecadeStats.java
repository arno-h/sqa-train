import java.util.ArrayList;
import java.util.List;

public class DecadeStats {
    private final TrainData trainData;

    public DecadeStats(TrainData trainData) {
        this.trainData = trainData;
    }

    /**
     * Calculates sum of properties group by decade.
     * NA values are treated as zero.
     */
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

    /**
     * Calculates average of properties grouped by decade.
     * NA values are treated as zero.
     * Average of NA-only values is also set to zero (for consistency).
     */
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
        result.ridesMkm = zeroSafeDivide(sum.ridesMkm, count.ridesMkm);
        result.collAcc = zeroSafeDivide(sum.collAcc, count.collAcc);
        result.collFatal = zeroSafeDivide(sum.collFatal, count.collFatal);
        result.roadAcc = zeroSafeDivide(sum.roadAcc, count.roadAcc);
        result.roadFatal = zeroSafeDivide(sum.roadFatal, count.roadFatal);
        result.moveAcc = zeroSafeDivide(sum.moveAcc, count.moveAcc);
        result.moveFatal = zeroSafeDivide(sum.moveFatal, count.moveFatal);

        return result;
    }

    private Float zeroSafeDivide(Float sum, Float count) {
        if (count == 0) {
            return 0f;
        }
        return sum / count;
    }
}
