package rtg.world.biome.realistic.arsmagica;

import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.biome.BiomeConfig;
import rtg.api.biome.arsmagica.config.BiomeConfigAMWitchwoodForest;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.biome.deco.DecoFallenTree;
import rtg.world.biome.deco.DecoFallenTree.LogCondition;
import rtg.world.biome.deco.DecoGrass;
import rtg.world.biome.deco.DecoShrubCustom;
import rtg.world.gen.surface.arsmagica.SurfaceAMWitchwoodForest;
import rtg.world.gen.terrain.arsmagica.TerrainAMWitchwoodForest;

public class RealisticBiomeAMWitchwoodForest extends RealisticBiomeAMBase
{
    public RealisticBiomeAMWitchwoodForest(BiomeGenBase amBiome, BiomeConfig config)
    {
    
        super(config,
            amBiome, BiomeGenBase.river,
            new TerrainAMWitchwoodForest(),
            new SurfaceAMWitchwoodForest(config, amBiome.topBlock, amBiome.fillerBlock)
        );
        
        DecoShrubCustom decoShrub = new DecoShrubCustom();
        decoShrub.logBlock = this.witchwoodLogBlock;
        decoShrub.logMeta = this.witchwoodLogMeta;
        decoShrub.leavesBlock = this.witchwoodLeavesBlock;
        decoShrub.leavesMeta = this.witchwoodLeavesMeta;
        decoShrub.maxY = 110;
        decoShrub.strengthFactor = 4f;
        decoShrub.chance = 6;
		this.addDeco(decoShrub);
        
		DecoFallenTree decoFallenTree = new DecoFallenTree();
		decoFallenTree.distribution.noiseDivisor = 80f;
		decoFallenTree.distribution.noiseFactor = 60f;
		decoFallenTree.distribution.noiseAddend = -15f;
		decoFallenTree.logCondition = LogCondition.NOISE_GREATER_AND_RANDOM_CHANCE;
		decoFallenTree.logConditionChance = 6;
		decoFallenTree.logConditionNoise = 0f;
		decoFallenTree.maxY = 100;
		decoFallenTree.logBlock = this.witchwoodLogBlock;
		decoFallenTree.logMeta = this.witchwoodLogMeta;
		decoFallenTree.leavesBlock = this.witchwoodLeavesBlock;
		decoFallenTree.leavesMeta = (byte)-1;
		decoFallenTree.minSize = 3;
		decoFallenTree.maxSize = 5;
		this.addDeco(decoFallenTree, this.config._boolean(BiomeConfigAMWitchwoodForest.decorationLogsId));
        
		DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
		decoBaseBiomeDecorations.notEqualsZeroChance = 3;
		this.addDeco(decoBaseBiomeDecorations);
		
        // Grass filler.
		DecoGrass decoGrass = new DecoGrass();
		decoGrass.maxY = 128;
		decoGrass.loops = 8;
        this.addDeco(decoGrass); 
    }
}
