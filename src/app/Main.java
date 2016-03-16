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

package app;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import org.jvnet.substance.SubstanceLookAndFeel;
import org.jvnet.substance.skin.BusinessBlueSteelSkin;

import app.gui.ControllerViewMenu;

public class Main {

	private static void loadFeatures() {
		try {
			Config.load();
			Config.save();
			Translate.load();
		} catch (Exception e) {
			Log.error(Main.class, "loadFeatures()", e);
			System.exit(0);
		}
	}

	public static void main(String[] args) {
		loadFeatures();
		JFrame.setDefaultLookAndFeelDecorated(true);
		JDialog.setDefaultLookAndFeelDecorated(true);
		SubstanceLookAndFeel.setSkin(new BusinessBlueSteelSkin());
		UIManager.put(SubstanceLookAndFeel.BUTTON_NO_MIN_SIZE_PROPERTY, Boolean.TRUE);
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new ControllerViewMenu();
			}
		});
	}

}
