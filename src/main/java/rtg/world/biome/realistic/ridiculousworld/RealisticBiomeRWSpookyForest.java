package rtg.world.biome.realistic.ridiculousworld;

import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.biome.BiomeConfig;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.gen.surface.ridiculousworld.SurfaceRWSpookyForest;
import rtg.world.gen.terrain.ridiculousworld.TerrainRWSpookyForest;

public class RealisticBiomeRWSpookyForest extends RealisticBiomeRWBase
{
    
    public RealisticBiomeRWSpookyForest(BiomeGenBase rwBiome, BiomeConfig config)
    {
    
        super(config,
            rwBiome, BiomeGenBase.river,
            new TerrainRWSpookyForest(0f, 100f, 68f, 170f),
            new SurfaceRWSpookyForest(config,
                rwBiome.topBlock, //Block top 
                (byte)0, //byte topByte
                rwBiome.fillerBlock, //Block filler, 
                (byte)0, //byte fillerByte
                rwBiome.topBlock, //Block mixTop, 
                (byte)0, //byte mixTopByte, 
                rwBiome.fillerBlock, //Block mixFill, 
                (byte)0, //byte mixFillByte,
                0.5f, //float mixWidth, 
                -0.15f, //float mixHeight, 
                10f, //float smallWidth, 
                0.5f //float smallStrength
            )
        );
		
		DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
		this.addDeco(decoBaseBiomeDecorations);
    }
}
