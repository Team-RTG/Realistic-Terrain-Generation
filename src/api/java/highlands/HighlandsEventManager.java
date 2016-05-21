package highlands;

import highlands.api.HighlandsBiomes;
import highlands.api.HighlandsBlocks;
import highlands.block.BlockHighlandsSapling;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.AchievementList;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.structure.MapGenVillage;
import net.minecraftforge.event.entity.player.BonemealEvent;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.event.terraingen.BiomeEvent.GetVillageBlockID;
import net.minecraftforge.event.terraingen.PopulateChunkEvent.Populate;
import net.minecraftforge.event.terraingen.WorldTypeEvent.BiomeSize;
import net.minecraftforge.event.world.WorldEvent.Load;
import cpw.mods.fml.common.eventhandler.Event;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class HighlandsEventManager {

	private Random rand = new Random();
	
	//allows get wood achievement for Highlands woods
	@SubscribeEvent
	public void onItemPickupWood(EntityItemPickupEvent e){
		if (e.item.getEntityItem() == new ItemStack(HighlandsBlocks.acaciaWood)
			|| e.item.getEntityItem() == new ItemStack(HighlandsBlocks.canopyWood)
			|| e.item.getEntityItem() == new ItemStack(HighlandsBlocks.firWood)
			|| e.item.getEntityItem() == new ItemStack(HighlandsBlocks.poplarWood)
			|| e.item.getEntityItem() == new ItemStack(HighlandsBlocks.redwoodWood)
			|| e.item.getEntityItem() == new ItemStack(HighlandsBlocks.palmWood)
			|| e.item.getEntityItem() == new ItemStack(HighlandsBlocks.ironWood)
			|| e.item.getEntityItem() == new ItemStack(HighlandsBlocks.mangroveWood)
			|| e.item.getEntityItem() == new ItemStack(HighlandsBlocks.ashWood)
			|| e.item.getEntityItem() == new ItemStack(HighlandsBlocks.japaneseMapleWood)
			){
			e.entityPlayer.triggerAchievement(AchievementList.mineWood);
		}
	}
	
	
	// Adds village spawning to Highlands worlds and default worlds if Highlands is enabled.
    @SubscribeEvent
	public void onWorldStart(Load e){
		if(e.world.provider.terrainType == Highlands.HL || e.world.provider.terrainType == Highlands.HLLB){
			ArrayList<BiomeGenBase> newTotalVillageBiomes = new ArrayList<BiomeGenBase>();
			newTotalVillageBiomes.addAll(MapGenVillage.villageSpawnBiomes);
			newTotalVillageBiomes.addAll(MapGenStructureConfig.hlvillagebiomes);
			
			MapGenVillage.villageSpawnBiomes = newTotalVillageBiomes;
		}
		
		// loads compatibility mob lists for all biomes
		//if(HighlandsMain.mocreaturescomp)HighlandsCompatibilityManager.mobload_biomes();
		
		//System.out.println(MapGenVillage.villageSpawnBiomes);
	}
	
	// Sets biome size for Highlands Large Biomes
    @SubscribeEvent
	public void onLoadWorldType(BiomeSize e){
		if(e.worldType == Highlands.HL){
			e.newSize = (byte)Highlands.HighlandsBiomeSizeDefault;
		}
		if(e.worldType == Highlands.HLLB){
			e.newSize = (byte)Highlands.HighlandsBiomeSizeLB;
		}
	}
	
	// Initiates the new GenLayers
    /**
    @SubscribeEvent
    public void onInitBiomeGenerators(WorldTypeEvent.InitBiomeGens event) {
    	if (event.worldType instanceof IMWWorldType) {
			//event.newBiomeGens = TerrainGenInjector.assembleModdedBiomeGenerators(event.seed, event.worldType);
			// event.newBiomeGens = GenLayerBiomeHL.initializeAllBiomeGenerators(event.seed, event.worldType)
		}
    	Logs.log(Level.INFO, "[Highlands] WorldTypeEvent.InitBiomeGens="+event.originalBiomeGens);
    	event.newBiomeGens = event.originalBiomeGens;
    	event.setResult(Result.ALLOW);
    }
    */

	
	/*
	// Prevents lakes from generating in Highlands worlds
	@ForgeSubscribe
	public void onDecorateLakes(Decorate e){
		if(e.type == Decorate.EventType.LAKE && 
				(e.world.provider.terrainType == HighlandsMain.HL || e.world.provider.terrainType == HighlandsMain.HLLB || HighlandsMain.highlandsInDefaultFlag)){
			e.setResult(Event.Result.DENY);
			//System.out.println("Stopped a tiny pond from generating");
		}
	}
	*/
	
	// Prevents populate lakes from generating in Highlands worlds-
	// Biomes that don't decorate lakes actually won't have any.
    @SubscribeEvent
	public void onDecorateLakes2(Populate e){
		if(e.type == Populate.EventType.LAKE && 
				(e.world.provider.terrainType == Highlands.HL || e.world.provider.terrainType == Highlands.HLLB)){
			e.setResult(Event.Result.DENY);
			//System.out.println("Stopped a tiny pond from generating");
		}
	}
	
    @SubscribeEvent
    public void bonemealEvent(BonemealEvent e)
    {
        if (!e.world.isRemote)
        {
        	boolean isHLSapling = false;
        	for(Block b: HighlandsBlocks.saplings)if(b != null && e.block == b)isHLSapling = true;
            if (isHLSapling)
            {
                BlockHighlandsSapling sapling = (BlockHighlandsSapling)e.block;
                e.setResult(Event.Result.ALLOW);
                if(e.entityPlayer.capabilities.isCreativeMode)
                	sapling.growTree(e.world, e.x, e.y, e.z, rand);
                else 
                	sapling.updateTick(e.world, e.x, e.y, e.z, rand);
            }
        }
    }
	
	
	// sets default village blocks
	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void onVillageSelectBlock(GetVillageBlockID event){
		if(event.biome != null && HighlandsBiomes.sahel != null && HighlandsBiomes.outback != null && BiomeGenBase.icePlains != null){
			if (event.biome.biomeName.equals(HighlandsBiomes.sahel.biomeName) || event.biome.biomeName.equals(HighlandsBiomes.outback.biomeName))
	        {
				if (event.original == Blocks.log)event.replacement = Blocks.log;
	            if (event.original == Blocks.cobblestone)event.replacement = Blocks.sandstone;
	            if (event.original == Blocks.planks)event.replacement = Blocks.planks;
	            if (event.original == Blocks.oak_stairs)event.replacement = Blocks.oak_stairs;
	            if (event.original == Blocks.stone_stairs)event.replacement = Blocks.sandstone_stairs;
	            if (event.original == Blocks.gravel)event.replacement = Blocks.gravel;
	        }
			if (event.biome.biomeName.equals(BiomeGenBase.icePlains.biomeName))
	        {
	            if (event.original == Blocks.log)event.replacement = Blocks.log;
	            if (event.original == Blocks.cobblestone)event.replacement = Blocks.cobblestone;
	            if (event.original == Blocks.planks)event.replacement = Blocks.snow;
	            if (event.original == Blocks.oak_stairs)event.replacement = Blocks.oak_stairs;
	            if (event.original == Blocks.stone_stairs)event.replacement = Blocks.stone_stairs;
	            if (event.original == Blocks.gravel)event.replacement = Blocks.gravel;
	        }
		}
	}
}




