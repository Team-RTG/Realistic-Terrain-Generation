package rtg.world.gen.surface;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.biome.BiomeConfig;
import rtg.config.rtg.ConfigRTG;
import rtg.util.CellNoise;
import rtg.util.ModPresenceTester;
import rtg.util.OpenSimplexNoise;
import rtg.util.UBColumnCache;
import cpw.mods.fml.common.registry.GameData;
import exterminatorJeff.undergroundBiomes.api.BlockCodes;

public class SurfaceBase
{
    protected Block topBlock;
    public byte topBlockMeta;
    protected Block fillerBlock;
    public byte fillerBlockMeta;
	protected BiomeConfig biomeConfig;

	private final static ModPresenceTester undergroundBiomesMod = new ModPresenceTester("UndergroundBiomes");
	private final static ModPresenceTester abyssalCraftMod = new ModPresenceTester("abyssalcraft");
	
    // create UBColumnCache only if UB is present
    private static UBColumnCache ubColumnCache = undergroundBiomesMod.present() ? new UBColumnCache() : null;
    
    public SurfaceBase(BiomeConfig config, Block top, byte topByte, Block fill, byte fillByte)
    {
        if (config == null) throw new RuntimeException("Biome config in SurfaceBase is NULL.");
        
        biomeConfig = config;

        topBlock = top;
        topBlockMeta = topByte;
        fillerBlock = fill;
        fillerBlockMeta = fillByte;
        
        this.assignUserConfigs(config, top, topByte, fill, fillByte);
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
    
    protected Block hcStone(World world, int worldX, int worldZ, int chunkX, int chunkZ, int worldY)
    {
        return Blocks.stone;
    }
    
    protected byte hcStoneMeta(World world, int worldX, int worldZ, int chunkX, int chunkZ, int worldY)
    {
        return (byte)0;
    }
    
    protected Block hcCobble(World world, int worldX, int worldZ, int chunkX, int chunkZ, int worldY)
    { 
        if ((undergroundBiomesMod.present())) {
            
            BlockCodes cobble = ubColumnCache.column(worldX,worldZ).cobblestone(worldY);
            
            return cobble.block;
        }
        else {
            
            return Blocks.cobblestone;
        }
    }
    
    protected byte hcCobbleMeta(World world, int worldX, int worldZ, int chunkX, int chunkZ, int worldY)
    {
        if ((undergroundBiomesMod.present())) {

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
    
    private void assignUserConfigs(BiomeConfig config, Block top, byte topByte, Block fill, byte fillByte)
    {
        String userTopBlock = config._string(BiomeConfig.surfaceTopBlockId);
        try {
            if (GameData.getBlockRegistry().containsKey(userTopBlock)) {
                topBlock = GameData.getBlockRegistry().getObject(userTopBlock);
            }
            else {
                topBlock = top;
            }
        }
        catch (Exception e) {
            topBlock = top;
        }
        
        String userTopBlockMeta = config._string(BiomeConfig.surfaceTopBlockMetaId);
        try {
            this.topBlockMeta = Byte.valueOf(userTopBlockMeta);
        }
        catch (Exception e) {
            this.topBlockMeta = topByte;
        }
        
        String userFillerBlock = config._string(BiomeConfig.surfaceFillerBlockId);
        try {
            if (GameData.getBlockRegistry().containsKey(userFillerBlock)) {
                fillerBlock = GameData.getBlockRegistry().getObject(userFillerBlock);
            }
            else {
                fillerBlock = fill;
            }
        }
        catch (Exception e) {
            fillerBlock = fill;
        }
        
        String userFillerBlockMeta = config._string(BiomeConfig.surfaceFillerBlockMetaId);
        try {
            this.fillerBlockMeta = Byte.valueOf(userFillerBlockMeta);
        }
        catch (Exception e) {
            this.fillerBlockMeta = fillByte;
        }
    }
    
    protected Block getConfigBlock(BiomeConfig config, String propertyId, Block blockDefault)
    {
        Block blockReturn = blockDefault;
        String userBlockId = config._string(propertyId);
        
        try {
            if (GameData.getBlockRegistry().containsKey(userBlockId)) {
                blockReturn = GameData.getBlockRegistry().getObject(userBlockId);
            }
            else {
                blockReturn = blockDefault;
            }
        }
        catch (Exception e) {
            blockReturn = blockDefault;
        }

        return blockReturn;
    }
    
    protected byte getConfigBlockMeta(BiomeConfig config, String propertyId, byte metaDefault)
    {
        byte metaReturn = metaDefault;
        String userMeta = config._string(propertyId);
        
        try {
            metaReturn = Byte.valueOf(userMeta);
        }
        catch (Exception e) {
            metaReturn = metaDefault;
        }
        
        return metaReturn;
    }
}
