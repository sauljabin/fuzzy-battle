/**
 * Copyright (c) 2014 Saúl Piña <sauljabin@gmail.com>.
 * <p>
 * This file is part of FuzzyBattle.
 * <p>
 * FuzzyBattle is licensed under The MIT License.
 * For full copyright and license information please see the LICENSE file.
 */

package app.gui;

import app.Config;
import app.Translate;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;

public class ViewCalculateDamage extends View {

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
