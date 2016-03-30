package rtg.util;

/**
 * @author topisani
 */
public enum SupportedMods {
    BOP("BiomesOPlenty"),
    ABYSSALCRAFT("abyssalcraft");

    public final boolean present;
    public final String modId;

    SupportedMods(String modId) {
        this.modId = modId;
        present = new ModPresenceTester(modId).present();
    }
}
