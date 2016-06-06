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

public class RTGBiomeVanillaMegaTaiga extends RTGBiomeVanilla {

    public RTGBiomeVanillaMegaTaiga() {

        super(
                Biomes.REDWOOD_TAIGA,
            Biomes.RIVER
        );
    }

    @Override
    public TerrainBase initTerrain() {
        return new TerrainBase() {
            @Override
            public float generateNoise(RTGWorld rtgWorld, int x, int y, float biomeWeight, float border, float river) {
                return terrainFlatLakes(x, y, rtgWorld.simplex, river, 13f, 66f);
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
        decoTrees.strengthNoiseFactorXForLoops = true;
        decoTrees.distribution.noiseDivisor = 100f;
        decoTrees.distribution.noiseFactor = 6f;
        decoTrees.distribution.noiseAddend = 0.8f;
        decoTrees.treeType = TreeType.MEGA_TAIGA;
        decoTrees.treeCondition = TreeCondition.ALWAYS_GENERATE;
        decoTrees.maxY = 100;
        this.addDeco(decoTrees);

        DecoFallenTree decoFallenTree = new DecoFallenTree();
        decoFallenTree.distribution.noiseDivisor = 100f;
        decoFallenTree.distribution.noiseFactor = 6f;
        decoFallenTree.distribution.noiseAddend = 0.8f;
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
        decoShrub.chance = 10;
        this.addDeco(decoShrub);

        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        decoBaseBiomeDecorations.equalsZeroChance = 3;
        this.addDeco(decoBaseBiomeDecorations);

        DecoMushrooms decoMushrooms = new DecoMushrooms();
        decoMushrooms.maxY = 90;
        decoMushrooms.randomType = teamrtg.rtg.api.tools.deco.DecoMushrooms.RandomType.X_DIVIDED_BY_STRENGTH;
        decoMushrooms.randomFloat = 3f;
        this.addDeco(decoMushrooms);

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

    }
}
