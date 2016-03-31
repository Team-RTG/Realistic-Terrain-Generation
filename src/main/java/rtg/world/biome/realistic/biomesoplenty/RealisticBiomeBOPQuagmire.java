package rtg.world.biome.realistic.biomesoplenty;

import biomesoplenty.api.biome.BOPBiomes;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Biomes;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPQuagmire;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPQuagmire;

public class RealisticBiomeBOPQuagmire extends RealisticBiomeBOPBase {
    public static BiomeGenBase bopBiome = BOPBiomes.quagmire.get();

    public static IBlockState topBlock = bopBiome.topBlock;
    public static IBlockState fillerBlock = bopBiome.fillerBlock;

    public RealisticBiomeBOPQuagmire() {
        super(
                bopBiome, Biomes.river,
                new TerrainBOPQuagmire(),
                new SurfaceBOPQuagmire(config, topBlock, fillerBlock)
        );
    }
}
