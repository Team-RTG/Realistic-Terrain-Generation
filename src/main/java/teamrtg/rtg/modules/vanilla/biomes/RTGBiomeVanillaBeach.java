package teamrtg.rtg.modules.vanilla.biomes;

import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import teamrtg.rtg.api.tools.deco.DecoTree;
import teamrtg.rtg.api.tools.feature.tree.rtg.TreeRTG;
import teamrtg.rtg.api.tools.feature.tree.rtg.TreeRTGCocosNucifera;
import teamrtg.rtg.api.tools.surface.SurfaceBase;
import teamrtg.rtg.api.world.RTGWorld;
import teamrtg.rtg.api.world.biome.TerrainBase;
import teamrtg.rtg.api.world.biome.surface.part.SurfacePart;
import teamrtg.rtg.modules.vanilla.RTGBiomeVanilla;

public class RTGBiomeVanillaBeach extends RTGBiomeVanilla {

    public RTGBiomeVanillaBeach() {

        super(Biomes.BEACH, Biomes.RIVER);
    }

    @Override
    public void initConfig() {

        config.addBlock(config.CLIFF_BLOCK_1).setDefault(Blocks.SAND.getDefaultState());
        config.FILL_BLOCK.setDefault(Blocks.SANDSTONE.getDefaultState());
    }

    @Override
    public TerrainBase initTerrain() {

        return new TerrainBase() {
            @Override
            public float generateNoise(RTGWorld rtgWorld, int x, int y, float biomeWeight, float border, float river) {

                return terrainBeach(x, y, rtgWorld.simplex, river, 180f, 35f, 63f);
            }
        };
    }

    @Override
    public SurfacePart initSurface() {

        return SurfaceBase.surfaceBeach(this);
    }

    @Override
    public void initDecos() {

        // Scattered palm trees.

        TreeRTG nuciferaTree = new TreeRTGCocosNucifera();
        nuciferaTree.minTrunkSize = 7;
        nuciferaTree.maxTrunkSize = 9;
        nuciferaTree.minCrownSize = 6;
        nuciferaTree.maxCrownSize = 8;
        nuciferaTree.validGroundBlocks.clear();
        nuciferaTree.validGroundBlocks.add(Blocks.SAND.getDefaultState());
        this.addTree(nuciferaTree);

        DecoTree palmTrees = new DecoTree(nuciferaTree);
        palmTrees.loops = 1;
        palmTrees.treeType = DecoTree.TreeType.RTG_TREE;
        palmTrees.treeCondition = DecoTree.TreeCondition.X_DIVIDED_BY_STRENGTH;
        palmTrees.treeConditionFloat = 4f;
        palmTrees.maxY = 80;
        this.addDeco(palmTrees);
    }
}
