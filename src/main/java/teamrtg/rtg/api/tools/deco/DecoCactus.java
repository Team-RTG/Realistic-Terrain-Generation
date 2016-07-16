package teamrtg.rtg.api.tools.deco;

import static net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.CACTUS;

import java.util.Random;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.event.terraingen.TerrainGen;
import teamrtg.rtg.api.tools.feature.WorldGenCacti;
import teamrtg.rtg.api.world.RTGWorld;
import teamrtg.rtg.api.world.biome.deco.DecoBase;
import teamrtg.rtg.api.world.gen.RealisticBiomeGenerator;

/**
 * 
 * @author WhichOnesPink
 *
 */
public class DecoCactus extends DecoBase
{
	public int loops;
	public int chance;
	public float strengthFactor;
	public int maxY;
	public boolean sandOnly;
    public IBlockState soilBlock;
    public byte soilMeta;
	
	public DecoCactus()
	{
		super();
		
		/**
		 * Default values.
		 * These can be overridden when configuring the Deco object in the realistic biome.
		 */
		this.loops = 1;
		this.chance = 1;
		this.maxY = 255; // No height limit by default.
		this.strengthFactor = 0f; // The higher the value, the more there will be.
		this.sandOnly = false;
        this.soilBlock = Blocks.SAND.getDefaultState();

		this.addDecoTypes(DecoType.CACTUS);
	}
	
	@Override
	public void generate(RTGWorld rtgWorld, Random rand, int chunkX, int chunkY, float strength, float river, RealisticBiomeGenerator realisticBiomeGenerator, boolean hasPlacedVillageBlocks)
	{
		if (this.allowed) {
			
			if (TerrainGen.decorate(rtgWorld.world, rand, new BlockPos(chunkX, 0, chunkY), CACTUS)) {
	            
				WorldGenerator worldGenerator = new WorldGenCacti(this.sandOnly, 0, this.soilBlock);
				
                int loopCount = this.loops;
                loopCount = (this.strengthFactor > 0f) ? (int)(this.strengthFactor * strength) : loopCount;
	            for (int i = 0; i < loopCount*10; i++)
	            {
	                int intX = chunkX + rand.nextInt(16);// + 8;
	                int intY = rand.nextInt(this.maxY);
	                int intZ = chunkY + rand.nextInt(16);// + 8;

	                if (intY <= this.maxY && rand.nextInt(this.chance) == 0) {
	                	worldGenerator.generate(rtgWorld.world, rand, new BlockPos(intX, intY, intZ));
	                }
	            }
	        }
		}
	}
}
