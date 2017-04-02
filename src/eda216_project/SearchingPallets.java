package eda216_project;

import java.awt.GridLayout;
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
	private JTextField[] fields2;
	private InputPanel p2;
	private InputPanel p3;
	private JPanel p;
	private String inputBoxName;
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
		p.setLayout(new GridLayout(2, 1));
		p.add(new JLabel("Search by:"));
		p.add(p1);
		return p;
	}

	public JComponent createRightBottomPanel() {
		String[] texts = new String[1];
		String[] texts2 = new String[1];
		texts[0] = inputBoxName;
		texts2[0] = "Last date";
		fields = new JTextField[1];
		fields[0] = new JTextField(2);
		fields2 = new JTextField[1];
		fields2[0] = new JTextField(2);
		p2 = new InputPanel(texts, fields);
		p3 = new InputPanel(texts2, fields2);
		// p2.setSize(10, 1);
		p2.setVisible(false);
		p3.setVisible(false);
		p.add(p2);
		p.add(p3);
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
			p3.setVisible(false);
			p2.setVisible(true);
			if (choice == "Time span") {
				p3.setVisible(true);
				inputBoxName = "First Date";
				searching = 1;
			} else if (choice == "Pallet ID") {
				inputBoxName = "Pallet ID";
				searching = 2;
			} else if (choice == "Product") {
				inputBoxName = "Product";
				searching = 3;
			} else if (choice == "Blocked") {
				inputBoxName = "Blocked";
				searching = 4;
			} else if (choice == "Customer") {
				inputBoxName = "Customer";
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

	private void fillInfoListProduct(String product) {
		infoListModel.removeAllElements();
		Set<String> dates = null;
		for (String d : dates) {
			infoListModel.addElement(d);
		}
	}

	private void fillInfoListCustomer(String customer) {
		infoListModel.removeAllElements();
		Set<String> dates = null;
		for (String d : dates) {
			infoListModel.addElement(d);
		}
	}

	private void fillInfoListBlocked() {
		infoListModel.removeAllElements();
		Set<String> dates = null;
		for (String d : dates) {
			infoListModel.addElement(d);
		}
	}

	private void fillInfoListDate() {
		infoListModel.removeAllElements();
		infoListModel.addElement("hej");
		infoListModel.addElement("då");

	}

	/**
	 * A class that listens for clicks in the info list.
	 */
	class InfoSelectionListener implements ListSelectionListener {
		/**
		 * Called when the user selects a name in the info list. Fetches
		 * data from the database and displays it in the text
		 * fields.
		 * 
		 * @param e
		 *            The selected list item.
		 */
		public void valueChanged(ListSelectionEvent e) {
			if (nameList.isSelectionEmpty() || infoList.isSelectionEmpty()) {
				return;
			}
			String customer = "Jenny"; // = db.getCustomer(palletID);
			String palletID = infoList.getSelectedValue(); // db.getPalletID();
			String blocked = "ja"; // db.isBlocked(palletID);
			String date = "2017";//db.getPalletDateProduced(palletID);
			String cookie = "Nötkaka"; // db.getCookie(palletID);
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
			String input = p2.toString();
			String date2 = p3.toString();
			switch (searching) {
			case 1:
				fillInfoListDate();
				// if (db.getPalletDate().compareTo(input) >= 0
				// && db.getPalletDate().compareTo(date2) <= 0) {
				break; // optional
			case 2:
				if (db.getPalletID().contains(input)) {
					fillInfoListID(input);
				}
				break; 
			case 3:
				if (db.getCookieName().contains(input)) {
					fillInfoListProduct(input);
				}
				// Statements
				break; 
			case 4:
				fillInfoListBlocked();
				break;
			case 5: 
				// if(db.getCustomer().contains(customer)){
				// fillInfoListCustomer();
				// }
			}
		}

	}
}
