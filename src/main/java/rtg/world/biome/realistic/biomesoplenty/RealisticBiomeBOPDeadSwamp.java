package rtg.world.biome.realistic.biomesoplenty;

import net.minecraft.init.Biomes;
import net.minecraft.world.biome.Biome;

import biomesoplenty.api.biome.BOPBiomes;
import biomesoplenty.api.block.BOPBlocks;

import rtg.api.biome.BiomeConfig;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPDeadSwamp;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPDeadSwamp;

public class RealisticBiomeBOPDeadSwamp extends RealisticBiomeBOPBase {

    public static Biome biome = BOPBiomes.dead_swamp.get();
    public static Biome river = Biomes.RIVER;

    public RealisticBiomeBOPDeadSwamp(BiomeConfig config) {

        super(config, biome, river,
            new TerrainBOPDeadSwamp(),
            new SurfaceBOPDeadSwamp(config, BOPBlocks.grass.getDefaultState(), BOPBlocks.dirt.getDefaultState())
        );

        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        this.addDeco(decoBaseBiomeDecorations);
    }
}
