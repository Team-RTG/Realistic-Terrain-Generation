package teamrtg.rtg.api.mods;

import teamrtg.rtg.world.gen.ChunkProviderRTG;

/**
 * @author topisani
 */
public class RTGSupportAC extends RTGSupport {

    public RTGSupportAC() {
        super("Abyssalcraft", false, false);
    }

    @Override
    public void initBiomes(ChunkProviderRTG chunkProvider) {}

    @Override
    public void syncConfig() {}
}
