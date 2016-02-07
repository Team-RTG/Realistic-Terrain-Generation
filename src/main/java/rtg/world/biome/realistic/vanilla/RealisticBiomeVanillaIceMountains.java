package rtg.world.biome.realistic.vanilla;

import java.util.Random;

import rtg.api.biome.BiomeConfig;
import rtg.api.biome.vanilla.config.BiomeConfigVanillaIceMountains;
import rtg.config.vanilla.ConfigVanilla;
import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.gen.feature.WorldGenBlob;
import rtg.world.gen.feature.WorldGenLog;
import rtg.world.gen.feature.tree.WorldGenTreeRTGPine;
import rtg.world.gen.feature.tree.WorldGenTreeRTGPineSmall;
import rtg.world.gen.surface.vanilla.SurfaceVanillaIceMountains;
import rtg.world.gen.terrain.vanilla.TerrainVanillaIceMountains;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenIceSpike;
import net.minecraft.world.gen.feature.WorldGenerator;

public class RealisticBiomeVanillaIceMountains extends RealisticBiomeVanillaBase
{
    
    public static Block topBlock = BiomeGenBase.iceMountains.topBlock;
    public static Block fillerBlock = BiomeGenBase.iceMountains.fillerBlock;
    
    public RealisticBiomeVanillaIceMountains(BiomeConfig config)
    {
    
        super(
            BiomeGenBase.iceMountains,
            BiomeGenBase.frozenRiver,
            new TerrainVanillaIceMountains(230f, 80f, 0f),
            new SurfaceVanillaIceMountains(topBlock, fillerBlock, Blocks.snow, Blocks.snow, Blocks.packed_ice, Blocks.ice, 60f,
                -0.14f, 14f, 0.25f));
        
        this.config = config;
    }
    
    @Override
    public void rDecorate(World world, Random rand, int chunkX, int chunkY, OpenSimplexNoise simplex, CellNoise cell, float strength, float river)
    {
        
        /**
         * Using rDecorateSeedBiome() to partially decorate the biome? If so, then comment out this method.
         */
        rOreGenSeedBiome(world, rand, chunkX, chunkY, simplex, cell, strength, river, baseBiome);
    
        for (int l = 0; l < 6f * strength; ++l)
        {
            int i1 = chunkX + rand.nextInt(16) + 8;
            int j1 = chunkY + rand.nextInt(16) + 8;
            int k1 = world.getHeightValue(i1, j1);
            
            if (k1 < 95 && rand.nextInt(16) == 0) {
                (new WorldGenBlob(Blocks.cobblestone, 0, rand)).generate(world, rand, i1, k1, j1);
            }
        }
        
        float l = simplex.noise2(chunkX / 100f, chunkY / 100f) * 12f + 4f;
        for (int b1 = 0; b1 < l * strength; b1++)
        {
            int j6 = chunkX + rand.nextInt(16) + 8;
            int k10 = chunkY + rand.nextInt(16) + 8;
            int z52 = world.getHeightValue(j6, k10);
            
            if (z52 < 90)
            {
                WorldGenerator worldgenerator =
                    rand.nextInt(8) != 0 ? new WorldGenTreeRTGPine(4, rand.nextInt(4) == 0 ? 1 : 0)
                        : rand.nextInt(3) != 0 ? new WorldGenTreeRTGPineSmall(3 + rand.nextInt(6), 6 + rand.nextInt(8), 0)
                            : new WorldGenIceSpike();
                worldgenerator.setScale(1.0D, 1.0D, 1.0D);
                worldgenerator.generate(world, rand, j6, z52, k10);
            }
            else if (z52 < 120)
            {
                WorldGenerator worldgenerator =
                    rand.nextInt(4) != 0 ? new WorldGenTreeRTGPineSmall(1 + rand.nextInt(3), 3 + rand.nextInt(5), rand.nextInt(2))
                    : new WorldGenIceSpike();
                worldgenerator.setScale(1.0D, 1.0D, 1.0D);
                worldgenerator.generate(world, rand, j6, z52, k10);
            }
        }
        
        if (this.config.getPropertyById(BiomeConfigVanillaIceMountains.decorationLogsId).valueBoolean) {
        
            if (rand.nextInt((int) (12f / strength)) == 0)
            {
                int x22 = chunkX + rand.nextInt(16) + 8;
                int z22 = chunkY + rand.nextInt(16) + 8;
                int y22 = world.getHeightValue(x22, z22);
                if (y22 < 100)
                {
                    (new WorldGenLog(1, 3 + rand.nextInt(4), false)).generate(world, rand, x22, y22, z22);
                }
            }
        }
    }
}
