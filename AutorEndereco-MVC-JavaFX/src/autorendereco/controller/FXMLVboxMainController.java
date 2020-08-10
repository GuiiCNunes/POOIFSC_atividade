/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autorendereco.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author GuiiCNunes <guiicnunes@gmail.com>
 */
public class FXMLVboxMainController implements Initializable {
    
    @FXML
    private MenuBar menuCadastros;

    @FXML
    private MenuItem menuItemCadastroAutor;

    @FXML
    private MenuItem menuItemConsultaAutor;

    @FXML
    private MenuItem menuItemConsultaId;

    @FXML
    private MenuItem menuItemConsultaCEP;
    
    @FXML
    private AnchorPane anchorPane;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    @FXML
    public void handleMenuItemCadastroAutores() throws IOException {
        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/autorendereco/view/FXMLAnchorPaneCadastrosAutores.fxml"));
        anchorPane.getChildren().setAll(a);
    }
    
}
