package teamrtg.rtg.mods.vanilla.biomes;

import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import teamrtg.rtg.util.noise.CellNoise;
import teamrtg.rtg.util.noise.OpenSimplexNoise;
import teamrtg.rtg.world.biome.terrain.GroundEffect;
import teamrtg.rtg.world.biome.terrain.TerrainBase;
import teamrtg.rtg.world.gen.ChunkProviderRTG;
import teamrtg.rtg.world.gen.deco.DecoBaseBiomeDecorations;
import teamrtg.rtg.world.gen.deco.DecoFallenTree;
import teamrtg.rtg.world.gen.deco.DecoFallenTree.LogCondition;
import teamrtg.rtg.world.gen.structure.MapGenScatteredFeatureRTG;

public class RealisticBiomeVanillaJungleEdge extends RealisticBiomeVanillaBase {

    public RealisticBiomeVanillaJungleEdge(ChunkProviderRTG chunkProvider) {

        super(
                Biomes.JUNGLE_EDGE,
                Biomes.RIVER,
                chunkProvider
        );
    }

    @Override
    protected TerrainBase initTerrain() {
        return new TerrainBase() {

            private GroundEffect groundEffect = new GroundEffect(4f);

            @Override
            public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river) {
                return riverized(65f + groundEffect.added(simplex, cell, x, y), river);
            }
        };
    }


    @Override
    protected void initDecos() {
        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        this.addDeco(decoBaseBiomeDecorations);

        DecoFallenTree decoFallenTree = new DecoFallenTree();
        decoFallenTree.loops = 1;
        decoFallenTree.distribution.noiseDivisor = 100f;
        decoFallenTree.distribution.noiseFactor = 6f;
        decoFallenTree.distribution.noiseAddend = 0.8f;
        decoFallenTree.logCondition = LogCondition.NOISE_GREATER_AND_RANDOM_CHANCE;
        decoFallenTree.logConditionNoise = 0f;
        decoFallenTree.logConditionChance = 6;
        decoFallenTree.maxY = 110;
        decoFallenTree.logBlock = Blocks.LOG;
        decoFallenTree.logMeta = (byte) 3;
        decoFallenTree.leavesBlock = Blocks.LEAVES;
        decoFallenTree.leavesMeta = (byte) -1;
        decoFallenTree.minSize = 3;
        decoFallenTree.maxSize = 6;
        this.addDeco(decoFallenTree);
    }

    @Override
    protected void initProperties() {
        this.config.SCATTERED_FEATURE.setDefault(MapGenScatteredFeatureRTG.FeatureType.JUNGLE_TEMPLE.name());
    }
}
