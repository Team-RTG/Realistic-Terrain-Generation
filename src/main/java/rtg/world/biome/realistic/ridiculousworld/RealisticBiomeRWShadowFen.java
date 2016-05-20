package rtg.world.biome.realistic.ridiculousworld;

import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.biome.BiomeConfig;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.gen.surface.ridiculousworld.SurfaceRWShadowFen;
import rtg.world.gen.terrain.ridiculousworld.TerrainRWShadowFen;

public class RealisticBiomeRWShadowFen extends RealisticBiomeRWBase
{
    
    public RealisticBiomeRWShadowFen(BiomeGenBase rwBiome, BiomeConfig config)
    {
    
        super(config,
            rwBiome, BiomeGenBase.river,
            new TerrainRWShadowFen(),
            new SurfaceRWShadowFen(config,
                rwBiome.topBlock, //Block top 
                (byte)0, //byte topByte
                rwBiome.fillerBlock, //Block filler, 
                (byte)0, //byte fillerByte
                rwBiome.topBlock, //Block mixTop, 
                (byte)0, //byte mixTopByte, 
                rwBiome.fillerBlock, //Block mixFill, 
                (byte)0, //byte mixFillByte,
                80f, //float mixWidth, 
                -0.15f, //float mixHeight, 
                10f, //float smallWidth, 
                0.5f //float smallStrength
            )
        );
		
		DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
		this.addDeco(decoBaseBiomeDecorations);
    }
}
