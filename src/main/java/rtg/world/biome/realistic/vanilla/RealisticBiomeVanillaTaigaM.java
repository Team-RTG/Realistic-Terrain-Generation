package rtg.world.biome.realistic.vanilla;

import net.minecraft.block.state.IBlockState;
import net.minecraft.world.biome.BiomeGenBase;

import rtg.api.biome.BiomeConfig;
import rtg.api.biome.vanilla.config.BiomeConfigVanillaTaigaM;
import rtg.world.biome.deco.collection.DecoCollectionTaiga;
import rtg.world.gen.surface.vanilla.SurfaceVanillaTaigaM;
import rtg.world.gen.terrain.vanilla.TerrainVanillaTaigaM;

public class RealisticBiomeVanillaTaigaM extends RealisticBiomeVanillaBase {

    public static BiomeGenBase standardBiome = BiomeGenBase.taiga;
    public static BiomeGenBase mutationBiome = BiomeGenBase.getBiome(standardBiome.biomeID + MUTATION_ADDEND);

    public static IBlockState topBlock = mutationBiome.topBlock;
    public static IBlockState fillerBlock = mutationBiome.fillerBlock;

    public RealisticBiomeVanillaTaigaM(BiomeConfig config) {

        super(config,
            mutationBiome,
            BiomeGenBase.river,
            new TerrainVanillaTaigaM(70f, 180f, 7f, 100f, 38f, 160f, 68f),
            new SurfaceVanillaTaigaM(config, topBlock, fillerBlock)
        );

        this.noLakes = true;

        this.addDecoCollection(new DecoCollectionTaiga(this.config._boolean(BiomeConfigVanillaTaigaM.decorationLogsId), 10f));
    }
}
