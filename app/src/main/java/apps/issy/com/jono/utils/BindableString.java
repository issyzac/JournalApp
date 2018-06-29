package apps.issy.com.jono.utils;

import android.databinding.BaseObservable;

import java.util.Objects;

/**
 * Created by issy on 28/06/2018.
 *
 * @issyzac issyzac.iz@gmail.com
 * On Project JournalApp
 */

public class BindableString extends BaseObservable {

    private String value;

    public String get() {
        return value != null ? value : "";
    }
    public void set(String value) {
        if (!Objects.equals(this.value, value)) {
            this.value = value;
            notifyChange();
        }
    }
    public boolean isEmpty() {
        return value == null || value.isEmpty();
    }

}
