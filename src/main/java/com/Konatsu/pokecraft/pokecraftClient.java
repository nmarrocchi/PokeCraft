package com.Konatsu.pokecraft;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import org.lwjgl.glfw.GLFW;

public class pokecraftClient implements ClientModInitializer {

    public static KeyBinding scrollUpKey;
    public static KeyBinding scrollDownKey;

    @Override
    public void onInitializeClient() {

        scrollUpKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.pokedex.scroll_up",
                GLFW.GLFW_KEY_UP,
                "category.pokecraft"
        ));

        scrollDownKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.pokedex.scroll_down",
                GLFW.GLFW_KEY_DOWN,
                "category.pokecraft"
        ));
    }
}
