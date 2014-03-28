/**
 * 
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 * 
 *		SAUL PIÑA - SAULJP07@GMAIL.COM
 *		2014
 */

package app.gui;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;
import app.Translate;

public class ViewMenu extends View {

	private static final long serialVersionUID = 5406990172482581323L;
	private JButton btnStart;
	private JPanel pnlCenter;
	private JButton btnConfig;
	private JButton btnCredits;
	private JButton btnExit;

	@Override
	public void init() {
		setSize(400, 400);
		setLayout(new BorderLayout());

		pnlCenter = new JPanel(new MigLayout("insets 50"));
		add(pnlCenter, BorderLayout.CENTER);

		Font buttonFont = new Font(Font.SANS_SERIF, Font.BOLD, 20);

		btnStart = new JButton();
		btnStart.setFont(buttonFont);

		btnConfig = new JButton();
		btnConfig.setFont(buttonFont);

		btnCredits = new JButton();
		btnCredits.setFont(buttonFont);

		btnExit = new JButton();
		btnExit.setFont(buttonFont);

		String buttonLayout = "width 100%, height 50, wrap 20";
		pnlCenter.add(btnStart, buttonLayout);
		pnlCenter.add(btnConfig, buttonLayout);
		pnlCenter.add(btnCredits, buttonLayout);
		pnlCenter.add(btnExit, buttonLayout);

		addButtonToAction(btnConfig);
		addButtonToAction(btnStart);
		addButtonToAction(btnCredits);
		addButtonToAction(btnExit);
	}

	@Override
	public void translate() {
		btnStart.setText(Translate.get("GUI_START"));
		btnConfig.setText(Translate.get("GUI_CONFIG"));
		btnCredits.setText(Translate.get("GUI_CREDITS"));
		btnExit.setText(Translate.get("GUI_EXIT"));
	}

	public JButton getBtnStart() {
		return btnStart;
	}

	public JButton getBtnConfig() {
		return btnConfig;
	}

	public JButton getBtnCredits() {
		return btnCredits;
	}

	public JButton getBtnExit() {
		return btnExit;
	}

}
