package rtg.world.biome.realistic.vanilla;

import rtg.api.biomes.vanilla.config.BiomeConfigVanillaPlains;
import rtg.config.vanilla.ConfigVanilla;
import rtg.world.biome.BiomeBase;
import rtg.world.gen.surface.vanilla.SurfaceVanillaPlains;
import rtg.world.gen.terrain.vanilla.TerrainVanillaPlains;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeVanillaPlains extends RealisticBiomeVanillaBase
{
    
    public static Block topBlock = BiomeGenBase.plains.topBlock;
    public static Block fillerBlock = BiomeGenBase.plains.fillerBlock;
    
    public RealisticBiomeVanillaPlains()
    {
    
        super(
            BiomeGenBase.plains,
            BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.TEMPERATE),
            new TerrainVanillaPlains(),
            new SurfaceVanillaPlains(topBlock, fillerBlock));
        
        this.biomeConfig = new BiomeConfigVanillaPlains();
        this.biomeWeight = ConfigVanilla.weightVanillaPlains;
        this.generateVillages = ConfigVanilla.villageVanillaPlains;
    }
}
