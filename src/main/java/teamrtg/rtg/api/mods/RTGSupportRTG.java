package teamrtg.rtg.api.mods;

import teamrtg.rtg.api.config.ConfigRTG;
import teamrtg.rtg.api.util.ModPresenceTester;

/**
 * @author topisani
 */
public class RTGSupportRTG extends RTGSupport {

    public ConfigRTG config;

    public RTGSupportRTG() {
        super("RTG", true, false);
        config = new ConfigRTG();
        config.initDefaults();
        super.config = config;
    }

    /**
     * Is the mod present?
     * @return true if mod is currently installed
     * @see ModPresenceTester
     */
    @Override
    public boolean isPresent() {
        return true;
    }
}
