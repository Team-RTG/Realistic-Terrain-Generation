package rtg.world.biome.deco;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenWaterlily;
import net.minecraft.world.gen.feature.WorldGenerator;

import net.minecraftforge.event.terraingen.TerrainGen;
import static net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.LILYPAD;

import rtg.config.rtg.ConfigRTG;
import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.biome.realistic.RealisticBiomeBase;
import rtg.world.gen.feature.WorldGenVinesRTG;

/**
 * @author WhichOnesPink
 */
public class DecoJungleLilypadVines extends DecoBase {

    private static final Block volcanoBlock = Block.getBlockFromName(ConfigRTG.volcanoBlockId);
    private static final Block volcanoMix1Block = Block.getBlockFromName(ConfigRTG.volcanoMix1BlockId);
    private static final Block volcanoMix2Block = Block.getBlockFromName(ConfigRTG.volcanoMix2BlockId);
    private static final Block volcanoMix3Block = Block.getBlockFromName(ConfigRTG.volcanoMix3BlockId);

    public DecoJungleLilypadVines() {

        super();

        this.addDecoTypes(DecoType.LILYPAD, DecoType.VINE);
    }

    /**
     * No config options for this one yet. Just ripped it directly from the old code.
     */
    @Override
    public void generate(RealisticBiomeBase biome, World world, Random rand, int blockX, int blockY, OpenSimplexNoise simplex, CellNoise cell, float strength, float river, boolean hasPlacedVillageBlocks) {

        if (this.allowed) {

            if (TerrainGen.decorate(world, rand, new BlockPos(blockX, 0, blockY), LILYPAD)) {

                WorldGenerator worldgeneratorLilypads = new WorldGenWaterlily();
                WorldGenerator worldgeneratorVines = new WorldGenVinesRTG();
                Block vb;

                for (int b33 = 0; b33 < 5; b33++) {
                    int j6 = blockX + rand.nextInt(16) + 8;
                    int k10 = blockY + rand.nextInt(16) + 8;
                    int z52 = world.getHeight(new BlockPos(j6, 0, k10)).getY();

                    for (int h44 = 0; h44 < 8; h44++) {

                        if (z52 > 64) {
                            worldgeneratorLilypads.generate(world, rand, new BlockPos(j6, z52, k10));
                        }
                    }

                    for (int h44 = 100; h44 > 0; h44--) {

                        vb = world.getBlockState(new BlockPos(j6, h44, k10)).getBlock();

                        if (vb == volcanoBlock || vb == volcanoMix1Block || vb == volcanoMix2Block || vb == volcanoMix3Block) {
                            return;
                        }

                        worldgeneratorVines.generate(world, rand, new BlockPos(j6, z52, k10));
                    }
                }
            }
        }
    }
}
