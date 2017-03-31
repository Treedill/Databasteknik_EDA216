package eda216_project;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComponent;
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

	/**
	 * The list model for the movie name list.
	 */
	private DefaultListModel<String> nameListModel;

	/**
	 * The movie name list.
	 */
	private JList<String> nameList;

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
	private static final int PALLET_DELIVERED = 4;

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

		// dateListModel = new DefaultListModel<String>();
		//
		// dateList = new JList<String>(dateListModel);
		// dateList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		// dateList.setPrototypeCellValue("123456789012");
		// dateList.addListSelectionListener(new DateSelectionListener());
		// JScrollPane p2 = new JScrollPane(dateList);
		fillNameList();
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(1, 2));
		p.add(p1);
		return p;
	}

	/**
	 * Fetch choices and display them in the name list.
	 */
	private void fillNameList() {
		nameListModel.removeAllElements();
		nameListModel.addElement("Date");
		nameListModel.addElement("Pallet ID");
		nameListModel.addElement("Product");
		nameListModel.addElement("Blocked");
		nameListModel.addElement("Customer");
	}
	
	private void textBox(String c){
		
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
			textBox(choice);
		}
	}

	/**
	 * Create the top panel, consisting of the text field.
	 * 
	 * @return The top panel.
	 */
	public JComponent createTopPanel() {
		String[] texts = new String[NBR_FIELDS];
		texts[COOKIE_NAME] = "Product";
		texts[PALLET_DATE_TIME] = "Date and time";
		texts[PALLET_ID] = "Pallet ID";
		texts[PALLET_IS_BLOCKED] = "Blocked";
		texts[PALLET_DELIVERED] = "Customer";
		fields = new JTextField[NBR_FIELDS];
		for (int i = 0; i < fields.length; i++) {
			fields[i] = new JTextField(10);
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
		buttons[0] = new JButton("Search");
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
			// if(db.getCookieName().contains(cookieName) ||
			// db.getPalletID().contains(palletID) || db.isBlocked)
		}

	}
}
