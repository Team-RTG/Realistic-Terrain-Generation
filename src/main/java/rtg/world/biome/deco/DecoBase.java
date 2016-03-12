package rtg.world.biome.deco;

import java.util.Random;

import net.minecraft.world.World;
import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.biome.realistic.RealisticBiomeBase;

public class DecoBase
{
    
	public boolean allowed;
	
	public DecoBase()
	{
		this.allowed = false;
	}
	
	public void generate(RealisticBiomeBase biome, World world, Random rand, int chunkX, int chunkY, OpenSimplexNoise simplex, CellNoise cell, float strength, float river)
	{
		
    }
}