package rtg.world.biome.deco.collection;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;

import rtg.api.util.BlockUtil;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.biome.deco.DecoBoulder;
import rtg.world.biome.deco.DecoShrub;
import rtg.world.biome.deco.DecoSponge;
import rtg.world.biome.deco.helper.DecoHelper5050;


/**
 * @author WhichOnesPink
 */
public class DecoCollectionOcean extends DecoCollectionBase {

    private int maxY = 58;

    public DecoCollectionOcean() {

        this.addDeco(shrubDecos()) // Shrubs.
            .addDeco(boulderDecos()) // Mossy & non-mossy boulders.
            .addDeco(sponge()) // Rare, wet sponge (only in deeper waters).
            .addDeco(baseBiomeDecorations()); // Base biome decorations.
    }

    private DecoShrub shrubs(IBlockState log, IBlockState leaves) {
        DecoShrub decoShrub = new DecoShrub();
        decoShrub.setLogBlock(log);
        decoShrub.setLeavesBlock(leaves);
        decoShrub.setStrengthFactor(4f);
        decoShrub.setChance(2);
        decoShrub.setMaxY(maxY);
        return decoShrub;
    }

    private DecoHelper5050 shrubDecos() {
        return new DecoHelper5050(
            shrubs(Blocks.LOG.getDefaultState(), Blocks.LEAVES.getDefaultState()),
            shrubs(BlockUtil.getStateLog(1), BlockUtil.getStateLeaf(1))
        );
    }

    private DecoHelper5050 boulderDecos() {
        return new DecoHelper5050(
            boulders(Blocks.COBBLESTONE.getDefaultState(), 4),
            boulders(Blocks.MOSSY_COBBLESTONE.getDefaultState(), 4)
        );
    }

    private DecoBoulder boulders(IBlockState boulderBlock, int chance) {
        DecoBoulder decoBoulder = new DecoBoulder();
        decoBoulder.setBoulderBlock(boulderBlock);
        decoBoulder.setChance(chance);
        decoBoulder.setMinY(22);
        decoBoulder.setMaxY(58);
        decoBoulder.setHeightType(DecoBoulder.HeightType.NEXT_INT);
        decoBoulder.setStrengthFactor(4f);
        return decoBoulder;
    }

    private DecoSponge sponge() {
        DecoSponge decoSponge = new DecoSponge();
        decoSponge.setBoulderBlock(BlockUtil.getSponge(1));
        decoSponge.setChance(4);
        decoSponge.setMinY(22);
        decoSponge.setMaxY(32);
        decoSponge.setHeightType(DecoSponge.HeightType.NEXT_INT);
        decoSponge.setStrengthFactor(4f);
        return decoSponge;
    }

    private DecoBaseBiomeDecorations baseBiomeDecorations() {
        return new DecoBaseBiomeDecorations();
    }
}
