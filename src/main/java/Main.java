import java.io.*;
import java.net.URISyntaxException;

public class Main {

    public static void main(String[] args) throws IOException, URISyntaxException {
        Main main = new Main();

        File csv = new File(Main.class.getResource("BritishTrainFatalities.csv").toURI());
        FileInputStream csvStream = new FileInputStream(csv);
        PrintWriter out = new PrintWriter(System.out, true);
        main.stats(csvStream, out);
    }

    void stats(InputStream csv, PrintWriter out) {
        TrainData trainData = new TrainData();
        trainData.readCSV(csv);
        trainData.calcDecade();

        // sums per decade
        out.println("Total per decade ====================");
        Integer rides_mkm_sum = 0;
        Integer coll_acc_sum = 0;
        Integer coll_fatal_sum = 0;
        Integer road_acc_sum = 0;
        Integer road_fatal_sum = 0;
        Integer move_acc_sum = 0;
        Integer move_fatal_sum = 0;
        Integer old_decade = -1;
        for (int i = 0; i < trainData.decade.size(); i++) {
            Integer new_decade = trainData.decade.get(i);
            if (new_decade != old_decade) {
                if (old_decade != -1) {
                    out.println(String.format(
                            "Decade %d: " + "rides_mkm=%d, coll_acc=%d, " +
                                    "coll_fatal=%d, road_acc=%d, " +
                                    "road_fatal=%d, move_acc=%d, " +
                                    "move_fatal=%d",
                            1900 + old_decade * 10, rides_mkm_sum,
                            coll_acc_sum, coll_fatal_sum,
                            road_acc_sum, road_fatal_sum,
                            move_acc_sum, move_fatal_sum));
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
                move_acc_sum += trainData.move_acc.get(i);
                move_fatal_sum += trainData.move_fatal.get(i);
            }
        }
        out.println(String.format(
                "Decade %d: " + "rides_mkm=%d, coll_acc=%d, " +
                        "coll_fatal=%d, road_acc=%d, " +
                        "road_fatal=%d, move_acc=%d, " +
                        "move_fatal=%d",
                1900 + old_decade * 10, rides_mkm_sum,
                coll_acc_sum, coll_fatal_sum,
                road_acc_sum, road_fatal_sum,
                move_acc_sum, move_fatal_sum));

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
        for (int i = 0; i < trainData.decade.size(); i++) {
            Integer new_decade = trainData.decade.get(i);
            if (new_decade != old_decade) {
                if (old_decade != -1) {
                    out.println(String.format(
                            "Decade %d: " + "rides_mkm=%.2f, coll_acc=%.2f, " +
                                    "coll_fatal=%.2f, road_acc=%.2f, " +
                                    "road_fatal=%.2f, move_acc=%.2f, " +
                                    "move_fatal=%.2f",
                            1900 + old_decade * 10, (double) rides_mkm_avg / decade_cnt,
                            (double) coll_acc_avg / decade_cnt, (double) coll_fatal_avg / decade_cnt,
                            (double) road_acc_avg / decade_cnt, (double) road_fatal_avg / decade_cnt,
                            (double) move_acc_avg / decade_cnt, (double) move_fatal_avg / decade_cnt));
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
                rides_mkm_avg += trainData.rides_mkm.get(i);
                coll_acc_avg += trainData.coll_acc.get(i);
                coll_fatal_avg += trainData.coll_fatal.get(i);
                road_acc_avg += trainData.road_acc.get(i);
                road_fatal_avg += trainData.road_fatal.get(i);
                move_acc_avg += trainData.move_acc.get(i);
                move_fatal_avg += trainData.move_fatal.get(i);
                decade_cnt++;
            }
        }
        out.println(String.format(
                "Decade %d: " + "rides_mkm=%.2f, coll_acc=%.2f, " +
                        "coll_fatal=%.2f, road_acc=%.2f, " +
                        "road_fatal=%.2f, move_acc=%.2f, " +
                        "move_fatal=%.2f",
                1900 + old_decade * 10, ((double) rides_mkm_avg) / decade_cnt,
                ((double) coll_acc_avg) / decade_cnt, ((double) coll_fatal_avg) / decade_cnt,
                ((double) road_acc_avg) / decade_cnt, ((double) road_fatal_avg) / decade_cnt,
                ((double) move_acc_avg) / decade_cnt, ((double) move_fatal_avg) / decade_cnt));
    }
}
