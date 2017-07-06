package rtg.api.world.deco.collection;

import rtg.api.config.BiomeConfig;
import rtg.api.world.deco.DecoCactus;
import rtg.api.world.deco.DecoDeadBush;
import rtg.api.world.deco.DecoDesertWell;


/**
 * @author WhichOnesPink
 */
public class DecoCollectionDesert extends DecoCollectionBase {

    public DecoCollectionDesert(BiomeConfig config) {

        super(config);

        DecoDesertWell decoDesertWell = new DecoDesertWell();
        decoDesertWell.config().MAX_Y.set(80);
        decoDesertWell.setStrengthFactor(1f);
        decoDesertWell.setChance(160);
        this.addDeco(decoDesertWell);

        DecoCactus decoCactus = new DecoCactus();
        decoCactus.config().MAX_Y.set(90);
        decoCactus.setStrengthFactor(3f);
        decoCactus.setChance(2);
        this.addDeco(decoCactus, config.ALLOW_CACTUS.get());

        DecoDeadBush decoDeadBush = new DecoDeadBush();
        decoDeadBush.config().MAX_Y.set(128);
        decoDeadBush.setStrengthFactor(2f);
        decoDeadBush.setChance(3);
        this.addDeco(decoDeadBush);
    }
}
