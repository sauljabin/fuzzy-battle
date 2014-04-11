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
