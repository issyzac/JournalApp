package apps.issy.com.jono.jobs;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.firebase.jobdispatcher.JobParameters;
import com.firebase.jobdispatcher.JobService;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

import apps.issy.com.jono.base.BaseActivity;
import apps.issy.com.jono.base.BaseDatabase;
import apps.issy.com.jono.model.entities.JournalModel;

/**
 * Created by issy on 01/07/2018.
 *
 * @issyzac issyzac.iz@gmail.com
 * On Project JournalApp
 */

public class ScheduledDataSyncJobService extends JobService {

    private static final String TAG = ScheduledDataSyncJobService.class.getSimpleName();

    @Override
    public boolean onStartJob(final JobParameters params) {
        //Offloading work to a new thread.
        new Thread(new Runnable() {
            @Override
            public void run() {
                syncDataToFirebase(params);
            }
        }).start();

        return true;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        return false;
    }

    public void syncDataToFirebase(final JobParameters parameters) {
        try {

            final String currentUserId = FirebaseAuth.getInstance().getCurrentUser().getUid();
            final DatabaseReference mUserReference = FirebaseDatabase.getInstance().getReference()
                    .child("users-journals")
                    .child(currentUserId);

            BaseDatabase database = BaseDatabase.getINSTANCE(this);
            List<JournalModel> journals = database.journalModelDao().getAllJournalList();
            for (JournalModel j : journals){

                Log.d(TAG, "User is : "+currentUserId);
                Log.d(TAG, "Journal Title is : "+j.getTitle());
                Log.d(TAG, "Journal ID is : "+j.getJournalId());

                DatabaseReference journalRef = mUserReference.child(""+j.getJournalId());
                journalRef.setValue(j);
            }

            Log.d(TAG, "completeJob: " + "jobStarted");
            //This task takes 2 seconds to complete.
            Thread.sleep(2000);

            Log.d(TAG, "completeJob: " + "jobFinished");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            //Tell the framework that the job has completed and doesnot needs to be reschedule
            jobFinished(parameters, true);
        }
    }

}
