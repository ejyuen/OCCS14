package menus;

public class CompanyMenue extends Menue {

	public CompanyMenue(Menue menue) {
		super(menue);
		addTextField("Username:", 120, 75, 60, 15);
	}

}
