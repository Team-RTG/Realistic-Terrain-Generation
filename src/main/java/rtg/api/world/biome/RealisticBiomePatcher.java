package rtg.api.world.biome;

import net.minecraft.world.biome.Biome;

import rtg.api.RTGAPI;
import rtg.api.config.RTGConfig;


public class RealisticBiomePatcher {

    private int patchBiomeId;
    private IRealisticBiome realisticBiome;
    private Biome baseBiome;
    private RTGConfig rtgConfig = RTGAPI.config();

    public RealisticBiomePatcher() {

        this.patchBiomeId = rtgConfig.PATCH_BIOME_ID.get();

        if (this.patchBiomeId > -1) {

            try {
                this.realisticBiome = IRealisticBiome.getRealisticBiome(this.patchBiomeId);
            }
            catch (Exception e) {
                throw new RuntimeException("Realistic patch biome " + this.patchBiomeId + " not found. Please make sure this biome is enabled.");
            }

            try {
                this.baseBiome = realisticBiome.baseBiome();
            }
            catch (Exception e) {
                throw new RuntimeException("Base patch biome " + this.patchBiomeId + " not found. Please make sure this biome is enabled.");
            }
        }
    }

    public IRealisticBiome getPatchedRealisticBiome(String exceptionMessage) {

        if (this.patchBiomeId < 0) {
            throw new RuntimeException(exceptionMessage);
        }
        else {

            if (this.realisticBiome == null) {
                throw new RuntimeException("Problem patching realistic biome.");
            }

            return this.realisticBiome;
        }
    }

    public Biome getPatchedBaseBiome(String exceptionMessage) {

        if (this.patchBiomeId < 0) {
            throw new RuntimeException(exceptionMessage);
        }
        else {

            if (this.baseBiome == null) {
                throw new RuntimeException("Problem patching base biome.");
            }

            return this.baseBiome;
        }
    }
}
