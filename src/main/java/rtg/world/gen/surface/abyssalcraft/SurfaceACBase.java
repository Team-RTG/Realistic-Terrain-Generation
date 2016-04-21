package rtg.world.gen.surface.abyssalcraft;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import rtg.api.biome.BiomeConfig;
import rtg.util.ModPresenceTester;
import rtg.world.gen.surface.SurfaceBase;

import com.shinoow.abyssalcraft.api.block.ACBlocks;


public class SurfaceACBase extends SurfaceBase {

	private final static ModPresenceTester abyssalCraftMod = new ModPresenceTester("abyssalcraft");
	
    public SurfaceACBase(BiomeConfig config, Block top, byte topMeta, Block fill, byte fillMeta) {
    	super(config, top, topMeta, fill, fillMeta);
    }
    
    @Override
    protected Block hcStone(World world, int worldX, int worldZ, int chunkX, int chunkZ, int worldY)
    {
        return ACBlocks.darkstone;
    }
    
    @Override
    protected byte hcStoneMeta(World world, int worldX, int worldZ, int chunkX, int chunkZ, int worldY)
    {
        return (byte)0;
    }
    
    @Override
    protected Block hcCobble(World world, int worldX, int worldZ, int chunkX, int chunkZ, int worldY)
    {
        return ACBlocks.darkstone_cobblestone;
    }
    
    @Override
    protected byte hcCobbleMeta(World world, int worldX, int worldZ, int chunkX, int chunkZ, int worldY)
    {
        return (byte)0;
    }
    
    @Override
    protected Block getShadowStoneBlock(World world, int i, int j, int x, int y, int k)
    {
    	return ACBlocks.darkstone;
    }
    
    @Override
    protected byte getShadowStoneMeta(World world, int i, int j, int x, int y, int k)
    {
        return (byte)0;
    }
    
    @Override
    protected Block getShadowDesertBlock(World world, int i, int j, int x, int y, int k)
    {
    	return ACBlocks.darkstone;
    }
    
    @Override
    protected byte getShadowDesertMeta(World world, int i, int j, int x, int y, int k)
    {
        return (byte)0;
    }
}