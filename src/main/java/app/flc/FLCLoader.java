package app.flc;

import java.io.InputStream;

public class FLCLoader {

    public static final String FCL_RULES_FCL = "fcl/rules.fcl";

    public static InputStream load() {
        return ClassLoader.getSystemResourceAsStream(FCL_RULES_FCL);
    }
}
