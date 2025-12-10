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

            player.addStatusEffect(determineStatusEffect(fortune.getAura()));

            // Send packet to client to open UI
            FortunePacketHandler.sendFortuneToClient(player, fortune.getFortuneValue());
        }

        return result;
    }

    private StatusEffectInstance determineStatusEffect(int aura) {
        //negative aura check
        if (aura < 0) {
            return new StatusEffectInstance(StatusEffects.UNLUCK, 1000, 0);
        }
        //neutral aura check
        else if (aura == 0) {
            return new StatusEffectInstance(StatusEffects.GLOWING, 500, 0);
        }
        //if neither checks hit assumes positive aura
        return new StatusEffectInstance(StatusEffects.LUCK, 6000, 2);
    }
}
