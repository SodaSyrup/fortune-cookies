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
            String fortune = FortuneManager.getRandomFortune(world);

            // Give fortune paper to player
            ItemStack fortunePaper = new ItemStack(FortuneCookieMod.FORTUNE_PAPER);
            FortunePaperItem.setFortune(fortunePaper, fortune);

            if (!player.getInventory().insertStack(fortunePaper)) {
                player.dropItem(fortunePaper, false);
            }

            // Send packet to client to open UI
            FortunePacketHandler.sendFortuneToClient(player, fortune);
        }

        return result;
    }
}
