package teamrtg.rtg.modules.vanilla.biomes;

import net.minecraft.block.BlockDirt;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import teamrtg.rtg.api.tools.deco.DecoBoulder;
import teamrtg.rtg.api.tools.deco.DecoCactus;
import teamrtg.rtg.api.tools.deco.DecoDeadBush;
import teamrtg.rtg.api.tools.deco.DecoShrub;
import teamrtg.rtg.api.tools.deco.collection.DecoCollectionDesertRiver;
import teamrtg.rtg.api.tools.surface.SurfaceRiverOasis;
import teamrtg.rtg.api.tools.terrain.GroundEffect;
import teamrtg.rtg.api.util.math.CanyonColour;
import teamrtg.rtg.api.world.RTGWorld;
import teamrtg.rtg.api.world.biome.TerrainBase;
import teamrtg.rtg.api.world.biome.surface.part.BlockPart;
import teamrtg.rtg.api.world.biome.surface.part.CliffSelector;
import teamrtg.rtg.api.world.biome.surface.part.DepthSelector;
import teamrtg.rtg.api.world.biome.surface.part.HeightSelector;
import teamrtg.rtg.api.world.biome.surface.part.OrSelector;
import teamrtg.rtg.api.world.biome.surface.part.SurfacePart;
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
        this.addDecoCollection(new DecoCollectionDesertRiver());
        
		DecoBoulder decoBoulder = new DecoBoulder();
		decoBoulder.boulderBlock = Blocks.COBBLESTONE.getDefaultState();
		decoBoulder.maxY = 83;
		this.addDeco(decoBoulder);

        DecoShrub decoShrub = new DecoShrub();
        decoShrub.loops = 3;
        decoShrub.maxY = 90;
        addDeco(decoShrub);
        
        DecoDeadBush decoDeadBush = new DecoDeadBush();
		decoDeadBush.maxY = 100;
		decoDeadBush.loops = 3;
        this.addDeco(decoDeadBush);
        
        DecoCactus decoCactus = new DecoCactus();
        decoCactus.soilBlock = Blocks.SAND.getStateFromMeta(1);
        decoCactus.soilMeta = (byte)1;
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
