package rtg.world.biome.realistic.mineworld;

import net.minecraft.init.Biomes;
import net.minecraft.world.biome.Biome;

import rtg.api.biome.BiomeConfig;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.gen.surface.mineworld.SurfaceMWVolcano;
import rtg.world.gen.terrain.mineworld.TerrainMWVolcano;

public class RealisticBiomeMWVolcano extends RealisticBiomeMWBase {

    public static Biome river = Biomes.RIVER;

    public RealisticBiomeMWVolcano(Biome biome, BiomeConfig config) {

        super(config, biome, river,
            new TerrainMWVolcano(),
            new SurfaceMWVolcano(config,
                biome.topBlock, //Block top
                biome.fillerBlock, //Block filler,
                biome.topBlock, //IBlockState mixTop,
                biome.fillerBlock, //IBlockState mixFill,
                80f, //float mixWidth,
                -0.15f, //float mixHeight,
                10f, //float smallWidth,
                0.5f //float smallStrength
            )
        );

        this.waterSurfaceLakeChance = 0;
        this.lavaSurfaceLakeChance = 1;
        this.noLakes = true;
        this.noWaterFeatures = true;

        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        this.addDeco(decoBaseBiomeDecorations);
    }
}
