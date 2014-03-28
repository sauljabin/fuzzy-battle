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

import java.util.Collections;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.SpinnerNumberModel;
import javax.swing.table.AbstractTableModel;

import net.miginfocom.swing.MigLayout;
import app.Config;
import app.Translate;

public class ViewConfig extends View {

	private static final long serialVersionUID = -2532365944227381794L;
	private ConfigModelTable configModelTable;
	private JComboBox<String> cmbLanguages;
	private DefaultComboBoxModel<String> cmbModelLanguages;
	private JLabel lblLanguages;
	private JLabel lblFPS;
	private JSpinner spnFPS;
	private SpinnerNumberModel spnModelFPS;
	private JLabel lblMusic;
	private JCheckBox chckMusic;
	private JLabel lblSoundEffects;
	private JCheckBox chckSoundEffects;
	private JButton btnClose;
	private JTable table;

	@Override
	public void init() {
		setSize(400, 330);
		setLayout(new MigLayout());

		lblLanguages = new JLabel();
		cmbModelLanguages = new DefaultComboBoxModel<String>();
		cmbLanguages = new JComboBox<String>(cmbModelLanguages);

		lblFPS = new JLabel();
		spnModelFPS = new SpinnerNumberModel(Integer.parseInt(Config.get("DEFAULT_FPS")), 1, 1000, 1);
		spnFPS = new JSpinner(spnModelFPS);

		lblMusic = new JLabel();
		chckMusic = new JCheckBox();

		lblSoundEffects = new JLabel();
		chckSoundEffects = new JCheckBox();

		JScrollPane panel = new JScrollPane();
		table = new JTable();
		panel.setViewportView(table);
		configModelTable = new ConfigModelTable(table);

		btnClose = new JButton();

		add(lblLanguages, "width 50%");
		add(cmbLanguages, "width 100, wrap");
		add(lblFPS, "grow");
		add(spnFPS, "width 100, wrap");
		add(lblMusic, "grow");
		add(chckMusic, "width 100, wrap");
		add(lblSoundEffects, "grow");
		add(chckSoundEffects, "width 100, wrap 20");
		add(panel, "width 100%, height 90,  span 2, wrap 20");
		add(btnClose, "width 100%, height 40,  span 2");

		addComboBoxToAction(cmbLanguages);
		addCheckBoxToAction(chckMusic);
		addCheckBoxToAction(chckSoundEffects);
		addSpinnerToAction(spnFPS);
		addButtonToAction(btnClose);
	}

	public JTable getTable() {
		return table;
	}

	@Override
	public void translate() {
		configModelTable.setTitleName(Translate.get("GUI_NAME"));
		configModelTable.setTitleValue(Translate.get("GUI_VALUE"));
		lblLanguages.setText(Translate.get("GUI_LANGUAGE"));
		lblFPS.setText(Translate.get("GUI_FPS"));
		lblMusic.setText(Translate.get("GUI_MUSIC"));
		lblSoundEffects.setText(Translate.get("GUI_SOUNDEFFECTS"));
		btnClose.setText(Translate.get("GUI_CLOSE"));
	}

	public JComboBox<String> getCmbLanguages() {
		return cmbLanguages;
	}

	public DefaultComboBoxModel<String> getCmbModelLanguages() {
		return cmbModelLanguages;
	}

	public JSpinner getSpnFPS() {
		return spnFPS;
	}

	public SpinnerNumberModel getSpnModelFPS() {
		return spnModelFPS;
	}

	public JCheckBox getChckMusic() {
		return chckMusic;
	}

	public JCheckBox getChckSoundEffects() {
		return chckSoundEffects;
	}

	public JButton getBtnClose() {
		return btnClose;
	}

	public class ConfigModelTable extends AbstractTableModel {

		private static final long serialVersionUID = -8276891304790814037L;
		private Vector<String> keys;
		private JTable table;

		public void setTitleName(String titleName) {
			table.getColumnModel().getColumn(1).setHeaderValue(titleName);
			table.repaint();
		}

		public void setTitleValue(String titleValue) {
			table.getColumnModel().getColumn(2).setHeaderValue(titleValue);
			table.repaint();
		}

		public ConfigModelTable(JTable table) {
			keys = Config.getKeys();
			Collections.sort(keys);
			this.table = table;
			this.table.setModel(this);
			this.table.getColumnModel().getColumn(0).setMaxWidth(20);
			this.table.getColumnModel().getColumn(0).setHeaderValue("#");
		}

		@Override
		public int getColumnCount() {
			return 3;
		}

		@Override
		public int getRowCount() {
			return keys.size();
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			switch (columnIndex) {
			case 0:
				return rowIndex + 1;
			case 1:
				return keys.get(rowIndex);
			case 2:
				return Config.get(keys.get(rowIndex));
			}
			return null;
		}

	}

}
