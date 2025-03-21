package com.Konatsu.pokecraft.item;

import org.jetbrains.annotations.Nullable;

import com.Konatsu.pokecraft.screen.PokedexScreen;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import java.util.List; // pour la liste du tooltip
import net.minecraft.text.Text; // pour les textes affichés

public class PokedexItem extends Item {

    public PokedexItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        if (world.isClient) {
            MinecraftClient.getInstance().execute(() -> {
                MinecraftClient.getInstance().setScreen(new PokedexScreen());
            });
        }

        return TypedActionResult.success(player.getStackInHand(hand));
    }

        @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.of("Un appareil high-tech qui enregistre les Pokémon !"));
    }
}
