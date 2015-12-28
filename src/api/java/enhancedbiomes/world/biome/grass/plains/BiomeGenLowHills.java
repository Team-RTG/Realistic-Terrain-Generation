package enhancedbiomes.world.biome.grass.plains;

import java.util.Random;

import enhancedbiomes.world.biome.base.BiomeGenPlainsBase;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenTrees;
import net.minecraft.world.gen.feature.WorldGenerator;

public class BiomeGenLowHills extends BiomeGenPlainsBase
{
    public BiomeGenLowHills(int par1)
    {
        super(par1);
        this.spawnableCreatureList.add(new SpawnListEntry(EntityHorse.class, 5, 2, 6));
        this.theBiomeDecorator.grassPerChunk = -999;
    }
}
