package rtg.world.biome.realistic.biomesoplenty;

import biomesoplenty.api.biome.BOPBiomes;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Biomes;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.biome.BiomeConfig;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPAlps;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPAlps;

public class RealisticBiomeBOPAlps extends RealisticBiomeBOPBase {
    public static BiomeGenBase bopBiome = BOPBiomes.alps.get();

    public static IBlockState topBlock = bopBiome.topBlock;
    public static IBlockState fillerBlock = bopBiome.fillerBlock;

    public RealisticBiomeBOPAlps(BiomeConfig config) {
        super(config,
                bopBiome, Biomes.frozenRiver,
                new TerrainBOPAlps(),
                new SurfaceBOPAlps(config, topBlock, fillerBlock, false, null, 0.45f)
        );
        this.generatesEmeralds = true;
        this.noLakes = true;
    }
}
