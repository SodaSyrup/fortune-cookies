package net.syrupstudios.fortunecookie;

import net.minecraft.entity.LivingEntity;
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
            // Give fortune paper to player
            ItemStack fortunePaper = new ItemStack(FortuneCookieMod.FORTUNE_PAPER);
            if (!player.getInventory().insertStack(fortunePaper)) {
                player.dropItem(fortunePaper, false);
            }
        }
        return result;
    }
}
