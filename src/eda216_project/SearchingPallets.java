package eda216_project;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class SearchingPallets extends BasicPane {
	private static final long serialVersionUID = 1;

	/**
	 * The text field where the user id is entered.
	 */
	private JTextField[] fields;
	private JTextField field1;
	private JTextField field2;
	private JPanel p;
	private int searching;
	/**
	 * The list model for the name list.
	 */
	private DefaultListModel<String> nameListModel;

	/**
	 * The choices list.
	 */
	private JList<String> nameList;

	/**
	 * The list model for the info list.
	 */
	private DefaultListModel<String> infoListModel;

	/**
	 * The info list.
	 */
	private JList<String> infoList;

	/**
	 * The number of the field where the pallet time is entered.
	 */
	private static final int PALLET_ID = 0;

	/**
	 * The number of the field where the cookie is entered.
	 */
	private static final int COOKIE_NAME = 1;

	/**
	 * The number of the field where the pallet time is entered.
	 */
	private static final int PALLET_DATE_TIME = 2;

	/**
	 * The number of the field where the pallet time is entered.
	 */
	private static final int PALLET_IS_BLOCKED = 3;

	/**
	 * The number of the field where the pallet time is entered.
	 */
	private static final int CUSTOMER = 4;

	/**
	 * The total number of fields in the fields array.
	 */
	private static final int NBR_FIELDS = 5;

	public SearchingPallets(Database db) {
		super(db);
		// TODO Auto-generated constructor stub
	}

	public JComponent createLeftPanel() {

		nameListModel = new DefaultListModel<String>();

		nameList = new JList<String>(nameListModel);
		nameList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		nameList.setPrototypeCellValue("123456789012");
		nameList.addListSelectionListener(new NameSelectionListener());
		JScrollPane p1 = new JScrollPane(nameList);

		fillNameList();
		p = new JPanel();
		field1 = new JTextField();
		field2 = new JTextField();
		field1.setVisible(false);
		field2.setVisible(false);
		field2.setText("1st Date");
		p.setLayout(new GridLayout(2, 1));
		p.add(new JLabel("Search by:"));
		p.add(p1);
		p.add(field2);
		p.add(field1);
		return p;
	}

	/**
	 * Fetch choices and display them in the name list.
	 */
	private void fillNameList() {
		nameListModel.removeAllElements();
		nameListModel.addElement("Time span");
		nameListModel.addElement("Pallet ID");
		nameListModel.addElement("Product");
		nameListModel.addElement("Blocked");
		nameListModel.addElement("Customer");
	}

	/**
	 * A class that listens for clicks in the name list.
	 */
	class NameSelectionListener implements ListSelectionListener {
		/**
		 * Called when the user selects a choice in the name list.
		 * 
		 * @param e
		 *            The selected list item.
		 */
		public void valueChanged(ListSelectionEvent e) {
			if (nameList.isSelectionEmpty()) {
				return;
			}
			String choice = nameList.getSelectedValue();
			field2.setVisible(false);
			field1.setVisible(true);
			messageLabel.setText(" ");
			if (choice == "Time span") {
				field2.setVisible(true);
				field1.setText("2nd Date");
				searching = 1;
			} else if (choice == "Pallet ID") {
				field1.setText("Pallet ID");
				searching = 2;
			} else if (choice == "Product") {
				field1.setText("Product");
				searching = 3;
			} else if (choice == "Blocked") {
				field1.setText("Blocked");
				searching = 4;
			} else if (choice == "Customer") {
				field1.setText("Customer");
				searching = 5;
			}
		}
	}

	/**
	 * Create the top panel, consisting of the text field.
	 * 
	 * @return The top panel.
	 */
	public JComponent createTopPanel() {

		infoListModel = new DefaultListModel<String>();

		infoList = new JList<String>(infoListModel);
		infoList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		infoList.setPrototypeCellValue("123456789012");
		infoList.addListSelectionListener(new InfoSelectionListener());
		JScrollPane p1 = new JScrollPane(infoList);

		// fillInfoList();

		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(1, 2));
		panel.add(p1);
		String[] texts = new String[NBR_FIELDS];
		texts[COOKIE_NAME] = "Product";
		texts[PALLET_DATE_TIME] = "Date and time";
		texts[PALLET_ID] = "Pallet ID";
		texts[PALLET_IS_BLOCKED] = "Blocked";
		texts[CUSTOMER] = "Customer";
		fields = new JTextField[NBR_FIELDS];
		for (int i = 0; i < fields.length; i++) {
			fields[i] = new JTextField(10);
			fields[i].setEditable(false);
		}
		panel.add(new InputPanel(texts, fields));
		return panel;
	}

	private void fillInfoListID(String palletID) {
		infoListModel.removeAllElements();
		infoListModel.addElement(palletID);
	}

	private void fillInfoListProduct(String cookie) {
		infoListModel.removeAllElements();
		Set<String> cookies = db.getCookiePallets(cookie);
		for (String c : cookies) {
			infoListModel.addElement(c);
		}
	}

	private void fillInfoListCustomer(String customer) {
		infoListModel.removeAllElements();
		Set<String> customers = db.getCustomerPallets(customer);
		for (String c : customers) {
			infoListModel.addElement(c);
		}
	}

	private void fillInfoListBlocked() {
		infoListModel.removeAllElements();
		Set<String> blocked = db.getBlockedPallets();
		for (String b : blocked) {
			infoListModel.addElement(b);
		}
	}

	private void fillInfoListDate(String date1, String date2) {
		infoListModel.removeAllElements();
		Set<String> dates = db.getDatePallets(date1, date2);
		for (String d : dates) {
			infoListModel.addElement(d);
		}

	}

	/**
	 * A class that listens for clicks in the info list.
	 */
	class InfoSelectionListener implements ListSelectionListener {
		/**
		 * Called when the user selects a name in the info list. Fetches data
		 * from the database and displays it in the text fields.
		 * 
		 * @param e
		 *            The selected list item.
		 */
		public void valueChanged(ListSelectionEvent e) {
			if (nameList.isSelectionEmpty() || infoList.isSelectionEmpty()) {
				return;
			}
			String palletID = infoList.getSelectedValue();
			String customer = db.getCustomer(palletID); // vet inte om den
														// funkar
			String blocked = db.isBlocked(palletID);
			String date = db.getPalletDateProduced(palletID); // ger nr 2008 och
																// 2005??
			String cookie = db.getCookie(palletID);
			fields[CUSTOMER].setText(customer);
			fields[PALLET_ID].setText(palletID);
			fields[PALLET_IS_BLOCKED].setText(blocked);
			fields[PALLET_DATE_TIME].setText(date);
			fields[COOKIE_NAME].setText(cookie);
		}
	}

	/**
	 * Create the bottom panel, consisting of the login button and the message
	 * line.
	 * 
	 * @return The bottom panel.
	 */
	public JComponent createBottomPanel() {
		JButton[] buttons = new JButton[1];
		buttons[0] = new JButton("Search");
		ActionHandler actHand = new ActionHandler();
		// fields[COOKIE_NAME].addActionListener(actHand);
		return new ButtonAndMessagePanel(buttons, messageLabel, actHand);
	}

	/**
	 * A class which listens for button clicks.
	 */
	class ActionHandler implements ActionListener {
		/**
		 * Called when the user clicks the search button. Checks with the
		 * database if the object exists.
		 * 
		 * 
		 * @param e
		 *            The event object (not used).
		 */
		public void actionPerformed(ActionEvent e) {
			String input = field1.getText();
			String dateFirst = field2.getText();
			switch (searching) {
			case 1:
				fillInfoListDate(dateFirst, input);
				if (infoList.getModel().getSize() == 0) {
					messageLabel.setText("There are no pallets produced within that date");
				}
				break;
			case 2:
				if (db.getPalletID().contains(input)) {
					fillInfoListID(input);
				} else {
					messageLabel.setText("There are no pallets with that ID");
				}
				break;
			case 3:
				if (db.getCookieName().contains(input)) {
					fillInfoListProduct(input);
				} else {
					messageLabel.setText("There are no pallets with that cookie");
				}
				break;
			case 4:
				fillInfoListBlocked();
				if (infoList.getModel().getSize() == 0) {
					messageLabel.setText("There are no pallets that are blocked");
				}
				break;
			case 5:
				if (db.getCustomers().contains(input)) {
					fillInfoListCustomer(input);
				} else {
					messageLabel.setText("There are no pallets with that customer");
				}
			}
		}
	}
}
