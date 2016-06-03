package teamrtg.rtg.api.tools.feature;

import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import teamrtg.rtg.api.module.Mods;
import teamrtg.rtg.api.util.math.RandomUtil;

import java.util.Random;

import static net.minecraft.init.Blocks.*;

public class WorldGenBlob extends WorldGenerator {
    private static final String __OBFID = "CL_00000402";
    private Block blobBlock;
    private byte blobMeta;
    private int blobSize;
    private boolean booShouldGenerate;

    public WorldGenBlob(Block b, byte m, int s, Random rand) {
        this(b, s, rand);
        this.blobMeta = m;
    }

    public WorldGenBlob(Block b, int s, Random rand) {
        super(false);
        this.blobBlock = b;
        this.blobMeta = 0;
        this.blobSize = s;
        booShouldGenerate = true;

        if (blobBlock == MOSSY_COBBLESTONE || blobBlock == COBBLESTONE) {
            if (!Mods.RTG.config.ENABLE_COUBLESTONE_BOULDERS.get()) {
                booShouldGenerate = false;
            } else {
                if (!shouldGenerateCobblestoneBoulder(rand)) {
                    booShouldGenerate = false;
                }
            }
        }
    }

    public static boolean shouldGenerateCobblestoneBoulder(Random rand) {
        int chance = Mods.RTG.config.COBBLESTONE_BOULDER_CHANCE.get();
        chance = (chance < 1) ? 1 : ((chance > 100) ? 100 : chance);

        int random = RandomUtil.getRandomInt(rand, 1, chance);

        boolean booGenerate = (random == 1);

        //Logger.info("Random = %d; Generate? = %b", random, booGenerate);

        return booGenerate;
    }

    public boolean generate(World world, Random rand, BlockPos blockPos) {
        return this.generate(world, rand, blockPos.getX(), blockPos.getY(), blockPos.getZ());
    }

    public boolean generate(World world, Random rand, int x, int y, int z) {
        if (!booShouldGenerate) {
            return false;
        }
        while (true) {
            if (y > 3) {
                label63:
                {
                    if (!world.isAirBlock(new BlockPos(x, y - 1, z))) {
                        Block block = world.getBlockState(new BlockPos(x, y - 1, z)).getBlock();

                        if (block == GRASS || block == DIRT || block == STONE || block == GRAVEL || block == SAND) {
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
                                world.setBlockState(new BlockPos(l1, j2, i2), this.blobBlock.getStateFromMeta(this.blobMeta), 2);
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

    public void generate(World world, Random rand, int x, int y, int z, boolean honourConfig) {
        if (honourConfig) {
            booShouldGenerate = true;

            if (!Mods.RTG.config.ENABLE_COUBLESTONE_BOULDERS.get()) {
                booShouldGenerate = false;
            } else {
                if (!shouldGenerateCobblestoneBoulder(rand)) {
                    booShouldGenerate = false;
                }
            }
        }

        generate(world, rand, x, y, z);
    }
}