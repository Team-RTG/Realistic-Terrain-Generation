package rtg.world.biome.realistic.vanilla;

import rtg.api.biome.BiomeConfig;
import rtg.world.gen.surface.vanilla.SurfaceVanillaExtremeHillsPlusM;
import rtg.world.gen.terrain.vanilla.TerrainVanillaExtremeHillsPlusM;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeVanillaExtremeHillsPlusM extends RealisticBiomeVanillaBase
{
    public static BiomeGenBase standardBiome = BiomeGenBase.extremeHillsPlus;
    public static BiomeGenBase mutationBiome = BiomeGenBase.getBiome(standardBiome.biomeID + MUTATION_ADDEND);
    
    public static Block topBlock = mutationBiome.topBlock.getBlock();
    public static Block fillerBlock = mutationBiome.fillerBlock.getBlock();
    
    public RealisticBiomeVanillaExtremeHillsPlusM(BiomeConfig config)
    {
    
        super(config, 
            mutationBiome,
            BiomeGenBase.river,
            new TerrainVanillaExtremeHillsPlusM(230f, 120f, 0f),
            new SurfaceVanillaExtremeHillsPlusM(config, Blocks.grass, Blocks.dirt, false, null, 0f, 1.5f, 60f, 65f, 1.5f, Blocks.gravel, 0.08f));
        this.generatesEmeralds = true;
    }
}
