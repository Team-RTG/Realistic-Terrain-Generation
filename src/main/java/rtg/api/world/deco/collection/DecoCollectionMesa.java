package rtg.api.world.deco.collection;

import net.minecraft.init.Blocks;

import rtg.api.config.BiomeConfig;
import rtg.api.util.BlockUtil;
import rtg.api.world.deco.*;


/**
 * @author WhichOnesPink
 */
public class DecoCollectionMesa extends DecoCollectionBase {

    public DecoCollectionMesa(BiomeConfig config) {

        super(config);

        DecoBoulder decoBoulder = new DecoBoulder();
        decoBoulder.config().BOULDER_BLOCK.set(Blocks.COBBLESTONE.getDefaultState());
        decoBoulder.config().MAX_Y.set(83);
        this.addDeco(decoBoulder);

        DecoShrub decoShrub = new DecoShrub();
        decoShrub.config().LOOPS.set(2);
        decoShrub.config().CHANCE.set(4);
        decoShrub.config().MAX_Y.set(90);
        addDeco(decoShrub);

        DecoDeadBush decoDeadBush = new DecoDeadBush();
        decoDeadBush.config().MAX_Y.set(100);
        decoDeadBush.config().LOOPS.set(3);
        this.addDeco(decoDeadBush);

        DecoCactus decoCactus = new DecoCactus();
        decoCactus.config().SOIL_BLOCK.set(BlockUtil.getStateSand(1));
        decoCactus.config().LOOPS.set(18);
        decoCactus.config().MAX_Y.set(100);
        this.addDeco(decoCactus, config.ALLOW_CACTUS.get());
    }
}
