package enhancedbiomes.world;

import java.util.Random;

import enhancedbiomes.world.biome.EnhancedBiomesTropical;
import enhancedbiomes.world.biome.EnhancedBiomesWetland;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.structure.ComponentScatteredFeaturePieces;
import net.minecraft.world.gen.structure.StructureStart;

public class StructureScatteredFeatureEnhancedBiomesStart extends StructureStart
{
    public StructureScatteredFeatureEnhancedBiomesStart(World par1World, Random par2Random, int par3, int par4)
    {
        BiomeGenBase var5 = par1World.getBiomeGenForCoords(par3 * 16 + 8, par4 * 16 + 8);

        if (var5 != EnhancedBiomesTropical.biomeRainforest && var5 != EnhancedBiomesTropical.biomeRainforestValley)
        {
            if (var5 == EnhancedBiomesWetland.biomeMangrove || var5 == EnhancedBiomesWetland.biomeCarr)
            {
                ComponentScatteredFeaturePieces.SwampHut var8 = new ComponentScatteredFeaturePieces.SwampHut(par2Random, par3 * 16, par4 * 16);
                this.components.add(var8);
            }
            else
            {
                ComponentScatteredFeaturePieces.DesertPyramid var7 = new ComponentScatteredFeaturePieces.DesertPyramid(par2Random, par3 * 16, par4 * 16);
                this.components.add(var7);
            }
        }
        else
        {
            ComponentScatteredFeaturePieces.JunglePyramid var6 = new ComponentScatteredFeaturePieces.JunglePyramid(par2Random, par3 * 16, par4 * 16);
            this.components.add(var6);
        }

        this.updateBoundingBox();
    }
}
