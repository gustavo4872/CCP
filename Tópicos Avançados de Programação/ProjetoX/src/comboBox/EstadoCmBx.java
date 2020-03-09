package comboBox;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javafx.scene.control.ComboBox;
import model.Estado;
import util.Conexao;

public class EstadoCmBx {
	
	public static void preencheComboBox(ComboBox<Estado> estadoC, ComboBox<Estado> estadoV) {
		try {
			Connection conn = Conexao.getConnection();
			String sql = "Select * from UF order by NOME";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Estado uf = new Estado();
				uf.setNome(rs.getString("NOME"));
				uf.setId(rs.getInt("ID"));
				estadoC.getItems().add(uf);
				estadoV.getItems().add(uf);
				if (uf.getNome().equalsIgnoreCase("SC")) {
					estadoC.getSelectionModel().select(uf);
					estadoV.getSelectionModel().select(uf);
				}
			}
			conn.close();			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void preencheComboBox(ComboBox<Estado> estado) {
		try {
			Connection conn = Conexao.getConnection();
			String sql = "Select * from UF order by NOME";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Estado uf = new Estado();
				uf.setNome(rs.getString("NOME"));
				uf.setId(rs.getInt("ID"));
				estado.getItems().add(uf);
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
}
