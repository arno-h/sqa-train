import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException, URISyntaxException {
        Main main = new Main();
        main.stats();
    }

    void stats() throws IOException, URISyntaxException {
        File csv = new File(getClass().getResource("BritishTrainFatalities.csv").toURI());
        Scanner scanner = new Scanner(csv);
        scanner.nextLine(); // ignore first line
        scanner.useDelimiter(", *|\n");
        ArrayList<Integer> year = new ArrayList<>();
        ArrayList<Integer> rides_mkm = new ArrayList<>();
        ArrayList<Integer> coll_acc = new ArrayList<>();
        ArrayList<Integer> coll_fatal = new ArrayList<>();
        ArrayList<Integer> road_acc = new ArrayList<>();
        ArrayList<Integer> road_fatal = new ArrayList<>();
        ArrayList<Integer> move_acc = new ArrayList<>();
        ArrayList<Integer> move_fatal = new ArrayList<>();
        ArrayList<Integer> decade = new ArrayList<>();
        while (scanner.hasNext()) {
            year.add(scanner.nextInt());
            rides_mkm.add(scanner.nextInt());
            coll_acc.add(scanner.nextInt());
            coll_fatal.add(scanner.nextInt());
            road_acc.add(scanner.nextInt());
            road_fatal.add(scanner.nextInt());
            move_acc.add(scanner.nextInt());
            move_fatal.add(scanner.nextInt());
            scanner.nextLine();
        }
        for (int i = 0; i < year.size(); i++) {
            decade.add((year.get(i) - 1900) / 10);
        }
        // sums per decade
        System.out.println("Total per decade ====================");
        Integer rides_mkm_sum = 0;
        Integer coll_acc_sum = 0;
        Integer coll_fatal_sum = 0;
        Integer road_acc_sum = 0;
        Integer road_fatal_sum = 0;
        Integer move_acc_sum = 0;
        Integer move_fatal_sum = 0;
        Integer old_decade = -1;
        for (int i = 0; i < decade.size(); i++) {
            Integer new_decade = decade.get(i);
            if (new_decade != old_decade) {
                if (old_decade != -1) {
                    System.out.println(String.format(
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
                rides_mkm_sum = rides_mkm.get(i);
                coll_acc_sum = coll_acc.get(i);
                coll_fatal_sum = coll_fatal.get(i);
                road_acc_sum = road_acc.get(i);
                road_fatal_sum = road_fatal.get(i);
                move_acc_sum = move_acc.get(i);
                move_fatal_sum = move_fatal.get(i);
            } else {
                rides_mkm_sum += rides_mkm.get(i);
                coll_acc_sum += coll_acc.get(i);
                coll_fatal_sum += coll_fatal.get(i);
                road_acc_sum += road_acc.get(i);
                road_fatal_sum += road_fatal.get(i);
                move_acc_sum += move_acc.get(i);
                move_fatal_sum += move_fatal.get(i);
            }
        }
        System.out.println(String.format(
                "Decade %d: " + "rides_mkm=%d, coll_acc=%d, " +
                        "coll_fatal=%d, road_acc=%d, " +
                        "road_fatal=%d, move_acc=%d, " +
                        "move_fatal=%d",
                1900 + old_decade * 10, rides_mkm_sum,
                coll_acc_sum, coll_fatal_sum,
                road_acc_sum, road_fatal_sum,
                move_acc_sum, move_fatal_sum));

        // average per decade
        System.out.println("\nAverage per decade ====================");
        Integer rides_mkm_avg = 0;
        Integer coll_acc_avg = 0;
        Integer coll_fatal_avg = 0;
        Integer road_acc_avg = 0;
        Integer road_fatal_avg = 0;
        Integer move_acc_avg = 0;
        Integer move_fatal_avg = 0;
        old_decade = -1;
        int decade_cnt = 0;
        for (int i = 0; i < decade.size(); i++) {
            Integer new_decade = decade.get(i);
            if (new_decade != old_decade) {
                if (old_decade != -1) {
                    System.out.println(String.format(
                            "Decade %d: " + "rides_mkm=%.2f, coll_acc=%.2f, " +
                                    "coll_fatal=%.2f, road_acc=%.2f, " +
                                    "road_fatal=%.2f, move_acc=%.2f, " +
                                    "move_fatal=%.2f",
                            1900 + old_decade * 10, (double)rides_mkm_avg / decade_cnt,
                            (double)coll_acc_avg / decade_cnt, (double)coll_fatal_avg / decade_cnt,
                            (double)road_acc_avg / decade_cnt, (double)road_fatal_avg / decade_cnt,
                            (double)move_acc_avg / decade_cnt, (double)move_fatal_avg / decade_cnt));
                }
                old_decade = new_decade;
                rides_mkm_avg = rides_mkm.get(i);
                coll_acc_avg = coll_acc.get(i);
                coll_fatal_avg = coll_fatal.get(i);
                road_acc_avg = road_acc.get(i);
                road_fatal_avg = road_fatal.get(i);
                move_acc_avg = move_acc.get(i);
                move_fatal_avg = move_fatal.get(i);
                decade_cnt = 1;
            } else {
                rides_mkm_avg += rides_mkm.get(i);
                coll_acc_avg += coll_acc.get(i);
                coll_fatal_avg += coll_fatal.get(i);
                road_acc_avg += road_acc.get(i);
                road_fatal_avg += road_fatal.get(i);
                move_acc_avg += move_acc.get(i);
                move_fatal_avg += move_fatal.get(i);
                decade_cnt++;
            }
        }
        System.out.println(String.format(
                "Decade %d: " + "rides_mkm=%.2f, coll_acc=%.2f, " +
                        "coll_fatal=%.2f, road_acc=%.2f, " +
                        "road_fatal=%.2f, move_acc=%.2f, " +
                        "move_fatal=%.2f",
                1900 + old_decade * 10, ((double)rides_mkm_avg) / decade_cnt,
                ((double)coll_acc_avg) / decade_cnt, ((double)coll_fatal_avg) / decade_cnt,
                ((double)road_acc_avg) / decade_cnt, ((double)road_fatal_avg) / decade_cnt,
                ((double)move_acc_avg) / decade_cnt, ((double)move_fatal_avg) / decade_cnt));
    }
}
