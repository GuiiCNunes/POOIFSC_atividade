/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autorendereco.controller;

import autorendereco.model.connection.ConnectionFactory;
import autorendereco.model.domain.Autor;
import autorendereco.model.domain.Endereco;
import autorendereco.model.repository.Autores;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.hibernate.mapping.Value;

/**
 * FXML Controller class
 *
 * @author GuiiCNunes <guiicnunes@gmail.com>
 */
public class FXMLAnchorPaneCadastrosAutoresController implements Initializable {

    @FXML
    private TableView<Autor> tableViewAutores;

    @FXML
    private TableColumn<Autor, String> tableColumnAutorNome;

    @FXML
    private TableColumn<Endereco, String> tableColumnEnderecoCEP; // PROBLEMA AQUI

    @FXML
    private Label labelAutorID;

    @FXML
    private Label labelAutorNome;

    @FXML
    private Label labelAutorCPF;

    @FXML
    private Label labelAutorDataNasc;

    @FXML
    private Label labelEnderecoId;

    @FXML
    private Label labelEnderecoCEP;

    @FXML
    private Label labelEnderecoLogradouro;

    @FXML
    private Label labelEnderecoNumero;

    @FXML
    private Label labelEnderecoComplemento;

    @FXML
    private Label labelEnderecoUF;

    @FXML
    private Label labelEnderecoCidade;

    @FXML
    private Button buttonInserir;

    @FXML
    private Button buttonAlterar;

    @FXML
    private Button buttonRemover;

    private List<Autor> listAutores;
    private ObservableList<Autor> observableListAutores;

    private static Autores autores;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        autores = new Autores(ConnectionFactory.getEmf());
        carregarTableViewAutores();

        tableViewAutores.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> selecionarItemTableViewAutores(newValue));
    }

    public void carregarTableViewAutores() {
        tableColumnAutorNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tableColumnEnderecoCEP.setCellValueFactory(new PropertyValueFactory<>("cep")); // PROBLEMA

        listAutores = autores.pesquisarAutores();

        observableListAutores = FXCollections.observableArrayList(listAutores);
        tableViewAutores.setItems(observableListAutores);
    }

    public void selecionarItemTableViewAutores(Autor autor) {
        if (autor != null) {
            labelAutorID.setText(String.valueOf(autor.getId()));
            labelAutorNome.setText(autor.getNome());
            labelAutorCPF.setText(autor.getCpf());
            labelAutorDataNasc.setText(String.valueOf(autor.getDataNascimento()));

            Endereco endereco = autores.pesquisarEnderecoPorId(autor.getId()); // Dúvida aqui
            labelEnderecoCEP.setText(endereco.getCep());
            labelEnderecoCidade.setText(endereco.getCidade());
            labelEnderecoComplemento.setText(endereco.getComplemento());
            labelEnderecoLogradouro.setText(endereco.getLogradouro());
            labelEnderecoUF.setText(endereco.getUf());
            labelEnderecoNumero.setText(String.valueOf(endereco.getId()));
            labelEnderecoId.setText(String.valueOf(endereco.getId()));
        } else {
            labelAutorID.setText("");
            labelAutorNome.setText("");
            labelAutorCPF.setText("");
            labelAutorDataNasc.setText("");
            labelEnderecoCEP.setText("");
            labelEnderecoCidade.setText("");
            labelEnderecoComplemento.setText("");
            labelEnderecoLogradouro.setText("");
            labelEnderecoUF.setText("");
            labelEnderecoNumero.setText("");
            labelEnderecoId.setText("");
        }
    }

    @FXML
    public void handleButtonInserir() throws IOException {
        Autor autor = new Autor();
        Endereco endereco = new Endereco();
        boolean buttonConfirmarCliked = showFXMLAnchorPaneCadastrosAutoresDialog(autor, endereco);
        if (buttonConfirmarCliked) {
            autor.setEndereco(endereco);
            endereco.setAutor(autor);
            autores.salvar(autor);
            carregarTableViewAutores();
        }
    }

    @FXML
    public void handleButtonAlterar() throws IOException {
        Autor autor = tableViewAutores.getSelectionModel().getSelectedItem();
        Endereco endereco = autores.pesquisarEnderecoPorId(autor.getId());

        if (autor != null) {
            boolean buttonConfirmarCliked = showFXMLAnchorPaneCadastrosAutoresDialog(autor, endereco);
            if (buttonConfirmarCliked) {
                autor.setEndereco(endereco);
                endereco.setAutor(autor);
                autores.salvar(autor);
                carregarTableViewAutores();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Favor selecionar um autor.");
            alert.show();
        }
    }

    @FXML
    public void handleButtonRemover() throws IOException {
        Autor autor = tableViewAutores.getSelectionModel().getSelectedItem();
        if (autor != null) {
                autores.remover(autor);
                carregarTableViewAutores();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Favor selecionar um autor.");
            alert.show();
        }
    }
    
    public boolean showFXMLAnchorPaneCadastrosAutoresDialog(Autor autor, Endereco endereco) throws  IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(FXMLAnchorPaneCadastrosAutoresDialogController.class.getResource("/autorendereco/view/FXMLAnchorPaneCadastrosAutoresDialog.fxml"));
        AnchorPane page = (AnchorPane) loader.load();
        
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Cadastro de Autores");
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);
        
        FXMLAnchorPaneCadastrosAutoresDialogController controller = loader.getController();
        controller.setDialogStage(dialogStage);
        controller.setAutor(autor);
        controller.setEndereco(endereco);
        
        dialogStage.showAndWait();
        
        return controller.isButtonConfirmarClicked();
    }
}
