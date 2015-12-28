package enhancedbiomes.handlers;

import java.awt.Event;
import java.util.ArrayList;
import java.util.Random;

import com.google.common.eventbus.Subscribe;

import cpw.mods.fml.common.eventhandler.Event.Result;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import enhancedbiomes.EnhancedBiomesMod;
import enhancedbiomes.blocks.EnhancedBiomesBlocks;
import enhancedbiomes.world.ChunkProviderEnhancedBiomes;
import enhancedbiomes.world.MapGenCavesEnhancedBiomes;
import enhancedbiomes.world.MapGenRavineEnhancedBiomes;
import enhancedbiomes.world.biome.EnhancedBiomesSand;
import net.minecraft.client.Minecraft;
import net.minecraft.init.Blocks;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.ChunkProviderGenerate;
import net.minecraft.world.gen.MapGenRavine;
import net.minecraft.world.gen.NoiseGenerator;
import net.minecraft.world.gen.NoiseGeneratorPerlin;
import net.minecraftforge.event.terraingen.ChunkProviderEvent.ReplaceBiomeBlocks;
import net.minecraftforge.event.terraingen.InitNoiseGensEvent;

public class ReplaceBiomeBlocksHandler
{
	private static double[] stoneNoise = new double[256];
    private static double[] stoneVariantNoise = new double[256];
    private static NoiseGeneratorPerlin perlin;
    
    public ReplaceBiomeBlocksHandler() {
        
	}
    
    /** Modifies the default biome block placement **/
	@SubscribeEvent
	public void replaceBlocksForBiome(ReplaceBiomeBlocks e)
	{
        if(e.biomeArray != null && (e.chunkProvider instanceof ChunkProviderGenerate || e.chunkProvider instanceof ChunkProviderEnhancedBiomes) && e.world.provider.dimensionId == 0) {
        	if(this.perlin == null) this.perlin = new NoiseGeneratorPerlin(e.world.rand, 4);
    		
        	double d0 = 0.03125D;
        	this.stoneNoise = this.perlin.func_151599_a(this.stoneNoise, (double)(e.chunkX * 16), (double)(e.chunkZ * 16), 16, 16, d0 * 2.0D, d0 * 2.0D, 1.0D);
        	this.stoneVariantNoise = this.perlin.func_151599_a(this.stoneNoise, (double)(e.chunkX * 16), (double)(e.chunkZ * 16), 16, 16, d0 * 2.0D, d0 * 2.0D, 1.0D);

        	for (int var8 = 0; var8 < 16; ++var8)
            {
                for (int var9 = 0; var9 < 16; ++var9)
                {
            		EnhancedBiomesMod.setStoneNoiseForCoords(e.chunkX * 16 + var8, e.chunkZ * 16 + var9, this.stoneVariantNoise[var9 + var8 * 16]);
                	BiomeGenBase biomegenbase = e.biomeArray[var9 + var8 * 16];
                    biomegenbase.genTerrainBlocks(e.world, e.world.rand, e.blockArray,e.metaArray, e.chunkX * 16 + var8, e.chunkZ * 16 + var9, this.stoneNoise[var9 + var8 * 16]);
                    
                    int i1 = (e.chunkX * 16 + var8) & 15;
                    int j1 = (e.chunkZ * 16 + var9) & 15;
                    int k1 = e.blockArray.length / 256;

                    for (int l1 = 255; l1 >= 0; --l1)
                    {
                        int i2 = (j1 * 16 + i1) * k1 + l1;

                        if(e.blockArray[i2] == Blocks.stone)
                        {
                        	e.blockArray[i2] = EnhancedBiomesMod.getRockForCoordsAndBiome(e.chunkX * 16 + var8, e.chunkZ * 16 + var9, biomegenbase.biomeID);
                            e.metaArray[i2] = EnhancedBiomesMod.getRockMetaForCoordsAndBiome(e.chunkX * 16 + var8, e.chunkZ * 16 + var9, biomegenbase.biomeID);
                        }
                        else if(e.blockArray[i2] == Blocks.cobblestone)
                        {
                        	e.blockArray[i2] = EnhancedBiomesMod.getCobbleFromStone(EnhancedBiomesMod.getRockForCoordsAndBiome(e.chunkX * 16 + var8, e.chunkZ * 16 + var9, biomegenbase.biomeID));
                            e.metaArray[i2] = EnhancedBiomesMod.getRockMetaForCoordsAndBiome(e.chunkX * 16 + var8, e.chunkZ * 16 + var9, biomegenbase.biomeID);
                        }
                        else if(e.blockArray[i2] == Blocks.dirt)
                        {
                        	e.blockArray[i2] = EnhancedBiomesMod.soilList[biomegenbase.biomeID];
                            e.metaArray[i2] = EnhancedBiomesMod.soilMetaList[biomegenbase.biomeID];
                        }
                        else if(e.blockArray[i2] == Blocks.grass)
                        {
                        	e.blockArray[i2] = EnhancedBiomesMod.grassList[biomegenbase.biomeID];
                            e.metaArray[i2] = EnhancedBiomesMod.grassMetaList[biomegenbase.biomeID];
                        }
                        else if(biomegenbase == EnhancedBiomesSand.biomeRedDesert) {
                            if(e.blockArray[i2] == Blocks.sand) {
                            	e.metaArray[i2] = 1;
                            }
                            else if(e.blockArray[i2] == Blocks.sandstone) {
                            	e.blockArray[i2] = EnhancedBiomesBlocks.stoneEB;
                            	e.metaArray[i2] = 2;
                            }
                        }
                    }
                }
            }
            new MapGenCavesEnhancedBiomes().create(e.chunkProvider, e.world, e.chunkX, e.chunkZ, e.blockArray, e.metaArray);
            new MapGenRavineEnhancedBiomes().create(e.chunkProvider, e.world, e.chunkX, e.chunkZ, e.blockArray, e.metaArray);
            e.setResult(Result.DENY);
        }
	}
	
	@SubscribeEvent
	public void receiveNoiseGens(InitNoiseGensEvent e) {
		if(e.world.provider.dimensionId == 0 && e.originalNoiseGens[3] instanceof NoiseGeneratorPerlin && this.perlin == null) this.perlin = (NoiseGeneratorPerlin) e.originalNoiseGens[3];
		e.newNoiseGens = e.originalNoiseGens;		
	}
}