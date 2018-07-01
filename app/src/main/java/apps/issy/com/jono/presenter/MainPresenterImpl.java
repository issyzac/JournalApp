package apps.issy.com.jono.presenter;

import java.util.List;

import apps.issy.com.jono.base.BaseDatabase;
import apps.issy.com.jono.interactor.Interactor;
import apps.issy.com.jono.model.entities.JournalModel;
import apps.issy.com.jono.view.BaseView;

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
    public void onDestroy() {
        mMainView = null;
        mLoadJournalsInteractor.removeObserver();
    }

    @Override
    public void onFinished(List<JournalModel> items) {
        if (mMainView != null){
            mMainView.setItems(items);
            mMainView.hideProgress();
        }
    }
}
