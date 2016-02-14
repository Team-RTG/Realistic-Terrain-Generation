package rtg.world.gen.feature;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import rtg.config.rtg.ConfigRTG;
import rtg.util.Logger;
import rtg.util.RandomUtil;

public class WorldGenBlob extends WorldGenerator
{
    private Block blobBlock;
    private byte blobMeta;
    private int blobSize;
    private boolean booShouldGenerate;
    private static final String __OBFID = "CL_00000402";

    public WorldGenBlob(Block b, int s, Random rand)
    {
        super(false);
        this.blobBlock = b;
        this.blobMeta = 0;
        this.blobSize = s;
        booShouldGenerate = true;
        
        if (blobBlock == Blocks.mossy_cobblestone || blobBlock == Blocks.cobblestone) {
            if (!ConfigRTG.enableCobblestoneBoulders) {
                booShouldGenerate = false;
            }
            else {
                if (!shouldGenerateCobblestoneBoulder(rand)) {
                    booShouldGenerate = false;
                }
            }
        }
    }
    
    public WorldGenBlob(Block b, byte m, int s, Random rand)
    {
        this(b, s, rand);
        this.blobMeta = m;
    }

    public void generate(World world, Random rand, BlockPos blockPos, boolean honourConfig)
    {
        if (honourConfig) {
            booShouldGenerate = true;
            
            if (!ConfigRTG.enableCobblestoneBoulders) {
                booShouldGenerate = false;
            }
            else {
                if (!shouldGenerateCobblestoneBoulder(rand)) {
                    booShouldGenerate = false;
                }
            }
        }
        
        generate(world, rand, blockPos);
    }
    
    public boolean generate(World world, Random rand, BlockPos blockPos)
    {
        if (!booShouldGenerate) {
            return false;
        }

        while (true)
        {
            if (blockPos.getY() > 3)
            {
                label63:
                {
                    if (!world.isAirBlock(blockPos.down()))
                    {
                        Block block = world.getBlockState(blockPos.down()).getBlock();

                        if (block == Blocks.grass || block == Blocks.dirt || block == Blocks.stone || block == Blocks.gravel || block == Blocks.sand)
                        {
                            break label63;
                        }
                    }

                    blockPos = blockPos.down();
                    continue;
                }
            }

            if (blockPos.getY() <= 3)
            {
                return false;
            }

            int k2 = this.blobSize;

            for (int l = 0; k2 >= 0 && l < 3; ++l)
            {
                int i1 = k2 + rand.nextInt(2);
                int j1 = k2 + rand.nextInt(2);
                int k1 = k2 + rand.nextInt(2);
                float f = (float)(i1 + j1 + k1) * 0.333F + 0.5F;

                for (int l1 = blockPos.getX() - i1; l1 <= blockPos.getX() + i1; ++l1)
                {
                    for (int i2 = blockPos.getZ() - k1; i2 <= blockPos.getZ() + k1; ++i2)
                    {
                        for (int j2 = blockPos.getY() - j1; j2 <= blockPos.getY() + j1; ++j2)
                        {
                            float f1 = (float)(l1 - blockPos.getX());
                            float f2 = (float)(i2 - blockPos.getZ());
                            float f3 = (float)(j2 - blockPos.getY());

                            if (f1 * f1 + f2 * f2 + f3 * f3 <= f * f)
                            {
                                world.setBlockState(new BlockPos(l1, j2, i2), this.blobBlock.getStateFromMeta(this.blobMeta), 2);
                            }
                        }
                    }
                }

                int x = -(k2 + 1) + rand.nextInt(2 + k2 * 2);
                int z = -(k2 + 1) + rand.nextInt(2 + k2 * 2);
                int y = 0 - rand.nextInt(2);
                blockPos = blockPos.add(x,y,z);
            }

            return true;
        }
    }
    
    public static boolean shouldGenerateCobblestoneBoulder(Random rand)
    {
    	int chance = ConfigRTG.cobblestoneBoulderChance;
    	chance = (chance < 1) ? 1 : ((chance > 100) ? 100 : chance);
    	
    	int random = RandomUtil.getRandomInt(rand, 1, chance);
    	
    	boolean booGenerate = (random == 1) ? true : false;
    	
    	//Logger.info("Random = %d; Generate? = %b", random, booGenerate);
    	
    	return booGenerate;
    }
}