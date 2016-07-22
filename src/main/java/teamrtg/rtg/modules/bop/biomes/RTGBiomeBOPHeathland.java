package teamrtg.rtg.modules.bop.biomes;

import biomesoplenty.api.biome.BOPBiomes;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import teamrtg.rtg.api.tools.deco.DecoFallenTree;
import teamrtg.rtg.api.tools.surface.SurfaceBase;
import teamrtg.rtg.api.tools.terrain.HillockEffect;
import teamrtg.rtg.api.world.RTGWorld;
import teamrtg.rtg.api.world.biome.TerrainBase;
import teamrtg.rtg.api.world.biome.deco.DecoBaseBiomeDecorations;
import teamrtg.rtg.api.world.biome.surface.part.SurfacePart;
import teamrtg.rtg.modules.bop.RTGBiomeBOP;

public class RTGBiomeBOPHeathland extends RTGBiomeBOP {

    public RTGBiomeBOPHeathland() {
        super(BOPBiomes.heathland.get(), Biomes.RIVER);
    }

    @Override
    public TerrainBase initTerrain() {
        return new TerrainBase() {

            private float baseHeight = 66f;
            private HillockEffect hills;

            {
                hills = new HillockEffect();
                hills.height = 25;
                hills.minimumSimplex = 0.3f;
                hills.octave = 0;
                hills.wavelength = 50f;
            }

            @Override
            public float generateNoise(RTGWorld rtgWorld, int x, int y, float biomeWeight, float border, float river) {
                float added = groundNoise(x, y, groundNoiseAmplitudeHills, rtgWorld.simplex);
                added += hills.added(rtgWorld.simplex, rtgWorld.cell,x, y);
                return riverized(baseHeight + added,river);
            }
        };
    }

    @Override
    public SurfacePart initSurface() {
        return SurfaceBase.surfaceGenericCliffs(this);
    }

    @Override
    public void initDecos() {

        DecoFallenTree decoFallenTree = new DecoFallenTree();
        decoFallenTree.distribution.noiseDivisor = 80f;
        decoFallenTree.distribution.noiseFactor = 60f;
        decoFallenTree.distribution.noiseAddend = -15f;
        decoFallenTree.logCondition = DecoFallenTree.LogCondition.X_DIVIDED_BY_STRENGTH;
        decoFallenTree.logConditionNoise = 8f;
        decoFallenTree.logConditionChance = 1;
        decoFallenTree.logBlock = Blocks.LOG.getDefaultState();
        decoFallenTree.leavesBlock = Blocks.LEAVES.getDefaultState();
        decoFallenTree.minSize = 3;
        decoFallenTree.maxSize = 4;
        this.addDeco(decoFallenTree);

        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        this.addDeco(decoBaseBiomeDecorations);
    }

    @Override
    public void initConfig() {

    }
}
