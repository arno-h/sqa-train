public class DecadeStats {
    private final TrainData trainData;

    public DecadeStats(TrainData trainData) {
        this.trainData = trainData;
    }

    void stats() {
        // sums per decade
        System.out.println("Total per decade ====================");
        YearStats sumStats = new YearStats();
        int old_decade = -1;
        for (int i = 0; i < trainData.numRows(); i++) {
            YearStats yearStats = trainData.getRow(i);
            Integer new_decade = yearStats.getDecade();
            if (new_decade.compareTo(old_decade) != 0) {
                if (old_decade != -1) {
                    System.out.printf(
                            "Decade %d: " + "rides_mkm=%d, coll_acc=%d, " +
                                    "coll_fatal=%d, road_acc=%d, " +
                                    "road_fatal=%d, move_acc=%d, " +
                                    "move_fatal=%d\n",
                            1900 + old_decade * 10, sumStats.ridesMkm,
                            sumStats.collAcc, sumStats.collFatal,
                            sumStats.roadAcc, sumStats.roadFatal,
                            sumStats.moveAcc, sumStats.moveFatal);
                }
                old_decade = new_decade;
                sumStats = new YearStats();
            }
            sumStats.addStats(yearStats);
        }
        System.out.printf(
                "Decade %d: " + "rides_mkm=%d, coll_acc=%d, " +
                        "coll_fatal=%d, road_acc=%d, " +
                        "road_fatal=%d, move_acc=%d, " +
                        "move_fatal=%d\n",
                1900 + old_decade * 10, sumStats.ridesMkm,
                sumStats.collAcc, sumStats.collFatal,
                sumStats.roadAcc, sumStats.roadFatal,
                sumStats.moveAcc, sumStats.moveFatal);

        // average per decade
        System.out.println("\nAverage per decade ====================");
        old_decade = -1;
        YearStats countStats = new YearStats();
        for (int i = 0; i < trainData.numRows(); i++) {
            YearStats yearStats = trainData.getRow(i);
            Integer new_decade = yearStats.getDecade();
            if (new_decade.compareTo(old_decade) != 0) {
                if (old_decade != -1) {
                    System.out.printf(
                            "Decade %d: " + "rides_mkm=%.2f, coll_acc=%.2f, " +
                                    "coll_fatal=%.2f, road_acc=%.2f, " +
                                    "road_fatal=%.2f, move_acc=%.2f, " +
                                    "move_fatal=%.2f\n",
                            1900 + old_decade * 10, (double) sumStats.ridesMkm / countStats.ridesMkm,
                            (double) sumStats.collAcc / countStats.collAcc, (double) sumStats.collFatal / countStats.collFatal,
                            (double) sumStats.roadAcc / countStats.roadAcc, (double) sumStats.roadFatal / countStats.roadFatal,
                            (double) sumStats.moveAcc / countStats.moveAcc, (double) sumStats.moveFatal / countStats.moveFatal);
                }
                old_decade = new_decade;
                sumStats = new YearStats();
                countStats = new YearStats();
            }
            sumStats.addStats(yearStats);
            countStats.countStats(yearStats);
        }
        System.out.printf(
                "Decade %d: " + "rides_mkm=%.2f, coll_acc=%.2f, " +
                        "coll_fatal=%.2f, road_acc=%.2f, " +
                        "road_fatal=%.2f, move_acc=%.2f, " +
                        "move_fatal=%.2f\n",
                1900 + old_decade * 10, (double) sumStats.ridesMkm / countStats.ridesMkm,
                (double) sumStats.collAcc / countStats.collAcc, (double) sumStats.collFatal / countStats.collFatal,
                (double) sumStats.roadAcc / countStats.roadAcc, (double) sumStats.roadFatal / countStats.roadFatal,
                (double) sumStats.moveAcc / countStats.moveAcc, (double) sumStats.moveFatal / countStats.moveFatal);
    }
}
