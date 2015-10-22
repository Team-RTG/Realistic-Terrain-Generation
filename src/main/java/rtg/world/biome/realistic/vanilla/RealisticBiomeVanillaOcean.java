package rtg.world.biome.realistic.vanilla;

import rtg.config.vanilla.ConfigVanilla;
import rtg.world.biome.BiomeBase;
import rtg.world.gen.surface.vanilla.SurfaceVanillaOcean;
import rtg.world.gen.terrain.vanilla.TerrainVanillaOcean;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeVanillaOcean extends RealisticBiomeVanillaBase
{
    
    public static Block topBlock = BiomeGenBase.ocean.topBlock;
    public static Block fillerBlock = BiomeGenBase.ocean.fillerBlock;
    
    public RealisticBiomeVanillaOcean()
    {
    
        super(
            BiomeGenBase.ocean,
            BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.WET),
            new TerrainVanillaOcean(),
            new SurfaceVanillaOcean(Blocks.sand, Blocks.sand, Blocks.gravel, 20f, 0.2f));
        
        this.setRealisticBiomeName("Vanilla Ocean");
        this.biomeCategory = BiomeCategory.WET;
        this.biomeWeight = ConfigVanilla.weightVanillaOcean;
    }
}
