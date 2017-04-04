package eda216_project;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JTextField;

public class BlockingPallets extends BasicPane {
	private static final long serialVersionUID = 1;

	/**
	 * The text field where the user id is entered.
	 */
	private JTextField[] fields;

	/**
	 * The number of the field where the cookie is entered.
	 */
	private static final int COOKIE_NAME = 0;

	/**
	 * The number of the field where the pallet time is entered.
	 */
	private static final int PALLET_START_TIME = 1;

	/**
	 * The number of the field where the pallet time is entered.
	 */
	private static final int PALLET_END_TIME = 2;

	/**
	 * The total number of fields in the fields array.
	 */
	private static final int NBR_FIELDS = 3;

	public BlockingPallets(Database db) {
		super(db);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Create the top panel, consisting of the text field.
	 * 
	 * @return The top panel.
	 */
	public JComponent createTopPanel() {
		String[] texts = new String[NBR_FIELDS];
		texts[COOKIE_NAME] = "Cookie name";
		texts[PALLET_START_TIME] = "Pallet start time";
		texts[PALLET_END_TIME] = "Pallet end time";
		fields = new JTextField[NBR_FIELDS];
		for (int i = 0; i < fields.length; i++) {
			fields[i] = new JTextField(20);
			fields[i].setEditable(true);
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
		buttons[0] = new JButton("Block Pallet");
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
			String cookieName = fields[COOKIE_NAME].getText();
			String date1 = fields[PALLET_START_TIME].getText();
			String date2 = fields[PALLET_END_TIME].getText();
			Set<String> pID = new HashSet<String>();
			Set<String> pallets = db.getCookieDatePallets(cookieName, date1, date2);
			if (db.getCookieName().contains(cookieName)) {
				String date = db.getCookieDateProduced(cookieName);
				if (date.compareTo(date1) >= 0 && date.compareTo(date2) <=0) {
					for(String p : pallets){
						db.blockPallet(p);
						pID.add(p);
					}
					messageLabel.setText("Pallet with ID " + pID + " is blocked");
				} else {
					messageLabel.setText(
							"There was no pallets produced within this time span with the product: " + cookieName);
				}
			} else {
				messageLabel.setText("There are no cookies with that name");
			}
		}
	}
}
