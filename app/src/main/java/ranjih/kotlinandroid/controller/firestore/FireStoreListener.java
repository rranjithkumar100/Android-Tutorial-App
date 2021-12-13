package ranjih.kotlinandroid.controller.firestore;

import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.QuerySnapshot;

/**
 * Created by Ranjith on 03-12-2017.
 */

public interface FireStoreListener {

    void success(int requestCode, Task<QuerySnapshot> task);

    void failure(int requestCode, Exception exception);
}
