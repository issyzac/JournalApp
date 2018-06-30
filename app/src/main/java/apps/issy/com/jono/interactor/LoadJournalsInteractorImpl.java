package apps.issy.com.jono.interactor;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.annotation.Nullable;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Observer;

import apps.issy.com.jono.MainActivity;
import apps.issy.com.jono.base.BaseDatabase;
import apps.issy.com.jono.model.entities.JournalModel;

/**
 * Created by issy on 30/06/2018.
 *
 * @issyzac issyzac.iz@gmail.com
 * On Project JournalApp
 */

public class LoadJournalsInteractorImpl implements LoadJournalsInteractor{

    LiveData<List<JournalModel>> items;

    @Override
    public void loadJournalItems(final onFinishListener listener, final BaseDatabase database) {
        //Load all the jornals of this user from the database

        items = database.journalModelDao().getAllJounals();
        items.observeForever(new android.arch.lifecycle.Observer<List<JournalModel>>() {
            @Override
            public void onChanged(@Nullable List<JournalModel> journalModels) {
                listener.onFinished(journalModels);
            }
        });
    }

    @Override
    public void removeObserver() {
    }
}
