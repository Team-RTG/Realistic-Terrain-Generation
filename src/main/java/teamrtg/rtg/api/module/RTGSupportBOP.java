package teamrtg.rtg.api.module;

import teamrtg.rtg.api.config.ConfigRTG;
import teamrtg.rtg.api.util.ModPresenceTester;

/**
 * @author WhichOnesPink
 */
public class RTGSupportBOP extends RTGModule {

    public ConfigRTG config;

    public RTGSupportBOP() {
        super("BiomesOPlenty", true, true);
    }

    /**
     * Is the mod present?
     * @return true if mod is currently installed
     * @see ModPresenceTester
     */
    @Override
    public boolean isPresent() {

        ModPresenceTester mpt = new ModPresenceTester("BiomesOPlenty");

        return mpt.present();
    }
}
