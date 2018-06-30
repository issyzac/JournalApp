package apps.issy.com.jono.interactor;

import java.util.List;

import apps.issy.com.jono.base.BaseDatabase;
import apps.issy.com.jono.model.entities.JournalModel;

/**
 * Created by issy on 30/06/2018.
 *
 * @issyzac issyzac.iz@gmail.com
 * On Project JournalApp
 */

public interface LoadJournalsInteractor {

    interface onFinishListener {
        void onFinished(List<JournalModel> items);
    }

    void loadJournalItems(onFinishListener listener, BaseDatabase database);

    void removeObserver();

}
