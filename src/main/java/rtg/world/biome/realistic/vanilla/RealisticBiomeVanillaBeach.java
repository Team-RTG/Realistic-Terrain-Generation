package rtg.world.biome.realistic.vanilla;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.biome.BiomeConfig;
import rtg.api.biome.vanilla.config.BiomeConfigVanillaBeach;
import rtg.world.biome.deco.DecoTree;
import rtg.world.biome.deco.DecoTree.TreeCondition;
import rtg.world.biome.deco.DecoTree.TreeType;
import rtg.world.gen.feature.tree.rtg.TreeRTGCocosNucifera;
import rtg.world.gen.surface.vanilla.SurfaceVanillaBeach;
import rtg.world.gen.terrain.vanilla.TerrainVanillaBeach;

public class RealisticBiomeVanillaBeach extends RealisticBiomeVanillaBase {
    
    public static Block topBlock = BiomeGenBase.beach.topBlock;
    public static Block fillerBlock = BiomeGenBase.beach.fillerBlock;
    
    public RealisticBiomeVanillaBeach(BiomeConfig config)
    {
        super(config, 
            BiomeGenBase.beach,
            BiomeGenBase.river,
            new TerrainVanillaBeach(),
            new SurfaceVanillaBeach(config, topBlock, fillerBlock, topBlock, fillerBlock, (byte) 0, 1)
        );
        
		/**
		 * ##################################################
		 * # DECORATIONS (ORDER MATTERS)
		 * ##################################################
		 */
        
		// Scattered palm trees.
		DecoTree palmTrees = new DecoTree(new TreeRTGCocosNucifera());
		palmTrees.loops = 1;
		palmTrees.treeType = TreeType.RTG_TREE;
		palmTrees.treeCondition = TreeCondition.X_DIVIDED_BY_STRENGTH;
		palmTrees.treeConditionFloat = 4f;
		palmTrees.maxY = 80;
		palmTrees.minTrunkSize = 7;
		palmTrees.maxTrunkSize = 9;
		palmTrees.minCrownSize = 6;
		palmTrees.maxCrownSize = 8;
		this.addDeco(palmTrees, this.config._boolean(BiomeConfigVanillaBeach.decorationPalmTreesId));
    }
}
