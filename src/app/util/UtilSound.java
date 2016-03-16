/**
 * Copyright (c) 2014 Saúl Piña <sauljabin@gmail.com>.
 * 
 * This file is part of FuzzyBattle.
 * 
 * FuzzyBattle is licensed under The MIT License.
 * For full copyright and license information please see the LICENSE file.
 */

package app.util;

import java.io.File;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;

public class UtilSound {

	public static synchronized Clip getClip(String path) {

		AudioInputStream soundIn;
		DataLine.Info info = null;
		try {
			soundIn = AudioSystem.getAudioInputStream(new File(path));
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
