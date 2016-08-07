package com.sdj64.highlands;

import com.sdj64.highlands.biome.ChunkProviderHighlands;
import com.sdj64.highlands.generator.layer.GenLayerBiomeHighlands;

import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.GenLayerBiome;
import net.minecraft.world.gen.layer.GenLayerBiomeEdge;
import net.minecraft.world.gen.layer.GenLayerZoom;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class WorldTypeHighlands extends WorldType{

	private String wtname;
	
	public WorldTypeHighlands(String name) {
		super(name);
		this.wtname = name;
	}
	
	@Override
    public net.minecraft.world.chunk.IChunkProvider getChunkGenerator(World world, String generatorOptions)
    {
        if (this == FLAT) return new net.minecraft.world.gen.ChunkProviderFlat(world, world.getSeed(), world.getWorldInfo().isMapFeaturesEnabled(), generatorOptions);
        if (this == DEBUG_WORLD) return new net.minecraft.world.gen.ChunkProviderDebug(world);
        return new ChunkProviderHighlands(world, world.getSeed(), world.getWorldInfo().isMapFeaturesEnabled(), generatorOptions);
    }
	
    /**
     * Gets the translation key for the name of this world type.
     */
    @SideOnly(Side.CLIENT)
    @Override
    public String getTranslateName()
    {
        return this.wtname;
    }
    
    /**
     * Get the height to render the clouds for this world type
     * @return The height to render clouds at
     */
    public float getCloudHeight()
    {
        return 212.0F;
    }
    
    /**
     * Creates the GenLayerBiome used for generating the world with the specified ChunkProviderSettings JSON String
     * *IF AND ONLY IF* this WorldType == WorldType.CUSTOMIZED.
     *
     *
     * @param worldSeed The world seed
     * @param parentLayer The parent layer to feed into any layer you return
     * @param chunkProviderSettingsJson The JSON string to use when initializing ChunkProviderSettings.Factory
     * @return A GenLayer that will return ints representing the Biomes to be generated, see GenLayerBiome
     */
    public GenLayer getBiomeLayer(long worldSeed, GenLayer parentLayer, String chunkProviderSettingsJson)
    {
        GenLayer ret = new GenLayerBiomeHighlands(200L, parentLayer, this, chunkProviderSettingsJson);
        ret = GenLayerZoom.magnify(1000L, ret, 2);
        ret = new GenLayerBiomeEdge(1000L, ret);
        return ret;
    }

}
