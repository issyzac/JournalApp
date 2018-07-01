package apps.issy.com.jono.interactor;


import android.annotation.SuppressLint;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.annotation.Nullable;

import apps.issy.com.jono.base.BaseDatabase;
import apps.issy.com.jono.model.entities.JournalModel;

/**
 * Created by issy on 30/06/2018.
 *
 * @issyzac issyzac.iz@gmail.com
 * On Project JournalApp
 */

public class JournalDetailInteractorImpl implements Interactor.JournalDetailInteractor {

    @Override
    public void loadJournalWithId(long journalId, BaseDatabase database, final onJournalLoaded listener) {
        LiveData<JournalModel> journalModelLiveData = database.journalModelDao().getJournalById(journalId);
        journalModelLiveData.observeForever(new Observer<JournalModel>() {
            @Override
            public void onChanged(@Nullable JournalModel journalModel) {
                listener.onLoaded(journalModel);
            }
        });
    }

    @SuppressLint("StaticFieldLeak")
    @Override
    public void saveEditedJournal(final JournalModel journalModel, final BaseDatabase database, final onJournalLoaded listener) {
        new AsyncTask<Void, Void, Void>(){
            @Override
            protected Void doInBackground(Void... voids) {
                database.journalModelDao().updateJournal(journalModel);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                listener.showEditedSuccessfully();
            }
        }.execute();
    }

    @SuppressLint("StaticFieldLeak")
    @Override
    public void deleteJournal(final JournalModel journalModel, final BaseDatabase database, final onJournalLoaded listener) {
        new AsyncTask<Void, Void, Void>(){
            @Override
            protected Void doInBackground(Void... voids) {
                database.journalModelDao().deleteJournal(journalModel);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                listener.deleted();
            }
        }.execute();
    }
}
