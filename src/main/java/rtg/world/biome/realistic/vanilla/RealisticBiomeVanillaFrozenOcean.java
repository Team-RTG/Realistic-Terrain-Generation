package rtg.world.biome.realistic.vanilla;

import rtg.api.biome.BiomeConfig;
import rtg.config.vanilla.ConfigVanilla;
import rtg.world.biome.BiomeBase;
import rtg.world.gen.surface.vanilla.SurfaceVanillaFrozenOcean;
import rtg.world.gen.terrain.vanilla.TerrainVanillaFrozenOcean;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeVanillaFrozenOcean extends RealisticBiomeVanillaBase
{
    
    public static Block topBlock = BiomeGenBase.frozenOcean.topBlock;
    public static Block fillerBlock = BiomeGenBase.frozenOcean.fillerBlock;
    
    public RealisticBiomeVanillaFrozenOcean(BiomeConfig config)
    {
    
        super(
            BiomeGenBase.frozenOcean,
            BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.ICE),
            new TerrainVanillaFrozenOcean(),
            new SurfaceVanillaFrozenOcean(Blocks.sand, Blocks.sand, Blocks.gravel, 20f, 0.2f));
        
        this.biomeConfig = config;
        this.biomeWeight = ConfigVanilla.weightVanillaFrozenOcean;
        this.generateVillages = ConfigVanilla.villageVanillaFrozenOcean;
        
        this.waterSurfaceLakeChance = 0;
        this.lavaSurfaceLakeChance = 0;
    }
}
