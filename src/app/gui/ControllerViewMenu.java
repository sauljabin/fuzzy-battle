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

import javax.swing.SwingUtilities;

public class ControllerViewMenu extends Controller {

	private ViewMenu viewMenu;

	public ControllerViewMenu() {
		viewMenu = new ViewMenu();
		viewMenu.setController(this);
	}

	@Override
	public void action(Object source) {
		if (source.equals(viewMenu))
			exit();
		else if (source.equals(viewMenu.getBtnExit()))
			exit();
		else if (source.equals(viewMenu.getBtnStart()))
			start();
		else if (source.equals(viewMenu.getBtnConfig()))
			config();
		else if (source.equals(viewMenu.getBtnCredits()))
			credits();
	}

	public void start() {

	}

	public void exit() {
		System.exit(0);
	}

	public void config() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new ControllerViewConfig();
				viewMenu.dispose();
			}
		});
	}

	public void credits() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new ViewAbout();
			}
		});
	}

}
