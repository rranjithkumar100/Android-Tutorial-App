package ranjih.kotlinandroid.controller.firestore;

import androidx.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;
import com.google.firebase.firestore.QuerySnapshot;


/**
 * Created by Ranjith on 03-12-2017.
 */

public class FireStoreUtil {
    private static final String TAG = FireStoreUtil.class.getSimpleName();
    private static final FireStoreUtil ourInstance = new FireStoreUtil();
    private FirebaseFirestore db ;
private static final String ROOT_TAG="news";
    private FireStoreUtil() {

    }

    public static FireStoreUtil getInstance() {
        return ourInstance;
    }



    public void init() {
        try {
            FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder()
                    .setPersistenceEnabled(true)
                    .build();
            db=FirebaseFirestore.getInstance();
            db.setFirestoreSettings(settings);
        }
        catch (Exception e){
            Log.e(TAG, "init: ",e );
        }
        }

    public void getNews(final int reqCode, final FireStoreListener fireStoreListener) {
        Log.d(TAG, "getNews() ");
        if(db!=null) {
            Task<QuerySnapshot> query = db.collection(ROOT_TAG).limit(100).
                    get();

            setListener(query, reqCode, fireStoreListener);
        }
        else {
            fireStoreListener.failure(reqCode, null);
            Log.d(TAG, "getNews: firestore db null.. pls check ");
        }
    }


    private void setListener(Task<QuerySnapshot> query, final int reqCode, final FireStoreListener fireStoreListener) {
        query.addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                Log.d(TAG, "onComplete:  success");
                if (fireStoreListener != null)
                    fireStoreListener.success(reqCode, task);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d(TAG, "onFailure: ",e);
                if (fireStoreListener != null)
                    fireStoreListener.failure(reqCode, e);
            }
        });
    }





}
