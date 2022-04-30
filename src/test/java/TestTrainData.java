import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestTrainData {
    @Test
    public void testReadCSVOneLine() {
        String csv = "first line\n1993,100,200,300,40,50,6,7\n";
        TrainData trainData = TrainData.fromString(csv);
        YearStats row = trainData.getRow(0);
        assertEquals(1993, row.year);
        assertEquals(100, (int)row.ridesMkm);
        assertEquals(200, (int)row.collAcc);
        assertEquals(300, (int)row.collFatal);
        assertEquals(40, (int)row.roadAcc);
        assertEquals(50, (int)row.roadFatal);
        assertEquals(6, (int)row.moveAcc);
        assertEquals(7, (int)row.moveFatal);
    }

    @Test
    public void testReadCSVTwoLines() {
        String csv = "first line\n1993,100,200,300,40,50,6,7\n1994,11,21,31,41,51,61,71\n";
        TrainData trainData = TrainData.fromString(csv);
        YearStats row = trainData.getRow(1);
        assertEquals(1994, row.year);
        assertEquals(11, (int)row.ridesMkm);
        assertEquals(21, (int)row.collAcc);
        assertEquals(31, (int)row.collFatal);
        assertEquals(41, (int)row.roadAcc);
        assertEquals(51, (int)row.roadFatal);
        assertEquals(61, (int)row.moveAcc);
        assertEquals(71, (int)row.moveFatal);
    }
}
