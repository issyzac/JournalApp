package apps.issy.com.jono.model.data;
import android.databinding.ObservableField;

/**
 * Created by issy on 28/06/2018.
 *
 * @issyzac issyzac.iz@gmail.com
 * On Project JournalApp
 */


public class User {

    private ObservableField<String> userName = new ObservableField<>();

    private ObservableField<String> password = new ObservableField<>();

    private ObservableField<String> passwordRepeat = new ObservableField<>();

    private ObservableField<String> email = new ObservableField<>();

    public void setUserName(ObservableField<String> userName) {
        this.userName = userName;
    }

    public void setPassword(ObservableField<String> password) {
        this.password = password;
    }

    public void setEmail(ObservableField<String> email) {
        this.email = email;
    }

    public ObservableField<String> getUserName() {
        return userName;
    }

    public ObservableField<String> getPassword() {
        return password;
    }

    public ObservableField<String> getEmail() {
        return email;
    }

}
