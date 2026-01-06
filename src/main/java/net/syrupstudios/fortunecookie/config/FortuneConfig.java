package net.syrupstudios.fortunecookie.config;

import eu.midnightdust.lib.config.MidnightConfig;

import java.util.List;

public class FortuneConfig extends MidnightConfig {
    public static final String FORTUNES = "Fortunes";

    @Comment(category = FORTUNES, centered = true, name = "Enter Your Custom Fortunes Below!") public static Comment fortuneMenuHeader;
    @Comment(category = FORTUNES, centered = true, name = "Press the number to add additional fortunes.") public static Comment fortuneMenuHeader2;
    @Comment(category = FORTUNES, centered = true, name = "(You can also edit the .json directly for adding multiple fortunes)") public static Comment fortuneMenuHeader3;
    @Comment(category = FORTUNES) public static Comment spacer;
    @Entry(category = FORTUNES, name = "Positive Fortunes List")
    //Supplied default list here, Additions to config will save automatically these are just populated on cfg creation
    public static List<String> positiveFortunes = List.of(
            "Later today, an unexpected adventure awaits.",
            "Good health will be yours for a long time.",
            "The most beautiful adventures are not those we go seek.",
            "Soon you will be sitting on top of the world.",
            "A big fortune will descend upon you soon.",
            "A deeper appreciation of life's beauty is coming close.",
            "You will take a pleasant journey to a place far away.",
            "You will be awarded some great honor.",
            "Today's profits are yesterday's good well ripened.",
            "Little acorns lead to mighty oaks.",
            "A long lost relative will soon come along to your benefit.",
            "You will reach the highest possible point in your business or profession.",
            "You will make a new friend who will become a lifelong companion.",
            "Strike iron while it is hot.",
            "Look! Good fortune is around you.",
            "To love and be loved is to feel the sun from both sides.",
            "Happiness will bring you good luck.",
            "Pursue your wishes aggressively.",
            "You are on the verge of something big.",
            "Follow your heart and you will find happiness.",
            "The land of sunshine and relaxation awaits you.",
            "You are the life of the party.",
            "You will find yourself at a happy crossroads.",
            "Friends will guide you to your next opportunity.",
            "You will always be successful in your professional career."
    );

    @Entry(category = FORTUNES, name = "Neutral Fortunes List")
    public static List<String> neutralFortunes = List.of(
            "A partner can help you achieve success.",
            "When you do make a mistake, do not treat yourself as if though you were the mistake.",
            "Confucius says your eyes have magnetized a secret admirer.",
            "You are never bitter, deceptive, or petty.",
            "Seek advice from those you trust to make important decisions.",
            "A modest man never talks to himself.",
            "Follow the advice of a trusted friend this week.",
            "There is no substitute for hard work.",
            "Time is the wisest counselor.",
            "Come back later... I am sleeping. (Yes, cookies need their sleep, too.)",
            "Kindness is the art of making someone else’s day better.",
            "Let reality be reality."
    );

    @Entry(category = FORTUNES, name = "Negative Fortunes List")
    public static List<String> NegativeFortunes =List.of(
            "Life always gets harder near the summit",
            "If I bring forth what is inside me, what I bring forth will save me",
            "Your optimism will lead you to opportunities hidden in the shadows",
            "The difficulties of life are intended to make us better, not bitter",
            "The strength in your character will come in handy soon",
            "Your perseverance will come in handy soon",
            "In life, it's good to not get too comfortable",
            "There is no mistake so great as that of being always right",
            "He that can't endure the bad will not live to see the good",
            "There's more to life than just money, there's Bitcoin",
            "In human endeavor, chance favors the prepared mind",
            "A calm sea does not make a skilled sailor",
            "You don't become a failure until you are satisfied with being one"
    );

}
