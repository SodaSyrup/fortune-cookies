package net.syrupstudios.fortunecookie;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.world.World;

public class FortuneCookieItem extends Item {
    public FortuneCookieItem(Settings settings) {
        super(settings);
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        ItemStack result = super.finishUsing(stack, world, user);

        if (!world.isClient && user instanceof ServerPlayerEntity player) {
            Fortune fortune = FortuneManager.getRandomFortune();

            // Give fortune paper to player
            ItemStack fortunePaper = new ItemStack(FortuneCookieMod.FORTUNE_PAPER);
            FortunePaperItem.setFortune(fortunePaper, fortune.getFortuneValue());

            if (!player.getInventory().insertStack(fortunePaper)) {
                player.dropItem(fortunePaper, false);
            }
            //determines whether fortune is positive or not, and adds effect accordingly
            player.addStatusEffect(
                    fortune.isPositive()
                            ? new StatusEffectInstance(StatusEffects.LUCK, 6000, 2)
                            : new StatusEffectInstance(StatusEffects.UNLUCK, 1000, 0));

            // Send packet to client to open UI
            FortunePacketHandler.sendFortuneToClient(player, fortune.getFortuneValue());
        }

        return result;
    }
}
