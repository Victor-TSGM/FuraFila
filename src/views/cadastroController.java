package views;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import products.Produto;
import products.ProdutoDAO;

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
    private TextField quantidadeTxt;

    @FXML
    void cadastrarProduto(MouseEvent event) {
      String codigo = codTxt.getText();
      String descricao = descricaoTxt.getText();
      String preco = precoTxt.getText();
      String peso = pesoTxt.getText();
      String quantidade = quantidadeTxt.getText();

      Produto p = new Produto();
      ProdutoDAO dao = new ProdutoDAO();   
        
      p.setCod(Integer.parseInt(codigo));
      p.setDescricao(descricao);
      p.setPreco(Double.parseDouble(preco));
      p.setPeso(Double.parseDouble(peso));
      p.setQuantidade(Integer.parseInt(quantidade));

      dao.insert(p);

    }

    @FXML
    void alterarProduto(ActionEvent event) {
        
    }
  }