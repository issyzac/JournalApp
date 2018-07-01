package apps.issy.com.jono.presenter;

import android.content.Context;

import java.util.List;

import apps.issy.com.jono.base.BaseDatabase;
import apps.issy.com.jono.interactor.Interactor;
import apps.issy.com.jono.jobs.ScheduledDataSyncJobService;
import apps.issy.com.jono.model.entities.JournalModel;
import apps.issy.com.jono.view.BaseView;

import com.firebase.jobdispatcher.Constraint;
import com.firebase.jobdispatcher.FirebaseJobDispatcher;
import com.firebase.jobdispatcher.GooglePlayDriver;
import com.firebase.jobdispatcher.Job;
import com.firebase.jobdispatcher.Lifetime;
import com.firebase.jobdispatcher.RetryStrategy;
import com.firebase.jobdispatcher.Trigger;

/**
 * Created by issy on 30/06/2018.
 *
 * @issyzac issyzac.iz@gmail.com
 * On Project JournalApp
 */

public class MainPresenterImpl implements Presenter.MainPresenter, Interactor.LoadJournalsInteractor.onFinishListener {

    private BaseView.MainView mMainView;
    private Interactor.LoadJournalsInteractor mLoadJournalsInteractor;
    private BaseDatabase mDatabase;
    private Context context;

    public MainPresenterImpl(BaseView.MainView mainView, Interactor.LoadJournalsInteractor loadJournalsInteractor,
                             BaseDatabase database){
        this.mMainView = mainView;
        this.mLoadJournalsInteractor = loadJournalsInteractor;
        this.mDatabase = database;
    }

    @Override
    public void onResume() {
        if (mMainView != null){
            mMainView.showProgress();
        }
        mLoadJournalsInteractor.loadJournalItems(this, mDatabase);
    }

    @Override
    public void onItemClicked(JournalModel journalModel) {
        if (mMainView != null){
            //Handle item Clicked of the recyclerView having the position
            mMainView.showMessage(journalModel.getTitle());
            mMainView.viewDetails(journalModel.getJournalId());
        }
    }

    @Override
    public void scheduleJob(Context context) {
        this.context = context;
        FirebaseJobDispatcher dispatcher = new FirebaseJobDispatcher(new GooglePlayDriver(context));
        //creating new job and adding it with dispatcher
        Job job = createJob(dispatcher);
        dispatcher.mustSchedule(job);
    }

    @Override
    public void onDestroy() {
        mMainView = null;
        mLoadJournalsInteractor.removeObserver();
        //cancelJob(context);
    }

    @Override
    public void onFinished(List<JournalModel> items) {
        if (mMainView != null){
            mMainView.setItems(items);
            mMainView.hideProgress();
        }
    }

    private Job createJob(FirebaseJobDispatcher dispatcher){
        Job job = dispatcher.newJobBuilder()
                //persist the task across boots
                .setLifetime(Lifetime.FOREVER)
                //.setLifetime(Lifetime.UNTIL_NEXT_BOOT)
                //call this service when the criteria are met.
                .setService(ScheduledDataSyncJobService.class)
                //unique id of the task
                .setTag("SJ001")
                //don't overwrite an existing job with the same tag
                .setReplaceCurrent(false)
                // We are mentioning that the job is periodic.
                .setRecurring(true)
                // Run between 0 - 120 seconds from now.
                .setTrigger(Trigger.executionWindow(0, 30))
                // retry with exponential backoff
                .setRetryStrategy(RetryStrategy.DEFAULT_LINEAR)
                //.setRetryStrategy(RetryStrategy.DEFAULT_EXPONENTIAL)
                //Run this job only when the network is available.
                .setConstraints(Constraint.ON_ANY_NETWORK)
                .build();
        return job;
    }

    public static Job updateJob(FirebaseJobDispatcher dispatcher) {
        Job newJob = dispatcher.newJobBuilder()
                //update if any task with the given tag exists.
                .setReplaceCurrent(true)
                //Integrate the job you want to start.
                .setService(ScheduledDataSyncJobService.class)
                .setTag("SJ001")
                // Run between 30 - 60 seconds from now.
                .setTrigger(Trigger.executionWindow(30, 60))
                .build();
        return newJob;
    }

    public void cancelJob(Context context){

        FirebaseJobDispatcher dispatcher = new FirebaseJobDispatcher(new GooglePlayDriver(context));
        //Cancel all the jobs for this package
        dispatcher.cancelAll();
        // Cancel the job for this tag
        dispatcher.cancel("SJ001");

    }

}
