package rtg.world.biome.realistic.vanilla;

import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

import rtg.api.biome.BiomeConfig;
import rtg.api.biome.vanilla.config.BiomeConfigVanillaBeach;
import rtg.world.biome.deco.DecoTree;
import rtg.world.gen.feature.tree.rtg.TreeRTG;
import rtg.world.gen.feature.tree.rtg.TreeRTGCocosNucifera;
import rtg.world.gen.surface.vanilla.SurfaceVanillaBeach;
import rtg.world.gen.terrain.vanilla.TerrainVanillaBeach;

public class RealisticBiomeVanillaBeach extends RealisticBiomeVanillaBase {

    public static BiomeGenBase biome = BiomeGenBase.beach;
    public static BiomeGenBase river = BiomeGenBase.river;

    public RealisticBiomeVanillaBeach(BiomeConfig config) {

        super(config, biome, river,
            new TerrainVanillaBeach(),
            new SurfaceVanillaBeach(config, biome.topBlock, biome.fillerBlock, biome.topBlock, biome.fillerBlock, (byte) 0, 1)
        );

        // Scattered palm trees.

        TreeRTG nuciferaTree = new TreeRTGCocosNucifera();
        nuciferaTree.setMinTrunkSize(7);
        nuciferaTree.setMaxTrunkSize(9);
        nuciferaTree.setMinCrownSize(6);
        nuciferaTree.setMaxCrownSize(8);
        nuciferaTree.getValidGroundBlocks().clear();
        nuciferaTree.getValidGroundBlocks().add(Blocks.sand.getDefaultState());
        this.addTree(nuciferaTree);

        DecoTree palmTrees = new DecoTree(nuciferaTree);
        palmTrees.treeType = DecoTree.TreeType.RTG_TREE;
        palmTrees.treeCondition = DecoTree.TreeCondition.NOISE_GREATER_AND_RANDOM_CHANCE;
        palmTrees.treeConditionNoise = -0.2f;
        palmTrees.treeConditionChance = 12;
        palmTrees.maxY = 68;
        this.addDeco(palmTrees, this.config._boolean(BiomeConfigVanillaBeach.decorationPalmTreesId));
    }
}
