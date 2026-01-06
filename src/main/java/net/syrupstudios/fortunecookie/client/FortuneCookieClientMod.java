package net.syrupstudios.fortunecookie.client;

import net.syrupstudios.fortunecookie.FortunePacketHandler;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.MinecraftClient;
import net.syrupstudios.fortunecookie.data.FortuneScreen;

public class FortuneCookieClientMod implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ClientPlayNetworking.registerGlobalReceiver(FortunePacketHandler.FORTUNE_PACKET_ID, (client, handler, buf, responseSender) -> {
            String fortune = buf.readString();

            client.execute(() -> {
                MinecraftClient.getInstance().setScreen(new FortuneScreen(fortune));
            });
        });
    }
}
