package net.syrupstudios.fortunecookie.data;

public class Fortune {
    private final String fortune;
    private final LuckEffect luckEffect; //if true gives luck, if it is false gives bad luck

    public Fortune(String fortuneValue, LuckEffect luckEffect) {
        this.fortune = fortuneValue;
        this.luckEffect = luckEffect;
    }

    public LuckEffect getLuckEffect() {
        return luckEffect;
    }

    public String getFortuneValue() {
        return fortune;
    }
}
