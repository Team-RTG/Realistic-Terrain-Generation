package rtg.world.biome.realistic.vanilla;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.biome.BiomeConfig;
import rtg.util.VillageMaterial;
import rtg.world.gen.surface.vanilla.SurfaceVanillaExtremeHillsM;
import rtg.world.gen.terrain.vanilla.TerrainVanillaExtremeHillsM;

public class RealisticBiomeVanillaExtremeHillsM extends RealisticBiomeVanillaBase
{
    
    public static BiomeGenBase standardBiome = BiomeGenBase.extremeHills;
    public static BiomeGenBase mutationBiome = BiomeGenBase.getBiome(standardBiome.biomeID + MUTATION_ADDEND);
    
    public static  IBlockState topBlock = mutationBiome.topBlock;
    public static IBlockState fillerBlock = mutationBiome.fillerBlock;
        
    public RealisticBiomeVanillaExtremeHillsM(BiomeConfig config)
    {
    
        super(config, 
            mutationBiome,
            BiomeGenBase.river,
            new TerrainVanillaExtremeHillsM(10f, 140f, 68f, 200f),
            new SurfaceVanillaExtremeHillsM(config, topBlock, fillerBlock, Blocks.grass.getDefaultState(), Blocks.dirt.getDefaultState(), 60f,
                -0.14f, 14f, 0.25f));
        config.setVillageMaterial(VillageMaterial.Preset.SPRUCE);
        this.generatesEmeralds = true;
    }
}
