package menus;

public class CompanyMenue extends Menue {

	public CompanyMenue(Menue menue) {
		super(menue);
		addTextField("Name:", 115, 75, 70, 15);
	}

}
