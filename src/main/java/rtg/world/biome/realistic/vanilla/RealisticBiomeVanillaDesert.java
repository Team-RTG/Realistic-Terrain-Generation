package rtg.world.biome.realistic.vanilla;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

import rtg.api.biome.BiomeConfig;
import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.biome.deco.collection.DecoCollectionDesert;
import rtg.world.biome.deco.collection.DecoCollectionDesertRiver;
import rtg.world.gen.surface.vanilla.SurfaceVanillaDesert;
import rtg.world.gen.terrain.vanilla.TerrainVanillaDesert;

public class RealisticBiomeVanillaDesert extends RealisticBiomeVanillaBase
{
    
    public static Block topBlock = BiomeGenBase.desert.topBlock;
    public static Block fillerBlock = BiomeGenBase.desert.fillerBlock;

    public RealisticBiomeVanillaDesert(BiomeConfig config)
    {
    
        super(config, 
            BiomeGenBase.desert,
            BiomeGenBase.river,
            new TerrainVanillaDesert(),
            new SurfaceVanillaDesert(config, topBlock, fillerBlock));
        
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
