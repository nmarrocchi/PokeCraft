package com.Konatsu.pokecraft.screen;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;
import com.Konatsu.pokecraft.pokecraftClient;
import net.minecraft.util.Identifier;

public class PokedexScreen extends Screen {

    private static final Identifier TEXTURE = new Identifier("pokecraft", "textures/gui/pokedex.png");
    private static final Identifier CLOSE_BUTTON_TEXTURE = new Identifier("pokecraft", "textures/gui/close_button.png"); // Texture du bouton Fermer

    private int scrollOffsetY = 0;
    private final int scrollSpeed = 10;

    private final int backgroundWidth = (int) Math.round(778 / 2.5);
    private final int backgroundHeight = (int) Math.round(468 / 2.5);
    private int x, y;

    private int totalRows = 4;
    private int slotSize = 23;
    private int slotMargin = 3;

    // Config de la grille
    private int gridColumns = 5;
    private int gridInnerPadding = 4; // Marge entre les slots et le bord du fond
    private int gridOuterPaddingX = 19; // Décalage à gauche de la grille par rapport au background
    private int gridOuterPaddingY = 25; // Décalage en haut de la grille par rapport au background

    private int gridX;
    private int gridY;
    private int gridWidth;
    private int gridHeight;
    private int maxScrollOffsetY;

    public PokedexScreen() {
        super(Text.of("Pokedex"));
    }

    @Override
    protected void init() {
        super.init();

        // Centrage de la texture de fond
        this.x = (this.width - backgroundWidth) / 2;
        this.y = (this.height - backgroundHeight) / 2;

        // Coordonnées de la grille
        this.gridX = x + gridOuterPaddingX;
        this.gridY = y + gridOuterPaddingY;

        // Calcul dynamique de la taille de la grille (fond coloré)
        this.gridWidth = (gridColumns * slotSize) + ((gridColumns - 1) * slotMargin) + (2 * gridInnerPadding);
        int visibleRows = 5; // Le nombre de lignes visibles dans la grille
        this.gridHeight = (visibleRows * slotSize) + ((visibleRows - 1) * slotMargin) + (2 * gridInnerPadding);

        // Calcul de la hauteur totale pour le scroll
        int totalGridHeight = (totalRows * (slotSize + slotMargin)) - slotMargin;
        this.maxScrollOffsetY = Math.max(0, totalGridHeight - (gridHeight - 2 * gridInnerPadding));

        // Ajouter le bouton fermer sans utiliser ButtonWidget
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        super.render(context, mouseX, mouseY, delta);

        // Texture de fond de l'écran Pokédex
        context.drawTexture(TEXTURE, x, y, 0, 0, backgroundWidth, backgroundHeight, backgroundWidth, backgroundHeight);

        // Dessine la grid
        renderGrid(context);

        // Dessiner le bouton avec la texture
        renderCloseButtonWithTexture(context);

        // Clavier
        handleKeyInput();
    }

    private void renderGrid(DrawContext context) {
        // Fond de la grille avec marge extérieure
        context.fill(gridX, gridY, gridX + gridWidth, gridY + gridHeight, 0xAA333333); // Fond gris semi-transparent

        // Clip (scissor box) : on laisse la marge intérieure apparente
        int scissorX1 = gridX + gridInnerPadding;
        int scissorY1 = gridY + gridInnerPadding;
        int scissorX2 = gridX + gridWidth - gridInnerPadding;
        int scissorY2 = gridY + gridHeight - gridInnerPadding;
        context.enableScissor(scissorX1, scissorY1, scissorX2, scissorY2);

        // Début de l'affichage des slots, à l'intérieur du padding
        int startX = scissorX1;
        int startY = scissorY1 - scrollOffsetY;

        for (int row = 0; row < totalRows; row++) {
            for (int col = 0; col < gridColumns; col++) {
                int slotPosX = startX + col * (slotSize + slotMargin);
                int slotPosY = startY + row * (slotSize + slotMargin);

                // Affichage uniquement si dans la zone visible
                if (slotPosY + slotSize >= scissorY1 && slotPosY <= scissorY2) {
                    context.fill(slotPosX, slotPosY, slotPosX + slotSize, slotPosY + slotSize, 0xFFFFFFFF);
                    context.drawText(this.textRenderer, "" + (row * gridColumns + col + 1), slotPosX + 3, slotPosY + 6, 0xFF000000, false);
                }
            }
        }

        context.disableScissor();
    }

    private void renderCloseButtonWithTexture(DrawContext context) {
  
        int buttonX = x + 299;
        int buttonY = y + 25;
        int buttonSize = 10;
        
        context.drawTexture(CLOSE_BUTTON_TEXTURE, buttonX, buttonY, 0, 0, buttonSize, buttonSize, 10, 10);
    
        // Ajouter le texte sur le bouton
        context.drawText(this.textRenderer, "", buttonX + 10, buttonY + 5, 0xFFFFFF, false);
    }
    

    private void handleKeyInput() {
        if (pokecraftClient.scrollDownKey.isPressed()) {
            scrollOffsetY += scrollSpeed;
        }

        if (pokecraftClient.scrollUpKey.isPressed()) {
            scrollOffsetY -= scrollSpeed;
        }

        clampScroll();
    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double amount) {
        scrollOffsetY -= (int) (amount * scrollSpeed);
        clampScroll();
        return true;
    }

    private void clampScroll() {
        scrollOffsetY = Math.max(0, Math.min(scrollOffsetY, maxScrollOffsetY));
    }

    @Override
    public boolean shouldPause() {
        return false;
    }
}
