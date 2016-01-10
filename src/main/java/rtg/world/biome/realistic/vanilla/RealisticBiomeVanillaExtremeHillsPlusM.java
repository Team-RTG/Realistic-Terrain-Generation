package rtg.world.biome.realistic.vanilla;

import rtg.config.vanilla.ConfigVanilla;
import rtg.world.biome.BiomeBase;
import rtg.world.gen.surface.vanilla.SurfaceVanillaExtremeHillsPlusM;
import rtg.world.gen.terrain.vanilla.TerrainVanillaExtremeHillsPlusM;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeVanillaExtremeHillsPlusM extends RealisticBiomeVanillaBase
{
    public static BiomeGenBase standardBiome = BiomeGenBase.extremeHillsPlus;
    public static BiomeGenBase mutationBiome = BiomeGenBase.getBiome(standardBiome.biomeID + MUTATION_ADDEND);
    
    public static Block topBlock = mutationBiome.topBlock;
    public static Block fillerBlock = mutationBiome.fillerBlock;
    
    public RealisticBiomeVanillaExtremeHillsPlusM()
    {
    
        super(
            mutationBiome,
            BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.COLD),
            new TerrainVanillaExtremeHillsPlusM(230f, 120f, 0f),
            new SurfaceVanillaExtremeHillsPlusM(Blocks.grass, Blocks.dirt, false, null, 0f, 1.5f, 60f, 65f, 1.5f, Blocks.gravel, 0.08f));
        
        this.setRealisticBiomeName("Vanilla Extreme Hills + M");
        this.biomeSize = BiomeSize.NORMAL;
        this.biomeWeight = ConfigVanilla.weightVanillaExtremeHillsPlusM;
        this.generateVillages = ConfigVanilla.villageVanillaExtremeHillsPlusM;
        this.generatesEmeralds = true;
    }
}
