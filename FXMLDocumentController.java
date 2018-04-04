/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tuneup;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.input.MouseEvent;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;

/**
 *
 * @author dylanbanaszak
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private TextField textField_search;
    
    @FXML 
    private AnchorPane pane_signIn, pane_upload;
    
    @FXML
    private MaterialDesignIconView btn_close, btn_closeSignIn, btn_closeUpload, btn_search;
    
    @FXML
    private Button btn_signIn, btn_upload;
    
    @FXML
    public void handleButtonAction(MouseEvent event){
        if (event.getSource() == btn_close){
            System.exit(0);
        }
        else{
            if (event.getSource() == btn_signIn){
                pane_signIn.setVisible(true);
            }
            if (event.getSource() == btn_closeSignIn){
                pane_signIn.setVisible(false);
            }
            if (event.getSource() == btn_upload){
                pane_upload.setVisible(true);
            }
            if (event.getSource() == btn_closeUpload){
                pane_upload.setVisible(false);
            }
        }
        if (event.getSource() == btn_search){
            textField_search.getText();
            public ArrayList<Song>
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  

    
}
