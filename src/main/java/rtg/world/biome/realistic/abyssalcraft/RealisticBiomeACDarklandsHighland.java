package rtg.world.biome.realistic.abyssalcraft;

import net.minecraft.init.Biomes;
import net.minecraft.world.biome.Biome;

import com.shinoow.abyssalcraft.api.biome.ACBiomes;

import rtg.api.biome.BiomeConfig;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.gen.surface.abyssalcraft.SurfaceACDarklandsHighland;
import rtg.world.gen.terrain.abyssalcraft.TerrainACDarklandsHighland;

public class RealisticBiomeACDarklandsHighland extends RealisticBiomeACBase {

    public static Biome biome = ACBiomes.darklands_hills;
    public static Biome river = Biomes.RIVER;

    public RealisticBiomeACDarklandsHighland(BiomeConfig config) {

        super(config, biome, river,
            new TerrainACDarklandsHighland(10f, 120f, 10f, 200f),
            new SurfaceACDarklandsHighland(config, biome.topBlock, biome.fillerBlock, biome.topBlock, biome.fillerBlock, 60f, -0.14f, 14f, 0.25f)
        );

        this.noLakes = true;
        this.noWaterFeatures = true;

        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        this.addDeco(decoBaseBiomeDecorations);
    }
}
