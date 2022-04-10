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
        trainData.calcDecade();
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
        for (int i = 0; i < trainData.decade.size(); i++) {
            Integer new_decade = trainData.decade.get(i);
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
                rides_mkm_sum = trainData.rides_mkm.get(i);
                coll_acc_sum = trainData.coll_acc.get(i);
                coll_fatal_sum = trainData.coll_fatal.get(i);
                road_acc_sum = trainData.road_acc.get(i);
                road_fatal_sum = trainData.road_fatal.get(i);
                move_acc_sum = trainData.move_acc.get(i);
                move_fatal_sum = trainData.move_fatal.get(i);
            } else {
                rides_mkm_sum += trainData.rides_mkm.get(i);
                coll_acc_sum += trainData.coll_acc.get(i);
                coll_fatal_sum += trainData.coll_fatal.get(i);
                road_acc_sum += trainData.road_acc.get(i);
                road_fatal_sum += trainData.road_fatal.get(i);
                move_acc_sum += trainData.move_acc.get(i) < 0 ? 0 : trainData.move_acc.get(i);
                move_fatal_sum += trainData.move_fatal.get(i) < 0 ? 0 : trainData.move_fatal.get(i);
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
        for (int i = 0; i < trainData.decade.size(); i++) {
            Integer new_decade = trainData.decade.get(i);
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
                rides_mkm_avg = trainData.rides_mkm.get(i);
                coll_acc_avg = trainData.coll_acc.get(i);
                coll_fatal_avg = trainData.coll_fatal.get(i);
                road_acc_avg = trainData.road_acc.get(i);
                road_fatal_avg = trainData.road_fatal.get(i);
                move_acc_avg = trainData.move_acc.get(i);
                move_fatal_avg = trainData.move_fatal.get(i);
                decade_cnt = 1;
            } else {
                decade_cnt++;
                rides_mkm_avg += trainData.rides_mkm.get(i);
                coll_acc_avg += trainData.coll_acc.get(i);
                coll_fatal_avg += trainData.coll_fatal.get(i);
                road_acc_avg += trainData.road_acc.get(i);
                road_fatal_avg += trainData.road_fatal.get(i);
                if (trainData.move_acc.get(i) >= 0) {
                    move_acc_avg += trainData.move_acc.get(i);
                    move_acc_cnt++;
                }
                if (trainData.move_fatal.get(i) >= 0) {
                    move_fatal_avg += trainData.move_fatal.get(i);
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
