package rtg.world.biome.realistic.vanilla;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenPumpkin;
import net.minecraft.world.gen.feature.WorldGenerator;
import rtg.api.biome.BiomeConfig;
import rtg.api.biome.vanilla.config.BiomeConfigVanillaColdTaigaHills;
import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.util.VillageMaterial;
import rtg.world.gen.feature.WorldGenBlob;
import rtg.world.gen.feature.WorldGenGrass;
import rtg.world.gen.feature.WorldGenLog;
import rtg.world.gen.feature.tree.WorldGenTreeRTGPine;
import rtg.world.gen.feature.tree.WorldGenTreeRTGPineSmall;
import rtg.world.gen.feature.tree.WorldGenTreeRTGShrub;
import rtg.world.gen.feature.tree.WorldGenTreeRTGSpruceSmall;
import rtg.world.gen.surface.vanilla.SurfaceVanillaColdTaigaHills;
import rtg.world.gen.terrain.vanilla.TerrainVanillaColdTaigaHills;

import java.util.Random;

public class RealisticBiomeVanillaColdTaigaHills extends RealisticBiomeVanillaBase
{
    
    public static IBlockState topBlock = BiomeGenBase.coldTaigaHills.topBlock;
    public static IBlockState fillerBlock = BiomeGenBase.coldTaigaHills.fillerBlock;
    
    public RealisticBiomeVanillaColdTaigaHills(BiomeConfig config)
    {
    
        super(config, 
            BiomeGenBase.coldTaigaHills,
            BiomeGenBase.frozenRiver,
            new TerrainVanillaColdTaigaHills(),
            new SurfaceVanillaColdTaigaHills(config, Blocks.grass.getDefaultState(), Blocks.dirt.getDefaultState(), true, Blocks.sand.getDefaultState(), 0.2f));
        config.setVillageMaterial(VillageMaterial.Preset.SPRUCE);
    }
    
    @Override
    public void rDecorate(World world, Random rand, int chunkX, int chunkY, OpenSimplexNoise simplex, CellNoise cell, float strength, float river)
    {
        
        /**
         * Using rDecorateSeedBiome() to partially decorate the biome? If so, then comment out this method.
         */
        rOreGenSeedBiome(world, rand, new BlockPos(chunkX, 0, chunkY), simplex, cell, strength, river, baseBiome);
    
        for (int l = 0; l < 6f * strength; ++l)
        {
            int i1 = chunkX + rand.nextInt(16) + 8;
            int j1 = chunkY + rand.nextInt(16) + 8;
            int k1 = world.getHeight(new BlockPos(i1, 0, j1)).getY();
            
            if (k1 < 95 && rand.nextInt(16) == 0) {
                (new WorldGenBlob(Blocks.mossy_cobblestone, 0, rand)).generate(world, rand, new BlockPos(i1, k1, j1));
            }
        }
        
        float l = simplex.noise2(chunkX / 100f, chunkY / 100f) * 12f + 4f;
        for (int b1 = 0; b1 < l * strength; b1++)
        {
            int j6 = chunkX + rand.nextInt(16) + 8;
            int k10 = chunkY + rand.nextInt(16) + 8;
            int z52 = world.getHeight(new BlockPos(j6, 0, k10)).getY();
            
            if (z52 < 90)
            {
                WorldGenerator worldgenerator =
                    rand.nextInt(10) != 0 ? new WorldGenTreeRTGPine(4, rand.nextInt(4) == 0 ? 1 : 0)
                        : rand.nextInt(3) != 0 ? new WorldGenTreeRTGPineSmall(3 + rand.nextInt(6), 6 + rand.nextInt(8), 0)
                            : new WorldGenTreeRTGSpruceSmall(rand.nextInt(2) + 1);
                worldgenerator.generate(world, rand, new BlockPos(j6, z52, k10));
            }
            else if (z52 < 120)
            {
                WorldGenerator worldgenerator =
                    rand.nextInt(4) != 0 ? new WorldGenTreeRTGPineSmall(1 + rand.nextInt(3), 3 + rand.nextInt(5), rand.nextInt(2))
                        : new WorldGenTreeRTGSpruceSmall(rand.nextInt(2));
                worldgenerator.generate(world, rand, new BlockPos(j6, z52, k10));
            }
        }
        
        if (this.config.getPropertyById(BiomeConfigVanillaColdTaigaHills.decorationLogsId).valueBoolean) {
        
            if (rand.nextInt((int) (4f / strength)) == 0)
            {
                int x22 = chunkX + rand.nextInt(16) + 8;
                int z22 = chunkY + rand.nextInt(16) + 8;
                int y22 = world.getHeight(new BlockPos(x22, 0, z22)).getY();
                if (y22 < 100)
                {
                    (new WorldGenLog(1, 3 + rand.nextInt(4), false)).generate(world, rand, new BlockPos(x22, y22, z22));
                }
            }
        }
        
        for (int f24 = 0; f24 < 4f * strength; f24++)
        {
            int i1 = chunkX + rand.nextInt(16) + 8;
            int j1 = chunkY + rand.nextInt(16) + 8;
            int k1 = world.getHeight(new BlockPos(i1, 0, j1)).getY();
            if (k1 < 110)
            {
                (new WorldGenTreeRTGShrub(rand.nextInt(4) + 1, rand.nextInt(2), rand.nextInt(2))).generate(world, rand, new BlockPos(i1, k1, j1));
            }
        }
        
        if (rand.nextInt((int) (20f / strength)) == 0)
        {
            int j16 = chunkX + rand.nextInt(16) + 8;
            int j18 = rand.nextInt(100);
            int j21 = chunkY + rand.nextInt(16) + 8;
            (new WorldGenPumpkin()).generate(world, rand, new BlockPos(j16, j18, j21));
        }
        
        for (int l14 = 0; l14 < 12f * strength; l14++)
        {
            int l19 = chunkX + rand.nextInt(16) + 8;
            int k22 = rand.nextInt(128);
            int j24 = chunkY + rand.nextInt(16) + 8;
            (new WorldGenGrass(Blocks.tallgrass, 1)).generate(world, rand, new BlockPos(l19, k22, j24));
        }
    }
}
