package teamrtg.rtg.modules.vanilla.biomes;

import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import teamrtg.rtg.api.tools.deco.*;
import teamrtg.rtg.api.tools.deco.DecoFallenTree.LogCondition;
import teamrtg.rtg.api.tools.deco.DecoTree.TreeCondition;
import teamrtg.rtg.api.tools.deco.DecoTree.TreeType;
import teamrtg.rtg.api.util.noise.IFloatAt;
import teamrtg.rtg.api.world.RTGWorld;
import teamrtg.rtg.api.world.biome.TerrainBase;
import teamrtg.rtg.api.world.biome.deco.DecoBaseBiomeDecorations;
import teamrtg.rtg.api.world.biome.surface.part.*;
import teamrtg.rtg.modules.vanilla.RTGBiomeVanilla;

public class RTGBiomeVanillaColdTaigaHills extends RTGBiomeVanilla {

    public RTGBiomeVanillaColdTaigaHills() {

        super(
                Biomes.COLD_TAIGA_HILLS,
            Biomes.FROZEN_RIVER
        );
        this.noLakes = true;
    }

    @Override
    public TerrainBase initTerrain() {
        return new TerrainBase() {
            @Override
            public float generateNoise(RTGWorld rtgWorld, int x, int y, float biomeWeight, float border, float river) {
                return terrainHighland(x, y, rtgWorld.simplex, rtgWorld.cell, river, 10f, 68f, 45f, 10f);
            }
        };
    }

    @Override
    public SurfacePart initSurface() {
        SurfacePart surface = new SurfacePart();

        IFloatAt cliffNoise = (x, y, z, rtgWorld) -> rtgWorld.simplex.noise3(x / 8f, y / 8f, z / 8f) * 0.5f;

        surface.add(PARTS.selectTopAndFill()

            .add(new CliffSelector((x, y, z, rtgWorld) -> {
                float n = 1.5f - ((y - 60f) / 65f) + cliffNoise.getAt(x, y, z, rtgWorld);
                return (n > 0.2f) ? n : 0.2f;
            })
                .add(PARTS.selectTop()
                    .add(PARTS.STONE_OR_COBBLE))
                .add(PARTS.STONE))

            .add(new CliffSelector(1.5f)
                .add(this.PARTS.SHADOW_STONE))

            .add(new CliffSelector((x, y, z, rtgWorld) -> 0.3f + ((y - 100f) / 50f) + cliffNoise.getAt(x, y, z, rtgWorld))
                .add(new Selector((x, y, z, rtgWorld) -> y > 110 + (cliffNoise.getAt(x, y, z, rtgWorld) * 4))
                    .add(new BlockPart(Blocks.SNOW.getDefaultState()))))

            .add(PARTS.selectTop()
                .add(new Selector((x, y, z, rtgWorld) -> rtgWorld.simplex.noise2(x / 50f, z / 50f) + cliffNoise.getAt(x, y, z, rtgWorld) * 0.6f > 0.24f)
                    .add(new BlockPart(Blocks.DIRT.getStateFromMeta(2))))
                .add(new BlockPart(Blocks.GRASS.getDefaultState())))
            .add(new TopPosSelector(0, 63)
                .add(new BlockPart(Blocks.GRAVEL.getDefaultState())))
            .add(new BlockPart(Blocks.DIRT.getDefaultState()))
        );
        return surface;
    }

    @Override
    public void initDecos() {
        DecoBoulder decoBoulder = new DecoBoulder();
        decoBoulder.boulderBlock = Blocks.MOSSY_COBBLESTONE;
        decoBoulder.chance = 16;
        decoBoulder.maxY = 95;
        decoBoulder.strengthFactor = 3f;
        this.addDeco(decoBoulder);

        DecoTree decoTrees = new DecoTree();
        decoTrees.strengthFactorForLoops = 4f;
        decoTrees.strengthNoiseFactorForLoops = true;
        decoTrees.distribution.noiseDivisor = 100f;
        decoTrees.distribution.noiseFactor = 12f;
        decoTrees.distribution.noiseAddend = 4f;
        decoTrees.treeType = TreeType.VANILLA_COLD_TAIGA;
        decoTrees.treeCondition = TreeCondition.ALWAYS_GENERATE;
        decoTrees.maxY = 110;
        this.addDeco(decoTrees);

        DecoFallenTree decoFallenTree = new DecoFallenTree();
        decoFallenTree.distribution.noiseDivisor = 100f;
        decoFallenTree.distribution.noiseFactor = 12f;
        decoFallenTree.distribution.noiseAddend = 4f;
        decoFallenTree.logCondition = LogCondition.NOISE_GREATER_AND_RANDOM_CHANCE;
        decoFallenTree.logConditionNoise = 0f;
        decoFallenTree.logConditionChance = 6;
        decoFallenTree.maxY = 100;
        decoFallenTree.logBlock = Blocks.LOG;
        decoFallenTree.logMeta = (byte) 1;
        decoFallenTree.leavesBlock = Blocks.LEAVES;
        decoFallenTree.leavesMeta = (byte) -1;
        decoFallenTree.minSize = 3;
        decoFallenTree.maxSize = 6;
        this.addDeco(decoFallenTree);

        DecoShrub decoShrub = new DecoShrub();
        decoShrub.maxY = 100;
        decoShrub.strengthFactor = 2f;
        this.addDeco(decoShrub);

        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        decoBaseBiomeDecorations.maxY = 110;
        decoBaseBiomeDecorations.equalsZeroChance = 3;
        this.addDeco(decoBaseBiomeDecorations);

        DecoPumpkin decoPumpkin = new DecoPumpkin();
        decoPumpkin.maxY = 90;
        decoPumpkin.randomType = teamrtg.rtg.api.tools.deco.DecoPumpkin.RandomType.X_DIVIDED_BY_STRENGTH;
        decoPumpkin.randomFloat = 20f;
        this.addDeco(decoPumpkin);

        DecoGrass decoGrass = new DecoGrass();
        decoGrass.maxY = 128;
        decoGrass.strengthFactor = 10f;
        this.addDeco(decoGrass);
    }

    @Override
    public void initConfig() {
        config.addBlock(config.BEACH_BLOCK).setDefault(Blocks.GRAVEL.getDefaultState());
    }
}
