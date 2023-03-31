import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.PrintWriter;
import java.io.StringWriter;

public class TestEnd2End {
    @Test
    public void testEnd2End() {
        String csv = "year,rides_mkm,coll_acc,coll_fatal,road_acc,road_fatal,move_acc,move_fatal\n" +
                "1989,436,4,10,4,6,48,49\n" +
                "1998,487,0,0,4,4,31,31\n" +
                "1999,505,1,31,2,2,25,25\n" +
                "2000,503,1,4,3,3,18,18\n" +
                "2001,508,0,0,4,13,23,23\n" +
                "2002,516,1,7,3,3,30,30\n";
        StringWriter out = new StringWriter();
        PrintWriter outWriter = new PrintWriter(out);
        Main trainStats = new Main();
        // when
        trainStats.stats(new ByteArrayInputStream(csv.getBytes()), outWriter);
        // then
        String outStr = out.toString();
        // sum stats
        Assert.assertTrue(outStr.contains("Decade 1980: rides_mkm=436, coll_acc=4, coll_fatal=10, road_acc=4, road_fatal=6, move_acc=48, move_fatal=49"));
        Assert.assertTrue(outStr.contains("Decade 1990: rides_mkm=992, coll_acc=1, coll_fatal=31, road_acc=6, road_fatal=6, move_acc=56, move_fatal=56"));
        Assert.assertTrue(outStr.contains("Decade 2000: rides_mkm=1527, coll_acc=2, coll_fatal=11, road_acc=10, road_fatal=19, move_acc=71, move_fatal=71"));
        // avg stats
        Assert.assertTrue(outStr.contains("Decade 1980: rides_mkm=72.67, coll_acc=0.67, coll_fatal=1.67, road_acc=0.67, road_fatal=1.00, move_acc=8.00, move_fatal=8.17"));
        Assert.assertTrue(outStr.contains("Decade 1990: rides_mkm=165.33, coll_acc=0.17, coll_fatal=5.17, road_acc=1.00, road_fatal=1.00, move_acc=9.33, move_fatal=9.33"));
        Assert.assertTrue(outStr.contains("Decade 2000: rides_mkm=254.50, coll_acc=0.33, coll_fatal=1.83, road_acc=1.67, road_fatal=3.17, move_acc=11.83, move_fatal=11.83"));
    }
}
