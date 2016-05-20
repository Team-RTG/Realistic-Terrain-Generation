package rtg.world.gen.feature.tree.vanilla;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenMegaJungle;

public class WorldGenMegaJungleRTG extends WorldGenMegaJungle
{

	public int minTrunkHeight;
	public int maxTrunkHeight;
	public int minCrownHeight;
	public int maxCrownHeight;
	
    public WorldGenMegaJungleRTG(boolean doBlockNotify, int minTrunkHeight, int maxTrunkHeight, int minCrownHeight, int maxCrownHeight, int logMeta, int leavesMeta)
    {
        this(doBlockNotify, minTrunkHeight, minCrownHeight, logMeta, leavesMeta);
        
    	this.minTrunkHeight = minTrunkHeight;
    	this.maxTrunkHeight = maxTrunkHeight;
    	this.minCrownHeight = minCrownHeight;
    	this.maxCrownHeight = maxCrownHeight;
    }
    
    public WorldGenMegaJungleRTG(boolean doBlockNotify, int trunkHeight, int crownHeight, int logMeta, int leavesMeta)
    {
        super(doBlockNotify, trunkHeight, crownHeight, logMeta, leavesMeta);
        
        this.minTrunkHeight = 0;
        this.maxTrunkHeight = 0;
        this.minCrownHeight = 0;
        this.maxCrownHeight = 0;
    }

