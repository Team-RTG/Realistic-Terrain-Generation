package highlands.biome;

import highlands.worldgen.WorldGenHighlandsGroundcover;
import highlands.worldgen.WorldGenHighlandsShrub;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenerator;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BiomeGenOutback extends BiomeGenBaseHighlands
{
	private static final Height biomeHeight = new Height(0.1F, 0.3F);

	public BiomeGenOutback(int par1){
		super(par1);
		
		int trees = 1;
	    int grass = 10;
	    int flowers = 0;
	    this.theBiomeDecorator = new BiomeDecoratorHighlands(this, trees, grass, flowers);
	    
	    this.theBiomeDecorator.deadBushPerChunk = 40;
	    this.theBiomeDecorator.cactiPerChunk = 4;
	    this.setHeight(biomeHeight);
        this.temperature = 1.6F;
        this.rainfall = 0.1F;
        
        this.topBlock = Blocks.sand;
        this.field_150604_aj = 1; // metadata for top block
        this.fillerBlock = Blocks.sand;
        
        this.spawnableCreatureList.clear();
        this.spawnableCreatureList.add(new SpawnListEntry(EntityChicken.class, 10, 4, 4));
        
    }

	

    /**
     * Gets a WorldGen appropriate for this biome.
     */
    public WorldGenerator getRandomWorldGenForGrass(Random par1Random)
    {
        return new WorldGenHighlandsGroundcover(Blocks.tallgrass, 1, 2);
    }
    
    @Override
    public WorldGenAbstractTree func_150567_a(Random par1Random)
    {
        return (WorldGenAbstractTree)new WorldGenHighlandsShrub(0, 0);
    }

    @Override
	public void decorate(World world, Random random, int x, int z) {
    	BiomeGenBaseHighlands biome = this;
		this.theBiomeDecorator.decorateChunk(world, random, biome, x, z);
    	((BiomeDecoratorHighlands)this.theBiomeDecorator).genOreHighlands(world, random, x, z, 2, this.theBiomeDecorator.goldGen, 0, 32);
        ((BiomeDecoratorHighlands)this.theBiomeDecorator).genOreHighlands(world, random, x, z, 10, ((BiomeDecoratorHighlands)this.theBiomeDecorator).HLsand, 0, 64);
        
        //the code for the random half grass
        /*
    	for(int i = 0; i < 16; i++){
    		for(int j = 0; j < 16; j++){
    			if(par1World.getBiomeGenForCoords(par3+i, par4+j) == HighlandsMain.outback){
	    			int topY = 128;
	    			int var11;
	    	        for (boolean var6 = false; ((var11 = par1World.getBlockId(par3+i, topY, par4+j)) == 0 || var11 == Block.leaves.blockID) && topY > 0; --topY)
	    	        {
	    	            ;
	    	        }
	    			//System.out.println("the top block is id" + par1World.getBlockId(par3+i, topY, par4+j));
	    			if(par1World.getBlockId(par3+i, topY, par4+j) == 0)topY--;
	    			if(par1World.getBlockId(par3+i, topY, par4+j) == Block.sand.blockID && par2Random.nextInt(2) == 0){
	    				par1World.setBlock(par3+i, topY, par4+j, Block.grass.blockID);
	    				topY++;
	    				if(par2Random.nextInt(3) != 0 && par1World.getBlockId(par3+i, topY, par4+j) == 0){
	    					par1World.setBlockAndMetadata(par3+i, topY, par4+j, Block.tallGrass.blockID, 1);
	    				}
	    			}
	    		}
    		}
    	}
    	*/
    	
    	
    }
    
    //TODO- could break all the things- has no @Override
    public int getBiomeFoliageColor(){
    	return 0xA6C968;
    }
    
    @SideOnly(Side.CLIENT)
    @Override
    public int getBiomeGrassColor(int x, int y, int z)
    {
        return getModdedBiomeGrassColor(0xEEE980);
    }
    
    public void genTerrainBlocks(World world, Random random, Block[] blocksArray, byte[] blocksMetadataArray, int p_150560_5_, int p_150560_6_, double p_150560_7_)
    {
        boolean flag = true;
        Block block = this.topBlock;
        byte b0 = (byte)(this.field_150604_aj & 255);
        Block block1 = this.fillerBlock;
        int k = -1;
        int l = (int)(p_150560_7_ / 3.0D + 3.0D + random.nextDouble() * 0.25D);
        int i1 = p_150560_5_ & 15;
        int j1 = p_150560_6_ & 15;
        int k1 = blocksArray.length / 256;

        for (int l1 = 255; l1 >= 0; --l1)
        {
            int i2 = (j1 * 16 + i1) * k1 + l1;

            if (l1 <= 0 + random.nextInt(5))
            {
                blocksArray[i2] = Blocks.bedrock;
            }
            else
            {
                Block block2 = blocksArray[i2];

                if (block2 != null && block2.getMaterial() != Material.air)
                {
                    if (block2 == Blocks.stone)
                    {
                        if (k == -1)
                        {
                            if (l <= 0)
                            {
                                block = null;
                                b0 = 0;
                                block1 = Blocks.stone;
                            }
                            else if (l1 >= 59 && l1 <= 64)
                            {
                                block = this.topBlock;
                                b0 = (byte)(this.field_150604_aj & 255);
                                block1 = this.fillerBlock;
                            }

                            // oceans/rivers
                            if (l1 < 63 && (block == null || block.getMaterial() == Material.air))
                            {
                                block = Blocks.water;
                                b0 = 0;
                            }

                            k = l;

                            if (l1 >= 62)
                            {
                                blocksArray[i2] = block;
                                blocksMetadataArray[i2] = b0;
                            }
                            else if (l1 < 56 - l)
                            {
                                block = null;
                                block1 = Blocks.stone;
                                blocksArray[i2] = Blocks.gravel;
                            }
                            else
                            {
                                blocksArray[i2] = block1;
                                // make fill metadata the same as topBlock
                                b0 = (byte)(this.field_150604_aj & 255);
                                blocksMetadataArray[i2] = b0;
                            }
                        }
                        else if (k > 0)
                        {
                            --k;
                            blocksArray[i2] = block1;
                            // make fill metadata the same as topBlock
                            b0 = (byte)(this.field_150604_aj & 255);
                            blocksMetadataArray[i2] = b0;
                        }
                    }
                }
                else
                {
                    k = -1;
                }
            }
        }
    }
}







