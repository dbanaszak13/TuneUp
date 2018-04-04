import java.util.Date;

/**
 * Created by mohammedawan on 3/28/18.
 */
public class User {
    private String username;
    private String userType;
    private int userID;
    private String password;
    private String firstName;
    private String lastName;
    private String dateOfBirth;

    //Default Constructor
    User()
    {
        username = "";
        userType = "";
        userID = 0;
        password = "";
        firstName = "";
        lastName = "";
        dateOfBirth = "";
    }

    //Actual Constructor
    User(String input_username, String input_userType, String input_password, String input_firstname, String input_lastname, String input_DOB)
    {
        username = input_username;
        userType = input_userType;
        password = input_password;
        firstName = input_firstname;
        lastName = input_lastname;
        dateOfBirth = input_DOB;
    }

    public String getPassword()
    {
        return password;
    }

    public String getUsername()
    {
        return username;
    }

    public String getUserType()
    {
        return userType;
    }

    public int getUserID()
    {
        return userID;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public String getDateOfBirth()
    {
        return dateOfBirth;
    }

    public void setPassword(String new_password)
    {
        password = new_password;
    }

    public void setUsername(String new_username)
    {
        username = new_username;
    }

    public void setUserType(String new_userType)
    {
        userType = new_userType;
    }

    public void setFirstName(String new_firstname)
    {
        firstName = new_firstname;
    }

    public void setLastName(String new_lastname)
    {
        lastName = new_lastname;
    }

    public void setDateOfBirth(String new_DOB)
    {
        dateOfBirth = new_DOB;
    }
}
