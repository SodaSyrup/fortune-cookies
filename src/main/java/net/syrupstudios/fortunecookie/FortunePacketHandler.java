package net.syrupstudios.fortunecookie;

import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;

public class FortunePacketHandler {
    public static final Identifier FORTUNE_PACKET_ID = new Identifier(FortuneCookieMod.MOD_ID, "fortune");

    public static void sendFortuneToClient(ServerPlayerEntity player, String fortune) {
        PacketByteBuf buf = PacketByteBufs.create();
        buf.writeString(fortune);
        ServerPlayNetworking.send(player, FORTUNE_PACKET_ID, buf);
    }
}
