package rtg.util;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;

import rtg.api.RTGAPI;
import rtg.api.config.RTGConfig;
import rtg.api.util.ModPresenceTester;

public class BoulderUtil {

    private RTGConfig rtgConfig = RTGAPI.config();
    private final static ModPresenceTester undergroundBiomesMod = new ModPresenceTester("undergroundbiomes");

    // Create UBColumnCache only if UB is present
    private static UBColumnCache ubColumnCache = undergroundBiomesMod.present() ? new UBColumnCache() : null;

    public IBlockState getBoulderBlock(IBlockState defaultBlock, int worldX, int worldY, int worldZ) {

        if (defaultBlock == Blocks.COBBLESTONE.getDefaultState()) {

            if (undergroundBiomesMod.present() && rtgConfig.ENABLE_UBC_BOULDERS.get()) {

                return ubColumnCache.column(worldX, worldZ).cobblestone(worldY);
            }
            else {

                return defaultBlock;
            }
        }
        else {

            return defaultBlock;
        }
    }
}