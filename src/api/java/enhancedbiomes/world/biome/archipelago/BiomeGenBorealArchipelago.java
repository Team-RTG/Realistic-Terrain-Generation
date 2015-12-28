package enhancedbiomes.world.biome.archipelago;

import java.util.Random;

import enhancedbiomes.helpers.TreeGen;
import enhancedbiomes.world.biome.base.BiomeGenArchipelagoBase;
import enhancedbiomes.world.biome.base.BiomeGenWoodlandBase;
import net.minecraft.block.Block;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenTaiga1;
import net.minecraft.world.gen.feature.WorldGenTaiga2;
import net.minecraft.world.gen.feature.WorldGenerator;

public class BiomeGenBorealArchipelago extends BiomeGenArchipelagoBase
{
    public BiomeGenBorealArchipelago(int par1)
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
        return 	par1Random.nextInt(6) == 0 ? TreeGen.pine(par1Random) : 
        		par1Random.nextInt(5) == 0 ? TreeGen.cypress(par1Random) : 
        		par1Random.nextInt(4) == 0 ? TreeGen.fir_large(par1Random) :  
        		par1Random.nextInt(3) == 0 ? new WorldGenTaiga2(false) :  
        		par1Random.nextInt(10) == 0 ? TreeGen.silverPine(par1Random) :  
        	    new WorldGenTaiga1();
    }
}
