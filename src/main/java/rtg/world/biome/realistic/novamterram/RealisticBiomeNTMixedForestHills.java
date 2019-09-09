package rtg.world.biome.realistic.novamterram;

import net.minecraft.block.BlockPlanks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.world.biome.Biome;

import rtg.api.util.BlockUtil;


public class RealisticBiomeNTMixedForestHills extends RealisticBiomeNTBaseHills {

    public RealisticBiomeNTMixedForestHills(Biome biome) {

        super(biome);
    }

    @Override
    public void initConfig() {

        super.initConfig();
        this.getConfig().addProperty(this.getConfig().ALLOW_LOGS).set(true);
        this.getConfig().addProperty(this.getConfig().FALLEN_LOG_DENSITY_MULTIPLIER);
    }

    @Override
    public void initDecos() {

        super.initDecos();
        fallenTrees(
            new IBlockState[]{
                BlockUtil.getStateLog(BlockPlanks.EnumType.SPRUCE),
                BlockUtil.getStateLog(BlockPlanks.EnumType.BIRCH),
                BlockUtil.getStateLog(BlockPlanks.EnumType.OAK)
            },
            new int[]{4, 1, 1}
        );
    }
}
