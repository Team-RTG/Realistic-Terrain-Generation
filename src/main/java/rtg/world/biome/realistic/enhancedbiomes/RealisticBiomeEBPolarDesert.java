package rtg.world.biome.realistic.enhancedbiomes;

import java.util.Random;

import enhancedbiomes.EnhancedBiomesMod;
import rtg.config.enhancedbiomes.ConfigEB;
import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.biome.BiomeBase;
import rtg.world.gen.feature.WorldGenBlob;
import rtg.world.gen.feature.tree.WorldGenTreeSpruceSmall;
import rtg.world.gen.surface.enhancedbiomes.SurfaceEBPolarDesert;
import rtg.world.gen.terrain.enhancedbiomes.TerrainEBPolarDesert;

import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenerator;

public class RealisticBiomeEBPolarDesert extends RealisticBiomeEBBase
{
    
    public RealisticBiomeEBPolarDesert(BiomeGenBase ebBiome)
    {
    
        super(
            ebBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.COLD),
            new TerrainEBPolarDesert(),
            new SurfaceEBPolarDesert(
                ebBiome.topBlock, 
                ebBiome.fillerBlock
            )
        );
        
        this.setRealisticBiomeName("EB Polar Desert");
        this.biomeSize = BiomeSize.NORMAL;
        this.biomeWeight = ConfigEB.weightEBPolarDesert;
    }
    
    @Override
    public void rDecorate(World world, Random rand, int chunkX, int chunkY, OpenSimplexNoise simplex, CellNoise cell, float strength,
        float river)
    {
    
        if (river > 0.86f)
        {
            for (int j = 0; j < 5f * strength; j++)
            {
                int i1 = chunkX + rand.nextInt(16) + 8;
                int j1 = chunkY + rand.nextInt(16) + 8;
                int k1 = world.getHeightValue(i1, j1);
                if (k1 < 64)
                {
                    (new WorldGenBlob(Blocks.packed_ice, 0, rand)).generate(world, rand, i1, k1, j1);
                }
            }
            
            if (rand.nextInt((int) (2f / strength)) == 0)
            {
                int j6 = chunkX + rand.nextInt(16) + 8;
                int k10 = chunkY + rand.nextInt(16) + 8;
                int z52 = world.getHeightValue(j6, k10);
                
                WorldGenerator worldgenerator = new WorldGenTreeSpruceSmall(rand.nextInt(2));
                worldgenerator.setScale(1.0D, 1.0D, 1.0D);
                worldgenerator.generate(world, rand, j6, z52, k10);
            }
        }
    }
}
