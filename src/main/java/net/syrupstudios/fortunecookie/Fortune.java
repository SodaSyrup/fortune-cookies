package net.syrupstudios.fortunecookie;

public class Fortune {
    private final String fortune;
    private final Boolean isPositive; //if true gives luck, if it is false gives bad luck

    public Fortune(String fortuneValue, Boolean isPositive) {
        this.fortune = fortuneValue;
        this.isPositive = isPositive;
    }

    public Boolean isPositive() {
        return isPositive;
    }

    public String getFortuneValue() {
        return fortune;
    }
}
