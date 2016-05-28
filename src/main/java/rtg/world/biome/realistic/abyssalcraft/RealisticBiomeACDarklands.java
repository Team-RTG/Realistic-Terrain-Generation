package rtg.world.biome.realistic.abyssalcraft;

import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.biome.BiomeConfig;
import rtg.api.biome.abyssalcraft.config.BiomeConfigACDarklands;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.biome.deco.DecoFallenTree;
import rtg.world.biome.deco.DecoFallenTree.LogCondition;
import rtg.world.biome.deco.DecoShrub;
import rtg.world.gen.surface.abyssalcraft.SurfaceACDarklands;
import rtg.world.gen.terrain.abyssalcraft.TerrainACDarklands;

import com.shinoow.abyssalcraft.api.biome.ACBiomes;
import com.shinoow.abyssalcraft.api.block.ACBlocks;

public class RealisticBiomeACDarklands extends RealisticBiomeACBase
{

	public static BiomeGenBase acBiome = ACBiomes.darklands;
	
    public RealisticBiomeACDarklands(BiomeConfig config)
    {
    
        super(config, 
            acBiome,
            BiomeGenBase.river,
            new TerrainACDarklands(),
            new SurfaceACDarklands(config, acBiome.topBlock, acBiome.fillerBlock, false, null, 0f, 1.5f, 60f, 65f, 1.5f, acBiome.topBlock, (byte)0, 0.15f)
        );
        
        DecoFallenTree decoFallenTree = new DecoFallenTree();
        decoFallenTree.logCondition = LogCondition.RANDOM_CHANCE;
        decoFallenTree.logConditionChance = 8;
        decoFallenTree.maxY = 100;
        decoFallenTree.logBlock = ACBlocks.darklands_oak_wood;
        decoFallenTree.logMeta = (byte)0;
        decoFallenTree.leavesBlock = ACBlocks.darklands_oak_leaves;
        decoFallenTree.leavesMeta = (byte)-1;
        decoFallenTree.minSize = 2;
        decoFallenTree.maxSize = 3;
		this.addDeco(decoFallenTree, this.config._boolean(BiomeConfigACDarklands.decorationLogsId));
        
        DecoShrub decoShrubCustom = new DecoShrub();
        decoShrubCustom.logBlock = ACBlocks.darklands_oak_wood;
        decoShrubCustom.logMeta = (byte)0;
        decoShrubCustom.leavesBlock = ACBlocks.darklands_oak_leaves;
        decoShrubCustom.leavesMeta = (byte)0;
        decoShrubCustom.maxY = 110;
        decoShrubCustom.notEqualsZerochance = 3;
        decoShrubCustom.strengthFactor = 2f;
		this.addDeco(decoShrubCustom);
        
		DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
		this.addDeco(decoBaseBiomeDecorations);
    }
}
