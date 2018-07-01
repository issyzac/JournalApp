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
package apps.issy.com.jono.presenter;

import android.os.Handler;

import apps.issy.com.jono.base.BaseDatabase;
import apps.issy.com.jono.interactor.Interactor;
import apps.issy.com.jono.view.BaseView;

/**
 * Created by issy on 30/06/2018.
 *
 * @issyzac issyzac.iz@gmail.com
 * On Project JournalApp
 */

public class AddJournalPresenterImpl implements Presenter.AddJournalPresenter, Interactor.AddJournalInteractor.onJournalAdded{

    private BaseView.AddJournalView mAddJournalView;
    private Interactor.AddJournalInteractor mAddJournalInteractor;
    private BaseDatabase mDatabase;

    public AddJournalPresenterImpl(BaseView.AddJournalView addJournalView, Interactor.AddJournalInteractor addJournalInteractor, BaseDatabase database){
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
