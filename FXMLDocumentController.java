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
import javafx.scene.control.TableView;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.input.MouseEvent;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import com.jfoenix.controls.JFXSlider;
import com.jfoenix.controls.JFXProgressBar;
import java.io.File;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

/**
 *
 * @author dylanbanaszak
 */
public class FXMLDocumentController implements Initializable {
 
long length = 0;
 
 Media media = new Media(new File("/Users/dylanbanaszak/Downloads/DaveMaster & Ali (idea).wav").toURI().toString());
 private MediaPlayer mediaPlayer = new MediaPlayer(media);
 
    @FXML
    private JFXProgressBar progress;
    
    @FXML
    private JFXSlider volume;
    
    @FXML
    private Label lbl_time;
    
    @FXML
    private TextField textField_search;
    
    @FXML 
    private AnchorPane pane_signIn, pane_upload;
    
    @FXML
    private MaterialDesignIconView btn_close, btn_closeSignIn, btn_closeUpload, btn_search, btn_play, btn_pause;
    
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
            
        }
        if (event.getSource() == btn_play){
           //mediaPlayer = new MediaPlayer(media);
            //mediaPlayer.seek(length);
            mediaPlayer.play();
            
        }
        if (event.getSource() == btn_pause){
            //mediaPlayer = new MediaPlayer(media);
            mediaPlayer.pause();   
            //length = getCurrentPosition(media);
        }
        if (event.getSource() == volume){
            if (volume.isValueChanging()){
                mediaPlayer.setVolume(volume.getValue()/100.0);
            }
        }
        /*if (progress.isValueChanging()) {
       // multiply duration by percentage calculated by slider position
          
       }*/
    }
        
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  

    
}
