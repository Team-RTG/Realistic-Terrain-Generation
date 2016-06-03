package teamrtg.rtg.modules.vanilla.biomes;

import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import teamrtg.rtg.api.config.BiomeConfig;
import teamrtg.rtg.api.tools.deco.*;
import teamrtg.rtg.api.tools.surface.SurfaceRiverOasis;
import teamrtg.rtg.api.world.RTGWorld;
import teamrtg.rtg.api.world.biome.TerrainBase;
import teamrtg.rtg.api.world.biome.surface.part.BlockPart;
import teamrtg.rtg.api.world.biome.surface.part.CliffSelector;
import teamrtg.rtg.api.world.biome.surface.part.OrSelector;
import teamrtg.rtg.api.world.biome.surface.part.SurfacePart;
import teamrtg.rtg.modules.vanilla.RTGBiomeVanilla;

public class RTGBiomeVanillaDesertHills extends RTGBiomeVanilla {

    public RTGBiomeVanillaDesertHills() {
        super(
                Biomes.DESERT_HILLS,
            Biomes.RIVER
        );
        this.noLakes = true;
    }

    @Override
    public SurfacePart initSurface() {
        SurfacePart surface = PARTS.selectTopAndFill();
        surface.add(new SurfaceRiverOasis(this));
        surface.add(new OrSelector()
            .or(new CliffSelector((x, y, z, rtgWorld) -> 1.5f - ((y - 60f) / 65f) + rtgWorld.simplex.noise3(x / 8f, y / 8f, z / 8f) * 0.5f))
            .or(new CliffSelector(1.5f))
            .add(new BlockPart(Blocks.SANDSTONE.getDefaultState()))
        );
        surface.add(PARTS.surfaceGeneric());
        return surface;
    }

    @Override
    public TerrainBase initTerrain() {
        return new TerrainBase() {
            @Override
            public float generateNoise(RTGWorld rtgWorld, int x, int y, float biomeWeight, float border, float river) {
                return terrainHighland(x, y, rtgWorld.simplex, rtgWorld.cell, river, 10f, 200f, 120f, 10f);
            }
        };
    }

    @Override
    public void initDecos() {
        DecoTree riverTrees = new DecoTree();
        riverTrees.checkRiver = true;
        riverTrees.minRiver = 0.86f;
        riverTrees.strengthNoiseFactorForLoops = false;
        riverTrees.strengthFactorForLoops = 10f;
        riverTrees.treeType = DecoTree.TreeType.DESERT_RIVER;
        riverTrees.treeCondition = DecoTree.TreeCondition.ALWAYS_GENERATE;
        riverTrees.maxY = 100;
        this.addDeco(riverTrees);

        DecoCactus decoRiverCactus = new DecoCactus();
        decoRiverCactus.checkRiver = true;
        decoRiverCactus.minRiver = 0.7f;
        decoRiverCactus.maxY = 80;
        decoRiverCactus.strengthFactor = 12f;
        this.addDeco(decoRiverCactus);

        DecoReed decoReed = new DecoReed();
        decoReed.checkRiver = true;
        decoReed.minRiver = 0.7f;
        decoReed.maxY = 68;
        decoReed.strengthFactor = 2f;
        this.addDeco(decoReed);

        DecoFlowersRTG decoFlowersRTG = new DecoFlowersRTG();
        decoFlowersRTG.checkRiver = true;
        decoFlowersRTG.minRiver = 0.7f;
        decoFlowersRTG.flowers = new int[] {9, 9, 9, 9, 3, 3, 3, 3, 3, 2, 2, 2, 11, 11, 11};
        decoFlowersRTG.maxY = 128;
        decoFlowersRTG.loops = 3;
        this.addDeco(decoFlowersRTG);

        DecoGrassDoubleTallgrass decoGrassDoubleTallgrass = new DecoGrassDoubleTallgrass();
        decoGrassDoubleTallgrass.checkRiver = true;
        decoGrassDoubleTallgrass.minRiver = 0.7f;
        decoGrassDoubleTallgrass.maxY = 128;
        decoGrassDoubleTallgrass.loops = 15;
        decoGrassDoubleTallgrass.doubleGrassChance = 3;
        this.addDeco(decoGrassDoubleTallgrass);

        DecoDesertWell decoDesertWell = new DecoDesertWell();
        decoDesertWell.maxY = 80;
        decoDesertWell.strengthFactor = 1f;
        decoDesertWell.chance = 120;
        this.addDeco(decoDesertWell);

        DecoCactus decoCactus = new DecoCactus();
        decoCactus.maxY = 120;
        decoCactus.strengthFactor = 5f;
        this.addDeco(decoCactus);

        DecoDeadBush decoDeadBush = new DecoDeadBush();
        decoDeadBush.maxY = 128;
        decoDeadBush.strengthFactor = 1f;
        this.addDeco(decoDeadBush);
    }

    @Override
    public void initConfig() {
        config.addBlock(config.BEACH_BLOCK).setDefault(Blocks.SAND.getDefaultState());
        this.config.SCATTERED_FEATURE.setDefault(BiomeConfig.FeatureType.DESERT_TEMPLE.name());
        this.config.WATER_POND_CHANCE.setDefault(0);
    }
}
