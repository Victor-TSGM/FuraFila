import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class App extends Application {

    public void setStage(Stage myStage) {
        stage = myStage;
    }

    public static Stage getStage() {
        return stage;
    }

    static Stage stage = new Stage();

    public static void main(String[] args) throws Exception {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        

        // FXMLLoader loader = new
        // FXMLLoader(this.getClass().getResource("src/fxml/layout.fxml"));
        Parent root = FXMLLoader.load(this.getClass().getResource("layout.fxml"));
        stage.setScene(new Scene(root));
        stage.show();
        setStage(stage);

    }

    public void conecta(){
        
    }

    public void cadastrar(int cod, String descricao, double preco, double peso){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/furafila", "root", "@c3ss0");
            Statement stmt = conn.createStatement();
            String sql = "insert into produtos(cod, descricao, preco, peso) values(?,?,?,?)";
            
            try {    
                PreparedStatement prepare = conn.prepareStatement(sql);  

                prepare.setInt(1, cod);
                prepare.setString(2, descricao);
                prepare.setDouble(3, preco);
                prepare.setDouble(4, peso);

                    prepare.execute();
                    prepare.close();
                    
            } catch (SQLException u) {    
                throw new RuntimeException(u);    
            }  
            //ResultSet rs = stmt.executeQuery("select * from produtos");
            //ResultSet rs = stmt.execute();            
            System.out.println("Banco de Dados Conectado");
        } catch (ClassNotFoundException e) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE,null, e);

            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Erro ao cadastrar produto");
            alert.show();
            

        }catch(SQLException e) {

            Logger.getLogger(App.class.getName()).log(Level.SEVERE,null, e);
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Erro de SQL");
            alert.show();
        }
    } 

    public void alterar(){



    }

    public static void fechar() {
        stage.close();
    }

}
