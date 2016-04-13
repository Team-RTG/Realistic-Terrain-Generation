package teamrtg.rtg.mods.vanilla.biomes;

import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.ChunkPrimer;
import teamrtg.rtg.util.noise.CellNoise;
import teamrtg.rtg.util.noise.OpenSimplexNoise;
import teamrtg.rtg.world.biome.surface.SurfaceBase;
import teamrtg.rtg.world.biome.surface.SurfaceRiverOasis;
import teamrtg.rtg.world.biome.surface.part.*;
import teamrtg.rtg.world.biome.terrain.TerrainBase;
import teamrtg.rtg.world.gen.ChunkProviderRTG;
import teamrtg.rtg.world.gen.deco.*;
import teamrtg.rtg.world.gen.structure.MapGenScatteredFeatureRTG;

import java.util.Random;

public class RealisticBiomeVanillaDesertHills extends RealisticBiomeVanillaBase {

    public RealisticBiomeVanillaDesertHills(ChunkProviderRTG chunkProvider) {
        super(
                Biomes.DESERT_HILLS,
                Biomes.RIVER,
                chunkProvider
        );
        this.noLakes = true;
    }

    @Override
    protected TerrainBase initTerrain() {
        return new TerrainBase() {
            @Override
            public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river) {
                return terrainHighland(x, y, simplex, cell, river, 10f, 200f, 120f, 10f);
            }
        };
    }

    @Override
    protected void initNewSurfaces() {
        surfacePart.add(new CliffSelector(1.5f)
            .add(new DepthSelector(0, 6)
                .add(new BlockPart(SurfaceBase.getShadowStoneBlock()))));
        surfacePart.add(new CliffSelector((x, y, z) -> 1.5f - ((y - 60f) / 65f) + chunkProvider.simplex.noise3(x / 8f, y / 8f, z / 8f) * 0.5f)
            .add(new DepthSelector(0, 0)
                .add(PARTS.STONE_OR_COBBLE))
            .add(new DepthSelector(0, 6)
                .add(PARTS.STONE)));
        surfacePart.add(new DepthSelector(0, 0)
            .add(new HeightSelector(0, 62)
                .add(new BlockPart(config.FILL_BLOCK.get())))
            .add(new Selector((x, y, z) -> chunkProvider.simplex.noise2(x / 12f, z / 12f) > 0.15f)
                .add(new BlockPart(config.MIX_BLOCK_TOP.get())))
        );
    }

    public void paintSurface(ChunkPrimer primer, int i, int j, int x, int y, int depth, World world, Random rand, OpenSimplexNoise simplex, CellNoise cell, float[] noise, float river, BiomeGenBase[] base) {
        this.getSurface().paintSurface(primer, i, j, x, y, depth, world, rand, simplex, cell, noise, river, base);

        SurfaceBase riverSurface = new SurfaceRiverOasis(this);
        riverSurface.paintSurface(primer, i, j, x, y, depth, world, rand, simplex, cell, noise, river, base);
    }

    @Override
    protected void initDecos() {
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
    protected void initProperties() {
        config.addBlock(config.BEACH_BLOCK).setDefault(Blocks.SAND.getDefaultState());
        this.config.SCATTERED_FEATURE.setDefault(MapGenScatteredFeatureRTG.Type.DESERT_TEMPLE.name());
        this.config.SURFACE_WATER_LAKE_CHANCE.setDefault(0);
    }
}
