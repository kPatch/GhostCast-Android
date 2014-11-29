package st.ghostca.ghostcast.datastore;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseGeoPoint;
import com.parse.ParseObject;
import com.parse.ParseQuery;

/**
 * Created by uwe on 11/28/14.
 */
public class Post {

    private final String NAME = "Post";
    private ParseObject postObject;
    private String username;
    private String text;
    private String audioPath;
    private String imagePath;
    private double latitude;
    private double longitude;
    private int likes;
/*
    // Create the post
    ParseObject myPost = new ParseObject("Post");
    myPost.put("title", "I'm Hungry");
    myPost.put("content", "Where should we go for lunch?");

    // Create the comment
    ParseObject myComment = new ParseObject("Comment");
    myComment.put("content", "Let's do Sushirrito.");

// Add a relation between the Post and Comment
    myComment.put("parent", myPost);

// This will save both myPost and myComment
    myComment.saveInBackground();
    */
    public Post(ParseObject user) {
        postObject = new ParseObject(NAME);
        //adding relation
        postObject.put("parent", user);
    }

    /***********  Getter methods ***************/
    public String getPost(String Objectid) {
        ParseQuery<ParseObject> query = ParseQuery.getQuery(NAME);
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

    public String getText() {
        return text;
    }

    public String getAudioPath() {
        return audioPath;
    }

    public String getImagePath() {
        return imagePath;
    }

    public String getUsername() {
        return username;
    }

    /***********  Setter methods ***************/
    public void setUsername(String username) {
        postObject.put("username", username);
        //this.username = username;
    }
    public void setText(String text) {
        postObject.put("text", text);
        //this.text= text;
    }

    public void setAudioPath(String audioPath) {
        postObject.put("audioFile", audioPath);
        //this.audioPath = audioPath;
    }

    public void setImagePath(String imagePath) {
        postObject.put("imageFile", imagePath);
       this.imagePath = imagePath;
    }


    public void setGeo(double latitude, double longitude) {
        ParseGeoPoint point = new ParseGeoPoint(latitude, longitude);
        postObject.put("location", point);
    }

    public void save() throws ParseException {
        synchronized (this) {
            postObject.saveInBackground();
        }
    }


}
