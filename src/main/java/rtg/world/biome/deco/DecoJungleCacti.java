package rtg.world.biome.deco;

import static net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.CACTUS;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraftforge.event.terraingen.TerrainGen;
import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.biome.realistic.RealisticBiomeBase;
import rtg.world.gen.feature.WorldGenJungleCacti;

public class DecoJungleCacti extends DecoBase
{
    
	public float strengthFactor;
	public int maxY;
	public boolean sandOnly;
	public int extraHeight;
	public byte sandMeta;
	
	public DecoJungleCacti()
	{
		super();
		
		this.strengthFactor = 8f;
		this.maxY = 120;
		this.sandOnly = false;
		this.extraHeight = 7;
		this.sandMeta = (byte)1;
	}
	
	@Override
	public void generate(RealisticBiomeBase biome, World world, Random rand, int chunkX, int chunkY, OpenSimplexNoise simplex, CellNoise cell, float strength, float river)
	{
		if (this.allowed) {

            if (TerrainGen.decorate(world, rand, chunkX, chunkY, CACTUS)) {
                
                for (int k18 = 0; k18 < this.strengthFactor * strength; k18++)
                {
                    int k21 = chunkX + rand.nextInt(16) + 8;
                    int j23 = rand.nextInt(160);
                    int k24 = chunkY + rand.nextInt(16) + 8;
                    
                    if (j23 < this.maxY)
                    {
                        (new WorldGenJungleCacti(this.sandOnly, rand.nextInt(this.extraHeight), this.sandMeta)).generate(world, rand, k21, j23, k24);
                    }
                }
            }
		}
	}
}
