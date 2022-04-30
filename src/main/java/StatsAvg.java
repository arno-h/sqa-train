public class StatsAvg implements  StatsOperator {
    YearStats sum = new YearStats();
    YearStats count = new YearStats();

    @Override
    public void reset() {
        sum = new YearStats();
        count = new YearStats();
    }

    @Override
    public void addYear(YearStats yearStats) {
        sum.addStats(yearStats);
        count.countStats(yearStats);
    }

    @Override
    public YearStats getStats() {
        YearStats result = new YearStats();
        result.year = sum.year;
        result.ridesMkm = zeroSafeDivide(sum.ridesMkm, count.ridesMkm);
        result.collAcc = zeroSafeDivide(sum.collAcc, count.collAcc);
        result.collFatal = zeroSafeDivide(sum.collFatal, count.collFatal);
        result.roadAcc = zeroSafeDivide(sum.roadAcc, count.roadAcc);
        result.roadFatal = zeroSafeDivide(sum.roadFatal, count.roadFatal);
        result.moveAcc = zeroSafeDivide(sum.moveAcc, count.moveAcc);
        result.moveFatal = zeroSafeDivide(sum.moveFatal, count.moveFatal);
        return result;
    }

    private Float zeroSafeDivide(Float sum, Float count) {
        if (count == 0) {
            return 0f;
        }
        return sum / count;
    }
}
