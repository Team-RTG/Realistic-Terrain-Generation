package enhancedbiomes.world.biome.grass.plains;

import java.util.Random;

import enhancedbiomes.blocks.EnhancedBiomesBlocks;
import enhancedbiomes.helpers.TreeGen;
import enhancedbiomes.world.biome.EnhancedBiomesPlains;
import enhancedbiomes.world.biome.base.BiomeGenPlainsBase;
import net.minecraft.block.Block;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenShrub;
import net.minecraft.world.gen.feature.WorldGenTaiga1;
import net.minecraft.world.gen.feature.WorldGenTaiga2;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.event.terraingen.BiomeEvent;

public class BiomeGenPrairie extends BiomeGenPlainsBase
{
    public BiomeGenPrairie(int par1)
    {
        super(par1);
        this.spawnableCreatureList.add(new SpawnListEntry(EntityHorse.class, 5, 2, 6));
        this.theBiomeDecorator.treesPerChunk = -999;
        this.theBiomeDecorator.flowersPerChunk = -999;
        this.theBiomeDecorator.grassPerChunk = 25;
        this.theBiomeDecorator.bigMushroomsPerChunk = -999;
        this.theBiomeDecorator.generateLakes = false;
        this.theBiomeDecorator.reedsPerChunk = -999;
    }
    
    /**
     * Gets a WorldGen appropriate for this biome.
     */
    @Override
    public int getModdedBiomeGrassColor(int original)
    {
        return EnhancedBiomesPlains.prairieGrass;
    }
    
    public void decorate(World worldObj, Random rand, int x, int z)
    {
        for(int c = 15; c > 0; c--)
       	{
    		int j2 = x + rand.nextInt(16) + 8;
    		int l3 = rand.nextInt(120);
        	int j5 = z + rand.nextInt(16) + 8;
        	if(worldObj.getBlock(j2, l3, j5) == Blocks.air && EnhancedBiomesBlocks.isGrass(worldObj.getBlock(j2, l3 - 1, j5)))
        	{
        		(TreeGen.baobab(rand)).generate(worldObj, rand, j2, l3, j5);
        	}
    	}
        
        super.decorate(worldObj, rand, x, z);
    }
}
