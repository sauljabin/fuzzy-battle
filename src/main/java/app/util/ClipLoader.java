/**
 * Copyright (c) 2014 Saúl Piña <sauljabin@gmail.com>.
 * <p>
 * This file is part of FuzzyBattle.
 * <p>
 * FuzzyBattle is licensed under The MIT License.
 * For full copyright and license information please see the LICENSE file.
 */

package app.util;

import javax.sound.sampled.*;

public class ClipLoader {

    public static synchronized Clip getClip(String name) {
        AudioInputStream soundIn;
        DataLine.Info info;
        try {
            soundIn = AudioSystem.getAudioInputStream(ClassLoader.getSystemResourceAsStream("sound/" + name + ".wav"));
            AudioFormat format = soundIn.getFormat();
            info = new DataLine.Info(Clip.class, format);
            Clip clip = (Clip) AudioSystem.getLine(info);
            clip.open(soundIn);
            return clip;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
