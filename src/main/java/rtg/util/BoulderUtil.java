package rtg.util;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import rtg.config.rtg.ConfigRTG;
import cpw.mods.fml.common.registry.GameData;
import exterminatorJeff.undergroundBiomes.api.BlockCodes;

public class BoulderUtil
{
	private final static ModPresenceTester undergroundBiomesMod = new ModPresenceTester("UndergroundBiomes");
	private final static ModPresenceTester harderUndergroundMod = new ModPresenceTester("HarderUnderground");
	
    // Create UBColumnCache only if UB is present.
    private static UBColumnCache ubColumnCache = undergroundBiomesMod.present() ? new UBColumnCache() : null;
    
    // If the Harder Underground mod is installed, then let's use unstable cobble instead of vanilla cobble.
    private static Block unstableCobbleBlock = harderUndergroundMod.present() ? GameData.getBlockRegistry().getObject("HarderUnderground:unstable_stone") : Blocks.cobblestone;
    private static byte unstableCobbleMeta = harderUndergroundMod.present() ? (byte)3 : (byte)0;
	
    public Block getBoulderBlock(Block defaultBlock, int worldX, int worldY, int worldZ)
    {
    	if (defaultBlock == Blocks.cobblestone) {
    		
	        if (undergroundBiomesMod.present() && ConfigRTG.enableUBCBoulders) {
	            
	            BlockCodes cobble = ubColumnCache.column(worldX, worldZ).cobblestone(worldY);
	            
	            return cobble.block;
	        }
	        else if (harderUndergroundMod.present()) {
	        	
	        	return unstableCobbleBlock;
	        }
	        else {
	            
	            return defaultBlock;
	        }
    	}
    	else {
    		
    		return defaultBlock;
    	}
    }
    
    public byte getBoulderMeta(Block defaultBlock, byte defaultMeta, int worldX, int worldY, int worldZ)
    {
    	if (defaultBlock == Blocks.cobblestone) {
    		
	        if ((undergroundBiomesMod.present())) {
	
	            BlockCodes cobble = ubColumnCache.column(worldX, worldZ).cobblestone(worldY);
	            
	            return (byte) cobble.metadata;
	        }
	        else if (harderUndergroundMod.present()) {
	        	
	        	return unstableCobbleMeta;
	        }
	        else {
	            
	            return defaultMeta;
	        }
    	}
    	else {
    		
    		return defaultMeta;
    	}
    }
}