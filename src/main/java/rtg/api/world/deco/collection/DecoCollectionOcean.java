package rtg.api.world.deco.collection;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;

import rtg.api.util.BlockUtil;
import rtg.api.world.deco.DecoBase;
import rtg.api.world.deco.DecoBaseBiomeDecorations;
import rtg.api.world.deco.DecoBoulder;
import rtg.api.world.deco.DecoSponge;
import rtg.api.world.deco.helper.DecoHelperRandomSplit;


/**
 * @author WhichOnesPink
 */
public class DecoCollectionOcean extends DecoCollectionBase {

    private int maxY = 56;

    public DecoCollectionOcean() {

        super();

        this.addDeco(boulderDecos()) // Mossy, non-mossy & prismarine boulders.
            .addDeco(sponge()) // Rare, wet sponge (only in deeper waters).
            .addDeco(baseBiomeDecorations()); // Base biome decorations.
    }

    private DecoHelperRandomSplit boulderDecos() {
        return new DecoHelperRandomSplit()
            .setDecos(new DecoBase[]{
                boulders(Blocks.MOSSY_COBBLESTONE.getDefaultState(), 6),
                boulders(Blocks.PRISMARINE.getDefaultState(), 6),
                boulders(Blocks.COBBLESTONE.getDefaultState(), 4)
            })
            .setChances(new int[]{8, 8, 4});
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
        decoSponge.setMaxY(32);
        decoSponge.setHeightType(DecoSponge.HeightType.NEXT_INT);
        decoSponge.setStrengthFactor(8f);
        return decoSponge;
    }

    private DecoBaseBiomeDecorations baseBiomeDecorations() {
        return new DecoBaseBiomeDecorations();
    }
}
