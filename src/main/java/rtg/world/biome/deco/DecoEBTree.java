package rtg.world.biome.deco;

import static net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.TREE;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenForest;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.event.terraingen.TerrainGen;
import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.biome.realistic.RealisticBiomeBase;
import rtg.world.gen.feature.WorldGenShrubRTG;
import rtg.world.gen.feature.tree.vanilla.WorldGenTreesRTG;
import enhancedbiomes.api.EBAPI;
import enhancedbiomes.blocks.EnhancedBiomesBlocks;
import enhancedbiomes.helpers.TreeGen;
import enhancedbiomes.world.gen.WorldGenSpikedBush;

/**
 * 
 * @author WhichOnesPink
 *
 */
public class DecoEBTree extends DecoTree
{
	
	public TreeType treeType;
	
	public DecoEBTree()
	{
		super();
		
		/**
		 * Default values.
		 * These can be overridden when configuring the Deco object in the realistic biome.
		 */
		this.loops = 1;
		this.strengthFactorForLoops = 0f;
		this.strengthNoiseFactorForLoops = false;
		this.strengthNoiseFactorXForLoops = false;
		this.treeType = TreeType.DEAD;
		this.distribution = new DecoTree.Distribution(100f, 5f, 0.8f);
		this.treeCondition = TreeCondition.NOISE_GREATER_AND_RANDOM_CHANCE;
		this.treeConditionNoise = 0f;
		this.treeConditionChance = 1;
		this.minY = 1; // No lower height limit by default.
		this.maxY = 255; // No upper height limit by default.
		this.logBlock = Blocks.log;
		this.logMeta = (byte)0;
		this.leavesBlock = Blocks.leaves;
		this.leavesMeta = (byte)-1;
		this.minSize = 2;
		this.maxSize = 4;
		
		this.addDecoTypes(DecoType.TREE);
	}
    
    @Override
    public boolean properlyDefined() {
        return true;
    }

