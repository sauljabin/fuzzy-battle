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
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

public class ViewMenu extends View {

    private JButton btnStart;
    private JPanel pnlCenter;
    private JButton btnCredits;
    private JButton btnExit;
    private JButton btnMembershipFunctions;
    private JButton btnCalculateDamage;
    private JButton btnCalculateSpeed;

    @Override
    public void init() {
        setSize(400, 450);
        setLayout(new BorderLayout());

        pnlCenter = new JPanel(new MigLayout("insets 30"));
        add(pnlCenter, BorderLayout.CENTER);

        Font buttonFont = new Font(Font.SANS_SERIF, Font.BOLD, 20);

        btnStart = new JButton();
        btnStart.setFont(buttonFont);

        btnStart = new JButton();
        btnStart.setFont(buttonFont);

        btnStart = new JButton();
        btnStart.setFont(buttonFont);

        btnMembershipFunctions = new JButton();
        btnMembershipFunctions.setFont(buttonFont);

        btnCalculateDamage = new JButton();
        btnCalculateDamage.setFont(buttonFont);

        btnCalculateSpeed = new JButton();
        btnCalculateSpeed.setFont(buttonFont);

        btnCredits = new JButton();
        btnCredits.setFont(buttonFont);

        btnExit = new JButton();
        btnExit.setFont(buttonFont);

        String buttonLayout = "width 100%, height 40, wrap 10";
        pnlCenter.add(btnStart, buttonLayout);
        pnlCenter.add(btnMembershipFunctions, buttonLayout);
        pnlCenter.add(btnCalculateDamage, buttonLayout);
        pnlCenter.add(btnCalculateSpeed, buttonLayout);
        pnlCenter.add(btnCredits, buttonLayout);
        pnlCenter.add(btnExit, buttonLayout);

        addButtonToAction(btnStart);
        addButtonToAction(btnCredits);
        addButtonToAction(btnExit);
        addButtonToAction(btnMembershipFunctions);
        addButtonToAction(btnCalculateDamage);
        addButtonToAction(btnCalculateSpeed);
    }

    public JButton getBtnCalculateSpeed() {
        return btnCalculateSpeed;
    }

    public void setBtnCalculateSpeed(JButton btnCalculateSpeed) {
        this.btnCalculateSpeed = btnCalculateSpeed;
    }

    @Override
    public void translate() {
        btnStart.setText(Translate.get("GUI_START"));
        btnCredits.setText(Translate.get("GUI_CREDITS"));
        btnExit.setText(Translate.get("GUI_EXIT"));
        btnMembershipFunctions.setText(Translate.get("GUI_MEMBERSHIPFUNCTIONS"));
        btnCalculateDamage.setText(Translate.get("GUI_CALCULATEDAMAGE"));
        btnCalculateSpeed.setText(Translate.get("GUI_CALCULATESPEED"));
    }

    public JButton getBtnStart() {
        return btnStart;
    }

    public JButton getBtnCredits() {
        return btnCredits;
    }

    public JButton getBtnExit() {
        return btnExit;
    }

    public JButton getBtnMembershipFunctions() {
        return btnMembershipFunctions;
    }

    public JButton getBtnCalculateDamage() {
        return btnCalculateDamage;
    }

}
