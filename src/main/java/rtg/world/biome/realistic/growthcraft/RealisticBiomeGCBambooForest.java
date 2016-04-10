package rtg.world.biome.realistic.growthcraft;

import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.biome.BiomeConfig;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.biome.deco.DecoBoulder;
import rtg.world.biome.deco.DecoFlowersRTG;
import rtg.world.biome.deco.DecoFlowersRTG.HeightType;
import rtg.world.biome.deco.DecoGrass;
import rtg.world.biome.deco.DecoShrub;
import rtg.world.gen.surface.growthcraft.SurfaceGCBambooForest;
import rtg.world.gen.terrain.growthcraft.TerrainGCBambooForest;

public class RealisticBiomeGCBambooForest extends RealisticBiomeGCBase
{
    
    public RealisticBiomeGCBambooForest(BiomeGenBase gcBiome, BiomeConfig config)
    {
    
        super(config,
            gcBiome, BiomeGenBase.river,
            new TerrainGCBambooForest(),
            new SurfaceGCBambooForest(config, gcBiome.topBlock, gcBiome.fillerBlock, false, null, 0f, 1.5f, 60f, 65f, 1.5f, Blocks.dirt, (byte)2, 0.15f)
        );
        
		DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
		this.addDeco(decoBaseBiomeDecorations);
        
		DecoBoulder decoBoulder = new DecoBoulder();
		decoBoulder.boulderBlock = Blocks.cobblestone;
		decoBoulder.chance = 24;
		decoBoulder.maxY = 95;
		decoBoulder.strengthFactor = 1f;
		this.addDeco(decoBoulder);
        
        DecoShrub decoShrub = new DecoShrub();
        decoShrub.maxY = 110;
        decoShrub.notEqualsZerochance = 4;
        decoShrub.strengthFactor = 2f;
		this.addDeco(decoShrub);

		DecoFlowersRTG decoFlowersRTG = new DecoFlowersRTG();
		decoFlowersRTG.flowers = new int[] {6, 8};
		decoFlowersRTG.maxY = 128;
		decoFlowersRTG.heightType = HeightType.NEXT_INT;
		decoFlowersRTG.notEqualsZerochance = 4;
		decoFlowersRTG.strengthFactor = 8f;
        this.addDeco(decoFlowersRTG);
        
		DecoGrass decoGrass = new DecoGrass();
		decoGrass.maxY = 128;
		decoGrass.notEqualsZerochance = 4;
		decoGrass.strengthFactor = 10f;
        this.addDeco(decoGrass);
    }
}
