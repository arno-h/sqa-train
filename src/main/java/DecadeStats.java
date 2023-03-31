import java.io.PrintWriter;

public class DecadeStats {
    final TrainData trainData;
    final PrintWriter out;

    public DecadeStats(TrainData trainData, PrintWriter out) {
        this.trainData = trainData;
        this.out = out;
    }

    public void total() {
        out.println("Total per decade ====================");
        YearStats sumStats = new YearStats();
        int old_decade = -1;
        for (int i = 0; i < trainData.numRows(); i++) {
            YearStats currentYear = trainData.getRow(i);
            Integer new_decade = currentYear.getDecade();
            if (new_decade != old_decade) {
                if (old_decade != -1) {
                    printTotal(old_decade, sumStats);
                    sumStats = new YearStats();
                }
                old_decade = new_decade;
            }
            sumStats.addStats(currentYear);
        }
        printTotal(old_decade, sumStats);
    }

    private void printTotal(int old_decade, YearStats sumStats) {
        out.printf("Decade %d: " + "rides_mkm=%d, coll_acc=%d, " +
                        "coll_fatal=%d, road_acc=%d, " +
                        "road_fatal=%d, move_acc=%d, " +
                        "move_fatal=%d\n",
                1900 + old_decade * 10, sumStats.ridesMkm,
                sumStats.collAcc, sumStats.collFatal,
                sumStats.roadAcc, sumStats.roadFatal,
                sumStats.moveAcc, sumStats.moveFatal);
    }

    public void average() {
        int old_decade;
        out.println("\nAverage per decade ====================");
        YearStats avgStats = new YearStats();
        old_decade = -1;
        int decade_cnt = 0;
        for (int i = 0; i < trainData.numRows(); i++) {
            YearStats currentYear = trainData.getRow(i);
            Integer new_decade = currentYear.getDecade();
            if (new_decade != old_decade) {
                if (old_decade != -1) {
                    printAverage(old_decade, avgStats, decade_cnt);
                }
                old_decade = new_decade;
                avgStats = new YearStats();
                decade_cnt = 1;
            } else {
                decade_cnt++;
            }
            avgStats.addStats(currentYear);
        }
        printAverage(old_decade, avgStats, decade_cnt);
    }

    private void printAverage(int decade, YearStats avgStats, int decadeCnt) {
        out.printf("Decade %d: " + "rides_mkm=%.2f, coll_acc=%.2f, " +
                        "coll_fatal=%.2f, road_acc=%.2f, " +
                        "road_fatal=%.2f, move_acc=%.2f, " +
                        "move_fatal=%.2f\n",
                1900 + decade * 10, ((double) avgStats.ridesMkm) / decadeCnt,
                ((double) avgStats.collAcc) / decadeCnt, ((double) avgStats.collFatal) / decadeCnt,
                ((double) avgStats.roadAcc) / decadeCnt, ((double) avgStats.roadFatal) / decadeCnt,
                ((double) avgStats.moveAcc) / decadeCnt, ((double) avgStats.moveFatal) / decadeCnt);
    }
}
