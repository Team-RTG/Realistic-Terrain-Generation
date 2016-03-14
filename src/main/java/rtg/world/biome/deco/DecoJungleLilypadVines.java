package rtg.world.biome.deco;

import static net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.LILYPAD;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenVines;
import net.minecraft.world.gen.feature.WorldGenWaterlily;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.event.terraingen.TerrainGen;
import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.biome.realistic.RealisticBiomeBase;

/**
 * 
 * @author WhichOnesPink
 *
 */
public class DecoJungleLilypadVines extends DecoBase
{

	public DecoJungleLilypadVines()
	{
		super();
	}
	
	/**
	 * No config options for this one yet. Just ripped it directly from the old code.
	 */
	@Override
	public void generate(RealisticBiomeBase biome, World world, Random rand, int chunkX, int chunkY, OpenSimplexNoise simplex, CellNoise cell, float strength, float river)
	{
		if (this.allowed) {
			
	        if (TerrainGen.decorate(world, rand, chunkX, chunkY, LILYPAD)) {
	            
	            for (int b33 = 0; b33 < 5; b33++)
	            {
	                int j6 = chunkX + rand.nextInt(16) + 8;
	                int k10 = chunkY + rand.nextInt(16) + 8;
	                int z52 = world.getHeightValue(j6, k10);
	 
	                for (int h44 = 0; h44 < 8; h44++) {
	                    
	                    if (z52 > 64) {
	                        
	                        WorldGenerator worldgenerator2 = new WorldGenWaterlily();
	                        worldgenerator2.setScale(1.0D, 1.0D, 1.0D);
	                        worldgenerator2.generate(world, rand, j6, z52, k10);
	                    }
	                }
	                
	                for (int h44 = 0; h44 < 100; h44++) {
	                    WorldGenerator worldgenerator4 = new WorldGenVines();
	                    worldgenerator4.setScale(1.0D, 1.0D, 1.0D);
	                    worldgenerator4.generate(world, rand, j6, z52, k10);
	                }
	            }
	        }
		}
	}
}
