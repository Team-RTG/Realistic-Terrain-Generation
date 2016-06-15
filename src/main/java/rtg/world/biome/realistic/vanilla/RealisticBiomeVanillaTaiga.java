package rtg.world.biome.realistic.vanilla;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.biome.BiomeConfig;
import rtg.api.biome.vanilla.config.BiomeConfigVanillaTaiga;
import rtg.world.biome.deco.collection.DecoCollectionTaiga;
import rtg.world.gen.surface.vanilla.SurfaceVanillaTaiga;
import rtg.world.gen.terrain.vanilla.TerrainVanillaTaiga;

public class RealisticBiomeVanillaTaiga extends RealisticBiomeVanillaBase
{
    
    public static Block topBlock = BiomeGenBase.taiga.topBlock;
    public static Block fillerBlock = BiomeGenBase.taiga.fillerBlock;
    
    public RealisticBiomeVanillaTaiga(BiomeConfig config)
    {
    
        super(config, 
            BiomeGenBase.taiga,
            BiomeGenBase.river,
            new TerrainVanillaTaiga(),
            new SurfaceVanillaTaiga(config, topBlock, fillerBlock)
        );
        
        this.addDecoCollection(new DecoCollectionTaiga(this.config._boolean(BiomeConfigVanillaTaiga.decorationLogsId), 10f));
    }
}
