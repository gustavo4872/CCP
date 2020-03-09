package comboBox;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javafx.scene.control.ComboBox;
import model.Tipo;
import util.Conexao;

public class TipoCmBx {
	
	public static void preencheComboBox(ComboBox<Tipo> tipos) {
		tipos.getItems().clear();
		try {
			Connection conn = Conexao.getConnection();
			String sql = "Select * from TIPO order by NOME";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Tipo tipo = new Tipo();
				tipo.setNome(rs.getString("NOME"));
				tipo.setId(rs.getInt("ID"));
				tipo.setPreco(rs.getDouble("PRECO"));
				tipos.getItems().add(tipo);
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
