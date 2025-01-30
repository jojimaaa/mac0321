package ex01;

public class FatorialApp {
	
	public static void main(String[] args) {
		FatorialController controller = new FatorialController();
		FatorialView view = new FatorialView(controller);

		view.getFrame().setVisible(true);
	}
}
