package rtg.world.layer;

import java.util.List;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.GenLayerBiome;
import net.minecraft.world.gen.layer.IntCache;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.BiomeManager.BiomeEntry;
import net.minecraftforge.common.BiomeManager.BiomeType;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/*import enhancedbiomes.EnhancedBiomesMod;
import enhancedbiomes.handlers.BiomeGenManager;
import enhancedbiomes.handlers.SubBiomeEventHandler;
import enhancedbiomes.world.WorldTypeEnhancedBiomes;
import enhancedbiomes.world.biome.EnhancedBiomesArchipelago;
import enhancedbiomes.world.biome.EnhancedBiomesBiome;
import enhancedbiomes.world.biome.base.BiomeGenGrassBase;
import enhancedbiomes.world.biome.base.BiomeGenWoodlandBase;
import static enhancedbiomes.world.biome.EnhancedBiomesArchipelago.*;
import static enhancedbiomes.world.biome.EnhancedBiomesBiome.*;
import static enhancedbiomes.world.biome.EnhancedBiomesGrass.*;
import static enhancedbiomes.world.biome.EnhancedBiomesPlains.*;
import static enhancedbiomes.world.biome.EnhancedBiomesRock.*;
import static enhancedbiomes.world.biome.EnhancedBiomesSand.*;
import static enhancedbiomes.world.biome.EnhancedBiomesSandstone.*;
import static enhancedbiomes.world.biome.EnhancedBiomesSnow.*;
import static enhancedbiomes.world.biome.EnhancedBiomesTropical.*;
import static enhancedbiomes.world.biome.EnhancedBiomesWetland.*;
import static enhancedbiomes.world.biome.EnhancedBiomesWoodland.*; */


												/** aka rare biome */

public class GenLayerEBHills extends GenLayer
{
	private static final Logger logger = LogManager.getLogger();
	private GenLayer field_151628_d;
	private GenLayer heatLayer;

	public GenLayerEBHills(long p_i45479_1_, GenLayer p_i45479_3_, GenLayer p_i45479_4_, GenLayer heatLayer) {
		super(p_i45479_1_);
		this.parent = p_i45479_3_;
		this.field_151628_d = p_i45479_4_;
		this.heatLayer = heatLayer;
	}

