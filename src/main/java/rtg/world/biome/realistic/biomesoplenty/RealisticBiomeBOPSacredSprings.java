package rtg.world.biome.realistic.biomesoplenty;

import net.minecraft.init.Biomes;
import net.minecraft.world.biome.Biome;

import biomesoplenty.api.biome.BOPBiomes;

import rtg.api.biome.BiomeConfig;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPSacredSprings;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPSacredSprings;

public class RealisticBiomeBOPSacredSprings extends RealisticBiomeBOPBase {

    public static Biome biome = BOPBiomes.sacred_springs.get();
    public static Biome river = Biomes.RIVER;

    public RealisticBiomeBOPSacredSprings(BiomeConfig config) {

        super(config, biome, river,
            new TerrainBOPSacredSprings(150f, 30f, 68f),
            new SurfaceBOPSacredSprings(config, biome.topBlock, biome.fillerBlock)
        );

        this.noWaterFeatures = true;
        this.waterSurfaceLakeChance = 2;

        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        this.addDeco(decoBaseBiomeDecorations);
    }
}
