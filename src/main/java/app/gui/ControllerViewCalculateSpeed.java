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

        FIS fis = FIS.load(FLCLoader.load(), true);
        fis.setVariable("life", life);
        fis.evaluate();
        Variable speed = fis.getVariable("speed");

        JFuzzyChart.get().chart(speed, speed.getDefuzzifier(), true);
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
