package rtg.world.biome.realistic.biomesoplenty;

import net.minecraft.world.biome.BiomeGenBase;

import biomesoplenty.api.biome.BOPBiomes;

import rtg.api.biome.BiomeConfig;
import rtg.world.biome.deco.DecoGrassDoubleTallgrass;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPVolcanicIsland;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPVolcanicIsland;

public class RealisticBiomeBOPVolcanicIsland extends RealisticBiomeBOPBase {

    public static BiomeGenBase bopBiome = BOPBiomes.volcanic_island.get();

    public RealisticBiomeBOPVolcanicIsland(BiomeConfig config) {

        super(config,
            bopBiome, BiomeGenBase.river,
            new TerrainBOPVolcanicIsland(),
            new SurfaceBOPVolcanicIsland(config,
                bopBiome.topBlock, //Block top
                bopBiome.fillerBlock, //Block filler,
                bopBiome.topBlock, //IBlockState mixTop,
                bopBiome.fillerBlock, //IBlockState mixFill,
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

        DecoGrassDoubleTallgrass decoGrassDoubleTallgrass = new DecoGrassDoubleTallgrass();
        decoGrassDoubleTallgrass.doubleGrassChance = 3;
        decoGrassDoubleTallgrass.loops = 15;
        decoGrassDoubleTallgrass.maxY = 128;
        this.addDeco(decoGrassDoubleTallgrass);
    }
}
