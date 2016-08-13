package rtg.world.biome.realistic.vanilla;

import net.minecraft.init.Biomes;
import net.minecraft.world.biome.Biome;

import rtg.api.biome.BiomeConfig;
import rtg.api.biome.vanilla.config.BiomeConfigVanillaColdTaigaM;
import rtg.world.biome.deco.collection.DecoCollectionTaiga;
import rtg.world.gen.surface.vanilla.SurfaceVanillaColdTaigaM;
import rtg.world.gen.terrain.vanilla.TerrainVanillaColdTaigaM;

public class RealisticBiomeVanillaColdTaigaM extends RealisticBiomeVanillaBase {

    public static Biome standardBiome = Biomes.COLD_TAIGA;
    public static Biome mutationBiome = Biome.getBiome(standardBiome.biomeID + MUTATION_ADDEND);

    public RealisticBiomeVanillaColdTaigaM(BiomeConfig config) {

        super(config,
            mutationBiome,
            Biomes.FROZEN_RIVER,
            new TerrainVanillaColdTaigaM(),
            new SurfaceVanillaColdTaigaM(config, mutationBiome.topBlock, mutationBiome.fillerBlock)
        );

        this.noLakes = true;

        this.addDecoCollection(new DecoCollectionTaiga(this.config._boolean(BiomeConfigVanillaColdTaigaM.decorationLogsId), 8f));
    }
}
