package enhancedbiomes.world.biome.snow;

import java.util.Random;

import enhancedbiomes.helpers.TreeGen;
import enhancedbiomes.world.biome.base.BiomeGenSnowBase;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenShrub;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.minecraft.world.gen.feature.WorldGenerator;

public class BiomeGenSnowyRanges extends BiomeGenSnowBase
{
    private WorldGenerator theWorldGenerator;

    public BiomeGenSnowyRanges(int par1)
    {
        super(par1);
        this.theWorldGenerator = new WorldGenMinable(Blocks.monster_egg, 8);
        this.topBlock = Blocks.snow;
        this.fillerBlock = Blocks.stone;
        this.theBiomeDecorator.treesPerChunk = 8;
        this.theBiomeDecorator.grassPerChunk = 16;
    }

    public void decorate(World par1World, Random par2Random, int par3, int par4)
    {
        super.decorate(par1World, par2Random, par3, par4);
        int var5 = 3 + par2Random.nextInt(6);
        int var6;
        int var7;
        int var8;

        for (var5 = 0; var5 < 7; ++var5)
        {
            var6 = par3 + par2Random.nextInt(16);
            var7 = par2Random.nextInt(64);
            var8 = par4 + par2Random.nextInt(16);
            this.theWorldGenerator.generate(par1World, par2Random, var6, var7, var8);
        }
    }
    
    /**
     * Gets a WorldGen appropriate for this biome.
     */
    @Override
    public WorldGenAbstractTree func_150567_a(Random par1Random)
    {
        return par1Random.nextInt(4) == 0 ? TreeGen.pine(par1Random) : 
        		par1Random.nextInt(3) == 0 ? TreeGen.fir(par1Random) : 
        		TreeGen.aspen(par1Random);
    }
    
    /**
     * Gets a WorldGen appropriate for this biome.
     */
    public WorldGenerator getRandomWorldGenForGrass(Random par1Random)
    {
        return new WorldGenTallGrass(Blocks.tallgrass, 2);
    }
    
    public void genTerrainBlocks(World world, Random rand, Block[] blocks, byte[] metas, int x, int z, double stoneNoise)
	{
        this.topBlock = Blocks.snow;
        this.fillerBlock = Blocks.stone;

        int posX = x & 15;
        int posZ = z & 15;
        int k1 = blocks.length / 256;
        
        int topPos = getTopBlock(posX, posZ, k1, blocks);
        
        if(topPos < 100 + rand.nextInt(5)) {
        	this.topBlock = Blocks.grass;
            this.fillerBlock = Blocks.dirt;
        }
        
        this.genBiomeTerrain(world, rand, blocks, metas, x, z, stoneNoise);
	}

	public int getTopBlock(int posX, int posZ, int k1, Block[] blocks) {
		for(int a = 255; a >= 0; a--) {
			int i2 = (posZ * 16 + posX) * k1 + a;
			
			if(blocks[i2] != null && blocks[i2].getMaterial().isSolid()) return a;
		}
		return -1;
	}
}
