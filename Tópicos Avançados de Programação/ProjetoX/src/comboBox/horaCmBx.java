package comboBox;

import javafx.scene.control.ComboBox;

public class horaCmBx {

	public static void preencheComboBox(ComboBox<String> hora, ComboBox<String> minuto) {
		String horaString = "0";
		String minutoString = "0";
		for (int i = 1; i < 10; i++) {
			hora.getItems().add(horaString+i);
		}
		for (int i = 10; i < 24; i++) {
			hora.getItems().add(Integer.toString(i));
		}
		for (int i = 1; i < 10; i++) {
			minuto.getItems().add(minutoString+i);
		}
		for (int i = 10; i < 60; i++) {
			minuto.getItems().add(Integer.toString(i));
		}
	}
	
}