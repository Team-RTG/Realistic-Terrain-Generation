package rtg.world.gen.surface;

import java.util.Random;

import cpw.mods.fml.common.registry.GameData;
import rtg.config.rtg.ConfigRTG;
import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

public class SurfaceBase
{
	protected Block topBlock;
	protected Block fillerBlock;
	protected Block shadowBlock = GameData.getBlockRegistry().getObject(ConfigRTG.shadowBlockId);
	protected byte shadowByte = (byte)ConfigRTG.shadowBlockByte;
	
	public SurfaceBase(Block top, Block fill)
	{
		topBlock = top;
		fillerBlock = fill;
	}
	
	public void paintTerrain(Block[] blocks, byte[] metadata, int i, int j, int x, int y, int depth, World world, Random rand, OpenSimplexNoise simplex, CellNoise cell, float[] noise, float river, BiomeGenBase[] base)
	{
	}
}
