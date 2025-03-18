package com.Konatsu.pokecraft.screen;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

public class PokedexScreen extends Screen {

    public PokedexScreen() {
        super(Text.of("Pokedex")); // Le titre affiché en haut de l'écran
    }

    // Tu peux override les méthodes drawBackground, render, etc.
}