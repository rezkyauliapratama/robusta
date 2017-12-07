package android.rezkyauliapratama.id.robusta.utility;

import org.jetbrains.annotations.Contract;

/**
 * Created by Rezky Aulia Pratama on 9/3/2017.
 */

public class Constant {

    // Static member class member that holds only one instance of the
    // SingletonExample class
    private static class SingletonHolder{
        public static Constant singletonInstance =
                new Constant();
    }
    // SingletonExample prevents any other class from instantiating
    private Constant() {
    }

    // Providing Global point of access
    @Contract(pure = true)
    public static Constant getInstance() {
        return SingletonHolder.singletonInstance;
    }


    public final int PERMISSION_REQUEST = 10000;

    public final String DB_NAME = "hackjack_db";

    //FAB
    public final String POST = "Post";
    public final String SHARE_LOCATION = "Share location";
    public final String ADD_SCHEDULE = "Add schedule";

    public final String BASE_URL = "http://156.67.216.158/feo/public/v1";

    public final String MEDIA_FOLDER = "/media/";
    public final String AUDIO_FOLDER = MEDIA_FOLDER + "audio/";
    public final String PROFILE_FOLDER = MEDIA_FOLDER + "profile/";
    public final String PROFILE_BACKUP_FOLDER = MEDIA_FOLDER + "profileBackup/";
    public final String AUDIO_BACKUO_FOLDER = MEDIA_FOLDER + "audioBackup/";
    public final String DOCUMENT_FOLDER = MEDIA_FOLDER + "document/";
    public final String DOCUMENT_BACKUP_FOLDER = MEDIA_FOLDER + "documentBackup/";


}
