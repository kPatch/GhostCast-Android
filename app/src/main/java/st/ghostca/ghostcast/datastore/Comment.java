package st.ghostca.ghostcast.datastore;

import android.graphics.Bitmap;

import com.parse.ParseGeoPoint;
import com.parse.ParseObject;

import java.util.Date;

/**
 * Created by uwe on 11/28/14.
 */
public class Comment {

    private final String NAME = "Comment";
    private ParseObject commentObject;
    private Data data;
    private int likes;
    private Date timestamp;

    public Comment() {
        commentObject = new ParseObject(NAME);
    }

    public Comment(String text){
        data = new Data(text);
        data.Save();
    }

    /***********  Getter methods ***************/
    public void getUsername(String username) {
        commentObject.put("username", username);
    }

    public Data getData() {
        return data;
    }

    /***********  Setter methods ***************/

    public void setUsername() {

    }

    public void setData(Data data) {
        this.data = data;
    }

    public void setLocation(double latitude, double longitude) {
        ParseGeoPoint point = new ParseGeoPoint(latitude, longitude);
        commentObject.put("location", point);
    }


}
