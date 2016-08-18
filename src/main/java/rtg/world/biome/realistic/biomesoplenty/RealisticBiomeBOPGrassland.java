package rtg.world.biome.realistic.biomesoplenty;

import net.minecraft.init.Biomes;
import net.minecraft.world.biome.Biome;

import biomesoplenty.api.biome.BOPBiomes;

import rtg.api.biome.BiomeConfig;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPGrassland;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPGrassland;

public class RealisticBiomeBOPGrassland extends RealisticBiomeBOPBase {

    public static Biome biome = BOPBiomes.grassland.get();
    public static Biome river = Biomes.RIVER;

    public RealisticBiomeBOPGrassland(BiomeConfig config) {

        super(config, biome, river,
            new TerrainBOPGrassland(),
            new SurfaceBOPGrassland(config, biome.topBlock, biome.fillerBlock)
        );

        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        this.addDeco(decoBaseBiomeDecorations);
    }
}
