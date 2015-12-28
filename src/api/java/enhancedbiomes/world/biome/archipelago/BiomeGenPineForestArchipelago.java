package enhancedbiomes.world.biome.archipelago;

import java.util.Random;

import enhancedbiomes.helpers.TreeGen;
import enhancedbiomes.world.biome.base.BiomeGenArchipelagoBase;
import enhancedbiomes.world.biome.base.BiomeGenWoodlandBase;
import net.minecraft.block.Block;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenerator;

public class BiomeGenPineForestArchipelago extends BiomeGenArchipelagoBase
{
    public BiomeGenPineForestArchipelago(int par1)
    {
        super(par1);
        this.spawnableCreatureList.add(new SpawnListEntry(EntityWolf.class, 5, 4, 4));
        this.theBiomeDecorator.treesPerChunk = 10;
        this.theBiomeDecorator.grassPerChunk = 5;
    }

    /**
     * Gets a WorldGen appropriate for this biome.
     */
    @Override
    public WorldGenAbstractTree func_150567_a(Random par1Random)
    {
        return TreeGen.pine(par1Random);
    }
}
