package rtg.world.biome.realistic.abyssalcraft;

import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.biome.BiomeConfig;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.gen.surface.abyssalcraft.SurfaceACCoraliumInfestedSwamp;
import rtg.world.gen.terrain.abyssalcraft.TerrainACCoraliumInfestedSwamp;

import com.shinoow.abyssalcraft.api.biome.ACBiomes;

public class RealisticBiomeACCoraliumInfestedSwamp extends RealisticBiomeACBase
{

	public static BiomeGenBase acBiome = ACBiomes.coralium_infested_swamp;
	
    public RealisticBiomeACCoraliumInfestedSwamp(BiomeConfig config)
    {
    
        super(config, 
            acBiome,
            BiomeGenBase.river,
            new TerrainACCoraliumInfestedSwamp(),
            new SurfaceACCoraliumInfestedSwamp(config, acBiome.topBlock, acBiome.fillerBlock)
        );
        
		DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
		this.addDeco(decoBaseBiomeDecorations);
    }
}
