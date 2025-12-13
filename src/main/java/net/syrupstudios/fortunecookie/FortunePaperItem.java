package net.syrupstudios.fortunecookie;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class FortunePaperItem extends Item {
    public FortunePaperItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand){
        //Standard check for use to ensure we are dealing w/ an actual player
        if (!world.isClient && user instanceof ServerPlayerEntity player ) {
            //check to see if player is crouching, and ensure the fortune paper they are using hasn't been used yet.
            if (user.isSneaking() && !hasFortune(player.getMainHandStack().getNbt())) {
                Fortune fortune = FortuneManager.getRandomFortune();
                player.getMainHandStack().decrement(1);

                // Give fortune paper to player
                ItemStack fortunePaper = new ItemStack(FortuneCookieMod.FORTUNE_PAPER);
                FortunePaperItem.setFortune(fortunePaper, fortune.getFortuneValue());

                if (!player.getInventory().insertStack(fortunePaper)) {
                    player.dropItem(fortunePaper, false);
                }
                //Calls FortuneManager and gets random status effect dependent on status
                player.addStatusEffect(new StatusEffectInstance(FortuneManager.getFortuneStatusEffect(fortune), 665, 0));

                // Send packet to client to open UI
                FortunePacketHandler.sendFortuneToClient(player, fortune.getFortuneValue());
            }
        }
        return TypedActionResult.success(user.getStackInHand(hand));
    }

    public static void setFortune(ItemStack stack, String fortune) {
        NbtCompound nbt = stack.getOrCreateNbt();
        nbt.putString("Fortune", fortune);
    }

    public static String getFortune(ItemStack stack) {
        NbtCompound nbt = stack.getNbt();
        if (hasFortune(nbt)){
            return nbt.getString("Fortune");
        }
        return "Your fortune awaits.. Crouch + Use to discover your fate!";
    }

    private static boolean hasFortune(NbtCompound nbt) {
        return nbt != null && nbt.contains("Fortune");
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        String fortune = getFortune(stack);
        tooltip.add(Text.literal("\"" + fortune + "\"").styled(style ->
                style.withItalic(true).withColor(0xFFD700)));
    }
}