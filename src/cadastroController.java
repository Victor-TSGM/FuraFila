import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class cadastroController {

    App banco = new App();

    @FXML
    private ImageView btnNovo;

    @FXML
    private TextField codTxt;

    @FXML
    private TextField descricaoTxt;

    @FXML
    private TextField pesoTxt;

    @FXML
    private TextField precoTxt;

    @FXML
    void cadastrarProduto(MouseEvent event) {
      String codigo = codTxt.getText();
      String descricao = descricaoTxt.getText();
      String preco = precoTxt.getText();
      String peso = pesoTxt.getText();

    


      //banco.cadastrar(Integer.parseInt(codigo), descricao, Double.parseDouble(preco), Double.parseDouble(peso));

      if(codigo.equals("") || descricao.equals("") || preco.equals("") || peso.equals("")){
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Atenção");
        alert.setHeaderText("Verifique se todos os campos estão preenchidos");
        alert.show();
        
      }else{
        banco.cadastrar(Integer.parseInt(codigo), descricao, Double.parseDouble(preco), Double.parseDouble(peso));
      }
    }

    @FXML
    void alterarProduto(ActionEvent event) {
        banco.alterar();
    }
  }