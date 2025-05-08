package net.mattwhyy.skyblockislands.world.features;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockIgnoreProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import net.minecraftforge.fml.loading.FMLPaths;

import java.io.File;
import java.util.Set;

public class CustomIslandFeature extends Feature<NoneFeatureConfiguration> {
    private final ResourceLocation islandId;
    private final Set<ResourceKey<Level>> generateDimensions;
    private StructureTemplate template;

    public CustomIslandFeature(ResourceLocation islandId) {
        super(NoneFeatureConfiguration.CODEC);
        this.islandId = islandId;
        this.generateDimensions = Set.of(Level.OVERWORLD);
        this.template = null;
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        if (!this.generateDimensions.contains(context.level().getLevel().dimension())) {
            return false;
        } else {
            if (this.template == null) {
                File structureFile = new File(FMLPaths.CONFIGDIR.get().resolve("sky_isles/custom_islands/" + islandId.getPath() + ".nbt").toString());
                if (structureFile.exists()) {
                    this.template = context.level().getLevel().getStructureManager().get(islandId).orElse(null);
                }
            }

            if (this.template == null) {
                return false;
            } else {
                boolean anyPlaced = false;
                if (context.random().nextInt(1000000) + 1 <= 1500) {
                    int count = context.random().nextInt(1) + 1;

                    for (int a = 0; a < count; ++a) {
                        int i = context.origin().getX() + context.random().nextInt(16);
                        int k = context.origin().getZ() + context.random().nextInt(16);
                        int j = context.level().getHeight(Heightmap.Types.OCEAN_FLOOR_WG, i, k);
                        j += context.random().nextInt(64) + 16;
                        BlockPos spawnTo = new BlockPos(i, j + 100, k);
                        StructurePlaceSettings settings = new StructurePlaceSettings()
                                .setMirror(Mirror.values()[context.random().nextInt(2)])
                                .setRotation(Rotation.values()[context.random().nextInt(3)])
                                .setRandom(context.random())
                                .addProcessor(BlockIgnoreProcessor.STRUCTURE_BLOCK);
                        if (this.template.placeInWorld(context.level(), spawnTo, spawnTo, settings, context.random(), 2)) {
                            anyPlaced = true;
                        }
                    }
                }

                return anyPlaced;
            }
        }
    }
}
