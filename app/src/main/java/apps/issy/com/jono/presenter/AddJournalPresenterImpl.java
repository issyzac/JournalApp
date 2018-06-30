package apps.issy.com.jono.presenter;

import android.os.Handler;

import apps.issy.com.jono.base.BaseDatabase;
import apps.issy.com.jono.interactor.AddJournalInteractor;
import apps.issy.com.jono.view.AddJournalView;

/**
 * Created by issy on 30/06/2018.
 *
 * @issyzac issyzac.iz@gmail.com
 * On Project JournalApp
 */

public class AddJournalPresenterImpl implements AddJournalPresenter, AddJournalInteractor.onJournalAdded{

    private AddJournalView mAddJournalView;
    private AddJournalInteractor mAddJournalInteractor;
    private BaseDatabase mDatabase;

    public AddJournalPresenterImpl(AddJournalView addJournalView, AddJournalInteractor addJournalInteractor, BaseDatabase database){
        this.mAddJournalView = addJournalView;
        this.mAddJournalInteractor = addJournalInteractor;
        this.mDatabase = database;
    }

    @Override
    public void validateJournalContents(final String journalTitle, final String journalContent) {
        if (mAddJournalView != null){
            mAddJournalView.showProgress();
        }
        mAddJournalInteractor.addJournal(journalTitle, journalContent, AddJournalPresenterImpl.this, mDatabase);
    }

    @Override
    public void onDestroy() {
        mAddJournalView = null;
    }

    @Override
    public void addedSuccessfully() {
        if (mAddJournalView != null){
            mAddJournalView.hideProgress();
            mAddJournalView.showAddingJournalSuccess();
            mAddJournalView.dismissDialogue();
        }
    }

    @Override
    public void failed() {
        if (mAddJournalView != null){
            mAddJournalView.hideProgress();
            mAddJournalView.showAddingJournalFailed();
            new Handler().postDelayed(new Runnable(){
                @Override
                public void run() {
                    mAddJournalView.clearMesages();
                }
            }, 2000);
        }
    }

    @Override
    public void validationError() {
        if (mAddJournalView != null){
            mAddJournalView.hideProgress();
            mAddJournalView.showValidationError();
            new Handler().postDelayed(new Runnable(){
                @Override
                public void run() {
                    mAddJournalView.clearMesages();
                }
            }, 2000);
        }
    }
}
