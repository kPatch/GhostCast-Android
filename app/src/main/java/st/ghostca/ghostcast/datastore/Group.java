package st.ghostca.ghostcast.datastore;

import com.parse.ParseObject;

import java.util.Date;

/**
 * Created by uwe on 11/30/14.
 */
public class Group {

    private final String NAME = "Group";
    private String username;
    private String groupname;
    private Date timestamp;
    private ParseObject groupObject;

    public Group(String groupname) {
        groupObject = new ParseObject(NAME);
        //groupObject.put(username,groupname);
    }

    public Group() {
        groupObject = new ParseObject(NAME);

    }




}
