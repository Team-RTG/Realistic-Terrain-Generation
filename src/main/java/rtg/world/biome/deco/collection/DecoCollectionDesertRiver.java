package rtg.world.biome.deco.collection;

import net.minecraft.init.Blocks;

import rtg.world.biome.deco.*;
import rtg.world.biome.deco.DecoTree.TreeCondition;
import rtg.world.biome.deco.DecoTree.TreeType;
import rtg.world.biome.deco.helper.DecoHelperThisOrThat;
import rtg.world.biome.deco.helper.DecoHelperThisOrThat.ChanceType;
import rtg.world.gen.feature.tree.rtg.TreeRTG;
import rtg.world.gen.feature.tree.rtg.TreeRTGAcaciaBucheri;


/**
 * @author WhichOnesPink
 */
public class DecoCollectionDesertRiver extends DecoCollectionBase {

    public DecoCollectionDesertRiver() {

        super();

        TreeRTG acaciaTree = new TreeRTGAcaciaBucheri();
        acaciaTree.setLogBlock(Blocks.log2.getDefaultState());
        acaciaTree.setLeavesBlock(Blocks.leaves2.getDefaultState());
        acaciaTree.setMinTrunkSize(12);
        acaciaTree.setMaxTrunkSize(16);
        this.addTree(acaciaTree);

        DecoTree acaciaTrees = new DecoTree(acaciaTree);
        acaciaTrees.checkRiver = true;
        acaciaTrees.minRiver = 0.86f;
        acaciaTrees.loops = 1;
        acaciaTrees.treeType = TreeType.RTG_TREE;
        acaciaTrees.treeCondition = TreeCondition.ALWAYS_GENERATE;
        acaciaTrees.maxY = 65;
        this.addDeco(acaciaTrees);

        DecoShrub acaciaShrub = new DecoShrub();
        acaciaShrub.checkRiver = true;
        acaciaShrub.minRiver = 0.86f;
        acaciaShrub.logBlock = Blocks.log2.getDefaultState();
        acaciaShrub.leavesBlock = Blocks.leaves2.getDefaultState();
        acaciaShrub.maxY = 65;
        acaciaShrub.loops = 1;
        acaciaShrub.chance = 1;
        this.addDeco(acaciaShrub);

        DecoHelperThisOrThat decoHelperThisOrThat = new DecoHelperThisOrThat(4, ChanceType.NOT_EQUALS_ZERO, acaciaShrub, acaciaTrees);
        this.addDeco(decoHelperThisOrThat);

        DecoCactus decoRiverCactus = new DecoCactus();
        decoRiverCactus.checkRiver = true;
        decoRiverCactus.minRiver = 0.7f;
        decoRiverCactus.maxY = 80;
        decoRiverCactus.strengthFactor = 12f;
        this.addDeco(decoRiverCactus);

        DecoReed decoReed = new DecoReed();
        decoReed.checkRiver = true;
        decoReed.minRiver = 0.7f;
        decoReed.maxY = 68;
        decoReed.strengthFactor = 2f;
        this.addDeco(decoReed);

        DecoGrassDoubleTallgrass decoGrassDoubleTallgrass = new DecoGrassDoubleTallgrass();
        decoGrassDoubleTallgrass.checkRiver = true;
        decoGrassDoubleTallgrass.minRiver = 0.7f;
        decoGrassDoubleTallgrass.maxY = 128;
        decoGrassDoubleTallgrass.loops = 15;
        decoGrassDoubleTallgrass.doubleGrassChance = 3;
        this.addDeco(decoGrassDoubleTallgrass);
    }
}
