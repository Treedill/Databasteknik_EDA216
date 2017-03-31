package eda216_project;

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class CookieGUI {
	/**
     * db is the database object
     */
    private Database db;
    
    /**
     * tabbedPane is the contents of the window. It consists of two panes, User
     * login and Book tickets.
     */
    private JTabbedPane tabbedPane;

    /**
     * Create a GUI object and connect to the database.
     * 
     * @param db
     *            The database.
     */
    public CookieGUI(Database db) {
        this.db = db;

        JFrame frame = new JFrame("Krusty Kookies Sweden ");
        tabbedPane = new JTabbedPane();

        ProducingPallets prod = new ProducingPallets(db);
        tabbedPane.addTab("Produce Pallets", null, prod,
                          "produce a pallet");

        BlockingPallets block= new BlockingPallets(db);
        tabbedPane.addTab("Block Pallets", null, block, "Block a pallet");
        
        SearchingPallets search = new SearchingPallets(db);
        tabbedPane.addTab("Search Pallets", null, search, "Search for a pallet");

        tabbedPane.setSelectedIndex(0);

        frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);

        tabbedPane.addChangeListener(new ChangeHandler());
        frame.addWindowListener(new WindowHandler());

        frame.setSize(500, 400);
        frame.setVisible(true);

        //userLoginPane.displayMessage("Connecting to database ...");
                
        /* --- change code here --- */
        /* --- change xxx to the name of the file with your database --- DONE */
        if (db.openConnection("databas.db")) {
            prod.displayMessage("Connected to database");
        } else {
           prod.displayMessage("Could not connect to database");
        }
    }

    /**
     * ChangeHandler is a listener class, called when the user switches panes.
     */
    class ChangeHandler implements ChangeListener {
        /**
         * Called when the user switches panes. The entry actions of the new
         * pane are performed.
         * 
         * @param e
         *            The change event (not used).
         */
        public void stateChanged(ChangeEvent e) {
            BasicPane selectedPane = (BasicPane) tabbedPane
                .getSelectedComponent();
            selectedPane.entryActions();
        }
    }

    /**
     * WindowHandler is a listener class, called when the user exits the
     * application.
     */
    class WindowHandler extends WindowAdapter {
        /**
         * Called when the user exits the application. Closes the connection to
         * the database.
         * 
         * @param e
         *            The window event (not used).
         */
        public void windowClosing(WindowEvent e) {
            db.closeConnection();
            System.exit(0);
        }
    }
}

