package rtg.world.biome.realistic.vanilla;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

import rtg.api.biome.BiomeConfig;
import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.biome.deco.collection.DecoCollectionDesert;
import rtg.world.biome.deco.collection.DecoCollectionDesertRiver;
import rtg.world.gen.surface.vanilla.SurfaceVanillaDesertHills;
import rtg.world.gen.terrain.vanilla.TerrainVanillaDesertHills;

public class RealisticBiomeVanillaDesertHills extends RealisticBiomeVanillaBase
{	
	public static Block topBlock = BiomeGenBase.desertHills.topBlock;
	public static Block fillerBlock = BiomeGenBase.desertHills.fillerBlock;

	public RealisticBiomeVanillaDesertHills(BiomeConfig config)
	{
		super(config, 
			BiomeGenBase.desertHills,
			BiomeGenBase.river,
			new TerrainVanillaDesertHills(10f, 80f, 68f, 200f),
			new SurfaceVanillaDesertHills(config, Blocks.sand, Blocks.sandstone, false, null, 0f, 1.5f, 60f, 65f, 1.5f)
		);
		
        this.waterSurfaceLakeChance = 0;
        this.noLakes=true;
        
		this.addDecoCollection(new DecoCollectionDesertRiver());
		this.addDecoCollection(new DecoCollectionDesert());
	}

	@Override
	public void rReplace(Block[] blocks, byte[] metadata, int i, int j, int x, int y, int depth, World world, Random rand, OpenSimplexNoise simplex, CellNoise cell, float[] noise, float river, BiomeGenBase[] base) {
		this.rReplaceRiverSurface(blocks, metadata, i, j, x, y, depth, world, rand, simplex, cell, noise, river, base);
	}
}