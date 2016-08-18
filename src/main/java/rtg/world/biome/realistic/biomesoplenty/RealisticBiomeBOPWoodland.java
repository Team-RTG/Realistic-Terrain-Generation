package rtg.world.biome.realistic.biomesoplenty;

import net.minecraft.init.Biomes;
import net.minecraft.world.biome.Biome;

import biomesoplenty.api.biome.BOPBiomes;

import rtg.api.biome.BiomeConfig;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPWoodland;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPWoodland;

public class RealisticBiomeBOPWoodland extends RealisticBiomeBOPBase {

    public static Biome biome = BOPBiomes.woodland.get();
    public static Biome river = Biomes.RIVER;

    public RealisticBiomeBOPWoodland(BiomeConfig config) {

        super(config, biome, river,
            new TerrainBOPWoodland(10f, 25f, 72f, 120f),
            new SurfaceBOPWoodland(config, biome.topBlock, biome.fillerBlock)
        );

        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        this.addDeco(decoBaseBiomeDecorations);
    }
}
