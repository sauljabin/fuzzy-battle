/**
 * Copyright (c) 2014 Saúl Piña <sauljabin@gmail.com>.
 * 
 * This file is part of FuzzyBattle.
 * 
 * FuzzyBattle is licensed under The MIT License.
 * For full copyright and license information please see the LICENSE file.
 */

package app.gui;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import net.sourceforge.jFuzzyLogic.FIS;
import app.Config;
import app.Translate;

public class ControllerViewCalculateDamage extends Controller {

	private ViewCalculateDamage viewCalculateDamage;

	public ControllerViewCalculateDamage() {
		viewCalculateDamage = new ViewCalculateDamage();
		viewCalculateDamage.setController(this);
	}

	@Override
	public void action(Object source) {
		if (source.equals(viewCalculateDamage))
			close();
		else if (source.equals(viewCalculateDamage.getBtnClose()))
			close();
		else if (source.equals(viewCalculateDamage.getBtnCalculate()))
			calculate();
	}

	public void calculate() {
		Integer attack = (Integer) viewCalculateDamage.getSpiAttack().getValue();
		Integer resistance = (Integer) viewCalculateDamage.getSpiResistance().getValue();
		Integer life = (Integer) viewCalculateDamage.getSpiLife().getValue();

		if (attack == null || resistance == null || life == null) {
			JOptionPane.showMessageDialog(viewCalculateDamage, Translate.get("ERROR_EMPTYVALUES"), Translate.get("GUI_ERROR"), JOptionPane.ERROR_MESSAGE);
			return;
		}

		FIS fis = FIS.load(Config.get("RULES_PATH"), true);
		fis.setVariable("life", life);
		fis.setVariable("attack", attack);
		fis.setVariable("resistance", resistance);
		fis.evaluate();
		fis.getVariable("damage").chartDefuzzifier(true);
	}

	private void close() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new ControllerViewMenu();
				viewCalculateDamage.dispose();
			}
		});
	}

}
