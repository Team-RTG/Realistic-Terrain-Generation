package rtg.world.biome.realistic.biomesoplenty;

import net.minecraft.init.Biomes;
import net.minecraft.world.biome.Biome;

import biomesoplenty.api.biome.BOPBiomes;

import rtg.api.biome.BiomeConfig;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPOriginIsland;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPOriginIsland;

public class RealisticBiomeBOPOriginIsland extends RealisticBiomeBOPBase {

    public static Biome biome = BOPBiomes.origin_island.get();
    public static Biome river = Biomes.RIVER;

    public RealisticBiomeBOPOriginIsland(BiomeConfig config) {

        super(config, biome, river,
            new TerrainBOPOriginIsland(65f, 80f, 38f),
            new SurfaceBOPOriginIsland(config, biome.topBlock, biome.fillerBlock)
        );

        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        this.addDeco(decoBaseBiomeDecorations);
    }
}
