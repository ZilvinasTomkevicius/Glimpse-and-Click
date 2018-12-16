package lt.ziltom.zilvinastomkevicius.projektas;

public class Level {

    private int levelCount;
    private long seconds;

    public Level() {

    }

    public Level(int levelCount, int seconds) {
        this.levelCount = levelCount;
        this.seconds = seconds;
    }

    public int getLevelCount() {
        return levelCount;
    }

    public void setLevelCount(int levelCount) {
        this.levelCount = levelCount;
    }

    public long getSeconds() {
        return seconds;
    }

    public void setSeconds(long seconds) {
        this.seconds = seconds;
    }
}
