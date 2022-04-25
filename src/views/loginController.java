package views;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class loginController {

    @FXML
    private ImageView btnLogin;

    @FXML
    private TextField passwordTxt;

    @FXML
    private TextField userTxt;

    @FXML
    void autenticarLogin(MouseEvent event) {
        String user = userTxt.getText();
        String password = passwordTxt.getText();
  
        if(user.equals("admin") && password.equals("1234")){
          Cadastro cadastroTela = new Cadastro();
          App.fechar();
          try {
            cadastroTela.start(new Stage());
          } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
          }
        }else{
          Alert alert = new Alert(AlertType.WARNING);
          alert.setTitle("Falha na autenticação");
          alert.setHeaderText("Usuário ou senha incorretos");
          alert.show();
        }
    }

}
