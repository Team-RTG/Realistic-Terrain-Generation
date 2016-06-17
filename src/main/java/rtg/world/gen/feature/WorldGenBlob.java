package rtg.world.gen.feature;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import rtg.config.rtg.ConfigRTG;
import rtg.util.BoulderUtil;
import rtg.util.RandomUtil;

public class WorldGenBlob extends WorldGenerator
{
    private Block blobBlock;
    private byte blobMeta;
    private int blobSize;
    private boolean booShouldGenerate;
    private static final String __OBFID = "CL_00000402";
    protected boolean water;
    protected BoulderUtil boulderUtil;

    public WorldGenBlob(Block b, int s, Random rand)
    {
        super(false);
        this.blobBlock = b;
        this.blobMeta = 0;
        this.blobSize = s;
        booShouldGenerate = true;
        this.water = true;
        this.boulderUtil = new BoulderUtil();
        
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
    
    public WorldGenBlob(Block b, byte m, int s, Random rand, boolean water)
    {
        this(b, m, s, rand);
        this.blobMeta = m;
        this.water = water;
    }

    public void generate(World world, Random rand, int x, int y, int z, boolean honourConfig)
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

        generate(world, rand, x, y, z);
    }
    
    public boolean generate(World world, Random rand, int x, int y, int z)
    {
        if (!booShouldGenerate) {
            return false;
        }

        Block boulderBlock = this.boulderUtil.getBoulderBlock(this.blobBlock, x, y, z);
        byte boulderMeta = this.boulderUtil.getBoulderMeta(this.blobBlock, this.blobMeta, x, y, z);
        
        while (true)
        {
            if (y > 3)
            {
                label63:
                {
                    if (!world.isAirBlock(x, y - 1, z))
                    {
                        Block block = world.getBlock(x, y - 1, z);

                        // Water check.
                        if (!this.water) {

                    		if (block.getMaterial() == Material.water) { return false; }
                    		if (world.getBlock(x, y - 1, z - 1).getMaterial() == Material.water) { return false; }
                    		if (world.getBlock(x, y - 1, z + 1).getMaterial() == Material.water) { return false; }
                    		if (world.getBlock(x - 1, y - 1, z).getMaterial() == Material.water) { return false; }
                    		if (world.getBlock(x - 1, y - 1, z - 1).getMaterial() == Material.water) { return false; }
                    		if (world.getBlock(x - 1, y - 1, z + 1).getMaterial() == Material.water) { return false; }
                    		if (world.getBlock(x + 1, y - 1, z).getMaterial() == Material.water) { return false; }
                    		if (world.getBlock(x + 1, y - 1, z - 1).getMaterial() == Material.water) { return false; }
                    		if (world.getBlock(x + 1, y - 1, z + 1).getMaterial() == Material.water) { return false; }
                        }
                        
                        if (block == Blocks.grass || block == Blocks.dirt || block == Blocks.stone || block == Blocks.gravel || block == Blocks.sand)
                        {
                            break label63;
                        }
                    }

                    --y;
                    continue;
                }
            }

            if (y <= 3)
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

                for (int l1 = x - i1; l1 <= x + i1; ++l1)
                {
                    for (int i2 = z - k1; i2 <= z + k1; ++i2)
                    {
                        for (int j2 = y - j1; j2 <= y + j1; ++j2)
                        {
                            float f1 = (float)(l1 - x);
                            float f2 = (float)(i2 - z);
                            float f3 = (float)(j2 - y);

                            if (f1 * f1 + f2 * f2 + f3 * f3 <= f * f)
                            {
                                world.setBlock(l1, j2, i2, boulderBlock, boulderMeta, 2);
                            }
                        }
                    }
                }

                x += -(k2 + 1) + rand.nextInt(2 + k2 * 2);
                z += -(k2 + 1) + rand.nextInt(2 + k2 * 2);
                y += 0 - rand.nextInt(2);
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