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
package apps.issy.com.jono;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RelativeLayout;

import com.github.rahatarmanahmed.cpv.CircularProgressView;
import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

import apps.issy.com.jono.adapter.JournalRecyclerAdapter;
import apps.issy.com.jono.base.BaseActivity;
import apps.issy.com.jono.base.BaseDatabase;
import apps.issy.com.jono.fragment.AddJournalFragment;
import apps.issy.com.jono.interactor.LoadJournalsInteractorImpl;
import apps.issy.com.jono.model.entities.JournalModel;
import apps.issy.com.jono.presenter.Presenter;
import apps.issy.com.jono.presenter.MainPresenterImpl;
import apps.issy.com.jono.utils.RecyclerItemClickListener;
import apps.issy.com.jono.view.BaseView;

public class MainActivity extends BaseActivity implements BaseView.MainView, View.OnClickListener {

    private ConstraintLayout mContainer;
    private RecyclerView mRecyclerView;
    private RelativeLayout mAddEntryContainer;
    private CircularProgressView progressView;
    private Toolbar toolbar;

    private FirebaseAuth mAuth;
    private Presenter.MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpViews();

        presenter = new MainPresenterImpl(this, new LoadJournalsInteractorImpl(),
                BaseDatabase.getINSTANCE(this));

        mAuth = FirebaseAuth.getInstance();
        if (mAuth.getCurrentUser() == null){
            startActivity(new Intent(this, LoginSignUpActivity.class));
            finish();
        }

        mAddEntryContainer.setOnClickListener(this);

        mRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this, mRecyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                JournalRecyclerAdapter adapter = (JournalRecyclerAdapter) mRecyclerView.getAdapter();
                presenter.onItemClicked(adapter.getJournalAtPosition(position));
            }

            @Override
            public void onLongItemClick(View view, int position) {

            }
        }));

        presenter.scheduleJob(this);

    }

    private void setUpViews(){

        progressView = findViewById(R.id.progress_view);

        mRecyclerView = findViewById(R.id.journal_list_recycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(false);

        mContainer = findViewById(R.id.container);
        mAddEntryContainer = findViewById(R.id.rl_add_new_entry_container);

        toolbar =  findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    protected void onResume() {
        presenter.onResume();
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id){
            case  R.id.action_logout:
                mAuth.signOut();
                startActivity(new Intent(this, LoginSignUpActivity.class));
                finish();
                break;
            case R.id.action_settings:
                break;
        }
        return true;

    }

    @Override
    public void showProgress() {
        progressView.setVisibility(View.VISIBLE);
        mRecyclerView.setVisibility(View.GONE);
    }

    @Override
    public void hideProgress() {
        progressView.setVisibility(View.GONE);
        mRecyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void setItems(List<JournalModel> items) {
        //Create adapter and assign to Recycler
        mRecyclerView.setAdapter(new JournalRecyclerAdapter(items));
    }

    @Override
    public void showMessage(String message) {
        Snackbar.make(mContainer, message, Snackbar.LENGTH_LONG)
                .setAction("Dismiss", null).show();
    }

    @Override
    public void viewDetails(long journalId) {
        Intent intent = new Intent(this, JournalDetailActivity.class);
        intent.putExtra(JournalDetailActivity.JOURNAL_ID, journalId);
        startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.rl_add_new_entry_container:
                showAlertDialog();
                break;
        }
    }

    private void showAlertDialog(){
        FragmentManager fm = getSupportFragmentManager();
        AddJournalFragment alertDialog = AddJournalFragment.newInstance();
        alertDialog.show(fm, "fragment_alert");

    }

}
