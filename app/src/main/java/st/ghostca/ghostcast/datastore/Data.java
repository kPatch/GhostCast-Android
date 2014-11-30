package st.ghostca.ghostcast.datastore;

import android.graphics.Bitmap;

import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ProgressCallback;
import com.parse.SaveCallback;

import java.util.Date;

/**
 * Created by uwe on 11/30/14.
 */
public class Data {

    private final String NAME = "Data";
    private Date timestamp;
    private double latitude;
    private double longitude;
    private String text;
    private byte[] image;
    private byte[] audio;
    private ParseObject dataObject = new ParseObject(NAME);
    private ParseFile file;


    public Data(String text) {
        file = new ParseFile(text.getBytes());
    }

    public Data(String text, Bitmap image) {
        file = new ParseFile(text.getBytes());
    }

    public Data(String text, String audioPath) {
        file = new ParseFile(text.getBytes());
    }

    public void Save() {
        file.saveInBackground(new SaveCallback() {
            public void done(ParseException e) {
                // Handle success or failure here ...
            }
        }, new ProgressCallback() {
            public void done(Integer percentDone) {
                // Update your progress spinner here. percentDone will be between 0 and 100.
            }
        });

        dataObject.put("data",file);
        dataObject.saveInBackground();
    }







}
