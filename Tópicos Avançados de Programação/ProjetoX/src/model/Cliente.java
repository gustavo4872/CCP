package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Cliente {
	
	@Override
	public String toString() {
		return getNome();
	}
	
	private IntegerProperty id = new SimpleIntegerProperty();
	private StringProperty nome = new SimpleStringProperty("");
	private StringProperty contato = new SimpleStringProperty("");
	private StringProperty email = new SimpleStringProperty("");
	private StringProperty data = new SimpleStringProperty("");
	private Cidade cidade = new Cidade();
	private StringProperty comentario = new SimpleStringProperty("");
	
	public final StringProperty nomeProperty() {
		return this.nome;
	}
	
	public final String getNome() {
		return this.nomeProperty().get();
	}

	public final void setNome(final String nome) {
		this.nomeProperty().set(nome);
	}

	public final IntegerProperty idProperty() {
		return this.id;
	}
	
	public final int getId() {
		return this.idProperty().get();
	}

	public final void setId(final int id) {
		this.idProperty().set(id);
	}

	public final StringProperty contatoProperty() {
		return this.contato;
	}
	
	public final String getContato() {
		return this.contatoProperty().get();
	}

	public final void setContato(final String contato) {
		this.contatoProperty().set(contato);
	}
	
	public final StringProperty emailProperty() {
		return this.email;
	}
	
	public final String getEmail() {
		return this.emailProperty().get();
	}

	public final void setEmail(final String email) {
		this.emailProperty().set(email);
	}

	public final StringProperty dataProperty() {
		return this.data;
	}
	
	public final String getData() {
		return this.dataProperty().get();
	}

	public final void setData(final String data) {
		this.dataProperty().set(data);
	}
	
	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
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
