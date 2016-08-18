package rtg.world.biome.realistic.biomesoplenty;

import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;

import biomesoplenty.api.biome.BOPBiomes;

import rtg.api.biome.BiomeConfig;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPChaparral;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPChaparral;

public class RealisticBiomeBOPChaparral extends RealisticBiomeBOPBase {

    public static Biome biome = BOPBiomes.chaparral.get();
    public static Biome river = Biomes.RIVER;

    public RealisticBiomeBOPChaparral(BiomeConfig config) {

        super(config, biome, river,
            new TerrainBOPChaparral(),
            new SurfaceBOPChaparral(config, biome.topBlock, biome.fillerBlock, Blocks.SAND.getDefaultState(), 26f, 0.35f)
        );

        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        this.addDeco(decoBaseBiomeDecorations);
    }
}
