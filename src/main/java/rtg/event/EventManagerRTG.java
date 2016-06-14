package rtg.event;

import java.util.ArrayList;
import java.util.Random;
import java.util.WeakHashMap;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.structure.MapGenStructureIO;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.terraingen.BiomeEvent;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent;
import net.minecraftforge.event.terraingen.InitMapGenEvent;
import net.minecraftforge.event.terraingen.OreGenEvent;
import net.minecraftforge.event.terraingen.SaplingGrowTreeEvent;
import net.minecraftforge.event.terraingen.WorldTypeEvent;
import net.minecraftforge.event.world.ChunkEvent;
import net.minecraftforge.event.world.WorldEvent;
import rtg.RTG;
import rtg.config.rtg.ConfigRTG;
import rtg.util.Acceptor;
import rtg.util.Logger;
import rtg.util.RandomUtil;
import rtg.world.WorldTypeRTG;
import rtg.world.biome.WorldChunkManagerRTG;
import rtg.world.biome.realistic.RealisticBiomeBase;
import rtg.world.gen.MapGenCavesRTG;
import rtg.world.gen.MapGenRavineRTG;
import rtg.world.gen.feature.tree.rtg.TreeRTG;
import rtg.world.gen.genlayer.RiverRemover;
import rtg.world.gen.structure.MapGenScatteredFeatureRTG;
import rtg.world.gen.structure.MapGenVillageRTG;
import cpw.mods.fml.common.eventhandler.Event.Result;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class EventManagerRTG
{

    public RealisticBiomeBase biome = null;
    private WeakHashMap<Integer,Acceptor<ChunkEvent.Load>> chunkLoadEvents =
            new WeakHashMap<Integer,Acceptor<ChunkEvent.Load>> ();
    
    public EventManagerRTG()
    {
        MapGenStructureIO.registerStructure(MapGenScatteredFeatureRTG.Start.class, "rtg_MapGenScatteredFeatureRTG");
        MapGenStructureIO.registerStructure(MapGenVillageRTG.Start.class, "rtg_MapGenVillageRTG");
    }
    
	@SubscribeEvent(priority = EventPriority.LOW)
	public void eventListenerRTG(InitMapGenEvent event) {
	    
        Logger.debug("event type = %s", event.type.toString());
        Logger.debug("event originalGen = %s", event.originalGen.toString());
	    
		if (event.type == InitMapGenEvent.EventType.SCATTERED_FEATURE) {
			event.newGen = new MapGenScatteredFeatureRTG();
		}
        else if (event.type == InitMapGenEvent.EventType.VILLAGE) {
            
            if (ConfigRTG.enableVillageModifications) {
                event.newGen = new MapGenVillageRTG();
            }
        }
        else if (event.type == InitMapGenEvent.EventType.CAVE) {
            
            if (ConfigRTG.enableCaveModifications) {
                
                event.newGen = new MapGenCavesRTG();
            }
        }
        else if (event.type == InitMapGenEvent.EventType.RAVINE) {
            
            if (ConfigRTG.enableRavineModifications) {
                
                event.newGen = new MapGenRavineRTG();
            }
        }
		
        Logger.debug("event newGen = %s", event.newGen.toString());
	}
	
    @SubscribeEvent
    public void eventListenerRTG(WorldEvent.Load event) {

        if (!(event.world.getWorldInfo().getTerrainType() instanceof WorldTypeRTG)) {
            
            MinecraftForge.TERRAIN_GEN_BUS.unregister(RTG.eventMgr);
            MinecraftForge.ORE_GEN_BUS.unregister(RTG.eventMgr);
            MinecraftForge.EVENT_BUS.unregister(RTG.eventMgr);
        }
    }

    @SubscribeEvent
    public void onGenerateMinable(OreGenEvent.GenerateMinable event) {

        switch (event.type) {
            
            case COAL:
                
                if (!ConfigRTG.generateOreCoal) {
                    event.setResult(Result.DENY);
                }
                
                break;
            
            case IRON:
                
                if (!ConfigRTG.generateOreIron) {
                    event.setResult(Result.DENY);
                }
                
                break;
            
            case GOLD:
                
                if (!ConfigRTG.generateOreGold) {
                    event.setResult(Result.DENY);
                }
                
                break;
            
            case DIAMOND:
                
                if (!ConfigRTG.generateOreDiamond) {
                    event.setResult(Result.DENY);
                }
                
                break;
            
            case REDSTONE:
                
                if (!ConfigRTG.generateOreRedstone) {
                    event.setResult(Result.DENY);
                }
                
                break;
            
            case LAPIS:
                
                if (!ConfigRTG.generateOreLapis) {
                    event.setResult(Result.DENY);
                }
                
                break;
            
            default:
                break;
        }
    }

    @SubscribeEvent
    public void onBiomeGenInit(WorldTypeEvent.InitBiomeGens event) {
        
        // only handle RTG world type
        if (!event.worldType.getWorldTypeName().equalsIgnoreCase("RTG")) return;

        if (event.newBiomeGens[0].getClass().getName().contains("GenLayerEB")) return;
        boolean stripRivers = true; // This used to be a config option. Hardcoding until we have a need for the option.
        
        if (stripRivers) {
            try {
                event.newBiomeGens = new RiverRemover().riverLess(event.originalBiomeGens);
            } catch (ClassCastException ex) {
                //throw ex;
                // failed attempt because the GenLayers don't end with GenLayerRiverMix
            }
        }
    }
    
    @SubscribeEvent
    public void onWorldLoad(WorldEvent.Load event) {
        
        if (!event.world.getWorldInfo().getTerrainType().getWorldTypeName().equalsIgnoreCase("RTG")) {
            return;
        }
        
        if (event.world.provider.dimensionId == 0) {
            
            Logger.info("World Seed: %d", event.world.getSeed());
        }
    }
    
    @SubscribeEvent
    public void onGetVillageBlockID(BiomeEvent.GetVillageBlockID event)
    {

        // Use event.biome, if that's null, fall back to our own copy
        if (this.isDesertVillageBiome((event.biome == null) ? this.biome : event.biome)) {

            Block originalBlock = event.original;

            if (originalBlock == Blocks.cobblestone || originalBlock == Blocks.planks || originalBlock == Blocks.log
                    || originalBlock == Blocks.log2 || originalBlock == Blocks.gravel) {

                event.replacement = Blocks.sandstone;
            } else if (originalBlock == Blocks.oak_stairs || originalBlock == Blocks.stone_stairs) {

                event.replacement = Blocks.sandstone_stairs;
            }

            // The event has to be cancelled in order to override the original block.
            if (event.replacement != null) {

                event.setResult(Result.DENY);
            }
        }
    }
    
    @SubscribeEvent
    public void onGetVillageBlockMeta(BiomeEvent.GetVillageBlockMeta event)
    {
        boolean replaced = false;

        // Use event.biome, if that's null, fall back to our own copy
        if (this.isDesertVillageBiome((event.biome == null) ? this.biome : event.biome)) {

            Block originalBlock = event.original;

            if (originalBlock == Blocks.log || originalBlock == Blocks.log2 || originalBlock == Blocks.cobblestone) {

                event.replacement = 0;
                replaced = true;
            }

            if (originalBlock == Blocks.planks) {

                event.replacement = 2;
                replaced = true;
            }
        }

        // The event has to be cancelled in order to override the original block.
        if (replaced) {

            event.setResult(Result.DENY);
        }
    }
    
    @SubscribeEvent
    public void preBiomeDecorate(DecorateBiomeEvent.Pre event)
    {

        //Are we in an RTG world? Do we have RTG's chunk manager?
        if (event.world.getWorldInfo().getTerrainType() instanceof WorldTypeRTG && event.world.getWorldChunkManager() instanceof WorldChunkManagerRTG) {
            
            WorldChunkManagerRTG cmr = (WorldChunkManagerRTG) event.world.getWorldChunkManager();
            this.biome = cmr.getBiomeDataAt(event.chunkX, event.chunkZ);
        }
    }
    
	@SubscribeEvent
	public void onSaplingGrowTree(SaplingGrowTreeEvent event)
	{
		
		// Are RTG saplings enabled?
		if (!ConfigRTG.enableRTGSaplings) {
			return;
		}
		
		// Are we in an RTG world? Do we have RTG's chunk manager?
		if (!(event.world.getWorldInfo().getTerrainType() instanceof WorldTypeRTG) && !(event.world.getWorldChunkManager() instanceof WorldChunkManagerRTG)) {
			return;
		}
		
		World world = event.world;
		Random rand = event.rand;
		int x = event.x;
		int y = event.y;
		int z = event.z;
		Block saplingBlock = world.getBlock(x, y, z);
		byte saplingMeta = (byte) saplingBlock.getDamageValue(world, x, y, z);
		
		Logger.debug("Ground Sapling Block = %s", saplingBlock.getLocalizedName());
		Logger.debug("Ground Sapling Meta = %d", saplingMeta);
		
		// Should we generate a vanilla tree instead?
		if (rand.nextInt(ConfigRTG.rtgTreeChance) != 0) {
			Logger.debug("Skipping RTG tree generation.");
			return;
		}
		
		WorldChunkManagerRTG cmr = (WorldChunkManagerRTG) world.getWorldChunkManager();
		BiomeGenBase bgg = cmr.getBiomeGenAt(x, z);
		RealisticBiomeBase rb = RealisticBiomeBase.getBiome(bgg.biomeID);
		
		Logger.debug("Biome = %s", rb.baseBiome.biomeName);
		
		ArrayList<TreeRTG> biomeTrees = rb.rtgTrees;
		
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

				if (tree.minCrownSize > 0 && tree.maxCrownSize > tree.minCrownSize) {
					tree.crownSize = RandomUtil.getRandomInt(rand, tree.minCrownSize, tree.maxCrownSize);
				}
				
				if (tree.minTrunkSize > 0 && tree.maxTrunkSize > tree.minTrunkSize) {
					tree.trunkSize = RandomUtil.getRandomInt(rand, tree.minTrunkSize, tree.maxTrunkSize);
				}
				
				/**
				 * Set the generateFlag to what it needs to be for growing trees from saplings,
				 * generate the tree, and then set it back to what it was before.
				 * 
				 * TODO: Does this affect the generation of normal RTG trees?
				 */
				int oldFlag = tree.generateFlag;
				tree.generateFlag = 3;
				tree.generate(world, rand, x, y, z);
				tree.generateFlag = oldFlag;
				
				// Prevent the original tree from generating.
				event.setResult(Result.DENY);
			}
			else {
				
				Logger.debug("There are no RTG trees associated with the sapling on the ground. Generating a vanilla tree instead.");
			}
		}
	}
    
    private boolean isDesertVillageBiome(BiomeGenBase biome)
    {
        if(biome == null) return false;
        if (
            BiomeDictionary.isBiomeOfType(biome, Type.HOT)
            &&
            BiomeDictionary.isBiomeOfType(biome, Type.DRY)
            &&
            BiomeDictionary.isBiomeOfType(biome, Type.SANDY)
        ) {
            return true;
        }

        return false;
    }

    @SubscribeEvent
    public void onChunkLoadEvent(ChunkEvent.Load loadEvent)  {
        Integer dimension = loadEvent.world.provider.dimensionId;
        Acceptor<ChunkEvent.Load> acceptor = chunkLoadEvents.get(dimension);
        if (acceptor != null) {
            acceptor.accept(loadEvent);
        }
    }

    public void setDimensionChunkLoadEvent(int dimension, Acceptor<ChunkEvent.Load> action) {
        chunkLoadEvents.put(dimension, action);
    }
}