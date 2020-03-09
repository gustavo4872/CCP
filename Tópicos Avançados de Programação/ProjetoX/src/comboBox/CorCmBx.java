package comboBox;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javafx.scene.control.ComboBox;
import model.Cor;
import util.Conexao;

public class CorCmBx {
	
	public static void preencheComboBox(ComboBox<Cor> cor) {
		cor.getItems().clear();
		try {
			Connection conn = Conexao.getConnection();
			String sql = "Select * from COR order by NOME";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Cor c = new Cor();
				c.setId(rs.getInt("ID"));
				c.setNome(rs.getString("NOME"));
				cor.getItems().add(c);
			}
			cor.getSelectionModel().select(0);
			conn.close();			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
