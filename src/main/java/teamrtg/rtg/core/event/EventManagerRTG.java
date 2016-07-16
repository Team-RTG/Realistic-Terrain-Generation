package teamrtg.rtg.core.event;

import java.util.ArrayList;
import java.util.Random;
import java.util.WeakHashMap;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.terraingen.InitMapGenEvent;
import net.minecraftforge.event.terraingen.OreGenEvent;
import net.minecraftforge.event.terraingen.SaplingGrowTreeEvent;
import net.minecraftforge.event.terraingen.WorldTypeEvent;
import net.minecraftforge.event.world.ChunkEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.eventhandler.Event.Result;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import teamrtg.rtg.api.module.Mods;
import teamrtg.rtg.api.tools.feature.tree.rtg.TreeRTG;
import teamrtg.rtg.api.util.Acceptor;
import teamrtg.rtg.api.util.debug.Logger;
import teamrtg.rtg.api.util.math.RandomUtil;
import teamrtg.rtg.api.world.biome.RTGBiome;
import teamrtg.rtg.core.RTG;
import teamrtg.rtg.core.world.BiomeProviderRTG;
import teamrtg.rtg.core.world.WorldTypeRTG;
import teamrtg.rtg.core.world.gen.MapGenCavesRTG;
import teamrtg.rtg.core.world.gen.MapGenRavineRTG;
import teamrtg.rtg.core.world.gen.genlayer.RiverRemover;
import teamrtg.rtg.core.world.gen.structure.MapGenScatteredFeatureRTG;
import teamrtg.rtg.core.world.gen.structure.MapGenVillageRTG;
import teamrtg.rtg.core.world.gen.structure.StructureOceanMonumentRTG;

public class EventManagerRTG
{

    private final LoadChunkRTG LOAD_CHUNK_RTG = new LoadChunkRTG();
    private final GenerateMinableRTG GENERATE_MINABLE_RTG = new GenerateMinableRTG();
    private final InitBiomeGensRTG INIT_BIOME_GENS_RTG = new InitBiomeGensRTG();
    private final InitMapGenRTG INIT_MAP_GEN_RTG = new InitMapGenRTG();
    private final SaplingGrowTreeRTG SAPLING_GROW_TREE_RTG = new SaplingGrowTreeRTG();
    private final WorldEventRTG WORLD_EVENT_RTG = new WorldEventRTG();
    private final RTGEventRegister RTG_EVENT_REGISTER = new RTGEventRegister();

    private WeakHashMap<Integer, Acceptor<ChunkEvent.Load>> chunkLoadEvents = new WeakHashMap<>();
    private boolean registered = false;
    private long worldSeed;
    private final String EVENT_SYSTEM = "RTG Event System: ";
	
    public EventManagerRTG() {
        // These should be registered once, and stay registered -srs
        MinecraftForge.TERRAIN_GEN_BUS.register(RTG_EVENT_REGISTER);
        MinecraftForge.EVENT_BUS.register(WORLD_EVENT_RTG);
        Logger.info(EVENT_SYSTEM + "Initialising EventManagerRTG");
    }

    public class LoadChunkRTG
    {
        LoadChunkRTG() {
            Logger.debug(EVENT_SYSTEM + "Initialising LoadChunkRTG");
        }

		@SubscribeEvent
		public void loadChunkRTG(ChunkEvent.Load loadEvent)  {
			Integer dimension = loadEvent.getWorld().provider.getDimension();
			Acceptor<ChunkEvent.Load> acceptor = chunkLoadEvents.get(dimension);
			if (acceptor != null) {
				acceptor.accept(loadEvent);
			}
		}
    }
    
    public class GenerateMinableRTG
    {
        GenerateMinableRTG() {
            Logger.debug(EVENT_SYSTEM + "Initialising GenerateMinableRTG");
        }

