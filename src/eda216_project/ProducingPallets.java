package eda216_project;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ProducingPallets extends BasicPane {
	private static final long serialVersionUID = 1;

	/**
	 * The text field where the user id is entered.
	 */
	private JTextField[] fields;

	private JTextField field;

	/**
	 * The number of the field where the cookie is entered.
	 */
	private static final int COOKIE_NAME = 0;

	/**
	 * The number of the field where the pallet ID is.
	 */
	private static final int PALLET_ID = 1;

	/**
	 * The number of the field where the date and time is.
	 */

	private static final int DATE_TIME = 2;

	/**
	 * The total number of fields in the fields array.
	 */
	private static final int NBR_FIELDS = 3;

	public ProducingPallets(Database db) {
		super(db);
	}

	/**
	 * Create the left panel, consisting of the text field.
	 * 
	 * @return The left panel.
	 */
	public JComponent createLeftTopPanel() {
		JPanel panel = new JPanel();
		JLabel label = new JLabel();
		label.setText("Cookie name");
		field = new JTextField(20);
		panel.add(label);
		panel.add(field);
		return panel;
	}

	/**
	 * Create the left panel, consisting of the text field.
	 * 
	 * @return The left panel.
	 */
	public JComponent createTopPanel() {
		String[] texts = new String[NBR_FIELDS];
		texts[DATE_TIME] = "Date and time";
		texts[PALLET_ID] = "Pallet ID";
		texts[COOKIE_NAME] = "Product";
		fields = new JTextField[NBR_FIELDS];
		for (int i = 0; i < fields.length; i++) {
			fields[i] = new JTextField(20);
			fields[i].setEditable(false);
		}
		return new InputPanel(texts, fields);
	}

	/**
	 * Create the bottom panel, consisting of the login button and the message
	 * line.
	 * 
	 * @return The bottom panel.
	 */
	public JComponent createBottomPanel() {
		JButton[] buttons = new JButton[1];
		buttons[0] = new JButton("Produce Pallet");
		ActionHandler actHand = new ActionHandler();
		fields[COOKIE_NAME].addActionListener(actHand);
		return new ButtonAndMessagePanel(buttons, messageLabel, actHand);
	}

	/**
	 * A class which listens for button clicks.
	 */
	class ActionHandler implements ActionListener {
		/**
		 * Called when the user clicks the login button. Checks with the
		 * database if the user exists, and if so notifies the CurrentUser
		 * object.
		 * 
		 * @param e
		 *            The event object (not used).
		 */
		public void actionPerformed(ActionEvent e) {
			String cookieName = field.getText();
			if (db.getCookieName().contains(cookieName)) {
				int produce = db.producePallet(cookieName);
				Set<String> ingredients = db.getIngredients(cookieName);
				for (String ingredient : ingredients) {
					String amnt = db.getIngredientAmount(ingredient);
					db.updateIngredients(amnt, ingredient);
				}
				String palletID = Integer.toString(produce);
				fields[COOKIE_NAME].setText(cookieName);
				fields[DATE_TIME].setText(db.getPalletDateProduced(palletID));
				fields[PALLET_ID].setText(palletID);
				db.getAmountInStorage();

			} else {
				messageLabel.setText("There are no cookies with that name");
			}
		}
	}
}
