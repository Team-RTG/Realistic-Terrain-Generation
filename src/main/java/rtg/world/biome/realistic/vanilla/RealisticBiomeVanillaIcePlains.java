package rtg.world.biome.realistic.vanilla;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenerator;
import rtg.api.biome.BiomeConfig;
import rtg.api.biome.vanilla.config.BiomeConfigVanillaIcePlains;
import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.util.VillageMaterial;
import rtg.world.gen.feature.WorldGenBlob;
import rtg.world.gen.feature.WorldGenLog;
import rtg.world.gen.surface.vanilla.SurfaceVanillaIcePlains;
import rtg.world.gen.terrain.vanilla.TerrainVanillaIcePlains;

import java.util.Random;

public class RealisticBiomeVanillaIcePlains extends RealisticBiomeVanillaBase
{	
	public static Block topBlock = BiomeGenBase.icePlains.topBlock.getBlock();
	public static Block fillerBlock = BiomeGenBase.icePlains.fillerBlock.getBlock();
	
	public RealisticBiomeVanillaIcePlains(BiomeConfig config)
	{
		super(config, 
			BiomeGenBase.icePlains,
			BiomeGenBase.frozenRiver,
			new TerrainVanillaIcePlains(),
			new SurfaceVanillaIcePlains(config, topBlock, fillerBlock, topBlock, topBlock)
		);
        config.setVillageMaterial(VillageMaterial.Preset.ICE);
	}
	
    @Override
    public void rDecorate(World world, Random rand, int chunkX, int chunkY, OpenSimplexNoise simplex, CellNoise cell, float strength, float river)
    {
        
        /**
         * Using rDecorateSeedBiome() to partially decorate the biome? If so, then comment out this method.
         */
        //rOreGenSeedBiome(world, rand, new BlockPos(chunkX, 0, chunkY), simplex, cell, strength, river, baseBiome);
    
        rDecorateSeedBiome(world, rand, chunkX, chunkY, simplex, cell, strength, river, baseBiome);
        
        if(river > 0.86f)
        {
            for(int j = 0; j < 5f * strength; j++)
            {
                int i1 = chunkX + rand.nextInt(16) + 8;
                int j1 = chunkY + rand.nextInt(16) + 8;
                int k1 = world.getHeight(new BlockPos(i1, 0, j1)).getY();
                
                if (k1 < 64 && rand.nextInt(16) == 0) {
                    (new WorldGenBlob(Blocks.packed_ice, 0, rand)).generate(world, rand, new BlockPos(i1, k1, j1));
                }
            }
        }
        
        if (this.config.getPropertyById(BiomeConfigVanillaIcePlains.decorationLogsId).valueBoolean) {
        
            if(rand.nextInt((int)(24f / strength)) == 0)
            {
                int j6 = chunkX + rand.nextInt(16) + 8;
                int k10 = chunkY + rand.nextInt(16) + 8;
                int z52 = world.getHeight(new BlockPos(j6, 0, k10)).getY();
                
                WorldGenerator worldgenerator = new WorldGenLog(1, rand.nextInt(6), false);
                worldgenerator.generate(world, rand, new BlockPos(j6, z52, k10));
            }
        }
    }
}
