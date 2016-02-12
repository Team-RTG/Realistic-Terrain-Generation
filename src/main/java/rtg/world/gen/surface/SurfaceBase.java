package rtg.world.gen.surface;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import rtg.api.biome.BiomeConfig;
import rtg.config.rtg.ConfigRTG;
import rtg.util.CellNoise;
import rtg.util.ModPresenceTester;
import rtg.util.OpenSimplexNoise;
import rtg.util.UBColumnCache;
import cpw.mods.fml.common.registry.GameData;
import exterminatorJeff.undergroundBiomes.api.BlockCodes;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

public class SurfaceBase
{
	protected Block topBlock;
	protected Block fillerBlock;
	protected BiomeConfig biomeConfig;

    private final static ModPresenceTester undergroundBiomesMod = new ModPresenceTester("UndergroundBiomes");
    // create UBColumnCache only if UB is present
    private static UBColumnCache ubColumnCache = undergroundBiomesMod.present() ? new UBColumnCache() : null;

    public SurfaceBase(BiomeConfig config, Block top, Block fill)
    {
        topBlock = top;
        fillerBlock = fill;
        biomeConfig = config;
        
//        if (config == null) {
//            throw new RuntimeException("CONFIG IS NULL");
//        }
//        
//        if (BiomeConfig.surfaceTopBlockId == null) {
//            throw new RuntimeException("surfaceTopBlockId IS NULL");
//        }
//        
//        String userTopBlock = config._string(BiomeConfig.surfaceTopBlockId);
//        if (this.isValidBlockId(userTopBlock)) {
//            
//            try {
//                topBlock = GameData.getBlockRegistry().getObject(userTopBlock);
//            }
//            catch (Exception e) {
//                // Do nothing.
//            }
//        }
//        
//        String userFillerBlock = config._string(BiomeConfig.surfaceFillerBlockId);
//        if (this.isValidBlockId(userFillerBlock)) {
//            
//            try {
//                fillerBlock = GameData.getBlockRegistry().getObject(userFillerBlock);
//            }
//            catch (Exception e) {
//                // Do nothing.
//            }
//        }
    }
	
	public void paintTerrain(Block[] blocks, byte[] metadata, int i, int j, int x, int y, int depth, World world, Random rand, OpenSimplexNoise simplex, CellNoise cell, float[] noise, float river, BiomeGenBase[] base)
	{
	}
	
    protected Block getShadowStoneBlock(World world, int i, int j, int x, int y, int k)
    {
        if ((undergroundBiomesMod.present()) && ConfigRTG.enableUBCStoneShadowing) {
            
            return Blocks.stone;
        }
        else {
            
            return GameData.getBlockRegistry().getObject(ConfigRTG.shadowStoneBlockId);
        }
    }
    
    protected byte getShadowStoneMeta(World world, int i, int j, int x, int y, int k)
    {
        if ((undergroundBiomesMod.present()) && ConfigRTG.enableUBCStoneShadowing) {
            
            return (byte)0;
        }
        else {
            
            return (byte)ConfigRTG.shadowStoneBlockByte;
        }
    }
	
    protected Block getShadowDesertBlock(World world, int i, int j, int x, int y, int k)
    {
        if ((undergroundBiomesMod.present()) && ConfigRTG.enableUBCDesertShadowing) {
            
            return Blocks.stone;
        }
        else {
            
            return GameData.getBlockRegistry().getObject(ConfigRTG.shadowDesertBlockId);
        }
    }
    
    protected byte getShadowDesertMeta(World world, int i, int j, int x, int y, int k)
    {
        if ((undergroundBiomesMod.present()) && ConfigRTG.enableUBCDesertShadowing) {
            
            return (byte)0;
        }
        else {
            
            return (byte)ConfigRTG.shadowDesertBlockByte;
        }
    }
    
    protected Block hcStone(World world, int i, int j, int x, int y, int k)
    {
        int worldX = i;
        int worldY = k;
        int worldZ = j;
        
        return Blocks.stone;
    }
    
    protected byte hcStoneMeta(World world, int i, int j, int x, int y, int k)
    {
        int worldX = i;
        int worldY = k;
        int worldZ = j;
        
        return (byte)0;
    }
    
    protected Block hcCobble(World world, int i, int j, int x, int y, int k)
    {
        if ((undergroundBiomesMod.present())) {
            
            int worldX = i;
            int worldY = k;
            int worldZ = j;
            
            BlockCodes cobble = ubColumnCache.column(worldX,worldZ).cobblestone(worldY);
            
            return cobble.block;
        }
        else {
            
            return Blocks.cobblestone;
        }
    }
    
    protected byte hcCobbleMeta(World world, int i, int j, int x, int y, int k)
    {
        if ((undergroundBiomesMod.present())) {
            
            int worldX = i;
            int worldY = k;
            int worldZ = j;

            BlockCodes cobble = ubColumnCache.column(worldX,worldZ).cobblestone(worldY);
            
            return (byte) cobble.metadata;
        }
        else {
            
            return (byte)0;
        }
    }
    
    public Block getTopBlock()
    {
        return this.topBlock;
    }
    
    public Block getFillerBlock()
    {
        return this.fillerBlock;
    }
    
    private boolean isValidBlockId(String blockId)
    {
        // String to be scanned to find the pattern.
        String pattern = "^(.+):(.+)$";

        // Create a Pattern object
        Pattern r = Pattern.compile(pattern);

        // Now create matcher object.
        Matcher m = r.matcher(blockId.trim());
        
        if (m.find( )) {
           return true;
        }
        else {
            return false;
        }
    }
}
