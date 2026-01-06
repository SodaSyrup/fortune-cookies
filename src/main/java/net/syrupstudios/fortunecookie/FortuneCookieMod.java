package net.syrupstudios.fortunecookie;

import eu.midnightdust.lib.config.MidnightConfig;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.syrupstudios.fortunecookie.config.FortuneConfig;
import net.syrupstudios.fortunecookie.data.FortuneCookieItem;
import net.syrupstudios.fortunecookie.data.FortunePaperItem;

public class FortuneCookieMod implements ModInitializer {
    public static final String MOD_ID = "luckyducks_fortunecookies";

    public static final Item FORTUNE_COOKIE = new FortuneCookieItem(
            new FabricItemSettings()
                    .food(new FoodComponent.Builder()
                            .hunger(2)
                            .saturationModifier(0.3f)
                            .alwaysEdible()
                            .build())
    );

    public static final Item FORTUNE_PAPER = new FortunePaperItem(
            new FabricItemSettings()
    );

    @Override
    public void onInitialize() {
        MidnightConfig.init(FortuneCookieMod.MOD_ID, FortuneConfig.class);
        //populating Fortunes
        FortuneManager.initialize();
        Registry.register(Registries.ITEM, new Identifier(MOD_ID, "fortune_cookie"), FORTUNE_COOKIE);
        Registry.register(Registries.ITEM, new Identifier(MOD_ID, "fortune_paper"), FORTUNE_PAPER);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK).register(content -> {
            content.add(FORTUNE_COOKIE);
        });

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(content -> {
            content.add(FORTUNE_PAPER);
        });
    }
}