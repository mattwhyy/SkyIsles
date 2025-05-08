package net.mattwhyy.skyblockislands.init;

import net.mattwhyy.skyblockislands.world.features.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.HashMap;
import java.util.Map;

import static net.mattwhyy.skyblockislands.SkyIslesMod.LOGGER;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class SkyIslesModFeatures {
    public static final DeferredRegister<Feature<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.FEATURES, "sky_isles");

    public static final RegistryObject<Feature<?>> ISLANDCHERRY = REGISTRY.register("islandcherry", IslandcherryFeature::new);
    public static final RegistryObject<Feature<?>> ISLANDCAMPSITE = REGISTRY.register("islandcampsite", IslandcampsiteFeature::new);
    public static final RegistryObject<Feature<?>> ISLANDDARKFOREST = REGISTRY.register("islanddarkforest", IslanddarkforestFeature::new);
    public static final RegistryObject<Feature<?>> ISLANDDESERT = REGISTRY.register("islanddesert", IslanddesertFeature::new);
    public static final RegistryObject<Feature<?>> ISLANDDRIPSTONE = REGISTRY.register("islanddripstone", IslanddripstoneFeature::new);
    public static final RegistryObject<Feature<?>> ISLANDICESPIKE = REGISTRY.register("islandicespike", IslandicespikeFeature::new);
    public static final RegistryObject<Feature<?>> ISLANDJUNGLE = REGISTRY.register("islandjungle", IslandjungleFeature::new);
    public static final RegistryObject<Feature<?>> ISLANDJUNGLESMALL = REGISTRY.register("islandjunglesmall", IslandjunglesmallFeature::new);
    public static final RegistryObject<Feature<?>> ISLANDLUSH = REGISTRY.register("islandlush", IslandlushFeature::new);
    public static final RegistryObject<Feature<?>> ISLANDMANGROVE = REGISTRY.register("islandmangrove", IslandmangroveFeature::new);
    public static final RegistryObject<Feature<?>> ISLANDMESAMINE = REGISTRY.register("islandmesamine", IslandmesamineFeature::new);
    public static final RegistryObject<Feature<?>> ISLANDMUSHROOM = REGISTRY.register("islandmushroom", IslandmushroomFeature::new);
    public static final RegistryObject<Feature<?>> ISLANDPLAINS = REGISTRY.register("islandplains", IslandplainsFeature::new);
    public static final RegistryObject<Feature<?>> ISLANDPORTAL = REGISTRY.register("islandportal", IslandportalFeature::new);
    public static final RegistryObject<Feature<?>> ISLANDSAVANNA = REGISTRY.register("islandsavanna", IslandsavannaFeature::new);
    public static final RegistryObject<Feature<?>> ISLANDSNOWLAB = REGISTRY.register("islandsnowlab", IslandsnowlabFeature::new);
    public static final RegistryObject<Feature<?>> ISLANDSWAMP = REGISTRY.register("islandswamp", IslandswampFeature::new);
    public static final RegistryObject<Feature<?>> ISLANDTAIGA = REGISTRY.register("islandtaiga", IslandtaigaFeature::new);
    public static final RegistryObject<Feature<?>> ISLANDTEMPLE = REGISTRY.register("islandtemple", IslandtempleFeature::new);
    public static final RegistryObject<Feature<?>> ISLANDNETHERBASALT = REGISTRY.register("islandnetherbasalt", IslandnetherbasaltFeature::new);
    public static final RegistryObject<Feature<?>> ISLANDNETHERCRIMSON = REGISTRY.register("islandnethercrimson", IslandnethercrimsonFeature::new);
    public static final RegistryObject<Feature<?>> ISLANDNETHERSOUL = REGISTRY.register("islandnethersoul", IslandnethersoulFeature::new);
    public static final RegistryObject<Feature<?>> ISLANDNETHERWARPED = REGISTRY.register("islandnetherwarped", IslandnetherwarpedFeature::new);
    public static final RegistryObject<Feature<?>> ISLANDNETHERWASTES = REGISTRY.register("islandnetherwastes", IslandnetherwastesFeature::new);
    public static final RegistryObject<Feature<?>> ISLANDVILLAGEHOUSE = REGISTRY.register("islandvillagehouse", IslandvillagehouseFeature::new);
    public static final RegistryObject<Feature<?>> ISLANDENDPORTAL = REGISTRY.register("islandendportal", IslandendportalFeature::new);

    public static final Map<String, RegistryObject<Feature<?>>> CUSTOM_ISLANDS = new HashMap<>();

    public static void registerCustomIslands() {
        LOGGER.info("Loading custom islands...");
        CustomIslandLoader.loadCustomIslands();

        for (ResourceLocation islandId : CustomIslandLoader.getCustomIslandIds()) {
            LOGGER.info("Registering custom island: {}", islandId);
            RegistryObject<Feature<?>> feature = REGISTRY.register(islandId.getPath(), () -> new CustomIslandFeature(islandId));
            CUSTOM_ISLANDS.put(islandId.getPath(), feature);
        }
    }
}
