package rtg.world.biome.realistic.abyssalcraft;

import net.minecraft.init.Biomes;
import net.minecraft.world.biome.Biome;

import com.shinoow.abyssalcraft.api.biome.ACBiomes;

import rtg.api.biome.BiomeConfig;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.gen.surface.abyssalcraft.SurfaceACDarklandsMountains;
import rtg.world.gen.terrain.abyssalcraft.TerrainACDarklandsMountains;

public class RealisticBiomeACDarklandsMountains extends RealisticBiomeACBase {

    public static Biome biome = ACBiomes.darklands_mountains;
    public static Biome river = Biomes.RIVER;

    public RealisticBiomeACDarklandsMountains(BiomeConfig config) {

        super(config, biome, river,
            new TerrainACDarklandsMountains(120f, 100f),
            new SurfaceACDarklandsMountains(config, biome.topBlock, biome.fillerBlock, 0.2f)
        );

        this.noLakes = true;
        this.noWaterFeatures = true;

        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        this.addDeco(decoBaseBiomeDecorations);
    }
}
