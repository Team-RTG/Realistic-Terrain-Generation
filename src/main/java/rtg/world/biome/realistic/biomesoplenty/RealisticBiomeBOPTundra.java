package rtg.world.biome.realistic.biomesoplenty;

import net.minecraft.init.Biomes;
import net.minecraft.world.biome.Biome;

import biomesoplenty.api.biome.BOPBiomes;

import rtg.api.biome.BiomeConfig;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPTundra;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPTundra;

public class RealisticBiomeBOPTundra extends RealisticBiomeBOPBase {

    public static Biome biome = BOPBiomes.tundra.get();
    public static Biome river = Biomes.RIVER;

    public RealisticBiomeBOPTundra(BiomeConfig config) {

        super(config, biome, river,
            new TerrainBOPTundra(),
            new SurfaceBOPTundra(config, biome.topBlock, biome.fillerBlock)
        );

        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        this.addDeco(decoBaseBiomeDecorations);
    }
}
