package rtg.world.gen.feature;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import rtg.RTG;
import rtg.api.util.BoulderUtil;
import rtg.api.util.RandomUtil;
import rtg.config.RTGConfig;


public class WorldGenBlob extends WorldGenerator {

    protected boolean water;
    protected BoulderUtil boulderUtil;
    private IBlockState blobBlock;
    private int blobSize;
    private boolean booShouldGenerate;
    private RTGConfig rtgConfig = RTG.config();

    public WorldGenBlob(IBlockState b, int s, Random rand) {

        super(false);
        this.blobBlock = b;
        this.blobSize = s;
        booShouldGenerate = true;
        this.water = true;
        this.boulderUtil = new BoulderUtil();

        if (blobBlock == Blocks.MOSSY_COBBLESTONE.getDefaultState() || blobBlock == Blocks.COBBLESTONE.getDefaultState()) {
            if (!rtgConfig.ENABLE_COBBLESTONE_BOULDERS.get()) {
                booShouldGenerate = false;
            }
            else {
                if (!shouldGenerateCobblestoneBoulder(rand)) {
                    booShouldGenerate = false;
                }
            }
        }
    }

    public WorldGenBlob(IBlockState b, int s, Random rand, boolean water) {

        this(b, s, rand);
        this.water = water;
    }

    public boolean shouldGenerateCobblestoneBoulder(Random rand) {

        int chance = rtgConfig.COBBLESTONE_BOULDER_CHANCE.get();
        chance = (chance < 1) ? 1 : ((chance > 100) ? 100 : chance);

        int random = RandomUtil.getRandomInt(rand, 1, chance);

        boolean booGenerate = (random == 1) ? true : false;

        //Logger.info("Random = %d; Generate? = %b", random, booGenerate);

        return booGenerate;
    }

    public void generate(World world, Random rand, int x, int y, int z, boolean honourConfig) {

        if (honourConfig) {
            booShouldGenerate = true;

            if (!rtgConfig.ENABLE_COBBLESTONE_BOULDERS.get()) {
                booShouldGenerate = false;
            }
            else {
                if (!shouldGenerateCobblestoneBoulder(rand)) {
                    booShouldGenerate = false;
                }
            }
        }

        generate(world, rand, new BlockPos(x, y, z));
    }

    @Override
    public boolean generate(World world, Random rand, BlockPos pos) {

        if (!booShouldGenerate) {
            return false;
        }

        int x = pos.getX();
        int y = pos.getY();
        int z = pos.getZ();

        IBlockState boulderBlock = this.boulderUtil.getBoulderBlock(this.blobBlock, x, y, z);

        while (true) {
            if (y > 3) {
                label63:
                {
                    if (!world.isAirBlock(new BlockPos(x, y - 1, z))) {
                        IBlockState block = world.getBlockState(new BlockPos(x, y - 1, z));

                        // Water check.
                        if (!this.water) {

                            if (block.getMaterial() == Material.WATER) {
                                return false;
                            }
                            if (world.getBlockState(new BlockPos(x, y - 1, z - 1)).getMaterial() == Material.WATER) {
                                return false;
                            }
                            if (world.getBlockState(new BlockPos(x, y - 1, z + 1)).getMaterial() == Material.WATER) {
                                return false;
                            }
                            if (world.getBlockState(new BlockPos(x - 1, y - 1, z)).getMaterial() == Material.WATER) {
                                return false;
                            }
                            if (world.getBlockState(new BlockPos(x - 1, y - 1, z - 1)).getMaterial() == Material.WATER) {
                                return false;
                            }
                            if (world.getBlockState(new BlockPos(x - 1, y - 1, z + 1)).getMaterial() == Material.WATER) {
                                return false;
                            }
                            if (world.getBlockState(new BlockPos(x + 1, y - 1, z)).getMaterial() == Material.WATER) {
                                return false;
                            }
                            if (world.getBlockState(new BlockPos(x + 1, y - 1, z - 1)).getMaterial() == Material.WATER) {
                                return false;
                            }
                            if (world.getBlockState(new BlockPos(x + 1, y - 1, z + 1)).getMaterial() == Material.WATER) {
                                return false;
                            }
                        }

                        if (block.getBlock() == Blocks.GRASS
                            || block.getBlock() == Blocks.DIRT
                            || block.getBlock() == Blocks.STONE
                            || block.getBlock() == Blocks.GRAVEL
                            || block.getBlock() == Blocks.SAND) {
                            break label63;
                        }
                    }

                    --y;
                    continue;
                }
            }

            if (y <= 3) {
                return false;
            }

            int k2 = this.blobSize;

            for (int l = 0; k2 >= 0 && l < 3; ++l) {
                int i1 = k2 + rand.nextInt(2);
                int j1 = k2 + rand.nextInt(2);
                int k1 = k2 + rand.nextInt(2);
                float f = (float) (i1 + j1 + k1) * 0.333F + 0.5F;

                for (int l1 = x - i1; l1 <= x + i1; ++l1) {
                    for (int i2 = z - k1; i2 <= z + k1; ++i2) {
                        for (int j2 = y - j1; j2 <= y + j1; ++j2) {
                            float f1 = (float) (l1 - x);
                            float f2 = (float) (i2 - z);
                            float f3 = (float) (j2 - y);

                            if (f1 * f1 + f2 * f2 + f3 * f3 <= f * f) {
                                world.setBlockState(new BlockPos(l1, j2, i2), boulderBlock, 2);
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
}