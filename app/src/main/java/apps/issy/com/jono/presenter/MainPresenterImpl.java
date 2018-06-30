package apps.issy.com.jono.presenter;

import java.util.List;

import apps.issy.com.jono.base.BaseDatabase;
import apps.issy.com.jono.interactor.LoadJournalsInteractor;
import apps.issy.com.jono.model.entities.JournalModel;
import apps.issy.com.jono.view.MainView;

/**
 * Created by issy on 30/06/2018.
 *
 * @issyzac issyzac.iz@gmail.com
 * On Project JournalApp
 */

public class MainPresenterImpl implements MainPresenter, LoadJournalsInteractor.onFinishListener {

    private MainView mMainView;
    private LoadJournalsInteractor mLoadJournalsInteractor;
    private BaseDatabase mDatabase;

    public MainPresenterImpl(MainView mainView, LoadJournalsInteractor loadJournalsInteractor,
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
    public void onItemClicked(int position) {
        if (mMainView != null){
            //Handle item Clicked of the recyclerView having the position
            mMainView.showMessage("Position Clicked : "+position);
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
