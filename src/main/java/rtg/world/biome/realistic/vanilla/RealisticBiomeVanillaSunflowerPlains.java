package rtg.world.biome.realistic.vanilla;

import rtg.api.biome.BiomeConfig;
import rtg.world.gen.surface.vanilla.SurfaceVanillaSunflowerPlains;
import rtg.world.gen.terrain.vanilla.TerrainVanillaSunflowerPlains;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeVanillaSunflowerPlains extends RealisticBiomeVanillaBase
{
    public static BiomeGenBase standardBiome = BiomeGenBase.plains;
    public static BiomeGenBase mutationBiome = BiomeGenBase.getBiome(standardBiome.biomeID + MUTATION_ADDEND);
    
    public static Block topBlock = mutationBiome.topBlock;
    public static Block fillerBlock = mutationBiome.fillerBlock;
    
    public RealisticBiomeVanillaSunflowerPlains(BiomeConfig config)
    {
        super(config, 
            mutationBiome,
            BiomeGenBase.river,
            new TerrainVanillaSunflowerPlains(),
            new SurfaceVanillaSunflowerPlains(config, topBlock, fillerBlock));
    }
}
