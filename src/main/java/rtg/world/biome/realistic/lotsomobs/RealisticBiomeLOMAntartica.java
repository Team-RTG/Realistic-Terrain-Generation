package rtg.world.biome.realistic.lotsomobs;

import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.biome.BiomeConfig;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.gen.surface.lotsomobs.SurfaceLOMAntartica;
import rtg.world.gen.terrain.lotsomobs.TerrainLOMAntartica;

public class RealisticBiomeLOMAntartica extends RealisticBiomeLOMBase
{
    
    public RealisticBiomeLOMAntartica(BiomeGenBase lomBiome, BiomeConfig config)
    {
    
        super(config,
            lomBiome, BiomeGenBase.frozenRiver,
            new TerrainLOMAntartica(),
            new SurfaceLOMAntartica(config, 
                Blocks.snow, //Block top 
                (byte)0, //byte topByte
                lomBiome.fillerBlock, //Block filler, 
                (byte)0, //byte fillerByte
                Blocks.packed_ice, //Block mixTop, 
                (byte)0, //byte mixTopByte, 
                lomBiome.fillerBlock, //Block mixFill, 
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
