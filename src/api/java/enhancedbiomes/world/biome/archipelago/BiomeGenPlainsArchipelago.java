package enhancedbiomes.world.biome.archipelago;

import enhancedbiomes.world.biome.base.BiomeGenArchipelagoBase;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenPlains;

public class BiomeGenPlainsArchipelago extends BiomeGenArchipelagoBase
{
    public BiomeGenPlainsArchipelago(int par1)
    {
        super(par1);
        this.spawnableCreatureList.add(new SpawnListEntry(EntityHorse.class, 5, 2, 6));
        this.theBiomeDecorator.treesPerChunk = -999;
        this.theBiomeDecorator.flowersPerChunk = 4;
        this.theBiomeDecorator.grassPerChunk = 10;
    }
}
