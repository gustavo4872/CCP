package comboBox;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javafx.scene.control.ComboBox;
import model.Cliente;
import model.Veiculo;
import util.Conexao;

public class VeiculoCmBx {

	public static void preencheComboBox(ComboBox<Veiculo> veiculo, ComboBox<Cliente> cliente) {
		if (cliente.getValue()==null) { return; }
		veiculo.getItems().clear();
		try {
			Connection conn = Conexao.getConnection();
			String sql = "Select * from VEICULO where PROP_ID = "+cliente.getValue().getId()+" order by MODELO";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Veiculo v = new Veiculo();
				v.setId(rs.getInt("ID"));
				v.setModelo(rs.getString("MODELO"));
				v.setPlaca(rs.getString("PLACA"));
				v.setCliente(cliente.getValue());
				veiculo.getItems().add(v);
			}
			veiculo.getSelectionModel().select(0);
			conn.close();					
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
}
