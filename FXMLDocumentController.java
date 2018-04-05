/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tuneup;

import java.io.*;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.Button;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import java.util.*;

/**
 *
 * @author dylanbanaszak
 */
public class FXMLDocumentController implements Initializable {

    DBControl db = new DBControl();

    @FXML
    private TableView<Song> songsList;

    @FXML
    private TableView<User> artistsList;

    @FXML
    private TableColumn<Song, Integer> ID;

    @FXML
    private TableColumn<Song, String> Song, Artist, Genre;

    @FXML
    private TableColumn<Song, Double> Rating;

    @FXML
    private TableColumn<User, String> Username, FName, LName, DOB;

    @FXML
    private TextField textField_search, txt_Artist, txt_SongName, txt_Genre, txt_Description, txt_FilePath, txt_SIUsername, txt_SIPassword, txt_SUUsername, txt_SUPassword, txt_SUFName, txt_SULName;

    @FXML
    private DatePicker dp_SUDOB;

    @FXML 
    private AnchorPane pane_signIn, pane_upload;
    
    @FXML
    private MaterialDesignIconView btn_close, btn_closeSignIn, btn_closeUpload, btn_music, btn_artists;
    
    @FXML
    private Button btn_signIn, btn_upload, btn_results, btn_FileBrowser, btn_sUpload, btn_SignIn, btn_SignUp;
    
    @FXML
    public void handleButtonAction(MouseEvent event) throws SQLException, IOException, ClassNotFoundException {
        db.setConnections();
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
                System.out.println(db.getMostRecentSongID());
                pane_upload.setVisible(true);
            }
            if (event.getSource() == btn_closeUpload){
                pane_upload.setVisible(false);
            }
            if (event.getSource() == btn_results){
                String searchInput = textField_search.getText();
                ArrayList<Song> songList = db.searchSong(null, null, searchInput);
                for(int i = 0; i< songList.size()-1; i++){
                    System.out.println(songList.get(i).getName());
                }
                updateSongsList(songList);
            }
            if (event.getSource() == btn_FileBrowser){
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Open Resource File");
                File selectedFile = fileChooser.showOpenDialog(null);
                txt_FilePath.setText(selectedFile.getAbsolutePath());
            }
            if (event.getSource() == btn_sUpload){
                Song uploadSong = new Song(txt_SongName.getText(), txt_Artist.getText(), 0, 0, 0, txt_FilePath.getText(), txt_Genre.getText(), txt_Description.getText());
                db.addSong(uploadSong);
                db.addMp3(db.getMostRecentSongID(), txt_FilePath.getText());
                txt_SongName.clear();
                txt_Genre.clear();
                txt_Description.clear();
                txt_Artist.clear();
                txt_FilePath.clear();
                pane_upload.setVisible(false);
            }
            if (event.getSource() == btn_SignIn){
                User loginUser = new User(txt_SIUsername.getText(), "", txt_SIPassword.getText(), "", "", "");
                String message = db.userLogin(loginUser);
                if(message.equals("This user doesn't exist")){
                    System.out.println("This user doesn't exist");
                } else if (message.equals("Logged in!!")){
                    System.out.println("Logged in!!");
                    txt_SIPassword.clear();
                    txt_SIUsername.clear();
                    pane_signIn.setVisible(false);
                    btn_upload.setVisible(true);
                    btn_signIn.setVisible(false);
                } else if(message.equals("Incorrect Password entered")){
                    System.out.println("Incorrect Password entered");
                }

            }

            if(event.getSource() == btn_SignUp){
                User signUpUser = new User(txt_SUUsername.getText(), "User", txt_SUPassword.getText(), txt_SUFName.getText(),txt_SULName.getText(), dp_SUDOB.getValue().toString());
                db.addUser(signUpUser);
                txt_SUFName.clear();
                txt_SULName.clear();
                txt_SUPassword.clear();
                txt_SUUsername.clear();
                System.out.println("Added User");

            }

            if (event.getSource() == songsList){

                Song current = songsList.getSelectionModel().getSelectedItem();
                db.getMp3(current.getSongID());
            }

            if (event.getSource() == btn_artists){
                songsList.setVisible(false);
                artistsList.setVisible(true);
                updateUsersList(db.getUserList());

            }
            if (event.getSource() == btn_music){
                artistsList.setVisible(false);
                songsList.setVisible(true);
                updateSongsList(db.getSongsList());
            }

        }

    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void updateSongsList(ArrayList<Song> listSong){
        for(int i = 0; i < songsList.getItems().size(); i++) {
            songsList.getItems().clear();
        }
        final ObservableList<Song> data = FXCollections.observableArrayList(
                listSong
        );

        ID.setCellValueFactory(new PropertyValueFactory<Song, Integer>("songID"));

        Song.setCellValueFactory(new PropertyValueFactory<Song, String>("name"));

        Artist.setCellValueFactory(new PropertyValueFactory<Song, String>("artist"));

        Genre.setCellValueFactory(new PropertyValueFactory<Song, String>("genre"));

        Rating.setCellValueFactory(new PropertyValueFactory<Song, Double>("rating"));

        songsList.setItems(data);

    }

    public void updateUsersList(ArrayList<User> listUser){
        for(int i = 0; i < artistsList.getItems().size(); i++){
            artistsList.getItems().clear();
        }
        final ObservableList<User> data = FXCollections.observableArrayList(
          listUser
        );

        Username.setCellValueFactory(new PropertyValueFactory<User, String>("username"));

        FName.setCellValueFactory(new PropertyValueFactory<User, String>("firstName"));

        LName.setCellValueFactory(new PropertyValueFactory<User, String>("lastName"));

        DOB.setCellValueFactory(new PropertyValueFactory<User, String>("dateOfBirth"));

        artistsList.setItems(data);

    }

    
}
