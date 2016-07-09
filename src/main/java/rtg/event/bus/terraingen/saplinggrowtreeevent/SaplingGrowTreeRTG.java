package rtg.event.bus.terraingen.saplinggrowtreeevent;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.event.terraingen.SaplingGrowTreeEvent;
import rtg.config.rtg.ConfigRTG;
import rtg.util.Logger;
import rtg.util.RandomUtil;
import rtg.world.WorldTypeRTG;
import rtg.world.biome.WorldChunkManagerRTG;
import rtg.world.biome.realistic.RealisticBiomeBase;
import rtg.world.gen.feature.tree.rtg.TreeRTG;
import cpw.mods.fml.common.eventhandler.Event.Result;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class SaplingGrowTreeRTG
{

    public SaplingGrowTreeRTG()
    {

    }

	@SubscribeEvent
	public void onSaplingGrowTree(SaplingGrowTreeEvent event)
	{
		
		// Are RTG saplings enabled?
		if (!ConfigRTG.enableRTGSaplings) {
			return;
		}
		
		// Are we in an RTG world? Do we have RTG's chunk manager?
		if (!(event.world.getWorldInfo().getTerrainType() instanceof WorldTypeRTG) || !(event.world.getWorldChunkManager() instanceof WorldChunkManagerRTG)) {
			return;
		}
		
		Random rand = event.rand;
		
		// Should we generate a vanilla tree instead?
		if (rand.nextInt(ConfigRTG.rtgTreeChance) != 0) {
			Logger.debug("Skipping RTG tree generation.");
			return;
		}		
		
		World world = event.world;
		int x = event.x;
		int y = event.y;
		int z = event.z;

		Block saplingBlock = world.getBlock(x, y, z);
		byte saplingMeta = (byte) saplingBlock.getDamageValue(world, x, y, z);

		WorldChunkManagerRTG cmr = (WorldChunkManagerRTG) world.getWorldChunkManager();
		//BiomeGenBase bgg = cmr.getBiomeGenAt(x, z);
		BiomeGenBase bgg = world.getBiomeGenForCoords(x, z);
		RealisticBiomeBase rb = RealisticBiomeBase.getBiome(bgg.biomeID);
		ArrayList<TreeRTG> biomeTrees = rb.rtgTrees;
		
		Logger.debug("Biome = %s", rb.baseBiome.biomeName);
		Logger.debug("Ground Sapling Block = %s", saplingBlock.getLocalizedName());
		Logger.debug("Ground Sapling Meta = %d", saplingMeta);

		if (biomeTrees.size() > 0) {
			
			// First, let's get all of the trees in this biome that match the sapling on the ground.
			ArrayList<TreeRTG> validTrees = new ArrayList<TreeRTG>();
			
			for (int i = 0; i < biomeTrees.size(); i++) {
				
				Logger.debug("Biome Tree #%d = %s", i, biomeTrees.get(i).getClass().getName());
				Logger.debug("Biome Tree #%d Sapling Block = %s", i, biomeTrees.get(i).saplingBlock.getClass().getName());
				Logger.debug("Biome Tree #%d Sapling Meta = %d", i, biomeTrees.get(i).saplingMeta);
				
				if (saplingBlock == biomeTrees.get(i).saplingBlock && saplingMeta == biomeTrees.get(i).saplingMeta) {
					validTrees.add(biomeTrees.get(i));
					Logger.debug("Valid tree found!");
				}
			}
			
			// If there are valid trees, then proceed; otherwise, let's get out here.
			if (validTrees.size() > 0) {
				
				// Get a random tree from the list of valid trees.
				TreeRTG tree = validTrees.get(rand.nextInt(validTrees.size()));
				
				Logger.debug("Tree = %s", tree.getClass().getName());

				// Set the trunk size if min/max values have been set.
				if (tree.minTrunkSize > 0 && tree.maxTrunkSize > tree.minTrunkSize) {
					tree.trunkSize = RandomUtil.getRandomInt(rand, tree.minTrunkSize, tree.maxTrunkSize);
				}
				
				// Set the crown size if min/max values have been set.
				if (tree.minCrownSize > 0 && tree.maxCrownSize > tree.minCrownSize) {
					tree.crownSize = RandomUtil.getRandomInt(rand, tree.minCrownSize, tree.maxCrownSize);
				}

				/**
				 * Set the generateFlag to what it needs to be for growing trees from saplings,
				 * generate the tree, and then set it back to what it was before.
				 * 
				 * TODO: Does this affect the generation of normal RTG trees?
				 */
				int oldFlag = tree.generateFlag;
				tree.generateFlag = 3;
				boolean generated = tree.generate(world, rand, x, y, z);
				tree.generateFlag = oldFlag;

				if (generated) {
					
					// Prevent the original tree from generating.
					event.setResult(Result.DENY);
					
					// Sometimes we have to remove the sapling manually because some trees grow around it, leaving the original sapling.
					if (world.getBlock(x, y, z) == saplingBlock) {
						world.setBlock(x, y, z, Blocks.air, (byte)0, 2);
					}
				}
			}
			else {
				
				Logger.debug("There are no RTG trees associated with the sapling on the ground. Generating a vanilla tree instead.");
			}
		}
	}
}