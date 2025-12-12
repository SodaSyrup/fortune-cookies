package net.syrupstudios.fortunecookie;


import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffects;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FortuneManager {
    private static final Random RANDOM = new Random();
    private static final List<Fortune> FORTUNES = new ArrayList<>();
    private static final List<StatusEffect> POSITIVE_EFFECTS = List.of(
            StatusEffects.SPEED,
            StatusEffects.HASTE,
            StatusEffects.STRENGTH,
            StatusEffects.INSTANT_HEALTH,
            StatusEffects.JUMP_BOOST,
            StatusEffects.REGENERATION,
            StatusEffects.RESISTANCE,
            StatusEffects.FIRE_RESISTANCE,
            StatusEffects.WATER_BREATHING,
            StatusEffects.INVISIBILITY,
            StatusEffects.NIGHT_VISION,
            StatusEffects.HEALTH_BOOST,
            StatusEffects.ABSORPTION,
            StatusEffects.SATURATION,
            StatusEffects.LUCK,
            StatusEffects.SLOW_FALLING,
            StatusEffects.CONDUIT_POWER,
            StatusEffects.DOLPHINS_GRACE,
            StatusEffects.HERO_OF_THE_VILLAGE
    );
    private static final List<StatusEffect> NEUTRAL_EFFECTS = List.of(
            StatusEffects.GLOWING,
            StatusEffects.BAD_OMEN
    );
    private static final List<StatusEffect> NEGATIVE_EFFECTS = List.of(
            StatusEffects.SLOWNESS,
            StatusEffects.MINING_FATIGUE,
            StatusEffects.INSTANT_DAMAGE,
            StatusEffects.NAUSEA,
            StatusEffects.BLINDNESS,
            StatusEffects.HUNGER,
            StatusEffects.WEAKNESS,
            StatusEffects.POISON,
            StatusEffects.WITHER,
            StatusEffects.LEVITATION,
            StatusEffects.UNLUCK,
            StatusEffects.DARKNESS
    );
/**
 * Here we are populating our fortunes from each of the three subdivisions of the config file
 */
    public static void initialize() {
        //Add positive fortunes from config to fortunes list
        FortuneConfig.positiveFortunes.forEach(positiveFortune ->
                FORTUNES.add(new Fortune(positiveFortune, LuckEffect.GOOD)));
        //Add neutral fortunes from config to fortunes list
        FortuneConfig.neutralFortunes.forEach(neutralFortune ->
                FORTUNES.add(new Fortune(neutralFortune,LuckEffect.NEUTRAL)));
        //Add negative fortunes from config to fortunes list
        FortuneConfig.NegativeFortunes.forEach(negativeFortune ->
                FORTUNES.add(new Fortune(negativeFortune,LuckEffect.BAD)));
    }

    /**
     * Creates a random fortune from popualted list
     * @return Fortune - which contains a value and a LuckEffect. If list is empty, returns a NEUTRAL luck effect
     * fortune w/ a value reading "Your Fortune Cookie is Empty"
     */
    public static Fortune getRandomFortune() {
        if (FORTUNES.isEmpty()) {
            return new Fortune("Your fortune cookie is empty!", LuckEffect.NEUTRAL);
        }
        return FORTUNES.get(RANDOM.nextInt(FORTUNES.size()));
    }

    /**
     * Grabs random Status effect dependent on LuckEffect
     * @param fortune - fortune object to base status effect off of
     * @return StatusEffect
     */
    public static StatusEffect getFortuneStatusEffect(Fortune fortune) {
        if (fortune.getLuckEffect() == LuckEffect.GOOD) {
           return POSITIVE_EFFECTS.get(RANDOM.nextInt(POSITIVE_EFFECTS.size()));
        }
        else if (fortune.getLuckEffect() == LuckEffect.NEUTRAL) {
            return NEUTRAL_EFFECTS.get(RANDOM.nextInt(NEUTRAL_EFFECTS.size()));
        }
        return NEGATIVE_EFFECTS.get(RANDOM.nextInt(NEGATIVE_EFFECTS.size()));
    }
}
