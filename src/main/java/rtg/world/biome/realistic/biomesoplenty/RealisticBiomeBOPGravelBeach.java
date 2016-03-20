package rtg.world.biome.realistic.biomesoplenty;

import biomesoplenty.api.biome.BOPBiomes;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Biomes;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.biome.BiomeConfig;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPGravelBeach;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPGravelBeach;

public class RealisticBiomeBOPGravelBeach extends RealisticBiomeBOPBase {
    public static BiomeGenBase bopBiome = BOPBiomes.gravel_beach.get();

    public static IBlockState topBlock = bopBiome.topBlock;
    public static IBlockState fillerBlock = bopBiome.fillerBlock;

    public RealisticBiomeBOPGravelBeach(BiomeConfig config) {
        super(config,
                bopBiome, Biomes.river,
                new TerrainBOPGravelBeach(),
                new SurfaceBOPGravelBeach(
                        config,
                        bopBiome.topBlock,
                        bopBiome.fillerBlock,
                        bopBiome.topBlock,
                        bopBiome.fillerBlock,
                        (byte) 0,
                        1
                )
        );
    }
}
