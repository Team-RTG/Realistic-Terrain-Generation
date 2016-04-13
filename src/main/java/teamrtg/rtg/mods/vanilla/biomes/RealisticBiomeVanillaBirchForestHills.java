package teamrtg.rtg.mods.vanilla.biomes;

import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import teamrtg.rtg.util.noise.CellNoise;
import teamrtg.rtg.util.noise.OpenSimplexNoise;
import teamrtg.rtg.world.biome.surface.SurfaceBase;
import teamrtg.rtg.world.biome.surface.part.*;
import teamrtg.rtg.world.biome.terrain.TerrainBase;
import teamrtg.rtg.world.gen.ChunkProviderRTG;
import teamrtg.rtg.world.gen.deco.*;
import teamrtg.rtg.world.gen.deco.DecoFallenTree.LogCondition;
import teamrtg.rtg.world.gen.deco.DecoTree.TreeCondition;
import teamrtg.rtg.world.gen.deco.DecoTree.TreeType;

public class RealisticBiomeVanillaBirchForestHills extends RealisticBiomeVanillaBase {

    public RealisticBiomeVanillaBirchForestHills(ChunkProviderRTG chunkProvider) {
        super(
            Biomes.BIRCH_FOREST_HILLS,
            Biomes.RIVER,
            chunkProvider
        );
        this.noLakes = true;
    }

    @Override
    protected void initProperties() {
        config.addBlock(config.BEACH_BLOCK).setDefault(Blocks.SAND.getDefaultState());
        config.addBlock(config.MIX_BLOCK).setDefault(Blocks.DIRT.getStateFromMeta(2));
    }

    @Override
    protected void initDecos() {
        DecoTree smallBirch = new DecoTree();
        smallBirch.strengthNoiseFactorForLoops = true;
        smallBirch.treeType = TreeType.SMALL_BIRCH;
        smallBirch.distribution.noiseDivisor = 80f;
        smallBirch.distribution.noiseFactor = 60f;
        smallBirch.distribution.noiseAddend = -15f;
        smallBirch.treeCondition = TreeCondition.ALWAYS_GENERATE;
        smallBirch.maxY = 120;
        this.addDeco(smallBirch);

        DecoTree birchTreesForest = new DecoTree();
        birchTreesForest.strengthFactorForLoops = 3f;
        birchTreesForest.treeType = TreeType.BIRCH_TREES_FOREST;
        birchTreesForest.treeCondition = TreeCondition.ALWAYS_GENERATE;
        birchTreesForest.maxY = 100;
        this.addDeco(birchTreesForest);

        DecoFallenTree decoFallenTree = new DecoFallenTree();
        decoFallenTree.logCondition = LogCondition.RANDOM_CHANCE;
        decoFallenTree.logConditionChance = 8;
        decoFallenTree.maxY = 100;
        decoFallenTree.logBlock = Blocks.LOG;
        decoFallenTree.logMeta = (byte) 2;
        decoFallenTree.leavesBlock = Blocks.LEAVES;
        decoFallenTree.leavesMeta = (byte) -1;
        decoFallenTree.minSize = 3;
        decoFallenTree.maxSize = 6;
        this.addDeco(decoFallenTree);

        DecoShrub decoShrub = new DecoShrub();
        decoShrub.maxY = 120;
        decoShrub.strengthFactor = 3f;
        this.addDeco(decoShrub);

        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        decoBaseBiomeDecorations.notEqualsZeroChance = 3;
        this.addDeco(decoBaseBiomeDecorations);

        DecoFlowersRTG decoFlowersRTG = new DecoFlowersRTG();
        decoFlowersRTG.flowers = new int[] {3, 6};
        decoFlowersRTG.maxY = 128;
        decoFlowersRTG.strengthFactor = 12f;
        this.addDeco(decoFlowersRTG);

        DecoGrass decoGrass = new DecoGrass();
        decoGrass.maxY = 128;
        decoGrass.strengthFactor = 20f;
        this.addDeco(decoGrass);
    }

    @Override
    protected void initNewSurfaces() {
        surfacePart.add(new CliffSelector(1.5f)
            .add(new DepthSelector(0, 6)
                .add(new BlockPart(SurfaceBase.getShadowStoneBlock()))));
        surfacePart.add(new CliffSelector((x, y, z) -> 1.5f - ((y - 60f) / 65f) + chunkProvider.simplex.noise3(x / 8f, y / 8f, z / 8f) * 0.5f)
            .add(new DepthSelector(0, 0)
                .add(PARTS.STONE_OR_COBBLE)))
            .add(new DepthSelector(0, 6)
                .add(PARTS.STONE));
        surfacePart.add(new DepthSelector(0, 0)
            .add(new HeightSelector(0, 62)
                .add(new BlockPart(config.FILL_BLOCK.get())))
            .add(new Selector((x, y, z) -> chunkProvider.simplex.noise2(x / 12f, z / 12f) > 0.15f)
                .add(new BlockPart(config.MIX_BLOCK_TOP.get())))
        );
    }

    @Override
    protected TerrainBase initTerrain() {
        return new TerrainBase() {
            @Override
            public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river) {
                return terrainHighland(x, y, simplex, cell, river, 10f, 68f, 45f, 10f);
            }
        };
    }
}
