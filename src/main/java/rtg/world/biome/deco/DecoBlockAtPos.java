package rtg.world.biome.deco;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.biome.realistic.RealisticBiomeBase;
import rtg.world.gen.feature.WorldGenBlockAtPos;

/**
 * Quick, dirty, hacky deco I created to test for double decorations.
 * 
 * @author WhichOnesPink
 *
 */
public class DecoBlockAtPos extends DecoBase
{
	
	public DecoBlockAtPos()
	{
		super();
	}
	
	@Override
	public void generate(RealisticBiomeBase biome, World world, Random rand, int chunkX, int chunkY, OpenSimplexNoise simplex, CellNoise cell, float strength, float river)
	{
		if (this.allowed) {

            int intX = chunkX + rand.nextInt(16);
            int intY = 240;
            int intZ = chunkY + rand.nextInt(16);

            (new WorldGenBlockAtPos(Blocks.glowstone)).generate(world, rand, intX, intY, intZ);
		}
	}
}
