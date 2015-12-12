package rtg.world.biome.realistic.biomesoplenty;

import java.util.Random;

import rtg.config.biomesoplenty.ConfigBOP;
import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.biome.BiomeBase;
import rtg.world.biome.realistic.RealisticBiomeBase;
import rtg.world.gen.feature.WorldGenLog;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPLushSwamp;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPLushSwamp;
import biomesoplenty.api.content.BOPCBiomes;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenBlockBlob;

public class RealisticBiomeBOPLushSwamp extends RealisticBiomeBOPBase
{	
	public static BiomeGenBase bopBiome = BOPCBiomes.lushSwamp;
	
	public static Block topBlock = bopBiome.topBlock;
	public static Block fillerBlock = bopBiome.fillerBlock;
	
	public RealisticBiomeBOPLushSwamp()
	{
		super(
			bopBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.WET),
			new TerrainBOPLushSwamp(),
			new SurfaceBOPLushSwamp(topBlock, fillerBlock, Blocks.stone, Blocks.cobblestone)
		);
		
		this.setRealisticBiomeName("BOP Lush Swamp");
		this.biomeSize = BiomeSize.NORMAL;
		this.biomeWeight = ConfigBOP.weightBOPLushSwamp;
		this.generateVillages = ConfigBOP.villageBOPLushSwamp;
	}
	
    @Override
    public void rDecorate(World world, Random rand, int chunkX, int chunkY, OpenSimplexNoise simplex, CellNoise cell, float strength,
        float river)
    {
    
        RealisticBiomeBase.rDecorateSeedBiome(world, rand, chunkX, chunkY, simplex, cell, strength, river, baseBiome);
        
        float l = simplex.noise2(chunkX / 80f, chunkY / 80f) * 60f - 15f;
        
        for (int i23 = 0; i23 < 1; i23++)
        {
            int i1 = chunkX + rand.nextInt(16) + 8;
            int j1 = chunkY + rand.nextInt(16) + 8;
            int k1 = world.getHeightValue(i1, j1);
            
            if (rand.nextInt(16) == 0) {
                (new WorldGenBlockBlob(Blocks.mossy_cobblestone, 0)).generate(world, rand, i1, k1, j1);
            }
        }

        if (rand.nextInt(4) == 0) {
            
            int x22 = chunkX + rand.nextInt(16) + 8;
            int z22 = chunkY + rand.nextInt(16) + 8;
            int y22 = world.getHeightValue(x22, z22);
            
            Block log;
            byte logMeta;
            int intLogLength;
            
            log = Blocks.log;
            logMeta = (byte)0;
            intLogLength = 3 + rand.nextInt(2);
    
            (new WorldGenLog(log, logMeta, Blocks.leaves, -1, intLogLength)).generate(world, rand, x22, y22, z22);
        }
    }
}
