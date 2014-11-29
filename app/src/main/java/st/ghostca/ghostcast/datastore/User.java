package st.ghostca.ghostcast.datastore;

import android.util.Log;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by uwe on 11/28/14.
 */
public class User {

    private final String NAME = "User";
    ParseObject userObject;
    private String username;
    private String password;
    private ArrayList<Post> posts;

    public User () {
        userObject = new ParseObject(NAME);
    }

    /***********  Getter methods ***************/

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public ArrayList<Post> getPosts(String username) {
        ParseQuery<ParseObject> query = ParseQuery.getQuery(NAME);
        query.whereEqualTo("username", username);
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> scoreList, ParseException e) {
                if (e == null) {
                    Log.d("score", "Retrieved " + scoreList.size() + " scores");
                } else {
                    Log.d("score", "Error: " + e.getMessage());
                }
            }
        });
        return null;
    }

    /***********  Setter methods ***************/
    public void setUsername(String username) {

    }

    public void setPassword(String password) {

    }


    }
