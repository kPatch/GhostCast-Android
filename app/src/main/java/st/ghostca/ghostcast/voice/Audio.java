package st.ghostca.ghostcast.voice;

import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Environment;

import java.io.IOException;
import java.util.Date;

/**
 * Created by leo on 11/27/2014.
 */
public class Audio {

    //region static
    private static MediaRecorder myAudioRecorder;
    private static Audio instance;
    private static final String storage_directory = Environment.getExternalStorageDirectory().
            getAbsolutePath() + "/ghostcast/Audio/";

    private static void initialize() {
        myAudioRecorder = new MediaRecorder();
        myAudioRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        myAudioRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        myAudioRecorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
    }

    /**
     * Get instance of Audio just recorded
     * @return
     */
    public static Audio getInstance() {
        return instance;
    }

    /**
     * Start recording audio
     */
    public static void startRecording() throws IOException {
        initialize();
        instance = new Audio();
        myAudioRecorder.setOutputFile(instance.name);
        myAudioRecorder.prepare();
        myAudioRecorder.start();
    }

    /**
     * Stop recording audio
     */
    public static void stopRecording() {
        myAudioRecorder.stop();
        myAudioRecorder.release();
        myAudioRecorder = null;
    }
    //endregion

    //region non-static
    private String name;

    private Audio() {
        name = (new Date()).toString();
    }

    public void play(String filePath) throws IOException {
        MediaPlayer m = new MediaPlayer();
        m.setDataSource(storage_directory + name);
        m.prepare();
        m.start();
    }
    //endregion

}
