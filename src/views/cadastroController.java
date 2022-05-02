package views;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import products.Produto;
import products.ProdutoDAO;

public class cadastroController{

    App banco = new App();

    @FXML
    private TableView<Produto> ViewTable;

    @FXML
    private TableColumn<Produto, Integer> cod;    

    @FXML
    private TableColumn<Produto, String> descricao;

    @FXML
    private TableColumn<Produto, Double> preco;

    @FXML
    private TableColumn<Produto, Double> peso;

    @FXML
    private TableColumn<Produto, Integer> quantidade;

    

    @FXML
    private ImageView closeBtn;

    @FXML
    private ImageView hideBtn;
    
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



    ProdutoDAO dao = new ProdutoDAO();

    public ObservableList<Produto> observableListProdutos;

    public void limpaCampos() {
      codTxt.setText("");
      descricaoTxt.setText("");
      precoTxt.setText("");
      pesoTxt.setText("");
      quantidadeTxt.setText("");
    }
   
    public void readTable(){
      
        observableListProdutos = FXCollections.observableArrayList(dao.updateViewTable());

        cod.setCellValueFactory(new PropertyValueFactory<>("cod"));
        descricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));
        preco.setCellValueFactory(new PropertyValueFactory<>("preco"));
        peso.setCellValueFactory(new PropertyValueFactory<>("peso"));
        quantidade.setCellValueFactory(new PropertyValueFactory<>("quantidade"));

        ViewTable.setItems(observableListProdutos);
           
    }

    @FXML
    void ViewTableKeyReleased(MouseEvent event) {
      int i = ViewTable.getSelectionModel().getSelectedIndex();

      Produto produto = (Produto)ViewTable.getItems().get(i);

      codTxt.setText(Integer.toString(produto.getCod()));
      descricaoTxt.setText(produto.getDescricao());
      precoTxt.setText(Double.toString(produto.getPreco()));
      pesoTxt.setText(Double.toString(produto.getPeso()));
      quantidadeTxt.setText(Integer.toString(produto.getQuantidade()));
    }

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
      readTable();
      limpaCampos();

    }

    @FXML
    void deleteProduct(MouseEvent event) {
      String codigo = null;

      codigo = codTxt.getText();

      if(codigo.equals("")){
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Produto");
        alert.setContentText("Insira o código do produto");
        alert.show();
      }else{
        dao.delete(Integer.parseInt(codigo));
      }

      limpaCampos();

    }

    @FXML
    void updateProduct(MouseEvent event) {
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

      System.out.println(p);

      dao.update(p);
      readTable();
      limpaCampos();
    }

    @FXML
    void procuraProduto(MouseEvent event) {
      ProdutoDAO dao = new ProdutoDAO();
      String codigo = null;

      codigo = codTxt.getText();

      if(codigo.equals("")){      
        readTable();
        limpaCampos();

      }else{
        observableListProdutos = FXCollections.observableArrayList(dao.searchProduto(Integer.parseInt(codigo)));

        cod.setCellValueFactory(new PropertyValueFactory<>("cod"));
        descricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));
        preco.setCellValueFactory(new PropertyValueFactory<>("preco"));
        peso.setCellValueFactory(new PropertyValueFactory<>("peso"));
        quantidade.setCellValueFactory(new PropertyValueFactory<>("quantidade"));

        ViewTable.setItems(observableListProdutos);
        limpaCampos();
      }      
    }

    @FXML
    void hideApp(MouseEvent event) {
      Stage obj = (Stage) hideBtn.getScene().getWindow();
      obj.setIconified(true);
    }

    @FXML
    void closeApp(MouseEvent event) {
      System.exit(0);
    }
}
  
