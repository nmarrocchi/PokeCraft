package com.Konatsu.pokecraft.item;

import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class Items {

    public static final Item POKEDEX = registerItem("pokedex",
            new PokedexItem(new Item.Settings().maxCount(1)));

    public static void registerItems() {
        System.out.println("Enregistrement des items de Pokecraft...");
    }

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier("pokecraft", name), item);
    }
}


