package rtg.world.biome.realistic.abyssalcraft;

import net.minecraft.init.Biomes;
import net.minecraft.world.biome.Biome;

import com.shinoow.abyssalcraft.api.biome.ACBiomes;
import com.shinoow.abyssalcraft.api.block.ACBlocks;

import rtg.api.biome.BiomeConfig;
import rtg.api.biome.abyssalcraft.config.BiomeConfigACDarklands;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.biome.deco.DecoFallenTree;
import rtg.world.biome.deco.DecoShrub;
import rtg.world.gen.surface.abyssalcraft.SurfaceACDarklands;
import rtg.world.gen.terrain.abyssalcraft.TerrainACDarklands;

public class RealisticBiomeACDarklands extends RealisticBiomeACBase {

    public static Biome biome = ACBiomes.darklands;
    public static Biome river = Biomes.RIVER;

    public RealisticBiomeACDarklands(BiomeConfig config) {

        super(config, biome, river,
            new TerrainACDarklands(),
            new SurfaceACDarklands(config, biome.topBlock, biome.fillerBlock, false, null, 0f, 1.5f, 60f, 65f, 1.5f, biome.topBlock, 0.15f)
        );

        DecoFallenTree decoFallenTree = new DecoFallenTree();
        decoFallenTree.logCondition = DecoFallenTree.LogCondition.RANDOM_CHANCE;
        decoFallenTree.logConditionChance = 8;
        decoFallenTree.logBlock = ACBlocks.darklands_oak_wood.getDefaultState();
        decoFallenTree.leavesBlock = ACBlocks.darklands_oak_leaves.getDefaultState();
        decoFallenTree.minSize = 2;
        decoFallenTree.maxSize = 3;
        this.addDeco(decoFallenTree, this.config._boolean(BiomeConfigACDarklands.decorationLogsId));

        DecoShrub decoShrubCustom = new DecoShrub();
        decoShrubCustom.logBlock = ACBlocks.darklands_oak_wood.getDefaultState();
        decoShrubCustom.leavesBlock = ACBlocks.darklands_oak_leaves.getDefaultState();
        decoShrubCustom.maxY = 110;
        decoShrubCustom.notEqualsZerochance = 3;
        decoShrubCustom.strengthFactor = 2f;
        this.addDeco(decoShrubCustom);

        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        this.addDeco(decoBaseBiomeDecorations);
    }
}
