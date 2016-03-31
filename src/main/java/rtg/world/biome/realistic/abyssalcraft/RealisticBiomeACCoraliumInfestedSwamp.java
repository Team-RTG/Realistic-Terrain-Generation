package rtg.world.biome.realistic.abyssalcraft;

import net.minecraft.init.Biomes;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.config.BiomeConfig;
import rtg.world.gen.surface.abyssalcraft.SurfaceACCoraliumInfestedSwamp;
import rtg.world.gen.terrain.abyssalcraft.TerrainACCoraliumInfestedSwamp;

public class RealisticBiomeACCoraliumInfestedSwamp extends RealisticBiomeACBase {

    public RealisticBiomeACCoraliumInfestedSwamp(BiomeGenBase acBiome, BiomeConfig config) {

        super(
                acBiome,
                Biomes.river,
                new TerrainACCoraliumInfestedSwamp(),
                new SurfaceACCoraliumInfestedSwamp(config, acBiome.topBlock, acBiome.fillerBlock, false, null, 0.15f));
    }
}
