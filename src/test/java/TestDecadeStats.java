import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TestDecadeStats {
    private DecadeStats decadeStats;

    @Before
    public void setUp() {
        String csv = "first line\n" +
                "1990,100,111,10,0,1,4,1\n"+
                "1994,200,222,30,0,1,6,5\n"+
                "1999,300,333,41,0,1,2,3\n"+
                "2015,11,21,31,41,51,61,71\n";

        ByteArrayInputStream csvStream = new ByteArrayInputStream(csv.getBytes());
        TrainData trainData = new TrainData();
        trainData.readCSV(csvStream);
        decadeStats = new DecadeStats(trainData);
    }

    @Test
    public void testTotal() {
        List<YearStats> stats = decadeStats.total();
        assertEquals(2, stats.size());

        YearStats nineties = stats.get(0);
        assertEquals(9, (int)nineties.getDecade());
        assertEquals(600, nineties.ridesMkm, 0);
        assertEquals(666, nineties.collAcc, 0);
        assertEquals(81, nineties.collFatal, 0);
        assertEquals(0, nineties.roadAcc, 0);
        assertEquals(3, nineties.roadFatal, 0);
        assertEquals(12, nineties.moveAcc, 0);
        assertEquals(9, nineties.moveFatal, 0);

        YearStats teens = stats.get(1);
        assertEquals(11, (int)teens.getDecade());
        assertEquals(11, teens.ridesMkm, 0);
        assertEquals(21, teens.collAcc, 0);
        assertEquals(31, teens.collFatal, 0);
        assertEquals(41, teens.roadAcc, 0);
        assertEquals(51, teens.roadFatal, 0);
        assertEquals(61, teens.moveAcc, 0);
        assertEquals(71, teens.moveFatal, 0);
    }

    @Test
    public void testAverage() {
        List<YearStats> stats = decadeStats.average();
        assertEquals(2, stats.size());

        YearStats nineties = stats.get(0);
        assertEquals(9, (int)nineties.getDecade());
        assertEquals(200, nineties.ridesMkm, 0);
        assertEquals(222, nineties.collAcc, 0);
        assertEquals(27, nineties.collFatal, 0);
        assertEquals(0, nineties.roadAcc, 0);
        assertEquals(1, nineties.roadFatal, 0);
        assertEquals(4, nineties.moveAcc, 0);
        assertEquals(3, nineties.moveFatal, 0);

        YearStats teens = stats.get(1);
        assertEquals(11, (int)teens.getDecade());
        assertEquals(11, teens.ridesMkm, 0);
        assertEquals(21, teens.collAcc, 0);
        assertEquals(31, teens.collFatal, 0);
        assertEquals(41, teens.roadAcc, 0);
        assertEquals(51, teens.roadFatal, 0);
        assertEquals(61, teens.moveAcc, 0);
        assertEquals(71, teens.moveFatal, 0);
    }
}
