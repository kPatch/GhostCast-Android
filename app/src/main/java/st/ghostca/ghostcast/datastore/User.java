package st.ghostca.ghostcast.datastore;

import android.util.Log;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by uwe on 11/28/14.
 */
public class User {

    private Date timestamp;
    private String username;
    private String password;
    private static ParseUser user;
    private ArrayList<Post> posts;

    public User () {
        user = new ParseUser();
    }

    //get instance of node
    public static synchronized ParseUser getInstance() {
        return user;
    }

    //override clone method
    @Override
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

    /***********  Getter methods ***************/

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    /***********  Setter methods ***************/
    public void setUsername(String username) {
        user.setUsername(username);
    }
    //TODO: Automatically generate and save into datastore file?
    public void setPassword(String password) {
        user.setPassword(password);
    }

    public void signUp(){
        user.signUpInBackground();
    }


    }
