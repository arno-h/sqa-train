import java.util.ArrayList;
import java.util.List;

public class DecadeStats {
    final TrainData trainData;

    public DecadeStats(TrainData trainData) {
        this.trainData = trainData;
    }

    public List<YearStats> total() {
        ArrayList<YearStats> result = new ArrayList<>();
        YearStats sumStats = new YearStats();
        int old_decade = -1;
        for (int i = 0; i < trainData.numRows(); i++) {
            YearStats currentYear = trainData.getRow(i);
            int new_decade = currentYear.getDecade();
            if (new_decade != old_decade) {
                if (old_decade != -1) {
                    result.add(sumStats);
                    sumStats = new YearStats();
                }
                old_decade = new_decade;
            }
            sumStats.addStats(currentYear);
        }
        result.add(sumStats);
        return result;
    }

    public List<YearStats> average() {
        int old_decade;
        ArrayList<YearStats> result = new ArrayList<>();
        YearStats avgStats = new YearStats();
        old_decade = -1;
        int decade_cnt = 0;
        for (int i = 0; i < trainData.numRows(); i++) {
            YearStats currentYear = trainData.getRow(i);
            int new_decade = currentYear.getDecade();
            if (new_decade != old_decade) {
                if (old_decade != -1) {
                    avgStats.divideBy(decade_cnt);
                    result.add(avgStats);
                }
                old_decade = new_decade;
                avgStats = new YearStats();
                decade_cnt = 1;
            } else {
                decade_cnt++;
            }
            avgStats.addStats(currentYear);
        }
        avgStats.divideBy(decade_cnt);
        result.add(avgStats);
        return result;
    }
}
