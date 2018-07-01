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

import java.util.Calendar;

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

public class JournalDetailsPresenterImpl implements Presenter.JournalDetailsPresenter, Interactor.JournalDetailInteractor.onJournalLoaded {

    private BaseView.JournalDetailsView mDetailsView;
    private Interactor.JournalDetailInteractor mInteractor;
    private BaseDatabase mDatabase;

    private JournalModel currentJournal;

    public JournalDetailsPresenterImpl(BaseView.JournalDetailsView journalDetailsView, Interactor.JournalDetailInteractor journalDetailInteractor,
                                       BaseDatabase database){
        this.mDetailsView = journalDetailsView;
        this.mInteractor = journalDetailInteractor;
        this.mDatabase = database;
    }

    @Override
    public void loadJournalWithId(long journaId) {
        if (mDetailsView != null){
            mDetailsView.showProgress();
        }
        mInteractor.loadJournalWithId(journaId, mDatabase, this);
    }

    @Override
    public void onLoaded(JournalModel journalModel) {
        if (mDetailsView != null){
            mDetailsView.hideProgress();
            currentJournal = journalModel;
            mDetailsView.setBinder(journalModel);
        }
    }

    @Override
    public void onDestroy() {
        mDetailsView = null;
    }

    @Override
    public void saveEdited(String title, String contents) {
        currentJournal.setTitle(title);
        currentJournal.setJournalContents(contents);
        currentJournal.setUpdatedAt(Calendar.getInstance().getTimeInMillis());
        mInteractor.saveEditedJournal(currentJournal, mDatabase, this);
    }

    @Override
    public void showEditedSuccessfully() {
        //Journal was edited successfully, exit
        mDetailsView.showEditedSuccessfully();
    }

    @Override
    public void dismissDialog() {
        mDetailsView.navigateBack();
    }

    @Override
    public void deleteJournal() {
        mInteractor.deleteJournal(currentJournal, mDatabase, this);
    }

    @Override
    public void deleted() {
        mDetailsView.showDeleted();
    }
}
