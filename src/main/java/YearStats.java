public class YearStats {
    Integer year = 0;
    Integer rides_mkm = 0;
    Integer coll_acc = 0;
    Integer coll_fatal = 0;
    Integer road_acc = 0;
    Integer road_fatal = 0;
    Integer move_acc = 0;
    Integer move_fatal = 0;
    Integer decade = 0;

    void calcDecade() {
        decade = (year - 1900) / 10;
    }
}