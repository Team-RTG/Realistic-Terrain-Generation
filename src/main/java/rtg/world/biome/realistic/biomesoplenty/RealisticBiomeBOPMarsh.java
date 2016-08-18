package rtg.world.biome.realistic.biomesoplenty;

import net.minecraft.init.Biomes;
import net.minecraft.world.biome.Biome;

import biomesoplenty.api.biome.BOPBiomes;

import rtg.api.biome.BiomeConfig;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPMarsh;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPMarsh;

public class RealisticBiomeBOPMarsh extends RealisticBiomeBOPBase {

    public static Biome biome = BOPBiomes.marsh.get();
    public static Biome river = Biomes.RIVER;

    public RealisticBiomeBOPMarsh(BiomeConfig config) {

        super(config, biome, river,
            new TerrainBOPMarsh(),
            new SurfaceBOPMarsh(config, biome.topBlock, biome.fillerBlock)
        );

        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        this.addDeco(decoBaseBiomeDecorations);
    }
}
