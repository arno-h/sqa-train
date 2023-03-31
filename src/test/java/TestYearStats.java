import org.junit.Assert;
import org.junit.Test;

public class TestYearStats {
    @Test
    public void testDecade() {
        YearStats yearStats = new YearStats();
        yearStats.year = 1900;
        Assert.assertEquals(Integer.valueOf(0), yearStats.getDecade());
        yearStats.year = 1999;
        Assert.assertEquals(Integer.valueOf(9), yearStats.getDecade());
        yearStats.year = 2023;
        Assert.assertEquals(Integer.valueOf(12), yearStats.getDecade());
    }
}
