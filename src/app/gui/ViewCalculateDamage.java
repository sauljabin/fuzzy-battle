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

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import app.Config;
import app.Translate;
import net.miginfocom.swing.MigLayout;

public class ViewCalculateDamage extends View {

	private static final long serialVersionUID = -8335062229599252409L;
	private JSpinner spiLife;
	private JLabel lblLife;
	private JLabel lblResistance;
	private JSpinner spiResistance;
	private JLabel lblAttack;
	private JSpinner spiAttack;
	private JButton btnCalculate;
	private JButton btnClose;

	@Override
	public void init() {
		setSize(300, 200);
		setLayout(new MigLayout("insets 20"));
		lblLife = new JLabel();
		spiLife = new JSpinner();
		spiLife.setModel(new SpinnerNumberModel(0, 0, Integer.parseInt(Config.get("MAX_LIFE_VALUE")), 1));

		lblResistance = new JLabel();
		spiResistance = new JSpinner();
		spiResistance.setModel(new SpinnerNumberModel(0, 0, Integer.parseInt(Config.get("MAX_RESISTANCE_VALUE")), 1));

		lblAttack = new JLabel();
		spiAttack = new JSpinner();
		spiAttack.setModel(new SpinnerNumberModel(0, 0, Integer.parseInt(Config.get("MAX_ATTACK_VALUE")), 1));

		btnCalculate = new JButton();
		btnClose = new JButton();

		add(lblLife, "width 50%");
		add(spiLife, "width 50%, wrap");
		add(lblResistance, "grow");
		add(spiResistance, "grow, wrap");
		add(lblAttack, "grow");
		add(spiAttack, "grow, wrap 20");
		add(btnCalculate, "grow, height 30");
		add(btnClose, "grow, height 30, wrap");

		addButtonToAction(btnCalculate);
		addButtonToAction(btnClose);
	}

	@Override
	public void translate() {
		btnClose.setText(Translate.get("GUI_CLOSE"));
		btnCalculate.setText(Translate.get("GUI_CALCULATE"));
		lblLife.setText(Translate.get("GUI_LIFE"));
		lblResistance.setText(Translate.get("GUI_RESISTANCE"));
		lblAttack.setText(Translate.get("GUI_ATTACK"));
	}

	public JSpinner getSpiLife() {
		return spiLife;
	}

	public JSpinner getSpiResistance() {
		return spiResistance;
	}

	public JSpinner getSpiAttack() {
		return spiAttack;
	}

	public JButton getBtnCalculate() {
		return btnCalculate;
	}

	public JButton getBtnClose() {
		return btnClose;
	}

}
