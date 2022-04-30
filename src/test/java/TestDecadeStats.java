import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class TestDecadeStats {
    private DecadeStats decadeStats;

    @Before
    public void setUp() {
        String csv = "year,rides_mkm,coll_acc,coll_fatal,road_acc,road_fatal,move_acc,move_fatal\n" +
                "1989,436,4,10,4,6,48,49\n" +
                "1998,487,0,0,4,4,31,31\n" +
                "1999,505,1,31,2,2,25,25\n" +
                "2000,503,1,4,3,3,18,18\n" +
                "2001,508,0,0,4,13,23,23\n" +
                "2002,516,1,7,3,3,30,30\n";
        TrainData trainData = TrainData.fromString(csv);
        decadeStats = new DecadeStats(trainData);
    }

    @Test
    public void testTotal() {
        // when
        List<YearStats> result = decadeStats.total();
        // then
        YearStats eighties = result.get(0);
        Assert.assertEquals(436, eighties.ridesMkm, 0);
        Assert.assertEquals(4, eighties.collAcc, 0);
        Assert.assertEquals(10, eighties.collFatal, 0);
        Assert.assertEquals(4, eighties.roadAcc, 0);
        Assert.assertEquals(6, eighties.roadFatal, 0);
        Assert.assertEquals(48, eighties.moveAcc, 0);
        Assert.assertEquals(49, eighties.moveFatal, 0);
        YearStats naughties = result.get(2);
        Assert.assertEquals(1527, naughties.ridesMkm, 0);
        Assert.assertEquals(2, naughties.collAcc, 0);
        Assert.assertEquals(11, naughties.collFatal, 0);
        Assert.assertEquals(10, naughties.roadAcc, 0);
        Assert.assertEquals(19, naughties.roadFatal, 0);
        Assert.assertEquals(71, naughties.moveAcc, 0);
        Assert.assertEquals(71, naughties.moveFatal, 0);
    }

    @Test
    public void testAverage() {
        // when
        List<YearStats> result = decadeStats.average();
        // then
        YearStats nineties = result.get(1);
        Assert.assertEquals(496, nineties.ridesMkm, 0.01);
        Assert.assertEquals(0.5, nineties.collAcc, 0.01);
        Assert.assertEquals(15.5, nineties.collFatal, 0.01);
        Assert.assertEquals(3, nineties.roadAcc, 0.01);
        Assert.assertEquals(3, nineties.roadFatal, 0.01);
        Assert.assertEquals(28, nineties.moveAcc, 0.01);
        Assert.assertEquals(28, nineties.moveFatal, 0.01);
        YearStats naughties = result.get(2);
        Assert.assertEquals(509, naughties.ridesMkm, 0.01);
        Assert.assertEquals(0.67, naughties.collAcc, 0.01);
        Assert.assertEquals(3.67, naughties.collFatal, 0.01);
        Assert.assertEquals(3.33, naughties.roadAcc, 0.01);
        Assert.assertEquals(6.33, naughties.roadFatal, 0.01);
        Assert.assertEquals(23.67, naughties.moveAcc, 0.01);
        Assert.assertEquals(23.67, naughties.moveFatal, 0.01);
    }
}
