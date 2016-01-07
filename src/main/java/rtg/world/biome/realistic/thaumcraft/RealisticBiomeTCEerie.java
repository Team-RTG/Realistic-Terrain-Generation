
package rtg.world.biome.realistic.thaumcraft;

import rtg.config.thaumcraft.ConfigTC;
import rtg.world.biome.BiomeBase;
<<<<<<< HEAD
import rtg.world.gen.surface.thaumcraft.SurfaceTCEerie;
import rtg.world.gen.terrain.thaumcraft.TerrainTCEerie;
=======
import rtg.world.gen.surface.thaumcraft.SurfaceTCMagicalForest;
import rtg.world.gen.terrain.thaumcraft.TerrainTCMagicalForest;
>>>>>>> master

import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeTCEerie extends RealisticBiomeTCBase
{
	public RealisticBiomeTCEerie(BiomeGenBase tcBiome)
	{
		super(
			tcBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.TEMPERATE),
<<<<<<< HEAD
			new TerrainTCEerie(),
			new SurfaceTCEerie(tcBiome.topBlock, tcBiome.fillerBlock)
		);

        this.setRealisticBiomeName("Thaumcraft Eerie");
        this.biomeSize = BiomeSize.NORMAL;
        this.biomeWeight = ConfigTC.weightTCEerie;
        this.generateVillages = ConfigTC.villageTCEerie;
=======
			new TerrainTCMagicalForest(),
			new SurfaceTCMagicalForest(tcBiome.topBlock, tcBiome.fillerBlock)
		);

		this.setRealisticBiomeName("Thaumcraft Eerie");
		this.biomeSize = BiomeSize.NORMAL;
		this.biomeWeight = 0;
		this.generateVillages = ConfigTC.villageTCMagicalForest;
>>>>>>> master
	}
}