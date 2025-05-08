package net.mattwhyy.skyblockislands.init;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.core.registries.Registries;
import net.minecraftforge.common.world.BiomeModifier;

import java.util.HashMap;
import java.util.Map;

public class SkyIslesBiomeModifiers {
    public static final Map<String, ResourceKey<BiomeModifier>> CUSTOM_ISLAND_MODIFIERS = new HashMap<>();

    public static void registerBiomeModifiers(Registry<BiomeModifier> context) {
        for (Map.Entry<String, ResourceKey<PlacedFeature>> entry : SkyIslesPlacedFeatures.CUSTOM_ISLANDS.entrySet()) {
            ResourceKey<BiomeModifier> modifier = ResourceKey.create(Registries.BIOME_MODIFIER, new ResourceLocation("sky_isles", "add_" + entry.getKey()));

            context.register(modifier, new BiomeModifier(
                    context1 -> true,
                    context.lookup(Registries.PLACED_FEATURE).getOrThrow(entry.getValue()),
                    GenerationStep.Decoration.SURFACE_STRUCTURES
            ));

            CUSTOM_ISLAND_MODIFIERS.put(entry.getKey(), modifier);
        }
    }
}