        @SubscribeEvent
        public void generateMinableRTG(OreGenEvent.GenerateMinable event) {

        	if (Mods.RTG.config.GENERATE_ORES.get()) {
            	
    	    	// COAL, DIAMOND, DIRT, GOLD, GRAVEL, IRON, LAPIS, REDSTONE, QUARTZ, DIORITE, GRANITE, ANDESITE, EMERALD, SILVERFISH, CUSTOM
    	        switch (event.getType()) {
    	            
    		        case COAL:
    		            if (!Mods.RTG.config.GENERATE_ORES_COAL.get()) { event.setResult(Result.DENY); }
    		            return;
    		            
    		        case DIAMOND:
    		            if (!Mods.RTG.config.GENERATE_ORES_DIAMOND.get()) { event.setResult(Result.DENY); }
    		            return;
    	            
    		        case DIRT:
    		            if (!Mods.RTG.config.GENERATE_ORES_DIRT.get()) { event.setResult(Result.DENY); }
    		            return;
    		            
    	            case GOLD:
    	                if (!Mods.RTG.config.GENERATE_ORES_GOLD.get()) { event.setResult(Result.DENY); }
    	                return;
    		            
    		        case GRAVEL:
    		            if (!Mods.RTG.config.GENERATE_ORES_GRAVEL.get()) { event.setResult(Result.DENY); }
    		            return;
    	
    	            case IRON:
    	                if (!Mods.RTG.config.GENERATE_ORES_IRON.get()) { event.setResult(Result.DENY); }
    	                return;
    	                
    	            case LAPIS:
    	                if (!Mods.RTG.config.GENERATE_ORES_LAPIS.get()) { event.setResult(Result.DENY); }
    	                return;
    	                
    	            case REDSTONE:
    	                if (!Mods.RTG.config.GENERATE_ORES_REDSTONE.get()) { event.setResult(Result.DENY); }
    	                return;
    	                
    	            case DIORITE:
    	                if (!Mods.RTG.config.GENERATE_ORES_DIORITE.get()) { event.setResult(Result.DENY); }
    	                return;                
    	
    	            case GRANITE:
    	                if (!Mods.RTG.config.GENERATE_ORES_GRANITE.get()) { event.setResult(Result.DENY); }
    	                return;
    	                
    	            case ANDESITE:
    	                if (!Mods.RTG.config.GENERATE_ORES_ANDESITE.get()) { event.setResult(Result.DENY); }
    	                return;
    	                
    	            case EMERALD:
    	                if (!Mods.RTG.config.GENERATE_ORES_EMERALD.get()) { event.setResult(Result.DENY); }
    	                return;
    	
    	            default:
    	            	return;
    	        }
        	}
        }
    }
    
    public class InitBiomeGensRTG
    {
        InitBiomeGensRTG()
        {
            Logger.debug(EVENT_SYSTEM + "Initialising InitBiomeGensRTG");
        }

        @SubscribeEvent
        public void initBiomeGensRTG(WorldTypeEvent.InitBiomeGens event) {

            // only handle RTG world type
            if (!event.getWorldType().getWorldTypeName().equalsIgnoreCase("RTG")) return;

            try {
                event.setNewBiomeGens(new RiverRemover().riverLess(event.getOriginalBiomeGens()));
            } catch (ClassCastException ex) {
                // failed attempt because the GenLayers don't end with GenLayerRiverMix
            }
        }
    }
    
    public class InitMapGenRTG
    {
        InitMapGenRTG() {
            Logger.debug(EVENT_SYSTEM + "Initialising InitMapGenRTG");
        }

        @SubscribeEvent(priority = EventPriority.LOW)
        public void initMapGenRTG(InitMapGenEvent event) {

            Logger.debug("event type = %s", event.getType().toString());
            Logger.debug("event originalGen = %s", event.getOriginalGen().toString());

            switch (event.getType()) {
            
	            case SCATTERED_FEATURE:
	            	event.setNewGen(new MapGenScatteredFeatureRTG());
	            	break;
	            
	            case VILLAGE:
	                if (Mods.RTG.config.ENABLE_VILLAGE_MODIFICATIONS.get()) {
	                    event.setNewGen(new MapGenVillageRTG());
	                }
	                break;
	            
	            case CAVE:
	                if (Mods.RTG.config.ENABLE_CAVE_MODIFICATIONS.get()) {
	                    event.setNewGen(new MapGenCavesRTG());
	                }
	                break;
	            
	            case RAVINE:
	                if (Mods.RTG.config.ENABLE_RAVINE_MODIFICATIONS.get()) {
	                    event.setNewGen(new MapGenRavineRTG());
	                }
	                break;
	                
	            case OCEAN_MONUMENT:
	            	event.setNewGen(new StructureOceanMonumentRTG());
	            	break;
	            	
	            default:
	            	break;
            }

            Logger.debug("event newGen = %s", event.getNewGen().toString());
        }
    }
    
    public class SaplingGrowTreeRTG
    {
        SaplingGrowTreeRTG() {
            Logger.debug(EVENT_SYSTEM + "Initialising SaplingGrowTreeRTG");
        }

