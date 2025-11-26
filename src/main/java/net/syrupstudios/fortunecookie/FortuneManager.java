package net.syrupstudios.fortunecookie;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.SimpleSynchronousResourceReloadListener;
import net.minecraft.resource.ResourceManager;
import net.minecraft.resource.ResourceType;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FortuneManager {
    private static final List<String> FORTUNES = new ArrayList<>();
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final Random RANDOM = new Random();

    // Default fortunes
    private static final String[] DEFAULT_FORTUNES = {
            "If money really changes everything, then maybe you should try changing the money.",
            "There is more to life than just money, there's Bitcoin.",
            "You dont become a failure until you're satisfied with being one."
    };

    public static void initialize() {
        // Load default fortunes
        for (String fortune : DEFAULT_FORTUNES) {
            FORTUNES.add(fortune);
        }

        // Register datapack listener
        ResourceManagerHelper.get(ResourceType.SERVER_DATA).registerReloadListener(new SimpleSynchronousResourceReloadListener() {
            @Override
            public Identifier getFabricId() {
                return new Identifier(FortuneCookieMod.MOD_ID, "fortunes");
            }

            @Override
            public void reload(ResourceManager manager) {
                FORTUNES.clear();

                // Load from datapacks
                manager.findResources("fortunes", path -> path.getPath().endsWith(".json"))
                        .forEach((identifier, resource) -> {
                            try (InputStreamReader reader = new InputStreamReader(resource.getInputStream())) {
                                JsonElement element = GSON.fromJson(reader, JsonElement.class);
                                if (element.isJsonObject() && element.getAsJsonObject().has("fortunes")) {
                                    JsonArray array = element.getAsJsonObject().getAsJsonArray("fortunes");
                                    for (JsonElement fortune : array) {
                                        FORTUNES.add(fortune.getAsString());
                                    }
                                }
                            } catch (Exception e) {
                                System.err.println("Error loading fortune file: " + identifier);
                                e.printStackTrace();
                            }
                        });

                // If no fortunes loaded from datapacks, use defaults
                if (FORTUNES.isEmpty()) {
                    for (String fortune : DEFAULT_FORTUNES) {
                        FORTUNES.add(fortune);
                    }
                }

                System.out.println("Loaded " + FORTUNES.size() + " fortunes");
            }
        });
    }

    public static String getRandomFortune(World world) {
        if (FORTUNES.isEmpty()) {
            return "Your fortune cookie is empty!";
        }
        return FORTUNES.get(RANDOM.nextInt(FORTUNES.size()));
    }
}
