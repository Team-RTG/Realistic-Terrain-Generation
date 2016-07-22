package teamrtg.rtg.modules.bop.biomes;

import biomesoplenty.api.biome.BOPBiomes;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import teamrtg.rtg.api.tools.deco.DecoBoulder;
import teamrtg.rtg.api.tools.deco.DecoFallenTree;
import teamrtg.rtg.api.tools.deco.helper.DecoHelper5050;
import teamrtg.rtg.api.tools.surface.SurfaceBase;
import teamrtg.rtg.api.tools.terrain.HeightEffect;
import teamrtg.rtg.api.tools.terrain.HeightVariation;
import teamrtg.rtg.api.tools.terrain.JitterEffect;
import teamrtg.rtg.api.world.RTGWorld;
import teamrtg.rtg.api.world.biome.TerrainBase;
import teamrtg.rtg.api.world.biome.deco.DecoBaseBiomeDecorations;
import teamrtg.rtg.api.world.biome.surface.part.SurfacePart;
import teamrtg.rtg.modules.bop.RTGBiomeBOP;

public class RTGBiomeBOPDeadSwamp extends RTGBiomeBOP {

    public RTGBiomeBOPDeadSwamp() {
        super(BOPBiomes.dead_swamp.get(), Biomes.RIVER);
    }

    @Override
    public TerrainBase initTerrain() {
        return new TerrainBase() {

            private HeightEffect height;

            {
                HeightVariation waterLand = new HeightVariation();
                waterLand.height = 2f;
                waterLand.wavelength = 40f;
                waterLand.octave =0;

                height = new JitterEffect(5f,10f,waterLand);
            }

            @Override
            public float generateNoise(RTGWorld rtgWorld, int x, int y, float biomeWeight, float border, float river) {
                return 62f+ height.added(rtgWorld.simplex, rtgWorld.cell,x, y);
            }
        };
    }

    @Override
    public SurfacePart initSurface() {
        return SurfaceBase.surfaceSwamp(this);
    }

    @Override
    public void initDecos() {

        DecoBoulder decoBoulder1 = new DecoBoulder();
        decoBoulder1.boulderBlock = Blocks.COBBLESTONE.getDefaultState();
        decoBoulder1.chance = 12;
        decoBoulder1.maxY = 95;
        decoBoulder1.strengthFactor = 1f;
        DecoBoulder decoBoulder2 = new DecoBoulder();
        decoBoulder2.boulderBlock = Blocks.MOSSY_COBBLESTONE.getDefaultState();
        decoBoulder2.chance = 12;
        decoBoulder2.maxY = 95;
        decoBoulder2.strengthFactor = 1f;
        DecoHelper5050 decoHelper5050 = new DecoHelper5050(decoBoulder1, decoBoulder2);
        this.addDeco(decoHelper5050);

        DecoFallenTree decoFallenTree = new DecoFallenTree();
        decoFallenTree.distribution.noiseDivisor = 100f;
        decoFallenTree.distribution.noiseFactor = 6f;
        decoFallenTree.distribution.noiseAddend = 0.8f;
        decoFallenTree.logCondition = DecoFallenTree.LogCondition.NOISE_GREATER_AND_RANDOM_CHANCE;
        decoFallenTree.logConditionNoise = 0f;
        decoFallenTree.logConditionChance = 16;
        decoFallenTree.logBlock = Blocks.LOG.getDefaultState();
        decoFallenTree.leavesBlock = Blocks.LEAVES.getDefaultState();
        decoFallenTree.minSize = 3;
        decoFallenTree.maxSize = 5;
        this.addDeco(decoFallenTree);

        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        this.addDeco(decoBaseBiomeDecorations);
    }

    @Override
    public void initConfig() {

    }
}
