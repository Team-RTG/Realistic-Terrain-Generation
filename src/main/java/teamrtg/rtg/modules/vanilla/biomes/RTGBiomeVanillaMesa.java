package teamrtg.rtg.modules.vanilla.biomes;

import net.minecraft.block.BlockDirt;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import teamrtg.rtg.api.tools.deco.*;
import teamrtg.rtg.api.tools.deco.DecoTree.TreeCondition;
import teamrtg.rtg.api.tools.deco.DecoTree.TreeType;
import teamrtg.rtg.api.tools.surface.SurfaceRiverOasis;
import teamrtg.rtg.api.tools.terrain.GroundEffect;
import teamrtg.rtg.api.util.math.CanyonColour;
import teamrtg.rtg.api.world.RTGWorld;
import teamrtg.rtg.api.world.biome.TerrainBase;
import teamrtg.rtg.api.world.biome.surface.part.*;
import teamrtg.rtg.modules.vanilla.RTGBiomeVanilla;

public class RTGBiomeVanillaMesa extends RTGBiomeVanilla {

    public RTGBiomeVanillaMesa() {

        super(
            Biomes.MESA,
            Biomes.RIVER
        );
    }

    @Override
    public void initConfig() {

    }

    @Override
    public void initDecos() {
        DecoBoulder decoBoulder = new DecoBoulder();
        decoBoulder.boulderBlock = Blocks.COBBLESTONE;
        decoBoulder.maxY = 83;
        this.addDeco(decoBoulder);

        DecoTree riverTrees = new DecoTree();
        riverTrees.checkRiver = true;
        riverTrees.minRiver = 0.86f;
        riverTrees.strengthNoiseFactorForLoops = false;
        riverTrees.strengthFactorForLoops = 10f;
        riverTrees.treeType = TreeType.SAVANNA_RIVER;
        riverTrees.treeCondition = TreeCondition.ALWAYS_GENERATE;
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

        DecoGrassDoubleTallgrass decoGrassDoubleTallgrass = new DecoGrassDoubleTallgrass();
        decoGrassDoubleTallgrass.checkRiver = true;
        decoGrassDoubleTallgrass.minRiver = 0.7f;
        decoGrassDoubleTallgrass.maxY = 128;
        decoGrassDoubleTallgrass.loops = 15;
        decoGrassDoubleTallgrass.doubleGrassChance = 3;
        this.addDeco(decoGrassDoubleTallgrass);

        DecoShrub decoShrub = new DecoShrub();
        decoShrub.loops = 3;
        decoShrub.maxY = 90;
        addDeco(decoShrub);

        DecoDeadBush decoDeadBush = new DecoDeadBush();
        decoDeadBush.maxY = 100;
        decoDeadBush.loops = 3;
        this.addDeco(decoDeadBush);

        DecoCactus decoCactus = new DecoCactus();
        decoCactus.soil = Blocks.SAND.getStateFromMeta(1);
        decoCactus.loops = 18;
        decoCactus.maxY = 100;
        this.addDeco(decoCactus);
    }

    @Override
    public SurfacePart initSurface() {
        SurfacePart surface = new SurfacePart();
        surface.add(new SurfaceRiverOasis(this));
        surface.add(
            new DepthSelector(0, 11)
                .add(new OrSelector()
                    .or(new CliffSelector(1.3f))
                    .or(new DepthSelector(4, 255))
                    .add(new BlockPart(CanyonColour.MESA)))
                .add(new HeightSelector(78, 255)
                    .add(PARTS.rand(5)
                        .add(new BlockPart(Blocks.DIRT.getDefaultState().withProperty(BlockDirt.VARIANT, BlockDirt.DirtType.COARSE_DIRT)))))
                .add(new HeightSelector(0, 77)
                    .add(PARTS.selectTop()
                        .add(new HeightSelector(0, 71)
                            .add(PARTS.rand(5)
                                .add(new BlockPart(Blocks.DIRT.getDefaultState().withProperty(BlockDirt.VARIANT, BlockDirt.DirtType.COARSE_DIRT))))
                        ))));
        surface.add(PARTS.surfaceGeneric());
        surface.add(new HeightSelector(50, 255).setMinNoise(PARTS.DEPTH_NOISE2)
            .add(new BlockPart(CanyonColour.MESA)));
        return surface;
    }

    @Override
    public TerrainBase initTerrain() {
        return new TerrainBase() {
            private GroundEffect groundEffect = new GroundEffect(4f);

            @Override
            public float generateNoise(RTGWorld rtgWorld, int x, int y, float biomeWeight, float border, float river) {
                return riverized(68f + groundEffect.added(rtgWorld.simplex, rtgWorld.cell, x, y), river);
            }
        };
    }
}
