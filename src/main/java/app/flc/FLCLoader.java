/**
 * Copyright (c) 2014 Saúl Piña <sauljabin@gmail.com>.
 * <p>
 * This file is part of FuzzyBattle.
 * <p>
 * FuzzyBattle is licensed under The MIT License.
 * For full copyright and license information please see the LICENSE file.
 */

package app.flc;

import java.io.InputStream;

public class FLCLoader {

    public static final String FCL_RULES_FCL = "fcl/rules.fcl";

    public static InputStream load() {
        return ClassLoader.getSystemResourceAsStream(FCL_RULES_FCL);
    }
}
