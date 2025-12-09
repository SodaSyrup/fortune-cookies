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

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FortuneManager {
    private static final List<Fortune> FORTUNES = new ArrayList<>();
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final Random RANDOM = new Random();

    // Default fortunes
    private static final List<Fortune> DEFAULT_FORTUNES =
            List.of(
                new Fortune("If money really changes everything, then maybe you should try changing the money.", 1),
                new Fortune("There is more to life than just money, there's Bitcoin.", 0),
                new Fortune("You dont become a failure until you're satisfied with being one.", -1)
            );

    public static void initialize() {

        // Register datapack listener
        ResourceManagerHelper.get(ResourceType.SERVER_DATA).registerReloadListener(new SimpleSynchronousResourceReloadListener() {
            @Override
            public Identifier getFabricId() {
                return new Identifier(FortuneCookieMod.MOD_ID, "fortunes");
            }

            @Override
            public void reload(ResourceManager manager) {
                FORTUNES.clear();

                /*  Load from datapacks (this will have to be updated to better correspond w/ the new object but
                    idk how the fk to do that yet without reading up on it, so for now all added fortunes = positive
                */
                manager.findResources("fortunes", path -> path.getPath().endsWith(".json"))
                        .forEach((identifier, resource) -> {
                            try (InputStreamReader reader = new InputStreamReader(resource.getInputStream())) {
                                JsonElement element = GSON.fromJson(reader, JsonElement.class);
                                if (element.isJsonObject() && element.getAsJsonObject().has("fortunes")) {
                                    JsonArray array = element.getAsJsonObject().getAsJsonArray("fortunes");
                                    for (JsonElement fortune : array) {
                                        FORTUNES.add(new Fortune(fortune.getAsString(), 1));
                                    }
                                }
                            } catch (Exception e) {
                                System.err.println("Error loading fortune file: " + identifier);
                                e.printStackTrace();
                            }
                        });

                // If no fortunes loaded from datapacks, use defaults
                if (FORTUNES.isEmpty()) {
                    FORTUNES.addAll(DEFAULT_FORTUNES);
                }

                System.out.println("Loaded " + FORTUNES.size() + " fortunes");
            }
        });
    }

    public static Fortune getRandomFortune() {
        if (FORTUNES.isEmpty()) {
            return new Fortune("Your fortune cookie is empty!", 0);
        }
        return FORTUNES.get(RANDOM.nextInt(FORTUNES.size()));
    }
}
