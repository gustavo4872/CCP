package application;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import mensagem.Mensagem;
import model.Cidade;
import model.Cliente;
import model.Cor;
import model.Estado;
import model.Hora;
import model.Tipo;
import model.Veiculo;
import sql.Sql;
import comboBox.*;

public class CadastroController {
	
	//Atributos de Cliente
	@FXML TextField nomeTxfC;
	@FXML TextField dddTxfC;
	@FXML TextField numeroTxfC;
	@FXML TextField emailTxfC;
	@FXML DatePicker dataDtPkC;
	@FXML ComboBox<Cidade> cidadeCmBxC;
	@FXML ComboBox<Estado> estadoCmBxC;
	@FXML TextArea comentarioTxAC;
	
	//Atributos de Veiculo
	@FXML TextField modeloTxfV;
	@FXML TextField placaTxfV;
	@FXML ComboBox<Cliente> clienteCmBxV;
	@FXML ComboBox<Cor> corCmBxV;
	@FXML ComboBox<Cidade> cidadeCmBxV;
	@FXML ComboBox<Estado> estadoCmBxV;
	@FXML TextArea comentarioTxAV;
	
	//Atributos de Servico
	@FXML ComboBox<Cliente> clienteCmBxS;
	@FXML ComboBox<Veiculo> veiculoCmBxS;
	@FXML ComboBox<String> horasEntradaCmBxS;
	@FXML ComboBox<String> minutosEntradaCmBxS;
	@FXML ComboBox<String> horasSaidaCmBxS;
	@FXML ComboBox<String> minutosSaidaCmBxS;
	@FXML ComboBox<Tipo> tipoCmBxS;
	@FXML TextField totalTxfS;
	@FXML TextArea comentarioTxAS;
	
	@FXML
	public void initialize() {
		EstadoCmBx.preencheComboBox(estadoCmBxC, estadoCmBxV);
		ClienteCmBx.preencheComboBox(clienteCmBxV, clienteCmBxS);
		CorCmBx.preencheComboBox(corCmBxV);
		TipoCmBx.preencheComboBox(tipoCmBxS);
		preencheCidadeCmBxC();
		preencheCidadeCmBxV();
		preencheVeiculoCmBxS();
		preencheDataCmBxS();
		Hora.hora = horasEntradaCmBxS;
		Hora.minuto = minutosEntradaCmBxS;
	}
	
	private void preencheDataCmBxS() {
		horaCmBx.preencheComboBox(horasEntradaCmBxS, minutosEntradaCmBxS);
		horaCmBx.preencheComboBox(horasSaidaCmBxS, minutosSaidaCmBxS);
	}
	
	@FXML
	public void preencheCidadeCmBxC() {
		CidadeCmBx.preencheComboBox(cidadeCmBxC, estadoCmBxC);		
	}
	
	@FXML
	public void preencheCidadeCmBxV() {
		CidadeCmBx.preencheComboBox(cidadeCmBxV, estadoCmBxV);
	}
	
	@FXML
	public void preencheVeiculoCmBxS() {
		VeiculoCmBx.preencheComboBox(veiculoCmBxS, clienteCmBxS);
	}
	
	@FXML
	public void cadastrarCliente() {
		if (nomeTxfC.getText().equals("")) {
			Mensagem.aviso("É Necessário Informar o Nome do Cliente!");
		}else {
			if (Sql.cadastraCliente(nomeTxfC, dddTxfC, numeroTxfC, emailTxfC, cidadeCmBxC, comentarioTxAC, dataDtPkC)) {
				ClienteCmBx.preencheComboBox(clienteCmBxV, clienteCmBxS);
				Mensagem.confirmacao("Cliente Cadastrado!");
			}else {
				Mensagem.erro("Erro ao Cadastrar o Cliente!");
			}
		}
	}
	
	@FXML
	public void cadastrarVeiculo() {
		if (modeloTxfV.getText().equals("")) {
			Mensagem.aviso("É Necessário Informar o Modelo do Veículo!");
		}else if (placaTxfV.getText().equals("")) {
			Mensagem.aviso("É Necessário Informar a Placa do Veículo!");
		}else {
			if (Sql.cadastraVeiculo(clienteCmBxV, modeloTxfV, placaTxfV, corCmBxV, cidadeCmBxV, comentarioTxAV)) {
				preencheVeiculoCmBxS();
				Mensagem.confirmacao("Veículo Cadastrado!");
			}else {
				Mensagem.erro("Erro ao Cadastrar o Veículo!");
			}
		}
	}
	
	@FXML
	public void cadastrarServico() {
		if (horasSaidaCmBxS.getSelectionModel().isEmpty()||minutosSaidaCmBxS.getSelectionModel().isEmpty()) {
			Mensagem.aviso("Horário de Saida Incorreto!");
		}else if (tipoCmBxS.getSelectionModel().isEmpty()) {
			Mensagem.aviso("Selecione um Tipo de Serviço!");
		}else {
			if (Sql.cadastraServico(veiculoCmBxS, tipoCmBxS, horasEntradaCmBxS, minutosEntradaCmBxS, horasSaidaCmBxS, minutosSaidaCmBxS, comentarioTxAS)) {
				Mensagem.confirmacao("Serviço Cadastrado!");
			}else {
				Mensagem.erro("Erro ao Cadastrar o Serviço!");
			}
		}
	}
	
}	