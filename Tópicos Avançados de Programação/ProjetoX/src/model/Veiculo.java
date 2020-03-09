package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Veiculo {
	
	@Override
	public String toString() {
		return getModelo()+" - "+getPlaca();
	}
	
	private IntegerProperty id = new SimpleIntegerProperty();
	private StringProperty modelo = new SimpleStringProperty("");
	private StringProperty placa = new SimpleStringProperty("");
	private Cor cor = new Cor();
	private Cidade cidade = new Cidade();
	private Cliente cliente = new Cliente();
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
	
	public final StringProperty modeloProperty() {
		return this.modelo;
	}
	
	public final String getModelo() {
		return this.modeloProperty().get();
	}

	public final void setModelo(final String modelo) {
		this.modeloProperty().set(modelo);
	}
	
	public final StringProperty placaProperty() {
		return this.placa;
	}
	
	public final String getPlaca() {
		return this.placaProperty().get();
	}

	public final void setPlaca(final String placa) {
		this.placaProperty().set(placa);
	}

	public Cor getCor() {
		return cor;
	}

	public void setCor(Cor cor) {
		this.cor = cor;
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
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
