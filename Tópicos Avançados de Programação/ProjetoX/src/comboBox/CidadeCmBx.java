package comboBox;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javafx.scene.control.ComboBox;
import model.Cidade;
import model.Estado;
import util.Conexao;

public class CidadeCmBx {
	
	public static void preencheComboBox(ComboBox<Cidade> cidade, ComboBox<Estado> estado) {
		cidade.getItems().clear();
		try {
			Connection conn = Conexao.getConnection();
			String sql = "Select * from CIDADE where UF_ID = "+estado.getValue().getId()+" order by NOME";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Cidade city = new Cidade();
				city.setNome(rs.getString("NOME"));
				city.setId(rs.getInt("ID"));
				city.setEstado(estado.getValue());
				cidade.getItems().add(city);
			}
			cidade.getSelectionModel().selectFirst();
			conn.close();			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
