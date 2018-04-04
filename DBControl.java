/**
 * Created by mohammedawan on 3/28/18.
 */
import java.io.*;
import java.util.*;
import java.sql.*;
import java.lang.*;


public class DBControl {
    Connection conn;
    Statement stmt;

    public void setConnections() throws SQLException, ClassNotFoundException, IOException{
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tuneup", "root", "admin");
        stmt =conn.createStatement();
    }

    public ArrayList<Song> getSongsList() throws SQLException{
        ArrayList<Song> songsList = new ArrayList<>();
        ResultSet rs = stmt.executeQuery("SELECT * FROM Song");
        while(rs.next()){
            Song tmp = new Song(rs.getString(2), rs.getString(3), rs.getInt(1), rs.getFloat(6),rs.getInt(7), rs.getString(8), rs.getString(4), rs.getString(5));
            songsList.add(tmp);
        }
        return songsList;
    }
    public ArrayList<Song> searchSong(String artist, String genre, String songName) throws SQLException, NullPointerException{
        String sql = "SELECT * FROM Song";
        if(!(artist == null)){
            sql = sql + " WHERE Artist like '%" + artist + "%'";
        }
        if(!(genre == null)){

            if(sql.equals("SELECT * FROM Song")){
                sql = sql + " WHERE Genre like '%" + genre + "%'";

            }
            else {
                sql = sql + "AND Genre like '%" + genre + "%'";
            }
        }
        if(!(songName == null)){
            if(sql.equals("SELECT * FROM Song")){
                sql = sql + " WHERE SongName like '%" + songName + "%'";
            }
            else {
                sql = sql + "AND SongName like '%" + songName + "%'";
            }
        }
        sql = sql + ";";
        ResultSet rs = stmt.executeQuery(sql);

        ArrayList<Song> songsList = new ArrayList<>();
        while(rs.next()){
            Song tmp = new Song(rs.getString(2), rs.getString(3), rs.getInt(1), rs.getFloat(6),rs.getInt(7), rs.getString(8), rs.getString(4), rs.getString(5));
            songsList.add(tmp);
        }
        return songsList;
    }

    public User getUser(String username) throws SQLException{
        String sql = "SELECT * FROM Account WHERE Username = '" + username + "';";
        ResultSet rs = stmt.executeQuery(sql);
        User tmp = new User(rs.getString(2), rs.getString(3), "", rs.getString(4), rs.getString(5), rs.getString(5));
        return tmp;
    }

    public void addSong(Song song) throws SQLException{
        String sql = "INSERT INTO Song (SongName, Artist, Genre, Description, Rating, TotalRatings, filepath) VALUE ('" + song.getName() + "','" + song.getArtist() + "','" + song.getGenre() + "','" + song.getDescription() + "',0,0,'" + song.getMp3File() + "');";
        stmt.executeUpdate(sql);
    }

    public void deleteSong(Song song) throws SQLException{
        String sql = "DELETE FROM Song WHERE SID = " + song.getSongID() + ";";
        stmt.executeUpdate(sql);
    }

    public void addUser(User user) throws SQLException{
        String sql = "INSERT INTO Account (Username,AccountType,FName,LName,DoB) VALUE ('" + user.getUsername() + "','" + user.getUserType() + "','" + user.getFirstName() + "','" + user.getLastName() + "','" + user.getDateOfBirth() + "');";
        stmt.executeUpdate(sql);
    }

    public void deleteUser(User user) throws SQLException{
        String sql = "DELETE FROM Account WHERE Username = '" + user.getUsername() + "';";
        stmt.executeUpdate(sql);
    }

    public Song editRating(Song song, float inputRating) throws SQLException{
        float newRating = ((song.getRating() * song.getTotalRatings()) + inputRating)/ (song.getTotalRatings() + 1);
        song.setTotalRatings(song.getTotalRatings() + 1);
        song.setRating(newRating);
        System.out.println(song.getSongID());
        String sql = "UPDATE Song set Rating = " + newRating + ", TotalRatings = " + song.getTotalRatings() + " WHERE SID = " + song.getSongID() +";";
        stmt.executeUpdate(sql);
        return song;
    }

    public void addMp3() throws SQLException, IOException{
        String sql = "UPDATE Song set mp3=? WHERE SID = 3;" ;
        PreparedStatement pstmt = conn.prepareStatement(sql);
        File newFile = new File("fire.mp3");
        FileInputStream input = new FileInputStream(newFile);
        pstmt.setBinaryStream(1,input);
        pstmt.executeUpdate();

    }

    public void getMp3() throws SQLException, IOException{
        ResultSet rs = null;
        String sql = "SELECT mp3 FROM Song WHERE SID = 3;" ;
        PreparedStatement pstmt = conn.prepareStatement(sql);
        rs = pstmt.executeQuery(sql);
        File file = new File("tmp.mp3");
        FileOutputStream fos = new FileOutputStream(file);
        while (rs.next()) {
            InputStream input = rs.getBinaryStream("mp3");
            byte[] buffer = new byte[1024];
            while (input.read(buffer) > 0) {
                fos.write(buffer);
            }
        }

    }



}
