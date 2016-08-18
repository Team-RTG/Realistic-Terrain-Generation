package rtg.world.biome.realistic.biomesoplenty;

import net.minecraft.block.state.IBlockState;
import net.minecraft.world.biome.BiomeGenBase;

import biomesoplenty.api.biome.BOPBiomes;

import rtg.api.biome.BiomeConfig;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPGravelBeach;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPGravelBeach;

public class RealisticBiomeBOPGravelBeach extends RealisticBiomeBOPBase {

    public static BiomeGenBase bopBiome = BOPBiomes.gravel_beach.get();

    public static IBlockState topBlock = bopBiome.topBlock;
    public static IBlockState fillerBlock = bopBiome.fillerBlock;

    public RealisticBiomeBOPGravelBeach(BiomeConfig config) {

        super(config,
            bopBiome, BiomeGenBase.river,
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

        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        this.addDeco(decoBaseBiomeDecorations);
    }
}
