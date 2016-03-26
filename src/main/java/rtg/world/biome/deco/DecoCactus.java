package rtg.world.biome.deco;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.terraingen.TerrainGen;
import rtg.util.noise.CellNoise;
import rtg.util.noise.OpenSimplexNoise;
import rtg.world.biome.realistic.RealisticBiomeBase;
import rtg.world.gen.feature.WorldGenCacti;

import java.util.Random;

import static net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.CACTUS;

/**
 * 
 * @author WhichOnesPink
 *
 */
public class DecoCactus extends DecoBase
{
    
	public float strengthFactor;
	public int maxY;
	public boolean sandOnly;
    /**
     * Block to place under cactus. Sand, Red sand and coarse dirt are possibilities.
     */
    public IBlockState soil;
	
	public DecoCactus()
	{
		super();
		
		/**
		 * Default values.
		 * These can be overridden when configuring the Deco object in the realistic biome.
		 */
		this.maxY = 255; // No height limit by default.
		this.strengthFactor = 2f; // The higher the value, the more there will be.
		this.sandOnly = false;
        this.soil = Blocks.sand.getDefaultState();
		
		this.addDecoTypes(DecoType.CACTUS);
	}
	
	@Override
	public void generate(RealisticBiomeBase biome, World world, Random rand, int chunkX, int chunkY, OpenSimplexNoise simplex, CellNoise cell, float strength, float river)
	{
		if (this.allowed) {
			
			if (TerrainGen.decorate(world, rand, new BlockPos(chunkX, 0, chunkY), CACTUS)) {
	            
	            for (int i = 0; i < this.strengthFactor * strength; i++)
	            {
	                int intX = chunkX + rand.nextInt(16) + 8;
	                int intY = rand.nextInt(this.maxY);
	                int intZ = chunkY + rand.nextInt(16) + 8;

	                if (intY <= this.maxY) {
	                	(new WorldGenCacti(this.sandOnly, 0, soil)).generate(world, rand, new BlockPos(intX, intY, intZ));
	                }
	            }
	        }
		}
	}
}
