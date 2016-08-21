package rtg.world.biome.realistic.vanilla;

import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;

import rtg.api.biome.BiomeConfig;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.gen.surface.vanilla.SurfaceVanillaExtremeHillsM;
import rtg.world.gen.terrain.vanilla.TerrainVanillaExtremeHillsM;

public class RealisticBiomeVanillaExtremeHillsM extends RealisticBiomeVanillaBase {

    public static Biome biome = Biomes.MUTATED_EXTREME_HILLS;
    public static Biome river = Biomes.RIVER;

    public RealisticBiomeVanillaExtremeHillsM(BiomeConfig config) {

        super(config, biome, river,
            new TerrainVanillaExtremeHillsM(10f, 140f, 68f, 200f),
            new SurfaceVanillaExtremeHillsM(config, biome.topBlock, biome.fillerBlock, Blocks.GRASS.getDefaultState(), Blocks.DIRT.getDefaultState(), 60f,
                -0.14f, 14f, 0.25f)
        );

        this.generatesEmeralds = true;
        this.generatesSilverfish = true;
        this.noWaterFeatures = true;

        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        this.addDeco(decoBaseBiomeDecorations);
    }
}
