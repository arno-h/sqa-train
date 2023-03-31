import org.junit.Assert;
import org.junit.Test;

public class TestYearStats {
    @Test
    public void testDecade() {
        YearStats yearStats = new YearStats();
        yearStats.year = 1900;
        Assert.assertEquals(1900, yearStats.getDecade());
        yearStats.year = 1999;
        Assert.assertEquals(1990, yearStats.getDecade());
        yearStats.year = 2023;
        Assert.assertEquals(2020, yearStats.getDecade());
    }
}
