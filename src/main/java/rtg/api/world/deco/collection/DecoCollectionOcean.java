package rtg.api.world.deco.collection;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;

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
            .addDeco(waves(), config.ALLOW_OCEAN_WAVES.get()) // Ocean swells.
            .addDeco(baseBiomeDecorations()); // Base biome decorations.
    }

    private DecoHelperRandomSplit boulderDecos() {
        return new DecoHelperRandomSplit()
            .setDecos(new DecoBase[]{
                boulders(Blocks.MOSSY_COBBLESTONE.getDefaultState(), 6),
                boulders(Blocks.PRISMARINE.getDefaultState(), 6)
            })
            .setChances(new int[]{8, 8});
    }

    private DecoBoulder boulders(IBlockState boulderBlock, int chance) {
        DecoBoulder decoBoulder = new DecoBoulder();
        decoBoulder.setBoulderBlock(boulderBlock);
        decoBoulder.setChance(chance);
        decoBoulder.setMinY(22);
        decoBoulder.setMaxY(58);
        decoBoulder.setHeightType(DecoBoulder.HeightType.NEXT_INT);
        decoBoulder.setStrengthFactor(3f);
        return decoBoulder;
    }

    private DecoSponge sponge() {
        DecoSponge decoSponge = new DecoSponge();
        decoSponge.setSpongeBlock(BlockUtil.getSponge(1));
        decoSponge.setChance(1);
        decoSponge.setMinY(22);
        decoSponge.setMaxY(39);
        decoSponge.setHeightType(DecoSponge.HeightType.NEXT_INT);
        decoSponge.setStrengthFactor(8f);
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
