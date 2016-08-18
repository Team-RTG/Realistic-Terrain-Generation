package rtg.world.biome.realistic.biomesoplenty;

import net.minecraft.init.Biomes;
import net.minecraft.world.biome.Biome;

import biomesoplenty.api.biome.BOPBiomes;

import rtg.api.biome.BiomeConfig;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPBog;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPBog;

public class RealisticBiomeBOPBog extends RealisticBiomeBOPBase {

    public static Biome biome = BOPBiomes.bog.get();
    public static Biome river = Biomes.RIVER;

    public RealisticBiomeBOPBog(BiomeConfig config) {

        super(config, biome, river,
            new TerrainBOPBog(),
            new SurfaceBOPBog(config, biome.topBlock, biome.fillerBlock)
        );

        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        this.addDeco(decoBaseBiomeDecorations);
    }
}
