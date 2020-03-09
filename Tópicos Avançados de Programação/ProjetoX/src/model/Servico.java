package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Servico {
	
	private IntegerProperty id = new SimpleIntegerProperty();
	private Veiculo veiculo = new Veiculo();
	private Tipo tipo = new Tipo();
	private StringProperty entrada = new SimpleStringProperty();
	private StringProperty saida = new SimpleStringProperty();
	private StringProperty comentario = new SimpleStringProperty("");
	
	public final IntegerProperty idProperty() {
		return this.id;
	}
	
	public final int getId() {
		return this.idProperty().get();
	}

	public final void setId(final int id) {
		this.idProperty().set(id);
	}
	
	public Veiculo getVeiculo() {
		return veiculo;
	}
	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}
	public Tipo getTipo() {
		return tipo;
	}
	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

	public final StringProperty entradaProperty() {
		return this.entrada;
	}
	
	public final String getEntrada() {
		return this.entradaProperty().get();
	}

	public final void setEntrada(final String entrada) {
		this.entradaProperty().set(entrada);
	}
	
	public final StringProperty saidaProperty() {
		return this.saida;
	}
	
	public final String getSaida() {
		return this.saidaProperty().get();
	}

	public final void setSaida(final String saida) {
		this.saidaProperty().set(saida);
	}
	
	public final StringProperty comentarioProperty() {
		return this.comentario;
	}
	
	public final String getComentario() {
		return this.comentarioProperty().get();
	}

	public final void setComentario(final String comentario) {
		this.comentarioProperty().set(comentario);
	}
}
