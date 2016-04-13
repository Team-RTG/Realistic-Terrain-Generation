package teamrtg.rtg.mods.vanilla.biomes;

import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;
import teamrtg.rtg.api.biome.RealisticBiomeBase;
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

public class RealisticBiomeVanillaBirchForestM extends RealisticBiomeVanillaBase {

    public static BiomeGenBase standardBiome = Biomes.BIRCH_FOREST;
    public static BiomeGenBase mutationBiome = BiomeGenBase.getBiome(RealisticBiomeBase.getIdForBiome(standardBiome) + MUTATION_ADDEND);

    public RealisticBiomeVanillaBirchForestM(ChunkProviderRTG chunkProvider) {

        super(
                mutationBiome,
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
                return terrainPlains(x, y, simplex, river, 160f, 10f, 60f, 80f, 65f);
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
                .add(new BlockPart(SurfaceBase.hcCobble())
                    .add(new RandomSelector(chunkProvider.rand, 3)
                        .add(new BlockPart(SurfaceBase.hcStone())))))
            .add(new DepthSelector(0, 6)
                .add(new BlockPart(SurfaceBase.hcStone()))));
        surfacePart.add(new DepthSelector(0, 0)
            .add(new HeightSelector(0, 62)
                .add(new BlockPart(config.FILL_BLOCK.get())))
            .add(new Selector((x, y, z) -> chunkProvider.simplex.noise2(x / 12f, z / 12f) > 0.15f)
                .add(new BlockPart(config.MIX_BLOCK_TOP.get())))
        );
    }

    @Override
    protected void initDecos() {
        DecoTree superTallBirch = new DecoTree();
        superTallBirch.strengthFactorForLoops = 16f;
        superTallBirch.strengthNoiseFactorForLoops = true;
        superTallBirch.treeType = TreeType.SUPER_TALL_BIRCH;
        superTallBirch.distribution.noiseDivisor = 80f;
        superTallBirch.distribution.noiseFactor = 60f;
        superTallBirch.distribution.noiseAddend = -15f;
        superTallBirch.treeCondition = TreeCondition.ALWAYS_GENERATE;
        superTallBirch.maxY = 100;
        this.addDeco(superTallBirch);

        DecoLargeFernDoubleTallgrass decoDoublePlants = new DecoLargeFernDoubleTallgrass();
        decoDoublePlants.maxY = 128;
        decoDoublePlants.strengthFactor = 8f;
        this.addDeco(decoDoublePlants);

        DecoGrass decoGrass = new DecoGrass();
        decoGrass.maxY = 128;
        decoGrass.strengthFactor = 24f;
        this.addDeco(decoGrass);

        DecoFallenTree decoFallenTree = new DecoFallenTree();
        decoFallenTree.logCondition = LogCondition.RANDOM_CHANCE;
        decoFallenTree.logConditionChance = 20;
        decoFallenTree.maxY = 100;
        decoFallenTree.logBlock = Blocks.LOG;
        decoFallenTree.logMeta = (byte) 2;
        decoFallenTree.leavesBlock = Blocks.LEAVES;
        decoFallenTree.leavesMeta = (byte) -1;
        decoFallenTree.minSize = 3;
        decoFallenTree.maxSize = 6;
        this.addDeco(decoFallenTree);

        DecoShrub decoShrub = new DecoShrub();
        decoShrub.maxY = 110;
        decoShrub.strengthFactor = 2f;
        this.addDeco(decoShrub);
    }

    @Override
    protected void initProperties() {

    }
}