    @Override
    public boolean generate(World world, Random rand, int worldX, int worldY, int worldZ)
    {
    	int trunkHeight = 0;
    	if (this.minTrunkHeight > 0 && this.maxTrunkHeight > 0) {
    		trunkHeight = this.minTrunkHeight + rand.nextInt(this.maxTrunkHeight - this.minTrunkHeight + 1);
    	}
    	
    	int crownHeight = 0;
    	if (this.minCrownHeight > 0 && this.maxCrownHeight > 0) {
    		crownHeight = this.minCrownHeight + rand.nextInt(this.maxCrownHeight - this.minCrownHeight + 1);
    	}
    	
        int treeHeight = trunkHeight + crownHeight;

        if (!this.func_150537_a(world, rand, worldX, worldY, worldZ, treeHeight))
        {
            return false;
        }
        else
        {

            this.func_150543_c(world, worldX, worldZ, worldY + treeHeight, 2, rand);

            for (int i1 = worldY + treeHeight - 2 - rand.nextInt(4); i1 > worldY + treeHeight / 2; i1 -= 2 + rand.nextInt(4))
            {
                float f = rand.nextFloat() * (float)Math.PI * 2.0F;
                int j1 = worldX + (int)(0.5F + MathHelper.cos(f) * 4.0F);
                int k1 = worldZ + (int)(0.5F + MathHelper.sin(f) * 4.0F);
                int l1;

                for (l1 = 0; l1 < 5; ++l1)
                {
                    j1 = worldX + (int)(1.5F + MathHelper.cos(f) * (float)l1);
                    k1 = worldZ + (int)(1.5F + MathHelper.sin(f) * (float)l1);
                    this.setBlockAndNotifyAdequately(world, j1, i1 - 3 + l1 / 2, k1, Blocks.log, this.woodMetadata);
                }

                l1 = 1 + rand.nextInt(2);
                int i2 = i1;

                for (int j2 = i1 - l1; j2 <= i2; ++j2)
                {
                    int k2 = j2 - i2;
                    this.func_150534_b(world, j1, j2, k1, 1 - k2, rand);
                }
            }

            for (int l2 = 0; l2 < treeHeight; ++l2)
            {
                Block block = world.getBlock(worldX, worldY + l2, worldZ);

                if (block.isAir(world, worldX, worldY + l2, worldZ) || block.isLeaves(world, worldX, worldY + l2, worldZ))
                {
                    this.setBlockAndNotifyAdequately(world, worldX, worldY + l2, worldZ, Blocks.log, this.woodMetadata);

                    if (l2 > 0)
                    {
                        if (rand.nextInt(3) > 0 && world.isAirBlock(worldX - 1, worldY + l2, worldZ))
                        {
                            this.setBlockAndNotifyAdequately(world, worldX - 1, worldY + l2, worldZ, Blocks.vine, 8);
                        }

                        if (rand.nextInt(3) > 0 && world.isAirBlock(worldX, worldY + l2, worldZ - 1))
                        {
                            this.setBlockAndNotifyAdequately(world, worldX, worldY + l2, worldZ - 1, Blocks.vine, 1);
                        }
                    }
                }

                if (l2 < treeHeight - 1)
                {
                    block = world.getBlock(worldX + 1, worldY + l2, worldZ);

                    if (block.isAir(world, worldX + 1, worldY + l2, worldZ) || block.isLeaves(world, worldX + 1, worldY + l2, worldZ))
                    {
                        this.setBlockAndNotifyAdequately(world, worldX + 1, worldY + l2, worldZ, Blocks.log, this.woodMetadata);

                        if (l2 > 0)
                        {
                            if (rand.nextInt(3) > 0 && world.isAirBlock(worldX + 2, worldY + l2, worldZ))
                            {
                                this.setBlockAndNotifyAdequately(world, worldX + 2, worldY + l2, worldZ, Blocks.vine, 2);
                            }

                            if (rand.nextInt(3) > 0 && world.isAirBlock(worldX + 1, worldY + l2, worldZ - 1))
                            {
                                this.setBlockAndNotifyAdequately(world, worldX + 1, worldY + l2, worldZ - 1, Blocks.vine, 1);
                            }
                        }
                    }

                    block = world.getBlock(worldX + 1, worldY + l2, worldZ + 1);

                    if (block.isAir(world, worldX + 1, worldY + l2, worldZ + 1) || block.isLeaves(world, worldX + 1, worldY + l2, worldZ + 1))
                    {
                        this.setBlockAndNotifyAdequately(world, worldX + 1, worldY + l2, worldZ + 1, Blocks.log, this.woodMetadata);

                        if (l2 > 0)
                        {
                            if (rand.nextInt(3) > 0 && world.isAirBlock(worldX + 2, worldY + l2, worldZ + 1))
                            {
                                this.setBlockAndNotifyAdequately(world, worldX + 2, worldY + l2, worldZ + 1, Blocks.vine, 2);
                            }

                            if (rand.nextInt(3) > 0 && world.isAirBlock(worldX + 1, worldY + l2, worldZ + 2))
                            {
                                this.setBlockAndNotifyAdequately(world, worldX + 1, worldY + l2, worldZ + 2, Blocks.vine, 4);
                            }
                        }
                    }

                    block = world.getBlock(worldX, worldY + l2, worldZ + 1);

                    if (block.isAir(world, worldX, worldY + l2, worldZ + 1) || block.isLeaves(world, worldX, worldY + l2, worldZ + 1))
                    {
                        this.setBlockAndNotifyAdequately(world, worldX, worldY + l2, worldZ + 1, Blocks.log, this.woodMetadata);

                        if (l2 > 0)
                        {
                            if (rand.nextInt(3) > 0 && world.isAirBlock(worldX - 1, worldY + l2, worldZ + 1))
                            {
                                this.setBlockAndNotifyAdequately(world, worldX - 1, worldY + l2, worldZ + 1, Blocks.vine, 8);
                            }

                            if (rand.nextInt(3) > 0 && world.isAirBlock(worldX, worldY + l2, worldZ + 2))
                            {
                                this.setBlockAndNotifyAdequately(world, worldX, worldY + l2, worldZ + 2, Blocks.vine, 4);
                            }
                        }
                    }
                }
            }

            return true;
        }
    }

    private void func_150543_c(World p_150543_1_, int p_150543_2_, int p_150543_3_, int p_150543_4_, int p_150543_5_, Random p_150543_6_)
    {
        byte b0 = 2;

        for (int i1 = p_150543_4_ - b0; i1 <= p_150543_4_; ++i1)
        {
            int j1 = i1 - p_150543_4_;
            this.func_150535_a(p_150543_1_, p_150543_2_, i1, p_150543_3_, p_150543_5_ + 1 - j1, p_150543_6_);
        }
    }
}