	/**
	 * Returns a list of integer values generated by this layer. These may be interpreted as temperatures, rainfall amounts, or biomeList[] indices based on the particular GenLayer subclass.
	 */
	public int[] getInts(int par1, int par2, int par3, int par4) {
		int[] aint = this.parent.getInts(par1 - 1, par2 - 1, par3 + 2, par4 + 2);
		int[] aint1 = this.field_151628_d.getInts(par1 - 1, par2 - 1, par3 + 2, par4 + 2);
		int[] aint2 = IntCache.getIntCache(par3 * par4);		
		int[] aintHeat = this.heatLayer.getInts(par1, par2, par3, par4);
		
		for(int i1 = 0; i1 < par4; ++i1) {
			for(int j1 = 0; j1 < par3; ++j1) {
				this.initChunkSeed((long) (j1 + par1), (long) (i1 + par2));
				int k1 = aint[j1 + 1 + (i1 + 1) * (par3 + 2)];
				int l1 = aint1[j1 + 1 + (i1 + 1) * (par3 + 2)];
				boolean flag = (l1 - 2) % 29 == 0;

				BiomeGenBase currentBiome = BiomeGenBase.getBiomeGenArray()[k1 % 256];
				
				if(k1 > 255) {
					logger.debug("old! " + k1);
				}

				if(k1 != 0 && l1 >= 2 && (l1 - 2) % 29 == 1 && k1 < 128) {
					if(BiomeGenManager.getRareBiome(k1) != 0) {
						aint2[j1 + i1 * par3] = BiomeGenManager.getRareBiome(k1);
					}
					else {
						aint2[j1 + i1 * par3] = k1;
					}
				}
				else if(this.nextInt(3) != 0 && !flag) {
					aint2[j1 + i1 * par3] = k1;
				}
				else {
					int i2 = k1;
					int j2;

					if(k1 == BiomeGenBase.ocean.biomeID) {
						if(this.nextInt(5) < 2) i2 = BiomeGenBase.deepOcean.biomeID;
						else switch(aintHeat[j1 + i1 * par3] & -3841) {
							case 1:
								i2 = EnhancedBiomesArchipelago.ab_hot.get(this.nextInt(EnhancedBiomesArchipelago.ab_hot.size())).biomeID;
								break;
							case 2:
								i2 = EnhancedBiomesArchipelago.ab_warm.get(this.nextInt(EnhancedBiomesArchipelago.ab_warm.size())).biomeID;
								break;
							case 3:
								i2 = EnhancedBiomesArchipelago.ab_cool.get(this.nextInt(EnhancedBiomesArchipelago.ab_cool.size())).biomeID;
								break;
							case 4:
								i2 = EnhancedBiomesArchipelago.ab_frozen.get(this.nextInt(EnhancedBiomesArchipelago.ab_frozen.size())).biomeID;
								break;
						}
					}

					else if(EnhancedBiomesBiome.volcanoGen && this.nextInt(16) == 0 && currentBiome.rootHeight < biomeVolcano.rootHeight) {
						i2 = biomeVolcano.biomeID;
					}

					else if(this.nextInt(8) == 0 && currentBiome.rootHeight <= 0.4F && currentBiome.rootHeight >= 0.1F) {
						i2 = biomeBasin.biomeID;
					}

					else if((currentBiome instanceof BiomeGenGrassBase || currentBiome instanceof BiomeGenWoodlandBase) && currentBiome.temperature < 1.0F) {
						i2 = biomeLake.biomeID;
					}

					//Hills
					else if(k1 == biomeRainforest.biomeID)			i2 = biomeRainforestValley.biomeID;
					else if(k1 == biomeBlossomWoods.biomeID)		i2 = biomeBlossomHills.biomeID;
					else if(k1 == biomeWoodLands.biomeID)			i2 = biomeWoodLandHills.biomeID;
					else if(k1 == biomeSilverPineForest.biomeID)	i2 = biomeSilverPineHills.biomeID;
					else if(k1 == biomeAspenForest.biomeID)			i2 = biomeAspenHills.biomeID;
					else if(k1 == BiomeGenBase.desert.biomeID)		i2 = BiomeGenBase.desertHills.biomeID;
					else if(k1 == BiomeGenBase.forest.biomeID)		i2 = BiomeGenBase.forestHills.biomeID;
					else if(k1 == BiomeGenBase.birchForest.biomeID)	i2 = BiomeGenBase.birchForestHills.biomeID;
					else if(k1 == BiomeGenBase.taiga.biomeID)		i2 = BiomeGenBase.taigaHills.biomeID;
					else if(k1 == BiomeGenBase.megaTaiga.biomeID)	i2 = BiomeGenBase.megaTaigaHills.biomeID;
					else if(k1 == BiomeGenBase.coldTaiga.biomeID)	i2 = BiomeGenBase.coldTaigaHills.biomeID;
					else if(k1 == BiomeGenBase.icePlains.biomeID)	i2 = BiomeGenBase.iceMountains.biomeID;
					else if(k1 == BiomeGenBase.jungle.biomeID)		i2 = BiomeGenBase.jungleHills.biomeID;
					else if(k1 == BiomeGenBase.extremeHills.biomeID)i2 = BiomeGenBase.extremeHillsPlus.biomeID;
					else if(k1 == BiomeGenBase.savanna.biomeID)		i2 = BiomeGenBase.savannaPlateau.biomeID;

					else if(k1 == biomeMeadow.biomeID)				i2 = biomeMeadowM.biomeID;
					
					//Mini-biomes
					else if(k1 == biomeRockyDesert.biomeID)			i2 = biomeOasis.biomeID;
					else if(k1 == biomeSavannah.biomeID)			i2 = biomeOasis.biomeID;
					else if(k1 == biomeTundra.biomeID)				i2 = BiomeGenBase.frozenOcean.biomeID;
					
					//Plains -> forest
					else if(k1 == biomeMeadow.biomeID)				i2 = biomeOakForest.biomeID;
					else if(k1 == biomePlateau.biomeID)				i2 = biomeForestMountains.biomeID;
					else if(k1 == biomeLowHills.biomeID) {
						if(this.nextInt(3) == 0) i2 = biomeWoodLandHills.biomeID;
						else i2 = biomeWoodLands.biomeID;
					}
					else if(k1 == BiomeGenBase.plains.biomeID) {
						if(this.nextInt(3) == 0) i2 = BiomeGenBase.forestHills.biomeID;
						else i2 = BiomeGenBase.forest.biomeID;
					}
					
					//Forest -> plains
					else if(k1 == BiomeGenBase.roofedForest.biomeID) i2 = BiomeGenBase.plains.biomeID;
										
					else if(compareBiomesById(k1, BiomeGenBase.mesaPlateau_F.biomeID)) i2 = BiomeGenBase.mesa.biomeID;
					
					else if(k1 == BiomeGenBase.deepOcean.biomeID && this.nextInt(3) == 0) {
						if(this.nextInt(2) == 0) i2 = BiomeGenBase.plains.biomeID;
						else i2 = BiomeGenBase.forest.biomeID;
					}

					if(flag && i2 != k1) {
						if(BiomeGenManager.getRareBiome(i2) != 0) {
							i2 = BiomeGenManager.getRareBiome(i2);
						}
						else {
							i2 = k1;
						}
					}

					if(i2 == k1) {
						aint2[j1 + i1 * par3] = k1;
					}
					else {
						j2 = aint[j1 + 1 + (i1 + 1 - 1) * (par3 + 2)];
						int k2 = aint[j1 + 1 + 1 + (i1 + 1) * (par3 + 2)];
						int l2 = aint[j1 + 1 - 1 + (i1 + 1) * (par3 + 2)];
						int i3 = aint[j1 + 1 + (i1 + 1 + 1) * (par3 + 2)];
						int j3 = 0;

						if(compareBiomesById(j2, k1)) {
							++j3;
						}

						if(compareBiomesById(k2, k1)) {
							++j3;
						}

						if(compareBiomesById(l2, k1)) {
							++j3;
						}

						if(compareBiomesById(i3, k1)) {
							++j3;
						}

						if(j3 >= 3) {
							aint2[j1 + i1 * par3] = i2;
						}
						else {
							aint2[j1 + i1 * par3] = k1;
						}
					}
				}
			}
		}

		return aint2;
	}
}