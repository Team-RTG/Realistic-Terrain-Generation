package rtg.world.biome.realistic.vanilla;

import rtg.api.biomes.BiomeConfig;
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
    
    public RealisticBiomeVanillaOcean(BiomeConfig config)
    {
    
        super(
            BiomeGenBase.ocean,
            BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.WET),
            new TerrainVanillaOcean(),
            new SurfaceVanillaOcean(Blocks.sand, Blocks.sand, Blocks.gravel, 20f, 0.2f));
        
        this.biomeConfig = config;
        this.biomeWeight = ConfigVanilla.weightVanillaOcean;
        this.generateVillages = ConfigVanilla.villageVanillaOcean;
        
        this.waterSurfaceLakeChance = 0;
        this.lavaSurfaceLakeChance = 0;
    }
}
