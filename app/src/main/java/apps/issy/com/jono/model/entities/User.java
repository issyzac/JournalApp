/*
 * Copyright (C) 2018 Isaya Zachariah Mollel - issyzac
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package apps.issy.com.jono.model.entities;
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
