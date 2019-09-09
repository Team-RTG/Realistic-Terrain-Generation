package rtg.world.biome.realistic.novamterram;

import net.minecraft.block.BlockDirt;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.world.biome.Biome;

import rtg.api.util.BlockUtil;


public class RealisticBiomeNTMegaAutumnTaiga extends RealisticBiomeNTBaseForest {

    public RealisticBiomeNTMegaAutumnTaiga(Biome biome) {

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
                BlockUtil.getStateLog(BlockPlanks.EnumType.OAK),
                BlockUtil.getStateLog(BlockPlanks.EnumType.SPRUCE)
            },
            new int[]{1, 2}
        );
    }

    @Override
    protected IBlockState getMixBlock1() {

        return BlockUtil.getStateDirt(BlockDirt.DirtType.PODZOL);
    }

    @Override
    protected IBlockState getMixBlock2() {

        return BlockUtil.getStateDirt(BlockDirt.DirtType.COARSE_DIRT);
    }

    @Override
    protected IBlockState getMixBlock3() {

        return BlockUtil.getStateDirt(BlockDirt.DirtType.PODZOL);
    }
}
