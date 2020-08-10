/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autorendereco.controller;

import autorendereco.model.domain.Autor;
import autorendereco.model.domain.Endereco;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.time.ZoneId;

/**
 * FXML Controller class
 *
 * @author GuiiCNunes <guiicnunes@gmail.com>
 */
public class FXMLAnchorPaneCadastrosAutoresDialogController implements Initializable {

    @FXML
    private DatePicker datePickerAutorDataNasc;

    @FXML
    private TextField textFieldAutorNome;

    @FXML
    private TextField textFieldAutorCPF;

    @FXML
    private TextField textFieldEnderecoLogradouro;

    @FXML
    private TextField textFieldEnderecoCEP;

    @FXML
    private TextField textFieldEnderecoNumero;

    @FXML
    private TextField textFieldEnderecoComplemento;

    @FXML
    private TextField textFieldEnderecoCidade;

    @FXML
    private TextField textFieldEnderecoUF;

    @FXML
    private Button buttonSalvar;

    @FXML
    private Button buttonCancelar;
    
    private Stage dialogStage;
    private boolean buttonConfirmarClicked = false;
    private Autor autor;
    private Endereco endereco;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    /**
     * @return the dialogStage
     */
    public Stage getDialogStage() {
        return dialogStage;
    }

    /**
     * @param dialogStage the dialogStage to set
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    /**
     * @return the buttonConfirmarClicked
     */
    public boolean isButtonConfirmarClicked() {
        return buttonConfirmarClicked;
    }

    /**
     * @param buttonConfirmarClicked the buttonConfirmarClicked to set
     */
    public void setButtonConfirmarClicked(boolean buttonConfirmarClicked) {
        this.buttonConfirmarClicked = buttonConfirmarClicked;
    }

    /**
     * @return the autor
     */
    public Autor getAutor() {
        return autor;
    }

    /**
     * @param autor the autor to set
     */
    public void setAutor(Autor autor) {
        this.autor = autor;
        this.textFieldAutorNome.setText(autor.getNome());
        this.textFieldAutorCPF.setText(autor.getCpf());
//        if (autor.getDataNascimento() != null) { ---------------------PROBLEMA----------------------
//            this.datePickerAutorDataNasc.setValue(autor.getDataNascimento().toInstant().atZone(ZoneId.systemDefault()).toLocalDate()); // Outro método?
//            this.datePickerAutorDataNasc.setValue(LocalDate.ofEpochDay(autor.getDataNascimento().getTime())); // Outro método?
//        }
    }

    /**
     * @return the endereco
     */
    public Endereco getEndereco() {
        return endereco;
    }

    /**
     * @param endereco the endereco to set
     */
    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
        this.textFieldEnderecoCEP.setText(endereco.getCep());
        this.textFieldEnderecoCidade.setText(endereco.getCidade());
        this.textFieldEnderecoComplemento.setText(endereco.getComplemento());
        this.textFieldEnderecoLogradouro.setText(endereco.getLogradouro());
        this.textFieldEnderecoUF.setText(endereco.getUf());
        this.textFieldEnderecoNumero.setText(String.valueOf(endereco.getNumero()));
    }
    
    @FXML
    public void handleButtonSalvar() {
        
        autor.setNome(textFieldAutorNome.getText());
        autor.setCpf(textFieldAutorCPF.getText());
        autor.setDataNascimento(Date.valueOf(datePickerAutorDataNasc.getValue()));
        endereco.setCep(textFieldEnderecoCEP.getText());
        endereco.setCidade(textFieldEnderecoCidade.getText());
        endereco.setComplemento(textFieldEnderecoComplemento.getText());
        endereco.setLogradouro(textFieldEnderecoLogradouro.getText());
        endereco.setNumero(Integer.valueOf(textFieldEnderecoNumero.getText()));
        endereco.setUf(textFieldEnderecoUF.getText());
        
        buttonConfirmarClicked = true;
        dialogStage.close();
        
    }
    
    public void handleButtonCancelar() {
        dialogStage.close();
    }    
}
