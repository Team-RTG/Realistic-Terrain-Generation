package rtg.api.world.deco;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenSavannaTree;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate;
import net.minecraftforge.fml.common.eventhandler.Event;
import rtg.RTGConfig;
import rtg.api.event.DecorateBiomeEventRTG;
import rtg.api.util.BlockUtil;
import rtg.api.util.ChunkInfo;
import rtg.api.util.BlockUtil.MatchType;
import rtg.api.world.RTGWorld;
import rtg.api.world.biome.IRealisticBiome;
import rtg.api.world.gen.feature.WorldGenShrubRTG;
import rtg.api.world.gen.feature.tree.rtg.TreeMaterials;
import rtg.api.world.gen.feature.tree.rtg.TreeDensityLimiter;
import rtg.api.world.gen.feature.tree.rtg.TreeRTG;
import rtg.api.world.gen.feature.tree.rtg.TreeRTGAcaciaAbyssinica;
import rtg.api.world.gen.feature.tree.rtg.TreeRTGAcaciaAbyssinicaMega;
import rtg.api.world.gen.feature.tree.rtg.TreeRTGAcaciaBucheri;
import rtg.api.world.gen.feature.tree.rtg.TreeRTGQuercusFalcata;
import rtg.api.world.gen.feature.tree.rtg.TreeRTGQuercusNigra;
import rtg.api.world.gen.feature.tree.rtg.TreeRTGQuercusRobur;

/**
 * Variable Trees
 * This class make trees of variable type and height based on a noise parameter from ChunkInfo
 * 
 */

public class DecoVariableAcacia extends DecoVariableTree {
	
	public DecoVariableAcacia() {
		 tallTree = new TreeRTGAcaciaAbyssinicaMega();
	     mediumTree = new TreeRTGAcaciaAbyssinica();
	     smallTree = new TreeRTGAcaciaBucheri();
	     this.materials = this.materialsPicker.acacia;
	     this.averageHeightSqrt += 0f;
	 	 mediumTreeMinimumHeight = 11; // shortest allowed tall tree
		 mediumTreeMinimumVariability = 3; // this less 1 (Random.nextInt()) added to minimum for largest allowed medium tree
		 smallTreeMinimumHeight = 6; // etc.
		 smallTreeMinimumVariability = 4;
		 averageHeightSqrt = 3f; 
		 heightNoiseVariability = 1f; 
         localHeightSqrtVariability = 0.25f; 
		
	}
	
    @Override
    public void generate(final IRealisticBiome biome, final RTGWorld rtgWorld, final Random rand, final ChunkPos chunkPos, final float river, final boolean hasVillage, ChunkInfo chunkInfo) {
    	// duped from DecoTree to add debuggers

        final BlockPos offsetPos = getOffsetPos(chunkPos);
        /*
         * Determine how many trees we're going to try to generate (loopCount).
         * The actual number of trees that end up being generated could be *less* than this value,
         * depending on environmental conditions.
         */

        float noise = distribution.getValue(offsetPos, rtgWorld.treeDistributionNoise());
        // square and reduce so acacias are usually highly scattered but occasionally dense
        noise = noise*noise/14f;
        int loopCount = (int)noise;
        if (loopCount <1 && noise>0) loopCount = 1;

        // Now let's check the configs to see if we should increase/decrease this value.
        int newCount = this.applyConfigMultipliers(loopCount, biome);

        if (newCount < 1) return;
        if (loopCount != newCount) {
            noise *= (float)newCount/(float)loopCount;
        }

        /*
         * Since RTG posts a TREE event for each batch of trees it tries to generate (instead of one event per chunk),
         * we post this custom event so that we can pass the number of trees RTG expects to generate in each batch.
         *
         * This provides more contextual information to mods like Recurrent Complex, which can use the info to better
         * determine how to handle each batch of trees.
         *
         * Because the custom event extends DecorateBiomeEvent.Decorate, it still works with mods that don't need
         * the additional context.
         */
        //TODO [1.12] Trees should just generate how they do in the vanilla BiomeDecorator::genDecorations and use the Forge event.
        DecorateBiomeEventRTG.DecorateRTG event = new DecorateBiomeEventRTG.DecorateRTG(rtgWorld.world(), rand, offsetPos, Decorate.EventType.TREE, loopCount);
        MinecraftForge.TERRAIN_GEN_BUS.post(event);

        if (event.getResult() != Event.Result.DENY) {

            newCount = event.getModifiedAmount();
            if (newCount < 1) return;
            if (loopCount != newCount) {
                noise *= (float)newCount/(float)loopCount;
            }

            // TODO: [1.12] This should be done in #setLeavesBlock.
            DecoBase.tweakTreeLeaves(this, false, true);

            TreeDensityLimiter treesRemaining = new TreeDensityLimiter(noise);
            while (treesRemaining.notDone()) {
                final BlockPos pos = offsetPos.add(rand.nextInt(16), 0, rand.nextInt(16));
                int y = rtgWorld.world().getHeight(pos).getY();
                if (y <= this.maxY && y >= this.minY && isValidTreeCondition(noise, rand)) {

                    // If we're in a village, check to make sure the tree has extra room to grow to avoid corrupting the village.
                    if (hasVillage) {
                        //if (BlockUtil.checkVerticalBlocks(MatchType.ALL, rtgWorld.world(), pos, -1, Blocks.FARMLAND) ||
                            //!BlockUtil.checkAreaBlocks(MatchType.ALL_IGNORE_REPLACEABLE, rtgWorld.world(), pos, 2)) {
                            return;
                        //}
                    }
                    // get a suitable tree Type
                    //treesRemaining -= doVariableGenerate(rand, rtgWorld, chunkInfo, pos,y,materialFor(pos,rtgWorld,rand), treesRemaining);
                    doVariableGenerate(rand, chunkInfo, pos,y, treesRemaining);
                } else {
                	treesRemaining.allowed(1f, rand);
                }

            }
        }
        else if (RTGConfig.enableDebugging()) {
        }
    }

	protected WorldGenAbstractTree vanillaTree() {
		return new WorldGenSavannaTree(false);
	}
}

