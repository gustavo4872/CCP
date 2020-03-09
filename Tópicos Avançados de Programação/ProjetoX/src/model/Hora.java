package model;

import java.util.Date;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

public class Hora extends Task<Void>{

	public Hora(Label horaLabel) {
		this.horaLabel = horaLabel;
	}
	
	public static ComboBox<String> hora = null;
	public static ComboBox<String> minuto = null;
	public static Date horaAtual = new Date();
	private Label horaLabel = new Label();
	
	@SuppressWarnings("deprecation")
	@Override
	protected Void call() throws Exception {
		try {			
			do {
				Thread.sleep(1000);
				horaAtual = new Date();
				Platform.runLater(()->{
					horaLabel.setText(horaAtual.getHours()
							+":"+
							((horaAtual.getMinutes()<10) ? "0"+horaAtual.getMinutes():horaAtual.getMinutes()));
					if (hora!=null&&minuto!=null) {
						hora.getSelectionModel().select(Integer.toString(horaAtual.getHours()));
						minuto.getSelectionModel().select(Integer.toString(horaAtual.getMinutes()));
					}
					});
			} while (!false);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}	
}
