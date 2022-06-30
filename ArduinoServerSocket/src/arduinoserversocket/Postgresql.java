package arduinoserversocket;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.json.*;
import java.time.LocalDateTime;

public class Postgresql {

   private LocalDateTime date = LocalDateTime.now();
   private PreparedStatement pstmt;
   private Connection conexao;
   private String url = "jdbc:postgresql://localhost/Firedesk";
   private String usuario = "postgres";
   private String senha = "root";

   public Postgresql() {
      try {
         Class.forName("org.postgresql.Driver");
         conexao = DriverManager.getConnection(url, usuario, senha);
         System.out.println("Conexão com o Banco PostgreSQL realizada!");
      } catch (ClassNotFoundException ex) {
         System.err.println("Erro ClassNotFoundException : " + ex);
      } catch (SQLException e) {
         System.err.println("Erro SQLException : " + e);
      }
   }

   public String executaSQL(String json) {
      try {
         JSONObject jsonobject = new JSONObject(json);
         String cnpj = jsonobject.getString("cnpj");
         System.out.println(cnpj);
         if (cnpj != null) {
            String select = "SELECT id, buscardados, tempambmin, "
                    + "tempambmax, umidambmin, umidambmax, editado "
                    + "FROM empreendimentos "
                    + "WHERE cnpj =?"
                    + "AND id_statusregistro = 1";
            pstmt = conexao.prepareStatement(select);
            pstmt.setString(1, cnpj);
            ResultSet resultconsulta = pstmt.executeQuery();
            System.out.println("Executou o Select");
            if (resultconsulta.next()) {
               double umidade = jsonobject.getDouble("umidade");
               double temperatura = jsonobject.getDouble("temperatura");

               int id = resultconsulta.getInt(1);
               System.out.println(id);
               String insert = "INSERT INTO log_empreendimentos "
                       + "(id_empreendimento, temperatura, umidade) "
                       + " VALUES (?, ?, ?)";
               pstmt = conexao.prepareStatement(insert);
               pstmt.setInt(1, id);
               pstmt.setDouble(2, temperatura);
               pstmt.setDouble(3, umidade);
               pstmt.executeUpdate();
               pstmt.close();
               System.out.println("Insert inserido com sucesso");
               boolean editado = resultconsulta.getBoolean("editado");
               System.out.println(editado);
               if (editado == true) {
                  String retorno = "{\"status\": \"success\", \"mensagem\": \"edit\", \"intervalo\": "
                          + resultconsulta.getString("buscardados") + ", \"tempmin\":"
                          + resultconsulta.getString("tempambmin") + ", \"tempmax\": "
                          + resultconsulta.getString("tempambmax") + ", \"umidambmin\": "
                          + resultconsulta.getString("umidambmin") + ", \"umidambmax\": "
                          + resultconsulta.getString("umidambmax") + "}";
                  String update = "UPDATE empreendimentos SET editado = 'f' WHERE id = ?";
                  pstmt = conexao.prepareStatement(update);
                  pstmt.setInt(1, id);
                  pstmt.executeQuery();
                  pstmt.close();
                  System.out.println("Retorno: " + retorno);
                  return retorno;
               }
            } 
            else {
               return "{status: error, mensagem: token}";
            }
         } 
         else {
            return "{status: error, mensagem: url}";
         }
      } catch (SQLException | JSONException ex) {
         System.out.println("Erro Exception : " + ex);
      }
      return "{\"status\": \"success\", \"mensagem\": \"null\"}";
   } 
}
