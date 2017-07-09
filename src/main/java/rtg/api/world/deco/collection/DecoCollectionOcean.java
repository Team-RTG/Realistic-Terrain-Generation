package rtg.api.world.deco.collection;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;

import rtg.api.RTGAPI;
import rtg.api.config.BiomeConfig;
import rtg.api.util.BlockUtil;
import rtg.api.world.deco.*;
import rtg.api.world.deco.helper.DecoHelperRandomSplit;


/**
 * @author WhichOnesPink
 */
public class DecoCollectionOcean extends DecoCollectionBase {

    public DecoCollectionOcean(BiomeConfig config) {

        super(config);

        this.addDeco(boulderDecos()) // Mossy cobble & prismarine boulders.
            .addDeco(sponge(), config.ALLOW_SPONGE.get()) // Rare, wet sponge (only in deeper waters).
            .addDeco(waves(), (RTGAPI.config().ENABLE_OCEAN_WAVES.get() && config.ALLOW_OCEAN_WAVES.get())) // Ocean swells.
            .addDeco(baseBiomeDecorations()); // Base biome decorations.
    }

    private DecoHelperRandomSplit boulderDecos() {
        return new DecoHelperRandomSplit(new DecoBase[]{
            boulders(Blocks.MOSSY_COBBLESTONE.getDefaultState(), 6),
            boulders(Blocks.PRISMARINE.getDefaultState(), 6)
        }, new int[]{8, 8});
    }

    private DecoBoulder boulders(IBlockState boulderBlock, int chance) {
        DecoBoulder decoBoulder = new DecoBoulder();
        decoBoulder.config().BOULDER_BLOCK.set(boulderBlock);
        decoBoulder.config().CHANCE.set(chance);
        decoBoulder.config().MIN_Y.set(22);
        decoBoulder.config().MAX_Y.set(58);
        decoBoulder.setHeightType(DecoBoulder.HeightType.NEXT_INT);
        decoBoulder.config().STRENGTH_FACTOR.set(3f);
        return decoBoulder;
    }

    private DecoSponge sponge() {
        DecoSponge decoSponge = new DecoSponge();
        decoSponge.setSpongeBlock(BlockUtil.getSponge(1));
        decoSponge.config().CHANCE.set(1);
        decoSponge.config().MIN_Y.set(22);
        decoSponge.config().MAX_Y.set(39);
        decoSponge.setHeightType(DecoSponge.HeightType.NEXT_INT);
        decoSponge.config().STRENGTH_FACTOR.set(8f);
        return decoSponge;
    }

    private DecoWave waves() {
        DecoWave decoWave = new DecoWave();
        decoWave.setMinSize(6);
        decoWave.setMaxSize(10);
        decoWave.setConditionChance(2);
        decoWave.setConditionType(DecoWave.ConditionType.RANDOM_CHANCE);
        return decoWave;
    }

    private DecoBaseBiomeDecorations baseBiomeDecorations() {
        return new DecoBaseBiomeDecorations();
    }
}
