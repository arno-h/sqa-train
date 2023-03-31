import java.io.PrintWriter;

public class DecadeStats {
    final TrainData trainData;
    final PrintWriter out;

    public DecadeStats(TrainData trainData, PrintWriter out) {
        this.trainData = trainData;
        this.out = out;
    }

    void stats() {
        // sums per decade
        out.println("Total per decade ====================");
        YearStats sumStats = new YearStats();
        int old_decade = -1;
        for (int i = 0; i < trainData.numRows(); i++) {
            YearStats currentYear = trainData.getRow(i);
            Integer new_decade = currentYear.getDecade();
            if (new_decade != old_decade) {
                if (old_decade != -1) {
                    out.printf("Decade %d: " + "rides_mkm=%d, coll_acc=%d, " +
                                    "coll_fatal=%d, road_acc=%d, " +
                                    "road_fatal=%d, move_acc=%d, " +
                                    "move_fatal=%d\n",
                            1900 + old_decade * 10, sumStats.ridesMkm,
                            sumStats.collAcc, sumStats.collFatal,
                            sumStats.roadAcc, sumStats.roadFatal,
                            sumStats.moveAcc, sumStats.moveFatal);
                    sumStats = new YearStats();
                }
                old_decade = new_decade;
            }
            sumStats.addStats(currentYear);
        }
        out.printf("Decade %d: " + "rides_mkm=%d, coll_acc=%d, " +
                        "coll_fatal=%d, road_acc=%d, " +
                        "road_fatal=%d, move_acc=%d, " +
                        "move_fatal=%d\n",
                1900 + old_decade * 10, sumStats.ridesMkm,
                sumStats.collAcc, sumStats.collFatal,
                sumStats.roadAcc, sumStats.roadFatal,
                sumStats.moveAcc, sumStats.moveFatal);

        // average per decade
        out.println("\nAverage per decade ====================");
        YearStats avgStats = new YearStats();
        old_decade = -1;
        int decade_cnt = 0;
        for (int i = 0; i < trainData.numRows(); i++) {
            YearStats currentYear = trainData.getRow(i);
            Integer new_decade = currentYear.getDecade();
            if (new_decade != old_decade) {
                if (old_decade != -1) {
                    out.printf("Decade %d: " + "rides_mkm=%.2f, coll_acc=%.2f, " +
                                    "coll_fatal=%.2f, road_acc=%.2f, " +
                                    "road_fatal=%.2f, move_acc=%.2f, " +
                                    "move_fatal=%.2f\n",
                            1900 + old_decade * 10, (double) avgStats.ridesMkm / decade_cnt,
                            (double) avgStats.collAcc / decade_cnt, (double) avgStats.collFatal / decade_cnt,
                            (double) avgStats.roadAcc / decade_cnt, (double) avgStats.roadFatal / decade_cnt,
                            (double) avgStats.moveAcc / decade_cnt, (double) avgStats.moveFatal / decade_cnt);
                }
                old_decade = new_decade;
                avgStats = new YearStats();
                decade_cnt = 1;
            } else {
                decade_cnt++;
            }
            avgStats.addStats(currentYear);
        }
        out.printf("Decade %d: " + "rides_mkm=%.2f, coll_acc=%.2f, " +
                        "coll_fatal=%.2f, road_acc=%.2f, " +
                        "road_fatal=%.2f, move_acc=%.2f, " +
                        "move_fatal=%.2f\n",
                1900 + old_decade * 10, ((double) avgStats.ridesMkm) / decade_cnt,
                ((double) avgStats.collAcc) / decade_cnt, ((double) avgStats.collFatal) / decade_cnt,
                ((double) avgStats.roadAcc) / decade_cnt, ((double) avgStats.roadFatal) / decade_cnt,
                ((double) avgStats.moveAcc) / decade_cnt, ((double) avgStats.moveFatal) / decade_cnt);
    }
}
