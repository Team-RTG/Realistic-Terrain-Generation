package rtg.world.biome.realistic.biomesoplenty;

import net.minecraft.init.Biomes;
import net.minecraft.world.biome.Biome;

import biomesoplenty.api.biome.BOPBiomes;
import biomesoplenty.api.block.BOPBlocks;

import rtg.api.biome.BiomeConfig;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPWetland;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPWetland;

public class RealisticBiomeBOPWetland extends RealisticBiomeBOPBase {

    public static Biome biome = BOPBiomes.wetland.get();
    public static Biome river = Biomes.RIVER;

    public RealisticBiomeBOPWetland(BiomeConfig config) {

        super(config, biome, river,
            new TerrainBOPWetland(),
            new SurfaceBOPWetland(config, BOPBlocks.grass.getDefaultState(), BOPBlocks.dirt.getDefaultState())
        );

        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        this.addDeco(decoBaseBiomeDecorations);
    }
}
