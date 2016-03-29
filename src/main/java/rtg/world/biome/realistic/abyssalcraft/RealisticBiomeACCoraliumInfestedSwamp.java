package rtg.world.biome.realistic.abyssalcraft;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import rtg.api.biome.BiomeConfig;
import rtg.world.gen.surface.abyssalcraft.SurfaceACCoraliumInfestedSwamp;
import rtg.world.gen.terrain.abyssalcraft.TerrainACCoraliumInfestedSwamp;

import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeACCoraliumInfestedSwamp extends RealisticBiomeACBase
{

    public RealisticBiomeACCoraliumInfestedSwamp(BiomeGenBase acBiome, BiomeConfig config)
    {
    
        super(config, 
            acBiome,
            Biomes.river,
            new TerrainACCoraliumInfestedSwamp(),
            new SurfaceACCoraliumInfestedSwamp(config, acBiome.topBlock, acBiome.fillerBlock, false, null, 0.15f));
    }
}
