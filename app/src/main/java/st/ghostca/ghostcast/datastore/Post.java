package st.ghostca.ghostcast.datastore;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseGeoPoint;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.Date;

/**
 * Created by uwe on 11/28/14.
 */
public class Post {

    private final String NAME = "Post";
    private ParseObject postObject;
    private String username;
    private double latitude;
    private double longitude;
    private int likes;
    private Date timestamp;
    private Data data;
    private int page;
    ParseQuery<ParseObject> query = ParseQuery.getQuery(NAME);

    public Post(ParseObject user) {
        postObject = new ParseObject(NAME);
        //adding relation
        postObject.put("parent", user);
    }

    /***********  Getter methods ***************/
    public String getPost(String Objectid) {
        if(Objectid != null) {
            query.getInBackground(Objectid, new GetCallback<ParseObject>() {
                public void done(ParseObject object, ParseException e) {
                    if (e == null) {
                        // object will be your game score
                    } else {
                        // something went wrong
                    }
                }
            });
        }
        return null;
    }

    public Data getData() {
        return data;
    }

    public String getUsername() {
        return username;
    }

    /***********  Setter methods ***************/
    public void setUsername(String username) {
        postObject.put("username", username);
        //this.username = username;
    }
    public void setData(Data data) {
        postObject.put("data", data);
        //this.text= text;
    }

    public void setLocation(double latitude, double longitude) {
        ParseGeoPoint point = new ParseGeoPoint(latitude, longitude);
        postObject.put("location", point);
    }

    public void save() throws ParseException {
        synchronized (this) {
            postObject.saveInBackground();
        }
    }

    public void fetchAllPosts(int pagination) {


        query.setSkip(pagination);
        query.getFirstInBackground();
    }

    public void fetchUserPosts(int pagination) {

        query.setSkip(pagination);
        query.getFirstInBackground();
    }

    public void remove() {

    }

    public void setPage(int page) {
        this.page = page;
    }


}
