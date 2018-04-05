/**
 * Created by mohammedawan on 3/28/18.
 */
package tuneup;

public class Song {
    private String name;
    private String artist;
    private int songID;
    private float rating;
    private int totalRatings;
    private String mp3file;
    private String genre;
    private String description;

    //Default Constructor
    Song()
    {
        name = "";
        artist = "";
        songID = 0;
        rating = 0;
        totalRatings = 0;
        mp3file = "";
        genre = "";
        description = "";
    }

    //Actual Constructor
    Song(String input_name, String input_artist, int input_songID, float input_rating, int input_totalRatings, String input_mp3file, String input_genre, String input_description)
    {
        name = input_name;
        artist = input_artist;
        songID = input_songID;
        rating = input_rating;
        totalRatings = input_totalRatings;
        mp3file = input_mp3file;
        genre = input_genre;
        description = input_description;

    }

    public int getSongID()
    {
        return songID;
    }

    public String getName()
    {
        return name;
    }

    public String getArtist()
    {
        return artist;
    }

    public float getRating()
    {
        return rating;
    }

    public int getTotalRatings()
    {
        return totalRatings;
    }

    public String getMp3File()
    {
        return mp3file;
    }

    public String getGenre()
    {
        return genre;
    }

    public String getDescription()
    {
        return description;
    }

    public void setName(String new_name)
    {
        name = new_name;
    }

    public void setArtist(String new_artist)
    {
        artist = new_artist;
    }

    public void setRating(float new_rating)
    {
        rating = new_rating;
    }

    public void setTotalRatings(int new_totalratings)
    {
        totalRatings = new_totalratings;
    }

    public void setMp3File(String new_mp3file)
    {
        mp3file = new_mp3file;
    }

    public void setGenre(String new_genre)
    {
        genre = new_genre;
    }

    public void setDescription(String new_description)
    {
        description = new_description;
    }
}
