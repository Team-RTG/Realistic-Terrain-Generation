package rtg.api.util;

import rtg.util.Logger;

/**
 * @author topisani
 */
public class RTGException extends Exception {

    public Type type;

    private String message = "";
    private String identifier = "";

    public enum Type {
        CONFIG_SYNTAX,
        STUPID_DEVELOPER
    }

    public RTGException(Type type, String message) {
        this.message = message;
        this.type = type;
    }

    public RTGException(Type type, String message, String identifier) {
        this.message = message;
        this.type = type;
        this.identifier = identifier;
    }

    public void log() {
        String s = "RTG experienced a %s error.";
        if (!this.message.isEmpty()) s += " Reason: " + identifier;
        if (!this.identifier.isEmpty()) s += "Crash identifier: " + identifier;
        Logger.error(s, type.name().replaceAll("_", " ").toLowerCase());
    }

    public void crash() {
        String s = "RTG experienced a %s error.";
        if (!this.message.isEmpty()) s += " Reason: %s";
        if (!this.identifier.isEmpty()) s += "Crash identifier: " + identifier;
        Logger.fatal(s, this, type.name().replaceAll("_", " ").toLowerCase());
    }

    public String getMessage() {
        return this.message;
    }
}