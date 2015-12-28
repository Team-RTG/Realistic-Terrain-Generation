package enhancedbiomes.world.biome.grass.plains;

import java.util.Random;

import enhancedbiomes.world.biome.base.BiomeGenPlainsBase;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.*;

public class BiomeGenGrasslandsRoofed extends BiomeGenPlainsBase
{
    public BiomeGenGrasslandsRoofed(int par1)
    {
        super(par1);
        this.spawnableCreatureList.add(new SpawnListEntry(EntityHorse.class, 5, 2, 6));
        this.theBiomeDecorator.treesPerChunk = 16;
        this.theBiomeDecorator.flowersPerChunk = 8;
        this.theBiomeDecorator.grassPerChunk = 25;
    }
    
    /**
     * Gets a WorldGen appropriate for this biome.
     */
    @Override
    public WorldGenAbstractTree func_150567_a(Random par1Random)
    {
        return (WorldGenAbstractTree)(par1Random.nextInt(3) == 0 ? (new WorldGenShrub(0, 0)) : (new WorldGenCanopyTree(false)));
    }
}
