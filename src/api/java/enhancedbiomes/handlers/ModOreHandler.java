package enhancedbiomes.handlers;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import enhancedbiomes.EnhancedBiomesMod;
import enhancedbiomes.api.internal.OreGenEntry;
import enhancedbiomes.world.gen.WorldGenMinableEnhancedBiomesOres;

public class ModOreHandler 
{
	@SubscribeEvent
	public void generateModOres(DecorateBiomeEvent.Pre e) {
		for(int a = 0; a < EnhancedBiomesMod.modOreList.size(); a++) {
			OreGenEntry oge = EnhancedBiomesMod.modOreList.get(a);
			genStandardOre(oge.rate, new WorldGenMinableEnhancedBiomesOres(oge.ore, oge.defaultOre, oge.quantity), oge.minHeight, oge.maxHeight, e.chunkX, e.chunkZ, e.world, e.rand);
		}
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
