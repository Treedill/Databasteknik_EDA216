package eda216_project;

public class CookieApp {

	public static void main(String[] args) {
		Database db = new Database();
		new CookieGUI(db);
	}
}
