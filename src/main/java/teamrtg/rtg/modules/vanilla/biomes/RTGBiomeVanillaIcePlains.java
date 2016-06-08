package teamrtg.rtg.modules.vanilla.biomes;

import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import teamrtg.rtg.api.config.BiomeConfig;
import teamrtg.rtg.api.tools.deco.DecoBoulder;
import teamrtg.rtg.api.tools.deco.DecoFallenTree;
import teamrtg.rtg.api.tools.deco.DecoFallenTree.LogCondition;
import teamrtg.rtg.api.world.RTGWorld;
import teamrtg.rtg.api.world.biome.TerrainBase;
import teamrtg.rtg.api.world.biome.deco.DecoBaseBiomeDecorations;
import teamrtg.rtg.api.world.biome.surface.part.SurfacePart;
import teamrtg.rtg.modules.vanilla.RTGBiomeVanilla;

public class RTGBiomeVanillaIcePlains extends RTGBiomeVanilla {

    public RTGBiomeVanillaIcePlains() {
        super(
                Biomes.ICE_PLAINS,
            Biomes.FROZEN_RIVER
        );
    }

    @Override
    public SurfacePart initSurface() {
        return PARTS.surfaceGeneric();
    }

    @Override
    public TerrainBase initTerrain() {
        return new TerrainBase() {
            @Override
            public float generateNoise(RTGWorld rtgWorld, int x, int y, float biomeWeight, float border, float river) {
                float base = 62;
                float b = rtgWorld.simplex.noise2(x / 24f, y / 24f) * 0.25f;
                b *= river;
                float n = rtgWorld.simplex.noise2(x / 16f, y / 16f) * 10f - 9f;
                n = (n < 0) ? 0f : n;
                b += n;
                return base + b;
            }
        };
    }


    @Override
    public void initDecos() {
        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        this.addDeco(decoBaseBiomeDecorations);

        DecoBoulder decoBoulder = new DecoBoulder();
        decoBoulder.checkRiver = true;
        decoBoulder.minRiver = 0.87f;
        decoBoulder.boulderBlock = Blocks.COBBLESTONE;
        decoBoulder.chance = 16;
        decoBoulder.maxY = 95;
        decoBoulder.strengthFactor = 5f;
        this.addDeco(decoBoulder);

        DecoFallenTree decoFallenTree = new DecoFallenTree();
        decoFallenTree.logCondition = LogCondition.NOISE_GREATER_AND_RANDOM_CHANCE;
        decoFallenTree.logConditionChance = 24;
        decoFallenTree.maxY = 90;
        decoFallenTree.logBlock = Blocks.LOG;
        decoFallenTree.logMeta = (byte) 1;
        decoFallenTree.leavesBlock = Blocks.LEAVES;
        decoFallenTree.leavesMeta = (byte) -1;
        decoFallenTree.minSize = 1;
        decoFallenTree.maxSize = 5;
        this.addDeco(decoFallenTree);
    }

    @Override
    public void initConfig() {
        config.TOP_BLOCK.setDefault(Blocks.SNOW.getDefaultState());
        config.FILL_BLOCK.setDefault(Blocks.DIRT.getDefaultState());
        this.config.SCATTERED_FEATURE.setDefault(BiomeConfig.FeatureType.IGLOO.name());
    }
}
