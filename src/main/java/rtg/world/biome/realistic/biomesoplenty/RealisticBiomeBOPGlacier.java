package rtg.world.biome.realistic.biomesoplenty;

import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;

import biomesoplenty.api.biome.BOPBiomes;

import rtg.api.biome.BiomeConfig;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPGlacier;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPGlacier;

public class RealisticBiomeBOPGlacier extends RealisticBiomeBOPBase {

    public static Biome biome = BOPBiomes.glacier.get();
    public static Biome river = Biomes.FROZEN_RIVER;

    public RealisticBiomeBOPGlacier(BiomeConfig config) {

        super(config, biome, river,
            new TerrainBOPGlacier(230f, 40f, 68f),
            new SurfaceBOPGlacier(config, biome.topBlock, biome.fillerBlock, biome.topBlock, biome.fillerBlock, Blocks.PACKED_ICE.getDefaultState(), Blocks.ICE.getDefaultState(), 60f,
                -0.14f, 14f, 0.25f)
        );

        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        this.addDeco(decoBaseBiomeDecorations);
    }
}
