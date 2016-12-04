package rtg.world.biome.deco;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.feature.WorldGenWaterlily;
import net.minecraft.world.gen.feature.WorldGenerator;

import net.minecraftforge.event.terraingen.TerrainGen;
import static net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.LILYPAD;

import rtg.api.RTGAPI;
import rtg.api.world.RTGWorld;
import rtg.world.biome.realistic.RealisticBiomeBase;
import rtg.world.gen.feature.WorldGenVinesRTG;

/**
 * @author WhichOnesPink
 */
public class DecoJungleLilypadVines extends DecoBase {

    private static final Block volcanoBlock = Block.getBlockFromName(RTGAPI.config().VOLCANO_BLOCK_ID.get());
    private static final Block volcanoMix1Block = Block.getBlockFromName(RTGAPI.config().VOLCANO_MIX1_BLOCK_ID.get());
    private static final Block volcanoMix2Block = Block.getBlockFromName(RTGAPI.config().VOLCANO_MIX2_BLOCK_ID.get());
    private static final Block volcanoMix3Block = Block.getBlockFromName(RTGAPI.config().VOLCANO_MIX3_BLOCK_ID.get());

    public DecoJungleLilypadVines() {

        super();

        this.addDecoTypes(DecoType.LILYPAD, DecoType.VINE);
    }

    /**
     * No config options for this one yet. Just ripped it directly from the old code.
     */
    @Override
    public void generate(RealisticBiomeBase biome, RTGWorld rtgWorld, Random rand, int worldX, int worldZ, float strength, float river, boolean hasPlacedVillageBlocks) {

        if (this.allowed) {

            if (TerrainGen.decorate(rtgWorld.world, rand, new BlockPos(worldX, 0, worldZ), LILYPAD)) {

                WorldGenerator worldgeneratorLilypads = new WorldGenWaterlily();
                WorldGenerator worldgeneratorVines = new WorldGenVinesRTG();
                Block vb;

                for (int b33 = 0; b33 < 5; b33++) {
                    int j6 = worldX + rand.nextInt(16) + 8;
                    int k10 = worldZ + rand.nextInt(16) + 8;
                    int z52 = rtgWorld.world.getHeight(new BlockPos(j6, 0, k10)).getY();

                    for (int h44 = 0; h44 < 8; h44++) {

                        if (z52 > 64) {
                            worldgeneratorLilypads.generate(rtgWorld.world, rand, new BlockPos(j6, z52, k10));
                        }
                    }

                    for (int h44 = 100; h44 > 0; h44--) {

                        vb = rtgWorld.world.getBlockState(new BlockPos(j6, h44, k10)).getBlock();

                        if (vb == volcanoBlock || vb == volcanoMix1Block || vb == volcanoMix2Block || vb == volcanoMix3Block) {
                            return;
                        }

                        worldgeneratorVines.generate(rtgWorld.world, rand, new BlockPos(j6, z52, k10));
                    }
                }
            }
        }
    }
}
