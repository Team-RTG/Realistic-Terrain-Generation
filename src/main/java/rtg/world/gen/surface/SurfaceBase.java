package rtg.world.gen.surface;

import java.util.Random;

import net.minecraft.world.chunk.ChunkPrimer;
import rtg.config.rtg.ConfigRTG;
import rtg.util.CellNoise;
import rtg.util.ModPresenceTester;
import rtg.util.OpenSimplexNoise;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

public class SurfaceBase
{
	protected Block topBlock;
	protected Block fillerBlock;

    private final static ModPresenceTester undergroundBiomesMod = new ModPresenceTester("UndergroundBiomes");
    
	public SurfaceBase(Block top, Block fill)
	{
		topBlock = top;
		fillerBlock = fill;
	}
	
	public void paintTerrain(ChunkPrimer chunkPrimer, byte[] metadata, int i, int j, int x, int y, int depth, World world, Random rand, OpenSimplexNoise simplex, CellNoise cell, float[] noise, float river, BiomeGenBase[] base)
	{
	}
	
    protected Block getShadowStoneBlock(World world, int i, int j, int x, int y, int k)
    {
        if ((undergroundBiomesMod.present()) && ConfigRTG.enableUBCStoneShadowing) {
            
            return Blocks.stone;
        }
        else {
            
            return Block.getBlockFromName(ConfigRTG.shadowStoneBlockId);
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
            
            return Block.getBlockFromName(ConfigRTG.shadowDesertBlockId);
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
        //if ((undergroundBiomesMod.present())) {
        //
        //    int worldX = i;
        //    int worldY = k;
        //    int worldZ = j;
        //
        //    BlockCodes cobble = ubColumnCache.column(worldX,worldZ).cobblestone(worldY);
        //
        //    return cobble.block;
        //}
        //else {

            return Blocks.cobblestone;
        //}
    }
    
    protected byte hcCobbleMeta(World world, int i, int j, int x, int y, int k)
    {
        //if ((undergroundBiomesMod.present())) {
        //
        //    int worldX = i;
        //    int worldY = k;
        //    int worldZ = j;
        //
        //    BlockCodes cobble = ubColumnCache.column(worldX,worldZ).cobblestone(worldY);
        //
        //    return (byte) cobble.metadata;
        //}
        //else {

            return (byte)0;
        //}
    }



}
