/**
 * Copyright (c) 2016 Saúl Piña <sauljabin@gmail.com>.
 * 
 * This file is part of FuzzyBattle.
 * 
 * FuzzyBattle is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2 of the License, or
 * (at your option) any later version.
 * 
 * FuzzyBattle is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with FuzzyBattle.  If not, see <http://www.gnu.org/licenses/>.
 */

package app.gui;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import app.Config;
import app.Log;
import app.Translate;

public class ControllerViewConfig extends Controller {

	private ViewConfig viewConfig;

	public ControllerViewConfig() {
		viewConfig = new ViewConfig();

		String[] languages = Translate.languages();
		for (String lan : languages) {
			viewConfig.getCmbModelLanguages().addElement(lan);
		}

		viewConfig.getCmbModelLanguages().setSelectedItem(Config.get("TRANSLATE"));
		viewConfig.getChckMusic().setSelected(Config.get("MUSIC").equals("ON"));
		viewConfig.getChckSoundEffects().setSelected(Config.get("SOUND_EFFECTS").equals("ON"));
		viewConfig.setController(this);
	}

	@Override
	public void action(Object source) {
		if (source.equals(viewConfig))
			close();
		else if (source.equals(viewConfig.getBtnClose()))
			close();
		else if (source.equals(viewConfig.getCmbLanguages()))
			changeLanguage();
		else if (source.equals(viewConfig.getSpnFPS()))
			changeFPS();
		else if (source.equals(viewConfig.getChckMusic()))
			changeMusic();
		else if (source.equals(viewConfig.getChckSoundEffects()))
			changeSoundEffects();
	}

	public void changeSoundEffects() {
		String soundEffects = viewConfig.getChckSoundEffects().isSelected() ? "ON" : "OFF";
		save("SOUND_EFFECTS", soundEffects);
	};

	public void changeMusic() {
		String music = viewConfig.getChckMusic().isSelected() ? "ON" : "OFF";
		save("MUSIC", music);
	};

	public void changeFPS() {
		String FPS = viewConfig.getSpnModelFPS().getValue().toString();
		save("DEFAULT_FPS", FPS);
	};

	public void changeLanguage() {
		try {
			String language = viewConfig.getCmbModelLanguages().getSelectedItem().toString();
			Translate.load(language);
			viewConfig.translate();
			save("TRANSLATE", language);
		} catch (Exception e) {
			Log.error(getClass(), Translate.get("LOG_ERRORLOADLANGUAGE"), e);
			JOptionPane.showMessageDialog(viewConfig, Translate.get("LOG_ERRORLOADLANGUAGE"), Translate.get("GUI_ERROR"), JOptionPane.ERROR_MESSAGE);
		}
	};

	public void save(String key, String value) {
		Config.set(key, value);
		viewConfig.getTable().repaint();
		try {
			Config.save();
		} catch (Exception e) {
			Log.error(getClass(), Translate.get("LOG_ERRORSAVECONFIG"), e);
			JOptionPane.showMessageDialog(viewConfig, Translate.get("LOG_ERRORSAVECONFIG"), Translate.get("GUI_ERROR"), JOptionPane.ERROR_MESSAGE);
		}
	}

	public void close() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new ControllerViewMenu();
				viewConfig.dispose();
			}
		});
	}

}
