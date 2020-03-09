package application;

import javax.swing.JOptionPane;

import comboBox.CidadeCmBx;
import comboBox.EstadoCmBx;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import mensagem.Mensagem;
import model.Cidade;
import model.Cliente;
import model.Estado;
import sql.Sql;

public class EditarClienteController {

	static Cliente cliente;
	
	@FXML TextField nomeTxfC;
	@FXML TextField dddTxfC;
	@FXML TextField numeroTxfC;
	@FXML TextField emailTxfC;
	@FXML DatePicker dataDtPkC;
	@FXML ComboBox<Cidade> cidadeCmBxC;
	@FXML ComboBox<Estado> estadoCmBxC;
	@FXML TextArea comentarioTxAC;
	
	@FXML
	public void initialize() {	
		EstadoCmBx.preencheComboBox(estadoCmBxC);	
		nomeTxfC.setText(cliente.getNome());
		emailTxfC.setText(cliente.getEmail());
		comentarioTxAC.setText((cliente.getComentario()==null) ? null : cliente.getComentario());
		preencheCidadeCmBxC();
	}
	
	@FXML
	public void preencheCidadeCmBxC() {
		estadoCmBxC.getSelectionModel().select(cliente.getCidade().getEstado());
		CidadeCmBx.preencheComboBox(cidadeCmBxC, estadoCmBxC);
		cidadeCmBxC.getSelectionModel().select(cliente.getCidade());
	}
	
	@FXML
	public void alterar() {
		if (nomeTxfC.getText().equals("")) {
			Mensagem.aviso("É Necessário Informar o Nome do Cliente!");
		}else {
			if (Sql.editarCliente(nomeTxfC, dddTxfC, numeroTxfC, emailTxfC, cidadeCmBxC, comentarioTxAC, dataDtPkC, cliente)) {
				Mensagem.confirmacao("Cliente Alterado!");
			}else {
				Mensagem.erro("Erro ao Alterar o Cliente!");
			}
		}
	}
	
	@FXML
	public void excluir() {
		if (JOptionPane.showConfirmDialog(null, "Excluir o Cliente "+cliente.getNome()+"?", "Alerta!", JOptionPane.YES_NO_OPTION)==0) {
			Sql.excluirCliente(cliente);
		}
	}
}
