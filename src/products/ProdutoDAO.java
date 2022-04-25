package products;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import connection.ConnectionFactory;
//import javafx.beans.property.ListProperty;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class ProdutoDAO {


// Inserindo produtos na base de dados  
  public void insert(Produto product){
    //Abrindo uma conexão com o banco
    Connection conn = ConnectionFactory.getConnection();
    PreparedStatement stmt = null;

    try{
      stmt = conn.prepareStatement("INSERT INTO produtos (cod, descricao, preco, peso, quantidade) values(?,?,?,?,?)");
      stmt.setInt(1, product.getCod());
      stmt.setString(2,product.getDescricao());
      stmt.setDouble(3, product.getPreco());
      stmt.setDouble(4, product.getPeso());
      stmt.setInt(5, product.getQuantidade());

      stmt.executeUpdate();

      Alert alert = new Alert(AlertType.INFORMATION);
      alert.setTitle("Cadastro");
      alert.setContentText("Cadastrado com sucesso");
      alert.show();

    }catch (SQLException ex){
      Alert alert = new Alert(AlertType.WARNING);
      alert.setTitle("Erro");
      alert.setHeaderText("Erro ao cadastrar produto");
      alert.show();
      
    }finally{
      ConnectionFactory.closeConnection(conn, stmt);
    }

  }

  //Alterando dados do produto ja cadastrado
  public void update(Produto product) {

    Connection conn = ConnectionFactory.getConnection();
    PreparedStatement stmt = null;

    try {
        stmt = conn.prepareStatement("UPDATE produto SET descricao = ?, preco = ?, peso = ?, quantidade = ? where cod = ?");
       
        stmt.setString(1, product.getDescricao());
        stmt.setDouble(2, product.getPreco());
        stmt.setDouble(3, product.getPeso());
        stmt.setInt(4, product.getQuantidade());
        stmt.setInt(5, product.getCod());


        stmt.executeUpdate();

        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Alteração");
        alert.setContentText("Alterado com sucesso");
        
    } catch (SQLException ex) {
         Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Erro");
        alert.setHeaderText("Erro ao Alterar produto");
    } finally {
        ConnectionFactory.closeConnection(conn, stmt);
    }
}

// Listando os produtos na tabela - função do tipo array  
  public List<Produto> updateViewTable(){

    Connection conn = ConnectionFactory.getConnection();
    PreparedStatement stmt = null;
    ResultSet rs = null;

    List<Produto> result = new ArrayList<>();

    try{

      stmt = conn.prepareStatement("SELECT * FROM produtos");
      rs = stmt.executeQuery();

      while(rs.next()){
        Produto produto = new Produto();

        produto.setCod(rs.getInt("cod"));
        produto.setDescricao(rs.getString("descricao"));
        produto.setPreco(rs.getDouble("preco"));
        produto.setPeso(rs.getDouble("peso"));
        produto.setQuantidade(rs.getInt("quantidade"));
        
      }

    } catch (SQLException ex) {
      Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
    }finally{
      ConnectionFactory.closeConnection(conn, stmt);
    }

    return result;    
  };

/*public List<Produto> updateViewTableByCod(int cod){

  Connection conn = ConnectionFactory.getConnection();
  PreparedStatement stmt = null;
  ResultSet rs = null;

  List<Produto> result = new ArrayList<>(); 

  try{
    stmt = conn.prepareStatement("SELECT * FROM produtos WHERE cod = ?");
    stmt.setInt(1, cod);
    rs = stmt.executeQuery();

    

  }

  return null;
}*/

}


