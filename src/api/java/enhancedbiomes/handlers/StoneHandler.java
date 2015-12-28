package enhancedbiomes.handlers;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import enhancedbiomes.EnhancedBiomesMod;
import enhancedbiomes.api.internal.OreGenEntry;
import enhancedbiomes.blocks.EnhancedBiomesBlocks;

public class StoneHandler 
{
	@SubscribeEvent
	public void generateModOres(DecorateBiomeEvent.Pre e) {
		genStandardOre(24, new WorldGenMinable(EnhancedBiomesBlocks.stoneEB, e.rand.nextInt(6), 32, Blocks.stone), 0, 128, e.chunkX, e.chunkZ, e.world, e.rand);
	}
	
    /**
     * Standard ore generation helper. Generates most ores.
     */
    protected static void genStandardOre(int rate, WorldGenerator par2WorldGenerator, int min, int max, int chunk_X, int chunk_Z, World world, Random rand)
    {
        for (int l = 0; l < rate; ++l)
        {
            int i1 = chunk_X + rand.nextInt(16);
            int j1 = rand.nextInt(max - min) + min;
            int k1 = chunk_Z + rand.nextInt(16);
            par2WorldGenerator.generate(world, rand, i1, j1, k1);
        }
    }
}