        @SubscribeEvent
        public void saplingGrowTreeRTG(SaplingGrowTreeEvent event) {
        	
    		// Are RTG saplings enabled?
    		if (!Mods.RTG.config.ENABLE_SAPLINGS.get()) {
    			return;
    		}
    		
    		// Are we in an RTG world? Do we have RTG's chunk manager?
    		if (!(event.getWorld().getWorldInfo().getTerrainType() instanceof WorldTypeRTG) || !(event.getWorld().getBiomeProvider() instanceof BiomeProviderRTG)) {
    			return;
    		}
    		
    		Random rand = event.getRand();
    		
    		// Should we generate a vanilla tree instead?
    		if (rand.nextInt(Mods.RTG.config.RTG_TREE_CHANCE.get()) != 0) {
    			Logger.debug("Skipping RTG tree generation.");
    			return;
    		}		
    		
    		World world = event.getWorld();
    		BlockPos pos = event.getPos();
    		IBlockState saplingBlock = world.getBlockState(pos);

    		BiomeProviderRTG cmr = (BiomeProviderRTG) event.getWorld().getBiomeProvider();
    		Biome vb = world.getBiome(pos);
    		RTGBiome rb = RTGBiome.forBiome(vb.getIdForBiome(vb));
    		ArrayList<TreeRTG> biomeTrees = rb.rtgTrees;
    		
    		Logger.debug("Biome = %s", vb.getBiomeName());
    		Logger.debug("Ground Sapling Block = %s", saplingBlock.getBlock().getLocalizedName());
    		Logger.debug("Ground Sapling Meta = %d", saplingBlock.getBlock().getMetaFromState(saplingBlock));

    		if (biomeTrees.size() > 0) {
    			
    			// First, let's get all of the trees in this biome that match the sapling on the ground.
    			ArrayList<TreeRTG> validTrees = new ArrayList<TreeRTG>();
    			
    			for (int i = 0; i < biomeTrees.size(); i++) {
    				
    				Logger.debug("Biome Tree #%d = %s", i, biomeTrees.get(i).getClass().getName());
    				Logger.debug("Biome Tree #%d Sapling Block = %s", i, biomeTrees.get(i).saplingBlock.getBlock().getLocalizedName());
    				Logger.debug("Biome Tree #%d Sapling Meta = %d", i, biomeTrees.get(i).saplingBlock.getBlock().getMetaFromState(biomeTrees.get(i).saplingBlock));
    				
    				if (saplingBlock == biomeTrees.get(i).saplingBlock) {
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
    				boolean generated = tree.generate(world, rand, pos);
    				tree.generateFlag = oldFlag;

    				if (generated) {
    					
    					// Prevent the original tree from generating.
    					event.setResult(Result.DENY);
    					
    					// Sometimes we have to remove the sapling manually because some trees grow around it, leaving the original sapling.
    					if (world.getBlockState(pos) == saplingBlock) {
    						world.setBlockState(pos, Blocks.AIR.getDefaultState(), 2);
    					}
    				}
    			}
    			else {
    				
    				Logger.debug("There are no RTG trees associated with the sapling on the ground. Generating a vanilla tree instead.");
    			}
    		}
        }
    }
    
    public class WorldEventRTG
    {
        WorldEventRTG() {
            Logger.debug(EVENT_SYSTEM + "Initialising WorldEventRTG");
        }

        @SubscribeEvent
        public void onWorldLoad(WorldEvent.Load event) {
        	
            // This event fires for each dimension loaded (and then one last time in which it returns 0?),
            // so initialise a field to 0 and set it to the world seed and only display it in the log once.
            if (worldSeed != event.getWorld().getSeed() && event.getWorld().getSeed() != 0) {

                worldSeed = event.getWorld().getSeed();
                Logger.info("World Seed: " + worldSeed);
            }
        }

        @SubscribeEvent
        public void onWorldUnload(WorldEvent.Unload event) {
        	
            // Reset WORLD_SEED so that it logs on the next server start if the seed is the same as the last load.
            worldSeed = 0;
        }
    }
    
    public class RTGEventRegister
    {
        RTGEventRegister() {
            Logger.debug(EVENT_SYSTEM + "Initialising RTGEventRegister");
        }

        @SubscribeEvent
        public void registerRTGEventHandlers(WorldTypeEvent.InitBiomeGens event) {
        	
            if (event.getWorldType() instanceof WorldTypeRTG) {
                if (!registered) {
                    Logger.info(EVENT_SYSTEM + "Registering RTG's Terrain Event Handlers...");
                    RTG.eventMgr.registerEventHandlers();
                    if (registered) Logger.info(EVENT_SYSTEM + "RTG's Terrain Event Handlers have been registered successfully.");
                }
            }
            else {
                if (registered) RTG.eventMgr.unRegisterEventHandlers();
            }
        }
    }
    
    public void registerEventHandlers() {
        MinecraftForge.EVENT_BUS.register(LOAD_CHUNK_RTG);
        MinecraftForge.ORE_GEN_BUS.register(GENERATE_MINABLE_RTG);
        MinecraftForge.TERRAIN_GEN_BUS.register(INIT_BIOME_GENS_RTG);
        MinecraftForge.TERRAIN_GEN_BUS.register(INIT_MAP_GEN_RTG);
        MinecraftForge.TERRAIN_GEN_BUS.register(SAPLING_GROW_TREE_RTG);
        registered = true;
    }
    
    public void unRegisterEventHandlers() {
        MinecraftForge.EVENT_BUS.unregister(LOAD_CHUNK_RTG);
        MinecraftForge.ORE_GEN_BUS.unregister(GENERATE_MINABLE_RTG);
        MinecraftForge.TERRAIN_GEN_BUS.unregister(INIT_BIOME_GENS_RTG);
        MinecraftForge.TERRAIN_GEN_BUS.unregister(INIT_MAP_GEN_RTG);
        MinecraftForge.TERRAIN_GEN_BUS.unregister(SAPLING_GROW_TREE_RTG);
        registered = false;
    }
    
	public void setDimensionChunkLoadEvent(int dimension, Acceptor<ChunkEvent.Load> action) {
		chunkLoadEvents.put(dimension, action);
	}
    
    public boolean isRegistered() {
        return registered;
    }
}