package rtg.world.biome.realistic.lotsomobs;

import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.biome.BiomeConfig;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.gen.surface.lotsomobs.SurfaceLOMTropicalBeach;
import rtg.world.gen.terrain.lotsomobs.TerrainLOMTropicalBeach;

public class RealisticBiomeLOMTropicalBeach extends RealisticBiomeLOMBase
{
    
    public RealisticBiomeLOMTropicalBeach(BiomeGenBase lomBiome, BiomeConfig config)
    {
    
        super(config,
            lomBiome, BiomeGenBase.river,
            new TerrainLOMTropicalBeach(),
            new SurfaceLOMTropicalBeach(config,
                lomBiome.topBlock, //Block top 
                (byte)0, //byte topByte
                lomBiome.fillerBlock, //Block filler, 
                (byte)0, //byte fillerByte
                lomBiome.topBlock, //Block mixTop, 
                (byte)0, //byte mixTopByte, 
                lomBiome.fillerBlock, //Block mixFill, 
                (byte)0, //byte mixFillByte,
                10f, //float mixWidth, 
                -0.15f, //float mixHeight, 
                5f, //float smallWidth, 
                0.5f //float smallStrength
            )
        );
		
		DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
		this.addDeco(decoBaseBiomeDecorations);
    }
}
