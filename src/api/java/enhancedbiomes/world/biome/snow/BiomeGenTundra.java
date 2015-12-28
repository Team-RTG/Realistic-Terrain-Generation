package enhancedbiomes.world.biome.snow;

import java.util.Random;

import enhancedbiomes.world.biome.base.BiomeGenSnowBase;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenShrub;
import net.minecraft.world.gen.feature.WorldGenTaiga1;
import net.minecraft.world.gen.feature.WorldGenTaiga2;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.minecraft.world.gen.feature.WorldGenerator;

public class BiomeGenTundra extends BiomeGenSnowBase
{
    public BiomeGenTundra(int par1)
    {
        super(par1);
        this.theBiomeDecorator.treesPerChunk = 1;
        this.theBiomeDecorator.flowersPerChunk = 10;
        this.theBiomeDecorator.grassPerChunk = 10;
    }
    
    /**
     * Gets a WorldGen appropriate for this biome.
     */
    @Override
    public WorldGenAbstractTree func_150567_a(Random par1Random)
    {
        return (WorldGenAbstractTree)new WorldGenShrub(1, 1);
    }
    
    /**
     * Gets a WorldGen appropriate for this biome.
     */
    public WorldGenerator getRandomWorldGenForGrass(Random par1Random)
    {
        return new WorldGenTallGrass(Blocks.tallgrass, 2);
    }
    
    /**
	 * takes temperature, returns color
	 */
	/*@Override
	public int getSkyColorByTemp(float par1)
	{
		return 0xA0A0FF;
	}*/
}
