package com.Konatsu.pokecraft.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;

public class PokeItemGroup {

    public static final ItemGroup POKECRAFT_GROUP = FabricItemGroup.builder()
        .displayName(Text.of("Pokecraft"))
        .icon(() -> new ItemStack(Items.POKEDEX))
        .entries((enabledFeatures, entries) -> {
            entries.add(Items.POKEDEX);
        })
        .build();

    public static void registerItemGroups() {
        System.out.println("Enregistrement du groupe d'items Pokecraft !");
    }
}
