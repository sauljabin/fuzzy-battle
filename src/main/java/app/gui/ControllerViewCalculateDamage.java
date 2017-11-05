/**
 * Copyright (c) 2014 Saúl Piña <sauljabin@gmail.com>.
 * <p>
 * This file is part of FuzzyBattle.
 * <p>
 * FuzzyBattle is licensed under The MIT License.
 * For full copyright and license information please see the LICENSE file.
 */

package app.gui;

import app.Translate;
import app.flc.FLCLoader;
import net.sourceforge.jFuzzyLogic.FIS;
import net.sourceforge.jFuzzyLogic.plot.JFuzzyChart;
import net.sourceforge.jFuzzyLogic.rule.Variable;

import javax.swing.*;

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

        FIS fis = FIS.load(FLCLoader.load(), true);
        fis.setVariable("life", life);
        fis.setVariable("attack", attack);
        fis.setVariable("resistance", resistance);
        fis.evaluate();
        Variable damage = fis.getVariable("damage");
        JFuzzyChart.get().chart(damage, damage.getDefuzzifier(), true);
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
