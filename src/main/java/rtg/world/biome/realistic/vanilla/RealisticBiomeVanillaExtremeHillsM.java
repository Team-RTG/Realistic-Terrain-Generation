package rtg.world.biome.realistic.vanilla;

import rtg.api.biome.BiomeConfig;
import rtg.world.gen.surface.vanilla.SurfaceVanillaExtremeHillsM;
import rtg.world.gen.terrain.vanilla.TerrainVanillaExtremeHillsM;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeVanillaExtremeHillsM extends RealisticBiomeVanillaBase
{
    
    public static BiomeGenBase standardBiome = BiomeGenBase.extremeHills;
    public static BiomeGenBase mutationBiome = BiomeGenBase.getBiome(standardBiome.biomeID + MUTATION_ADDEND);
    
    public static Block topBlock = mutationBiome.topBlock;
    public static Block fillerBlock = mutationBiome.fillerBlock;
        
    public RealisticBiomeVanillaExtremeHillsM(BiomeConfig config)
    {
    
        super(config, 
            mutationBiome,
            BiomeGenBase.river,
            new TerrainVanillaExtremeHillsM(10f, 140f, 68f, 200f),
            new SurfaceVanillaExtremeHillsM(config, topBlock, fillerBlock, Blocks.grass, Blocks.dirt, 60f,
                -0.14f, 14f, 0.25f));
        
        this.config = config;
        this.generatesEmeralds = true;
    }
}
