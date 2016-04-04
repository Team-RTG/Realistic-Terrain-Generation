package rtg.world.biome.realistic;

import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.mods.Mods;

public class RealisticBiomePatcher {

    private int patchBiomeId;
    private RealisticBiomeBase realisticBiome;
    private BiomeGenBase baseBiome;

    public RealisticBiomePatcher() {
        this.patchBiomeId = Mods.RTG.config.PATCH_BIOME_ID.get();

        if (this.patchBiomeId > -1) {

            try {
                this.realisticBiome = RealisticBiomeBase.getBiome(this.patchBiomeId);
            } catch (Exception e) {
                throw new RuntimeException("Realistic patch biome " + this.patchBiomeId + " not found. Please make sure this biome is enabled.");
            }

            try {
                this.baseBiome = realisticBiome.baseBiome;
            } catch (Exception e) {
                throw new RuntimeException("Base patch biome " + this.patchBiomeId + " not found. Please make sure this biome is enabled.");
            }
        }
    }

    public RealisticBiomeBase getPatchedRealisticBiome(String exceptionMessage) {
        if (this.patchBiomeId < 0) {
            throw new RuntimeException(exceptionMessage);
        } else {

            if (this.realisticBiome == null) throw new RuntimeException("Problem patching realistic biome.");

            return this.realisticBiome;
        }
    }

    public BiomeGenBase getPatchedBaseBiome(String exceptionMessage) {
        if (this.patchBiomeId < 0) {
            throw new RuntimeException(exceptionMessage);
        } else {

            if (this.baseBiome == null) throw new RuntimeException("Problem patching base biome.");

            return this.baseBiome;
        }
    }
}
