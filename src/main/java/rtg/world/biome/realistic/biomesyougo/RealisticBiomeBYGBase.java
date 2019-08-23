package rtg.world.biome.realistic.biomesyougo;

import javax.annotation.Nonnull;

import net.minecraft.block.BlockPlanks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.world.biome.Biome;
import rtg.api.util.BlockUtil;
import rtg.api.util.Logger;
import rtg.api.world.biome.RealisticBiomeBase;
import rtg.api.world.deco.DecoBase;
import rtg.api.world.deco.DecoFallenTree;
import rtg.api.world.deco.helper.DecoHelperRandomSplit;
import rtg.api.world.surface.SurfaceBase;
import rtg.api.world.surface.SurfaceGeneric;


public abstract class RealisticBiomeBYGBase extends RealisticBiomeBase {

    public RealisticBiomeBYGBase(@Nonnull final Biome baseBiome, @Nonnull final RiverType riverType, @Nonnull final BeachType beachType) {

        super(baseBiome, riverType, beachType);
    }

    public RealisticBiomeBYGBase(@Nonnull final Biome baseBiome) {
        this(baseBiome, RiverType.NORMAL, BeachType.NORMAL);
    }

    public RealisticBiomeBYGBase(@Nonnull final Biome baseBiome, @Nonnull final RiverType riverType) {
        this(baseBiome, riverType, BeachType.NORMAL);
    }

    public RealisticBiomeBYGBase(@Nonnull final Biome baseBiome, @Nonnull final BeachType beachType) {
        this(baseBiome, RiverType.NORMAL, beachType);
    }

    @Override
    public SurfaceBase initSurface() {
        return new SurfaceGeneric(getConfig(), this.baseBiome().topBlock, this.baseBiome().fillerBlock);
    }

    @Override
    public void initDecos() {

    }

    public void fallenTrees(IBlockState[] fallenTreeLogs, int[] fallenTreeChances) {

        if (fallenTreeLogs.length < 1 || (fallenTreeLogs.length != fallenTreeChances.length)) {
            return;
        }

        DecoBase[] fallenTreeDecos = new DecoBase[fallenTreeLogs.length];

        for (int i = 0; i < fallenTreeLogs.length; i++) {
            fallenTreeDecos[i] = new DecoFallenTree()
                    .setLogBlock(fallenTreeLogs[i])
                    .setLogConditionChance(8)
                    .setMaxSize(4);
        }

        this.addDeco(new DecoHelperRandomSplit()
                .setDecos(fallenTreeDecos)
                .setChances(fallenTreeChances));
    }
}
