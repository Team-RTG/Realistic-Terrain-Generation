package rtg.world.biome.realistic.biomesoplenty;

import net.minecraft.init.Biomes;
import net.minecraft.world.biome.Biome;

import biomesoplenty.api.biome.BOPBiomes;

import rtg.api.biome.BiomeConfig;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPGravelBeach;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPGravelBeach;

public class RealisticBiomeBOPGravelBeach extends RealisticBiomeBOPBase {

    public static Biome biome = BOPBiomes.gravel_beach.get();
    public static Biome river = Biomes.RIVER;

    public RealisticBiomeBOPGravelBeach(BiomeConfig config) {

        super(config, biome, river,
            new TerrainBOPGravelBeach(),
            new SurfaceBOPGravelBeach(
                config,
                biome.topBlock,
                biome.fillerBlock,
                biome.topBlock,
                biome.fillerBlock,
                (byte) 0,
                1
            )
        );

        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        this.addDeco(decoBaseBiomeDecorations);
    }
}
