package st.ghostca.ghostcast.model;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;

/**
 * Created by leo on 11/29/2014.
 */
public class Image {

    private static final String storage_directory = Environment.getExternalStorageDirectory().
            getAbsolutePath() + "/ghostcast/Image/";
    private static String name;
    private Bitmap image;

    public Image(Bitmap bm) {
        image = bm;
        name = (new Date()).toString();
    }

    public Image(Bitmap bm, String fileName) {
        image = bm;
        name = fileName;
    }

    public void saveImage() {
        File myPath = new File(storage_directory, name);
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(myPath);
            // Use the compress method on the BitMap object to write image to the OutputStream
            image.compress(Bitmap.CompressFormat.PNG, 100, fos);
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Image loadImage(String fileName) {
        Bitmap bm = null;
        try{
            bm = BitmapFactory.decodeFile(storage_directory + name);
        } catch(Exception e) {
            e.printStackTrace();
            return null;
        }
        return new Image(bm, fileName);
    }
}

