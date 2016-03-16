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

import net.sourceforge.jFuzzyLogic.FIS;
import app.Config;
import app.Translate;

public class ControllerViewCalculateSpeed extends Controller {

	private ViewCalculateSpeed viewCalculateSpeed;

	public ControllerViewCalculateSpeed() {
		viewCalculateSpeed = new ViewCalculateSpeed();
		viewCalculateSpeed.setController(this);
	}

	@Override
	public void action(Object source) {
		if (source.equals(viewCalculateSpeed))
			close();
		else if (source.equals(viewCalculateSpeed.getBtnClose()))
			close();
		else if (source.equals(viewCalculateSpeed.getBtnCalculate()))
			calculate();
	}

	public void calculate() {
		Integer life = (Integer) viewCalculateSpeed.getSpiLife().getValue();

		if (life == null) {
			JOptionPane.showMessageDialog(viewCalculateSpeed, Translate.get("ERROR_EMPTYVALUES"), Translate.get("GUI_ERROR"), JOptionPane.ERROR_MESSAGE);
			return;
		}

		FIS fis = FIS.load(Config.get("RULES_PATH"), true);
		fis.setVariable("life", life);
		fis.evaluate();
		fis.getVariable("speed").chartDefuzzifier(true);
	}

	private void close() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new ControllerViewMenu();
				viewCalculateSpeed.dispose();
			}
		});
	}

}
