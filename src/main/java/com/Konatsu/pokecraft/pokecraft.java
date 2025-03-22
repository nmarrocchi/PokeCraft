package com.Konatsu.pokecraft;

import com.Konatsu.pokecraft.item.Items;
import com.Konatsu.pokecraft.item.PokeItemGroup;
import net.fabricmc.api.ModInitializer;

public class pokecraft implements ModInitializer {
    public static final String MOD_ID = "pokecraft";

    @Override
    public void onInitialize() {
        System.out.println(" ");
        System.out.println(" ");
        System.out.println("╔════════════════════════════════╗");
        System.out.println("║     POKECRAFT EST CHARGÉ !     ║");
        System.out.println("╚════════════════════════════════╝");
        System.out.println(" ");
        System.out.println(" ");
        
        Items.registerItems(); //Enregistrement des items
        PokeItemGroup.registerItemGroups();  // Enregistre le groupe d'items
    }
}
