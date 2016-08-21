package rtg.world.biome.realistic.vanilla;

import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;

import rtg.api.biome.BiomeConfig;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.gen.surface.vanilla.SurfaceVanillaExtremeHillsPlusM;
import rtg.world.gen.terrain.vanilla.TerrainVanillaExtremeHillsPlusM;

public class RealisticBiomeVanillaExtremeHillsPlusM extends RealisticBiomeVanillaBase {

    public static Biome biome = Biomes.MUTATED_EXTREME_HILLS_WITH_TREES;
    public static Biome river = Biomes.RIVER;

    public RealisticBiomeVanillaExtremeHillsPlusM(BiomeConfig config) {

        super(config, biome, river,
            new TerrainVanillaExtremeHillsPlusM(230f, 120f, 68f),
            new SurfaceVanillaExtremeHillsPlusM(config, Blocks.GRASS.getDefaultState(), Blocks.DIRT.getDefaultState(), false, null, 0f, 1.5f, 60f, 65f, 1.5f, Blocks.GRAVEL.getDefaultState(), 0.08f)
        );

        this.generatesEmeralds = true;
        this.generatesSilverfish = true;
        this.noLakes = true;
        this.noWaterFeatures = true;

        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        this.addDeco(decoBaseBiomeDecorations);
    }
}
