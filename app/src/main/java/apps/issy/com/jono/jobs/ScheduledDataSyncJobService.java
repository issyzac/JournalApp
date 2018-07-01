/*
 * Copyright (C) 2018 Isaya Zachariah Mollel - issyzac
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package apps.issy.com.jono.jobs;

import android.util.Log;

import com.firebase.jobdispatcher.JobParameters;
import com.firebase.jobdispatcher.JobService;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

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
