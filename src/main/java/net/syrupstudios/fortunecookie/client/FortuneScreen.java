package net.syrupstudios.fortunecookie.client;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class FortuneScreen extends Screen {
    private static final Identifier TEXTURE = new Identifier("fortunecookie", "textures/gui/fortune_screen.png");
    private final String fortune;
    private static final int PANEL_WIDTH = 276;
    private static final int PANEL_HEIGHT = 166;

    public FortuneScreen(String fortune) {
        super(Text.literal("Fortune Cookie"));
        this.fortune = fortune;
    }

    @Override
    protected void init() {
        super.init();

        int x = (this.width - PANEL_WIDTH) / 2;
        int y = (this.height - PANEL_HEIGHT) / 2;

        this.addDrawableChild(ButtonWidget.builder(Text.literal("Ok thanks!"), button -> {
            this.close();
        }).dimensions(x + PANEL_WIDTH / 2 - 75, y + PANEL_HEIGHT - 35, 150, 20).build());
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        this.renderBackground(context);

        int x = (this.width - PANEL_WIDTH) / 2;
        int y = (this.height - PANEL_HEIGHT) / 2;

        // Draw panel background
        context.fill(x, y, x + PANEL_WIDTH, y + PANEL_HEIGHT, 0xDD3C2A1E);
        context.fill(x + 2, y + 2, x + PANEL_WIDTH - 2, y + PANEL_HEIGHT - 2, 0xFFD7CDB5);

        // Draw title
        Text title = Text.literal("Your Fortune");
        int titleWidth = this.textRenderer.getWidth(title);
        context.drawText(this.textRenderer, title, x + (PANEL_WIDTH - titleWidth) / 2, y + 15, 0x3C2A1E, false);

        // Draw fortune text with word wrapping
        int textY = y + 40;
        int maxWidth = PANEL_WIDTH - 40;
        int lineHeight = 12;

        String[] words = fortune.split(" ");
        StringBuilder currentLine = new StringBuilder();

        for (String word : words) {
            String testLine = currentLine.length() == 0 ? word : currentLine + " " + word;
            int testWidth = this.textRenderer.getWidth(testLine);

            if (testWidth > maxWidth && currentLine.length() > 0) {
                String line = currentLine.toString();
                int lineWidth = this.textRenderer.getWidth(line);
                context.drawText(this.textRenderer, line, x + (PANEL_WIDTH - lineWidth) / 2, textY, 0x5C4A3E, false);
                textY += lineHeight;
                currentLine = new StringBuilder(word);
            } else {
                currentLine = new StringBuilder(testLine);
            }
        }

        // Draw remaining text
        if (currentLine.length() > 0) {
            String line = currentLine.toString();
            int lineWidth = this.textRenderer.getWidth(line);
            context.drawText(this.textRenderer, line, x + (PANEL_WIDTH - lineWidth) / 2, textY, 0x5C4A3E, false);
        }

        super.render(context, mouseX, mouseY, delta);
    }

    @Override
    public boolean shouldPause() {
        return false;
    }
}