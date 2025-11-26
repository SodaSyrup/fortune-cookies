package net.syrupstudios.fortunecookie;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class FortunePaperItem extends Item {
    public FortunePaperItem(Settings settings) {
        super(settings);
    }

    public static void setFortune(ItemStack stack, String fortune) {
        NbtCompound nbt = stack.getOrCreateNbt();
        nbt.putString("Fortune", fortune);
    }

    public static String getFortune(ItemStack stack) {
        NbtCompound nbt = stack.getNbt();
        if (nbt != null && nbt.contains("Fortune")) {
            return nbt.getString("Fortune");
        }
        return "Your fortune is yet to be determined.";
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        String fortune = getFortune(stack);
        tooltip.add(Text.literal("\"" + fortune + "\"").styled(style ->
                style.withItalic(true).withColor(0xFFD700)));
    }
}