	@Override
	public void generate(RealisticBiomeBase biome, World world, Random rand, int chunkX, int chunkY, OpenSimplexNoise simplex, CellNoise cell, float strength, float river)
	{
		if (this.allowed) {
			
			if (TerrainGen.decorate(world, rand, chunkX, chunkY, TREE)) {
				
				float noise = simplex.noise2(chunkX / this.distribution.noiseDivisor, chunkY / this.distribution.noiseDivisor) * this.distribution.noiseFactor + this.distribution.noiseAddend;

                int loopCount = this.loops;
                loopCount = (this.strengthFactorForLoops > 0f) ? (int)(this.strengthFactorForLoops * strength) : loopCount;
                loopCount = (this.strengthNoiseFactorForLoops) ? (int)(noise * strength) : loopCount;
                loopCount = (this.strengthNoiseFactorXForLoops) ? (int)(noise * this.strengthFactorForLoops * strength) : loopCount;
	            for (int i = 0; i < loopCount; i++)
	            {
	                int intX = chunkX + rand.nextInt(16) + 8;
	                int intZ = chunkY + rand.nextInt(16) + 8;
	                int intY = world.getHeightValue(intX, intZ);
	                
	            	switch (this.treeType)
	            	{
		            		
		            	case ALDER:
		            		
		            		if (intY <= this.maxY && intY >= this.minY && isValidTreeCondition(noise, rand, strength)) {

		                        WorldGenerator worldgenerator = TreeGen.alder(rand);
		                        worldgenerator.setScale(1.0D, 1.0D, 1.0D);
		                        worldgenerator.generate(world, rand, intX, intY, intZ);
		            		}
		            		
		            		break;
		            		
		            	case BIRCH:
		            		
		            		if (intY <= this.maxY && intY >= this.minY && isValidTreeCondition(noise, rand, strength)) {

		                        WorldGenerator worldgenerator = TreeGen.birch();
		                        worldgenerator.setScale(1.0D, 1.0D, 1.0D);
		                        worldgenerator.generate(world, rand, intX, intY, intZ);
		            		}
		            		
		            		break;
		            		
		            	case CYPRESS:
		            		
		            		if (intY <= this.maxY && intY >= this.minY && isValidTreeCondition(noise, rand, strength)) {

		                        WorldGenerator worldgenerator = TreeGen.cypress(rand);
		                        worldgenerator.setScale(1.0D, 1.0D, 1.0D);
		                        worldgenerator.generate(world, rand, intX, intY, intZ);
		            		}
		            		
		            		break;
		            		
		            	case DEAD:
		            		
		            		if (intY <= this.maxY && intY >= this.minY && isValidTreeCondition(noise, rand, strength)) {

		                        WorldGenerator worldgenerator = TreeGen.dead(rand);
		                        worldgenerator.setScale(1.0D, 1.0D, 1.0D);
		                        worldgenerator.generate(world, rand, intX, intY, intZ);
		            		}
		            		
		            		break;
		            		
		            	case EUCALYPTUS:
		            		
		            		if (intY <= this.maxY && intY >= this.minY && isValidTreeCondition(noise, rand, strength)) {

		                        WorldGenerator worldgenerator = TreeGen.eucalyptus(rand);
		                        worldgenerator.setScale(1.0D, 1.0D, 1.0D);
		                        worldgenerator.generate(world, rand, intX, intY, intZ);
		            		}
		            		
		            		break;		            		
		            		
		            	case EUCALYPTUS_SHRUB:
		            		
		            		if (intY <= this.maxY && intY >= this.minY && isValidTreeCondition(noise, rand, strength)) {

		                        WorldGenerator worldgenerator = TreeGen.eucalyptus_shrub(rand);
		                        worldgenerator.setScale(1.0D, 1.0D, 1.0D);
		                        worldgenerator.generate(world, rand, intX, intY, intZ);
		            		}
		            		
		            		break;
		            		
		            	case FORESTED_VALLEY:
		            		
		            		if (intY <= this.maxY && intY >= this.minY && isValidTreeCondition(noise, rand, strength)) {

		                        WorldGenerator worldgenerator =
		                                rand.nextInt(2) == 0 ? TreeGen.birch()
		                                    : rand.nextInt(10) != 0 ? new WorldGenTreesRTG(false) : new WorldGenForest(false, false);
		                            worldgenerator.setScale(1.0D, 1.0D, 1.0D);
		                            worldgenerator.generate(world, rand, intX, intY, intZ);
		            		}
		            		
		            		break;
		            		
		            	case GREAT_OAK:
		            		
		            		if (intY <= this.maxY && intY >= this.minY && isValidTreeCondition(noise, rand, strength)) {

		                        WorldGenerator worldgenerator = TreeGen.greatOak(rand);
		                        worldgenerator.setScale(1.0D, 1.0D, 1.0D);
		                        worldgenerator.generate(world, rand, intX, intY, intZ);
		            		}
		            		
		            		break;
		            		
		            	case MANGROVE:
		            		
		            		if (intY <= this.maxY && intY >= this.minY && isValidTreeCondition(noise, rand, strength)) {

		                        WorldGenerator worldgenerator = TreeGen.mangrove();
		                        worldgenerator.setScale(1.0D, 1.0D, 1.0D);
		                        worldgenerator.generate(world, rand, intX, intY, intZ);
		            		}
		            		
		            		break;
		            		
		            	case SCRUB:
		            		
		            		if (intY <= this.maxY && intY >= this.minY && isValidTreeCondition(noise, rand, strength)) {
  
		                        if (rand.nextInt(4) != 0) {
		                            WorldGenerator spikedBush = new WorldGenSpikedBush(Blocks.log, 0, Blocks.leaves, 0, (EBAPI.ebGrassify(EnhancedBiomesBlocks.dirtEB, Blocks.dirt)));
		                            spikedBush.generate(world, rand, intX, intY, intZ);
		                        }
		                        else {
		                            new WorldGenShrubRTG(rand.nextInt(4) + 1, Blocks.log, (byte)0, Blocks.leaves, (byte)rand.nextInt(3)).generate(world, rand, intX, intY, intZ);
		                        }
		            		}
		            		
		            		break;

		            	default:
		            		break;
	            	}
	            }
			}
		}
	}

	public enum TreeType
	{
		ALDER,
		BIRCH,
		CYPRESS,
		DEAD,
		EUCALYPTUS,
		EUCALYPTUS_SHRUB,
		FORESTED_VALLEY,
		GREAT_OAK,
		MANGROVE,
		SCRUB;
	}
}
