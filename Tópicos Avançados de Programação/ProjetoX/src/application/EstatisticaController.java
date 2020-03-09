package application;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;

public class EstatisticaController implements Initializable{

	@FXML BarChart<String, Integer> barchartCliente;
	@FXML PieChart servicosPiechart; 

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		XYChart.Series<String, Integer> set = new XYChart.Series<>();
		set.getData().add(new XYChart.Data<>("Gustavo",1));
		set.getData().add(new XYChart.Data<>("João",2));
		set.getData().add(new XYChart.Data<>("José",3));
		set.getData().add(new XYChart.Data<>("Rafa",2));
		barchartCliente.getData().add(set);
		
		ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(new PieChart.Data("Lavagem", 1), new PieChart.Data("Limpar", 2));
		servicosPiechart.setData(pieChartData);
		servicosPiechart.setStartAngle(90);
		Date dataAtual = new Date();
	}
}