package rtg.data;

import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.event.terraingen.BiomeEvent;
import cpw.mods.fml.common.eventhandler.Event.Result;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class VillageMaterials 
{
	private static VillageMaterialData[] dataList = new VillageMaterialData[256];
	
	public static void registerVillageMaterial(VillageMaterialData data)
	{
		dataList[data.biomeID] = data;
	}
	
	@SubscribeEvent
	public void getVillageBlockID(BiomeEvent.GetVillageBlockID event)
	{
		if(event.biome != null)
		{
			VillageMaterialData vmd = this.dataList[event.biome.biomeID];
			
			if(vmd != null)
			{
				if (event.original == Blocks.log) { event.replacement = vmd.logBlock; event.setResult(Result.DENY); return; }
				if (event.original == Blocks.cobblestone) { event.replacement = vmd.cobbleBlock; event.setResult(Result.DENY); return; }
				if (event.original == Blocks.planks) { event.replacement = vmd.plankBlock; event.setResult(Result.DENY); return; }
				if (event.original == Blocks.gravel) { event.replacement = vmd.pathBlock; event.setResult(Result.DENY); return; }
				if (event.original == Blocks.oak_stairs) { event.replacement = vmd.stairsWoodBlock; event.setResult(Result.DENY); return; }
				if (event.original == Blocks.stone_stairs) { event.replacement = vmd.stairsStoneBlock; event.setResult(Result.DENY); return; }
				if (event.original == Blocks.stone_slab) { event.replacement = vmd.slabsBlock; event.setResult(Result.DENY); return; }
			}
		}
	}
	
	@SubscribeEvent
	public void getVillageBlockMeta(BiomeEvent.GetVillageBlockMeta event)
	{	
		if(event.biome != null)
		{
			VillageMaterialData vmd = this.dataList[event.biome.biomeID];
			
			if(vmd != null)
			{
				if (event.original == Blocks.log) { event.replacement = vmd.logBlockMeta; event.setResult(Result.DENY); return; }
				if (event.original == Blocks.cobblestone) { event.replacement = vmd.cobbleBlockMeta; event.setResult(Result.DENY); return; }
				if (event.original == Blocks.planks) { event.replacement = vmd.plankBlockMeta; event.setResult(Result.DENY); return; }
				if (event.original == Blocks.gravel) { event.replacement = vmd.pathBlockMeta; event.setResult(Result.DENY); return; }
				if (event.original == Blocks.stone_slab) { event.replacement = vmd.slabsBlockMeta; event.setResult(Result.DENY); return; }
			}
		}
	}
}
