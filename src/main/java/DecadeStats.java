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
        Integer rides_mkm_sum = 0;
        Integer coll_acc_sum = 0;
        Integer coll_fatal_sum = 0;
        Integer road_acc_sum = 0;
        Integer road_fatal_sum = 0;
        Integer move_acc_sum = 0;
        Integer move_fatal_sum = 0;
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
                            1900 + old_decade * 10, rides_mkm_sum,
                            coll_acc_sum, coll_fatal_sum,
                            road_acc_sum, road_fatal_sum,
                            move_acc_sum, move_fatal_sum);
                }
                old_decade = new_decade;
                rides_mkm_sum = currentYear.ridesMkm;
                coll_acc_sum = currentYear.collAcc;
                coll_fatal_sum = currentYear.collFatal;
                road_acc_sum = currentYear.roadAcc;
                road_fatal_sum = currentYear.roadFatal;
                move_acc_sum = currentYear.moveAcc;
                move_fatal_sum = currentYear.moveFatal;
            } else {
                rides_mkm_sum += currentYear.ridesMkm;
                coll_acc_sum += currentYear.collAcc;
                coll_fatal_sum += currentYear.collFatal;
                road_acc_sum += currentYear.roadAcc;
                road_fatal_sum += currentYear.roadFatal;
                move_acc_sum += currentYear.moveAcc;
                move_fatal_sum += currentYear.moveFatal;
            }
        }
        out.printf("Decade %d: " + "rides_mkm=%d, coll_acc=%d, " +
                        "coll_fatal=%d, road_acc=%d, " +
                        "road_fatal=%d, move_acc=%d, " +
                        "move_fatal=%d\n",
                1900 + old_decade * 10, rides_mkm_sum,
                coll_acc_sum, coll_fatal_sum,
                road_acc_sum, road_fatal_sum,
                move_acc_sum, move_fatal_sum);

        // average per decade
        out.println("\nAverage per decade ====================");
        Integer rides_mkm_avg = 0;
        Integer coll_acc_avg = 0;
        Integer coll_fatal_avg = 0;
        Integer road_acc_avg = 0;
        Integer road_fatal_avg = 0;
        Integer move_acc_avg = 0;
        Integer move_fatal_avg = 0;
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
                            1900 + old_decade * 10, (double) rides_mkm_avg / decade_cnt,
                            (double) coll_acc_avg / decade_cnt, (double) coll_fatal_avg / decade_cnt,
                            (double) road_acc_avg / decade_cnt, (double) road_fatal_avg / decade_cnt,
                            (double) move_acc_avg / decade_cnt, (double) move_fatal_avg / decade_cnt);
                }
                old_decade = new_decade;
                rides_mkm_avg = currentYear.ridesMkm;
                coll_acc_avg = currentYear.collAcc;
                coll_fatal_avg = currentYear.collFatal;
                road_acc_avg = currentYear.roadAcc;
                road_fatal_avg = currentYear.roadFatal;
                move_acc_avg = currentYear.moveAcc;
                move_fatal_avg = currentYear.moveFatal;
                decade_cnt = 1;
            } else {
                rides_mkm_avg += currentYear.ridesMkm;
                coll_acc_avg += currentYear.collAcc;
                coll_fatal_avg += currentYear.collFatal;
                road_acc_avg += currentYear.roadAcc;
                road_fatal_avg += currentYear.roadFatal;
                move_acc_avg += currentYear.moveAcc;
                move_fatal_avg += currentYear.moveFatal;
                decade_cnt++;
            }
        }
        out.printf("Decade %d: " + "rides_mkm=%.2f, coll_acc=%.2f, " +
                        "coll_fatal=%.2f, road_acc=%.2f, " +
                        "road_fatal=%.2f, move_acc=%.2f, " +
                        "move_fatal=%.2f\n",
                1900 + old_decade * 10, ((double) rides_mkm_avg) / decade_cnt,
                ((double) coll_acc_avg) / decade_cnt, ((double) coll_fatal_avg) / decade_cnt,
                ((double) road_acc_avg) / decade_cnt, ((double) road_fatal_avg) / decade_cnt,
                ((double) move_acc_avg) / decade_cnt, ((double) move_fatal_avg) / decade_cnt);
    }
}
