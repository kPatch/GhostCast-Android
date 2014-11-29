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
public class Feed {

    private final String NAME = "Feed";
    private ArrayList<Post> Feed;
    private ParseObject feedObject;
    private double latitude;
    private double longitude;
    private ParseQuery<ParseObject> query;

    public Feed() {
        feedObject = new ParseObject(NAME);
    }

    /***********  Getter methods ***************/
    public ArrayList<Post> getPosts() {
        query = ParseQuery.getQuery(NAME);
        query.whereEqualTo("username", "gerro");
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

    public ArrayList<Post> getPosts(String text) {
        return null;
    }

    public ArrayList<Post> getPosts(float latitude, float longitude) {
        return null;
    }

    /***********  Setter methods ***************/
    //pagination
    public void setPage(int page) {
        query.setSkip(page);
    }

}
