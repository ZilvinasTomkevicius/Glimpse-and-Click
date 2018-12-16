package lt.ziltom.zilvinastomkevicius.projektas;

import java.util.Comparator;
import java.util.Date;

public class Game implements Comparable<Game> {

    private String name;
    private int points;
    private Date date;

    public Game() {

    }

    public Game(String name, int points, Date date) {
        this.name = name;
        this.points = points;
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    @Override
    public int compareTo(Game g) {
        return getName().compareTo(g.getName());
    }

    public static Comparator<Game> byDate = (Game g1, Game g2) -> {
        // didėjanti tvarka, pradedant nuo mažiausios
        if (g1.getDate().before(g2.getDate())) {
            return -1;
        }
        if (g1.getDate().after(g2.getDate())) {
            return 1;
        }
        return 0;
    };
}
