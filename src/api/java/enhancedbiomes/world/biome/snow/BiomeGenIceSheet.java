package enhancedbiomes.world.biome.snow;

import java.util.Random;

import enhancedbiomes.world.biome.base.BiomeGenSnowBase;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.*;

public class BiomeGenIceSheet extends BiomeGenSnowBase
{
    public BiomeGenIceSheet(int par1)
    {
        super(par1);
        this.theBiomeDecorator.treesPerChunk = -999;
        this.topBlock = Blocks.ice;
        this.fillerBlock = Blocks.ice;
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
