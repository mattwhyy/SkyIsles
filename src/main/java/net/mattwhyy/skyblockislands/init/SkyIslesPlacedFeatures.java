package net.mattwhyy.skyblockislands.init;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import net.minecraft.world.level.levelgen.placement.RarityFilter;
import net.minecraft.world.level.levelgen.placement.HeightmapPlacementModifier;
import net.minecraft.core.registries.Registries;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SkyIslesPlacedFeatures {
    public static final Map<String, ResourceKey<PlacedFeature>> CUSTOM_ISLANDS = new HashMap<>();

    public static void registerPlacedFeatures(Registry<PlacedFeature> context) {
        for (Map.Entry<String, ResourceKey<ConfiguredFeature<?, ?>>> entry : SkyIslesConfiguredFeatures.CUSTOM_ISLANDS.entrySet()) {
            ResourceKey<PlacedFeature> placedKey = ResourceKey.create(Registries.PLACED_FEATURE, new ResourceLocation("sky_isles", entry.getKey()));

            context.register(placedKey, new PlacedFeature(
                    context.getOrThrow(entry.getValue()),
                    List.of(RarityFilter.onAverageOnceEvery(5), HeightmapPlacementModifier.AT_SURFACE)
            ));

            CUSTOM_ISLANDS.put(entry.getKey(), placedKey);
        }
    }
}
