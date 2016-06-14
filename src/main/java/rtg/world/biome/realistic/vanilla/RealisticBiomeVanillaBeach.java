package rtg.world.biome.realistic.vanilla;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.biome.BiomeConfig;
import rtg.api.biome.vanilla.config.BiomeConfigVanillaBeach;
import rtg.world.biome.deco.DecoTree;
import rtg.world.biome.deco.DecoTree.TreeCondition;
import rtg.world.biome.deco.DecoTree.TreeType;
import rtg.world.gen.feature.tree.rtg.TreeRTG;
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
        
		TreeRTG nuciferaTree = new TreeRTGCocosNucifera();
		nuciferaTree.minTrunkSize = 7;
		nuciferaTree.maxTrunkSize = 9;
		nuciferaTree.minCrownSize = 6;
		nuciferaTree.maxCrownSize = 8;
		this.addTree(nuciferaTree);
        
		DecoTree palmTrees = new DecoTree(nuciferaTree);
		palmTrees.loops = 1;
		palmTrees.treeType = TreeType.RTG_TREE;
		palmTrees.treeCondition = TreeCondition.X_DIVIDED_BY_STRENGTH;
		palmTrees.treeConditionFloat = 4f;
		palmTrees.maxY = 80;
		this.addDeco(palmTrees, this.config._boolean(BiomeConfigVanillaBeach.decorationPalmTreesId));
    }
}
