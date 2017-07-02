package rtg.world.biome.realistic.sugiforest;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

import rtg.api.biome.BiomeConfig;
import rtg.api.biome.sugiforest.config.BiomeConfigSFSugiForest;
import rtg.world.biome.deco.*;
import rtg.world.gen.surface.sugiforest.SurfaceSFSugiForest;
import rtg.world.gen.terrain.sugiforest.TerrainSFSugiForest;

public class RealisticBiomeSFSugiForest extends RealisticBiomeSFBase
{
    private static Block sugiLogBlock = Block.getBlockFromName("kegare.sugiforest:sugi_log");
    private static Block sugiLeavesBlock = Block.getBlockFromName("kegare.sugiforest:sugi_leaves");

    public RealisticBiomeSFSugiForest(BiomeGenBase sfBiome, BiomeConfig config)
    {
    
        super(config, 
            sfBiome, BiomeGenBase.river,
            new TerrainSFSugiForest(),
            new SurfaceSFSugiForest(config, sfBiome.topBlock, sfBiome.fillerBlock, false, null, 0f, 1.5f, 60f, 65f, 1.5f, Blocks.dirt, (byte)2, 0.10f)
        );

        DecoFallenTree decoFallenSugi = new DecoFallenTree();
        decoFallenSugi.logCondition = DecoFallenTree.LogCondition.RANDOM_CHANCE;
        decoFallenSugi.logConditionChance = 16;
        decoFallenSugi.maxY = 80;
        decoFallenSugi.logBlock = sugiLogBlock;
        decoFallenSugi.logMeta = (byte)0;
        decoFallenSugi.leavesBlock = sugiLeavesBlock;
        decoFallenSugi.leavesMeta = (byte)-1;
        decoFallenSugi.minSize = 3;
        decoFallenSugi.maxSize = 6;
        this.addDeco(decoFallenSugi, this.config._boolean(BiomeConfigSFSugiForest.decorationLogsId));

        DecoShrub decoShrubSugi = new DecoShrub();
        decoShrubSugi.logBlock = sugiLogBlock;
        decoShrubSugi.leavesBlock = sugiLeavesBlock;
        decoShrubSugi.maxY = 140;
        decoShrubSugi.strengthFactor = 4f;
        decoShrubSugi.chance = 4;
        this.addDeco(decoShrubSugi);
		
		DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
		this.addDeco(decoBaseBiomeDecorations);

        DecoFlowersRTG decoFlowersRTG = new DecoFlowersRTG();
        decoFlowersRTG.flowers = new int[] {6, 6, 6, 6};
        decoFlowersRTG.maxY = 128;
        decoFlowersRTG.strengthFactor = 3f;
        this.addDeco(decoFlowersRTG);

        DecoGrass decoGrass = new DecoGrass();
        decoGrass.minY = 62;
        decoGrass.maxY = 128;
        decoGrass.loops = 4;
        this.addDeco(decoGrass);
    }
}
