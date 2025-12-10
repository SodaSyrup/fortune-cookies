package net.syrupstudios.fortunecookie;

public class Fortune {
    private final String fortuneValue;
    private final int aura; //less than 0 corresponds to negative aura, 0 = neutral, greater than 0 is positive aura

    public Fortune(String fortuneValue, int aura) {
        this.fortuneValue = fortuneValue;
        this.aura = aura;
    }

    public int getAura() {
        return aura;
    }

    public String getFortuneValue() {
        return fortuneValue;
    }
}
