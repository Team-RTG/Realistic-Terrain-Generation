package rtg.world.biome.realistic.highlands;

import highlands.api.HighlandsBiomes;

import java.util.Random;

import rtg.api.biome.BiomeConfig;
import rtg.config.highlands.ConfigHL;
import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.biome.BiomeBase;
import rtg.world.gen.surface.highlands.SurfaceHLDesertMountains;
import rtg.world.gen.terrain.highlands.TerrainHLDesertMountains;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeHLDesertMountains extends RealisticBiomeHLBase
{
    public static BiomeGenBase hlBiome = HighlandsBiomes.desertMountains;
    
    public static Block topBlock = hlBiome.topBlock;
    public static Block fillerBlock = hlBiome.fillerBlock;
    
    public RealisticBiomeHLDesertMountains(BiomeConfig config)
    {
    
        super(
            hlBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.HOT),
            new TerrainHLDesertMountains(230f, 100f, 0f),
            new SurfaceHLDesertMountains(topBlock, fillerBlock, false, null, 0f, 1.5f, 90f, 30f, 1.5f));
        
        this.config = config;
        this.biomeWeight = ConfigHL.weightHLDesertMountains;
        this.generateVillages = ConfigHL.villageHLDesertMountains;
    }

    @Override
    public void rDecorate(World world, Random rand, int chunkX, int chunkY, OpenSimplexNoise simplex, CellNoise cell, float strength, float river)
    {
        
        /**
         * Using rDecorateSeedBiome() to partially decorate the biome? If so, then comment out this method.
         */
        rOreGenSeedBiome(world, rand, chunkX, chunkY, simplex, cell, strength, river, baseBiome);
        // nothing to suppress the highlands inverted sandstone parabolas
    }
}
