package mensagem;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Mensagem {

	public static void erro(String msg) {
		Alert a = new Alert(AlertType.ERROR);
		a.setHeaderText(null);
		a.setContentText(msg);
		a.show();
	}
	
	public static void confirmacao(String msg) {
		Alert a = new Alert(AlertType.CONFIRMATION);
		a.setHeaderText(null);
		a.setContentText(msg);
		a.show();
	}
	
	public static void information(String msg) {
		Alert a = new Alert(AlertType.INFORMATION);
		a.setHeaderText(null);
		a.setContentText(msg);
		a.show();
	}
	
	public static void aviso(String msg) {
		Alert a = new Alert(AlertType.WARNING);
		a.setHeaderText(null);
		a.setContentText(msg);
		a.show();
	}
	
}
