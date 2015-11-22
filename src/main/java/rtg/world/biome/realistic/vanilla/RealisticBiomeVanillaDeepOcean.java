package rtg.world.biome.realistic.vanilla;

import rtg.config.vanilla.ConfigVanilla;
import rtg.world.biome.BiomeBase;
import rtg.world.gen.surface.vanilla.SurfaceVanillaDeepOcean;
import rtg.world.gen.terrain.vanilla.TerrainVanillaDeepOcean;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeVanillaDeepOcean extends RealisticBiomeVanillaBase
{
    
    public static Block topBlock = BiomeGenBase.deepOcean.topBlock;
    public static Block fillerBlock = BiomeGenBase.deepOcean.fillerBlock;
    
    public RealisticBiomeVanillaDeepOcean()
    {
    
        super(
            BiomeGenBase.deepOcean,
            BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.WET),
            new TerrainVanillaDeepOcean(),
            new SurfaceVanillaDeepOcean(Blocks.gravel, Blocks.gravel, Blocks.clay, 20f, 0.1f));
        
        this.setRealisticBiomeName("Vanilla Deep Ocean");
        this.biomeSize = BiomeSize.NORMAL;
        this.biomeWeight = ConfigVanilla.weightVanillaDeepOcean;
        this.generateVillages = ConfigVanilla.villageVanillaDeepOcean;
        
        this.waterLakeFrequency = 0;
        this.lavaLakeFrequency = 0;
    }
}
