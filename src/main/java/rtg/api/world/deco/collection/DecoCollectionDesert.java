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
        decoDesertWell.setMaxY(80);
        decoDesertWell.setStrengthFactor(1f);
        decoDesertWell.setChance(160);
        this.addDeco(decoDesertWell);

        DecoCactus decoCactus = new DecoCactus();
        decoCactus.setMaxY(90);
        decoCactus.setStrengthFactor(3f);
        decoCactus.setChance(2);
        this.addDeco(decoCactus, config.ALLOW_CACTUS.get());

        DecoDeadBush decoDeadBush = new DecoDeadBush();
        decoDeadBush.setMaxY(128);
        decoDeadBush.setStrengthFactor(2f);
        decoDeadBush.setChance(3);
        this.addDeco(decoDeadBush);
    }
}
