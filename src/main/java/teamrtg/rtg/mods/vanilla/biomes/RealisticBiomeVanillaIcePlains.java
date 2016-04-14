package teamrtg.rtg.mods.vanilla.biomes;

import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import teamrtg.rtg.mods.vanilla.surfaces.SurfaceVanillaIcePlains;
import teamrtg.rtg.util.noise.CellNoise;
import teamrtg.rtg.util.noise.OpenSimplexNoise;
import teamrtg.rtg.world.biome.surface.SurfaceBase;
import teamrtg.rtg.world.biome.terrain.TerrainBase;
import teamrtg.rtg.world.gen.ChunkProviderRTG;
import teamrtg.rtg.world.gen.deco.DecoBaseBiomeDecorations;
import teamrtg.rtg.world.gen.deco.DecoBoulder;
import teamrtg.rtg.world.gen.deco.DecoFallenTree;
import teamrtg.rtg.world.gen.deco.DecoFallenTree.LogCondition;
import teamrtg.rtg.world.gen.structure.MapGenScatteredFeatureRTG;

public class RealisticBiomeVanillaIcePlains extends RealisticBiomeVanillaBase {

    public RealisticBiomeVanillaIcePlains(ChunkProviderRTG chunkProvider) {
        super(
                Biomes.ICE_PLAINS,
                Biomes.FROZEN_RIVER,
                chunkProvider
        );
    }

    @Override
    protected TerrainBase initTerrain() {
        return new TerrainBase() {
            @Override
            public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river) {
                float base = 62;
                float b = simplex.noise2(x / 24f, y / 24f) * 0.25f;
                b *= river;
                float n = simplex.noise2(x / 16f, y / 16f) * 10f - 9f;
                n = (n < 0) ? 0f : n;
                b += n;
                return base + b;
            }
        };
    }


    @Override
    protected void initDecos() {
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
    protected void initProperties() {
        config.addBlock(config.CLIFF_BLOCK_1).setDefault(Blocks.GRASS.getDefaultState());
        config.addBlock(config.CLIFF_BLOCK_2).setDefault(Blocks.DIRT.getDefaultState());
        this.config.SCATTERED_FEATURE.setDefault(MapGenScatteredFeatureRTG.Type.IGLOO.name());
    }
}
