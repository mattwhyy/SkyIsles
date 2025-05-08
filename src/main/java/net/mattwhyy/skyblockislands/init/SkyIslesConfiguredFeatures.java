package net.mattwhyy.skyblockislands.init;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.core.registries.Registries;
import net.minecraftforge.registries.RegistryObject;

import java.util.HashMap;
import java.util.Map;

public class SkyIslesConfiguredFeatures {
    public static final Map<String, ResourceKey<ConfiguredFeature<?, ?>>> CUSTOM_ISLANDS = new HashMap<>();

    public static void registerConfiguredFeatures(Registry<ConfiguredFeature<?, ?>> context) {
        for (Map.Entry<String, RegistryObject<Feature<?>>> entry : SkyIslesModFeatures.CUSTOM_ISLANDS.entrySet()) {
            ResourceLocation islandId = new ResourceLocation("sky_isles", entry.getKey());
            ConfiguredFeature<?, ?> feature = new ConfiguredFeature<>(entry.getValue().get(), NoneFeatureConfiguration.INSTANCE);
            ResourceKey<ConfiguredFeature<?, ?>> featureKey = ResourceKey.create(Registries.CONFIGURED_FEATURE, islandId);
            context.register(featureKey, feature);
            CUSTOM_ISLANDS.put(entry.getKey(), featureKey);
        }
    }
}
