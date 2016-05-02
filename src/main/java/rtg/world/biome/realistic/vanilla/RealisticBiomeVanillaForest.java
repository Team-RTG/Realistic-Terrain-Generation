package rtg.world.biome.realistic.vanilla;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.biome.BiomeConfig;
import rtg.api.biome.vanilla.config.BiomeConfigVanillaForest;
import rtg.world.biome.deco.collection.DecoCollectionForest;
import rtg.world.gen.surface.vanilla.SurfaceVanillaForest;
import rtg.world.gen.terrain.vanilla.TerrainVanillaForest;

public class RealisticBiomeVanillaForest extends RealisticBiomeVanillaBase
{
    
    public static Block topBlock = BiomeGenBase.forest.topBlock;
    public static Block fillerBlock = BiomeGenBase.forest.fillerBlock;
    
    public RealisticBiomeVanillaForest(BiomeConfig config)
    {
    
        super(config, 
            BiomeGenBase.forest,
            BiomeGenBase.river,
            new TerrainVanillaForest(),
            new SurfaceVanillaForest(config, Blocks.grass, Blocks.dirt, false, null, 0f, 1.5f, 60f, 65f, 1.5f, Blocks.dirt, (byte)2, 0.10f)
        );

		this.addDecoCollection(new DecoCollectionForest(this.config._boolean(BiomeConfigVanillaForest.decorationLogsId)));
    }
}
