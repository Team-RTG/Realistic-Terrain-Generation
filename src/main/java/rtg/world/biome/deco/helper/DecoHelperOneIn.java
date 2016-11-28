package rtg.world.biome.deco.helper;

/**
 * This deco helper has a one in x chance of called a given deco
 *
 * @author Zeno410
 */

import rtg.api.world.RTGWorld;
import rtg.world.biome.deco.DecoBase;
import rtg.world.biome.realistic.RealisticBiomeBase;

public class DecoHelperOneIn extends DecoBase {

    private DecoBase deco;
    private int chances;

    public DecoHelperOneIn(DecoBase deco, int chances) {

        super();

        this.deco = deco;
        this.chances = chances;
    }

    @Override
    public void generate(RealisticBiomeBase biome, RTGWorld rtgWorld, int worldX, int worldZ, float strength, float river, boolean hasPlacedVillageBlocks) {

        if (this.allowed) {

            if (rtgWorld.rand.nextInt(this.chances) == 0) {

                this.deco.generate(biome, rtgWorld, worldX, worldZ, strength, river, hasPlacedVillageBlocks);
            }
        }
    }
}