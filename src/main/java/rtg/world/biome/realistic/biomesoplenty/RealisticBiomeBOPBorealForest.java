package rtg.world.biome.realistic.biomesoplenty;

import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;

import biomesoplenty.api.biome.BOPBiomes;

import rtg.api.biome.BiomeConfig;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPBorealForest;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPBorealForest;

public class RealisticBiomeBOPBorealForest extends RealisticBiomeBOPBase {

    public static Biome biome = BOPBiomes.boreal_forest.get();
    public static Biome river = Biomes.RIVER;

    public RealisticBiomeBOPBorealForest(BiomeConfig config) {

        super(config, biome, river,
            new TerrainBOPBorealForest(),
            new SurfaceBOPBorealForest(config, biome.topBlock, biome.fillerBlock, false, null, 0f, 1.5f, 60f, 65f, 1.5f, Blocks.DIRT.getStateFromMeta(2), 0.15f)
        );

        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        this.addDeco(decoBaseBiomeDecorations);
    }
}
