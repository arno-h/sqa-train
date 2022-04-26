import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

public class Main {

    public static void main(String[] args) throws IOException, URISyntaxException {
        Main main = new Main();
        URL pathURL = Main.class.getResource("BritishTrainFatalities.csv");
        if (pathURL == null) {
            System.out.println("Couldn't load CSV file");
            return;
        }
        File csv = new File(pathURL.toURI());
        TrainData trainData = new TrainData();
        trainData.readCSV(new FileInputStream(csv));
        main.stats(trainData);
    }

    void stats(TrainData trainData) {
        // sums per decade
        System.out.println("Total per decade ====================");
        Integer rides_mkm_sum = 0;
        Integer coll_acc_sum = 0;
        Integer coll_fatal_sum = 0;
        Integer road_acc_sum = 0;
        Integer road_fatal_sum = 0;
        Integer move_acc_sum = 0;
        Integer move_fatal_sum = 0;
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
                            1900 + old_decade * 10, rides_mkm_sum,
                            coll_acc_sum, coll_fatal_sum,
                            road_acc_sum, road_fatal_sum,
                            move_acc_sum, move_fatal_sum);
                }
                old_decade = new_decade;
                rides_mkm_sum = yearStats.ridesMkm;
                coll_acc_sum = yearStats.collAcc;
                coll_fatal_sum = yearStats.collFatal;
                road_acc_sum = yearStats.roadAcc;
                road_fatal_sum = yearStats.roadFatal;
                move_acc_sum = yearStats.moveAcc;
                move_fatal_sum = yearStats.moveFatal;
            } else {
                rides_mkm_sum += yearStats.ridesMkm;
                coll_acc_sum += yearStats.collAcc;
                coll_fatal_sum += yearStats.collFatal;
                road_acc_sum += yearStats.roadAcc;
                road_fatal_sum += yearStats.roadFatal;
                move_acc_sum += yearStats.moveAcc == null ? 0 : yearStats.moveAcc;
                move_fatal_sum += yearStats.moveFatal == null ? 0 : yearStats.moveFatal;
            }
        }
        System.out.printf(
                "Decade %d: " + "rides_mkm=%d, coll_acc=%d, " +
                        "coll_fatal=%d, road_acc=%d, " +
                        "road_fatal=%d, move_acc=%d, " +
                        "move_fatal=%d\n",
                1900 + old_decade * 10, rides_mkm_sum,
                coll_acc_sum, coll_fatal_sum,
                road_acc_sum, road_fatal_sum,
                move_acc_sum, move_fatal_sum);

        // average per decade
        System.out.println("\nAverage per decade ====================");
        Integer rides_mkm_avg = 0;
        Integer coll_acc_avg = 0;
        Integer coll_fatal_avg = 0;
        Integer road_acc_avg = 0;
        Integer road_fatal_avg = 0;
        Integer move_acc_avg = 0;
        Integer move_fatal_avg = 0;
        int move_acc_cnt = 0;
        int move_fatal_cnt = 0;
        int decade_cnt = 0;
        old_decade = -1;
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
                            1900 + old_decade * 10, (double) rides_mkm_avg / decade_cnt,
                            (double) coll_acc_avg / decade_cnt, (double) coll_fatal_avg / decade_cnt,
                            (double) road_acc_avg / decade_cnt, (double) road_fatal_avg / decade_cnt,
                            (double) move_acc_avg / move_acc_cnt, (double) move_fatal_avg / move_fatal_cnt);
                }
                old_decade = new_decade;
                rides_mkm_avg = yearStats.ridesMkm;
                coll_acc_avg = yearStats.collAcc;
                coll_fatal_avg = yearStats.collFatal;
                road_acc_avg = yearStats.roadAcc;
                road_fatal_avg = yearStats.roadFatal;
                move_acc_avg = yearStats.moveAcc;
                move_fatal_avg = yearStats.moveFatal;
                decade_cnt = 1;
            } else {
                decade_cnt++;
                rides_mkm_avg += yearStats.ridesMkm;
                coll_acc_avg += yearStats.collAcc;
                coll_fatal_avg += yearStats.collFatal;
                road_acc_avg += yearStats.roadAcc;
                road_fatal_avg += yearStats.roadFatal;
                if (yearStats.moveAcc != null) {
                    move_acc_avg += yearStats.moveAcc;
                    move_acc_cnt++;
                }
                if (yearStats.moveFatal != null) {
                    move_fatal_avg += yearStats.moveFatal;
                    move_fatal_cnt++;
                }
            }
        }
        System.out.printf(
                "Decade %d: " + "rides_mkm=%.2f, coll_acc=%.2f, " +
                        "coll_fatal=%.2f, road_acc=%.2f, " +
                        "road_fatal=%.2f, move_acc=%.2f, " +
                        "move_fatal=%.2f\n",
                1900 + old_decade * 10, ((double) rides_mkm_avg) / decade_cnt,
                ((double) coll_acc_avg) / decade_cnt, ((double) coll_fatal_avg) / decade_cnt,
                ((double) road_acc_avg) / decade_cnt, ((double) road_fatal_avg) / decade_cnt,
                ((double) move_acc_avg) / move_acc_cnt, ((double) move_fatal_avg) / move_fatal_cnt);
    }
}
