package rtg.world.biome.realistic.biomesoplenty;

import net.minecraft.init.Biomes;
import net.minecraft.world.biome.Biome;

import biomesoplenty.api.biome.BOPBiomes;

import rtg.api.biome.BiomeConfig;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPSteppe;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPSteppe;

public class RealisticBiomeBOPSteppe extends RealisticBiomeBOPBase {

    public static Biome biome = BOPBiomes.steppe.get();
    public static Biome river = Biomes.RIVER;

    public RealisticBiomeBOPSteppe(BiomeConfig config) {

        super(config, biome, river,
            new TerrainBOPSteppe(65f, 68f, 30f),
            new SurfaceBOPSteppe(config, biome.topBlock, biome.fillerBlock)
        );

        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        this.addDeco(decoBaseBiomeDecorations);
    }
}
