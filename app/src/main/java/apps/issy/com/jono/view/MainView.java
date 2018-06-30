package apps.issy.com.jono.view;

import java.util.List;

import apps.issy.com.jono.model.entities.JournalModel;

/**
 * Created by issy on 30/06/2018.
 *
 * @issyzac issyzac.iz@gmail.com
 * On Project JournalApp
 */

public interface MainView {

    void showProgress();

    void hideProgress();

    void setItems(List<JournalModel> items);

    void showMessage(String message);

}
