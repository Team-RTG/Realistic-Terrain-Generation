package rtg.world.biome.realistic.biomesoplenty;

import net.minecraft.init.Biomes;
import net.minecraft.world.biome.Biome;

import biomesoplenty.api.biome.BOPBiomes;

import rtg.api.biome.BiomeConfig;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPShrubland;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPShrubland;

public class RealisticBiomeBOPShrubland extends RealisticBiomeBOPBase {

    public static Biome biome = BOPBiomes.shrubland.get();
    public static Biome river = Biomes.RIVER;

    public RealisticBiomeBOPShrubland(BiomeConfig config) {

        super(config, biome, river,
            new TerrainBOPShrubland(),
            new SurfaceBOPShrubland(config, biome.topBlock, biome.fillerBlock)
        );

        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        this.addDeco(decoBaseBiomeDecorations);
    }
}
