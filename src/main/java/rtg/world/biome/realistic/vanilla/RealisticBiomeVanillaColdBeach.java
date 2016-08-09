package rtg.world.biome.realistic.vanilla;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

import rtg.api.biome.BiomeConfig;
import rtg.world.biome.deco.DecoBoulder;
import rtg.world.gen.surface.vanilla.SurfaceVanillaColdBeach;
import rtg.world.gen.terrain.vanilla.TerrainVanillaColdBeach;

public class RealisticBiomeVanillaColdBeach extends RealisticBiomeVanillaBase {

    public static IBlockState topBlock = BiomeGenBase.coldBeach.topBlock;
    public static IBlockState fillerBlock = BiomeGenBase.coldBeach.fillerBlock;

    public RealisticBiomeVanillaColdBeach(BiomeConfig config) {

        super(config,
            BiomeGenBase.coldBeach,
            BiomeGenBase.river,
            new TerrainVanillaColdBeach(),
            new SurfaceVanillaColdBeach(config, topBlock, fillerBlock, topBlock, fillerBlock, (byte) 0, 1)
        );

        DecoBoulder decoBoulder = new DecoBoulder();
        decoBoulder.boulderBlock = Blocks.cobblestone.getDefaultState();
        decoBoulder.chance = 16;
        decoBoulder.maxY = 95;
        decoBoulder.strengthFactor = 3f;
        this.addDeco(decoBoulder);
    }
}
