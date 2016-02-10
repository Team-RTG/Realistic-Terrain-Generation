package rtg.world.biome.realistic.abyssalcraft;

import rtg.api.biome.BiomeConfig;
import rtg.world.gen.surface.abyssalcraft.SurfaceACCoraliumInfestedSwamp;
import rtg.world.gen.terrain.abyssalcraft.TerrainACCoraliumInfestedSwamp;

import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeACCoraliumInfestedSwamp extends RealisticBiomeACBase
{

    public RealisticBiomeACCoraliumInfestedSwamp(BiomeGenBase acBiome, BiomeConfig config)
    {
    
        super(
            acBiome,
            BiomeGenBase.river,
            new TerrainACCoraliumInfestedSwamp(),
            new SurfaceACCoraliumInfestedSwamp(acBiome.topBlock, acBiome.fillerBlock));
        
        this.config = config;
    }
}
