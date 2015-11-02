package rtg.world.biome.realistic.vanilla;

import java.util.Random;

import rtg.config.vanilla.ConfigVanilla;
import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.biome.BiomeBase;
import rtg.world.biome.realistic.RealisticBiomeBase;
import rtg.world.gen.feature.WorldGenBlob;
import rtg.world.gen.feature.WorldGenLog;
import rtg.world.gen.surface.vanilla.SurfaceVanillaIcePlains;
import rtg.world.gen.terrain.vanilla.TerrainVanillaIcePlains;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenerator;

public class RealisticBiomeVanillaIcePlains extends RealisticBiomeVanillaBase
{	
	public static Block topBlock = BiomeGenBase.icePlains.topBlock;
	public static Block fillerBlock = BiomeGenBase.icePlains.fillerBlock;
	
	public RealisticBiomeVanillaIcePlains()
	{
		super(
			BiomeGenBase.icePlains,
			BiomeBase.climatizedBiome(BiomeGenBase.frozenRiver, Climate.ICE),
			new TerrainVanillaIcePlains(),
			new SurfaceVanillaIcePlains(topBlock, fillerBlock, topBlock, topBlock)
		);
		
		this.setRealisticBiomeName("Vanilla Ice Plains");
		this.biomeCategory = BiomeCategory.NORMAL;
		this.biomeWeight = ConfigVanilla.weightVanillaIcePlains;
	}
	
    @Override
    public void rDecorate(World world, Random rand, int chunkX, int chunkY, OpenSimplexNoise simplex, CellNoise cell, float strength,
        float river) {
    
        RealisticBiomeBase.rDecorateSeedBiome(world, rand, chunkX, chunkY, simplex, cell, strength, river, baseBiome);
        
        if(river > 0.86f)
        {
            for(int j = 0; j < 5f * strength; j++)
            {
                int i1 = chunkX + rand.nextInt(16) + 8;
                int j1 = chunkY + rand.nextInt(16) + 8;
                int k1 = world.getHeightValue(i1, j1);
                if(k1 < 64)
                {
                    (new WorldGenBlob(Blocks.packed_ice, 0)).generate(world, rand, i1, k1, j1);
                }
            }
        }
        
        if(rand.nextInt((int)(24f / strength)) == 0)
        {
            int j6 = chunkX + rand.nextInt(16) + 8;
            int k10 = chunkY + rand.nextInt(16) + 8;
            int z52 = world.getHeightValue(j6, k10);
            
            WorldGenerator worldgenerator = new WorldGenLog(1, rand.nextInt(6), false);
            worldgenerator.setScale(1.0D, 1.0D, 1.0D);
            worldgenerator.generate(world, rand, j6, z52, k10);
        }
    }
}
