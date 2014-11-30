package st.ghostca.ghostcast.datastore;

import com.parse.ParseObject;

import java.util.Date;

/**
 * Created by uwe on 11/28/14.
 */
public class Comment {

    private final String NAME = "Comment";
    private ParseObject commentObject;
    private String username;
    private String text;
    private String audioPath;
    private String imagePath;
    private double latitude;
    private double longitude;
    private int likes;
    private Date timestamp;

    public Comment() {
        commentObject = new ParseObject(NAME);
    }

    /***********  Getter methods ***************/
    public void getUsername() {

    }

    public void getText() {

    }

    public void getAudio() {

    }

    public void getImage() {

    }

    /***********  Setter methods ***************/

    public void setUsername() {

    }

    public void setText() {

    }

    public void setAudio() {

    }

    public void setImage() {


    }




}
