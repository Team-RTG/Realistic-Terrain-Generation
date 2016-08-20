package rtg.world.biome.realistic.biomesoplenty;

import net.minecraft.init.Biomes;
import net.minecraft.world.biome.Biome;

import biomesoplenty.api.biome.BOPBiomes;

import rtg.api.biome.BiomeConfig;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPFlowerIsland;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPFlowerIsland;

public class RealisticBiomeBOPFlowerIsland extends RealisticBiomeBOPBase {

    public static Biome biome = BOPBiomes.flower_island.get();
    public static Biome river = Biomes.RIVER;

    public RealisticBiomeBOPFlowerIsland(BiomeConfig config) {

        super(config, biome, river,
            new TerrainBOPFlowerIsland(65f, 68f, 24f),
            new SurfaceBOPFlowerIsland(config, biome.topBlock, biome.fillerBlock)
        );

        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        this.addDeco(decoBaseBiomeDecorations);
    }
}
