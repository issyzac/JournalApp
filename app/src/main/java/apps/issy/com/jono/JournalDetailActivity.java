package apps.issy.com.jono;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;

import apps.issy.com.jono.base.BaseActivity;
import apps.issy.com.jono.base.BaseDatabase;
import apps.issy.com.jono.databinding.ActivityJournalDetailsBinding;
import apps.issy.com.jono.interactor.JournalDetailInteractorImpl;
import apps.issy.com.jono.model.entities.JournalModel;
import apps.issy.com.jono.presenter.JournalDetailsPresenterImpl;
import apps.issy.com.jono.presenter.Presenter;
import apps.issy.com.jono.view.BaseView;

/**
 * Created by issy on 30/06/2018.
 *
 * @issyzac issyzac.iz@gmail.com
 * On Project JournalApp
 */

public class JournalDetailActivity extends BaseActivity implements BaseView.JournalDetailsView, View.OnClickListener {

    public static final String JOURNAL_ID = "journal_id";

    private long currentJournalId;
    private Presenter.JournalDetailsPresenter presenter;
    private ActivityJournalDetailsBinding binding;

    private RelativeLayout loaderContainer;
    private ConstraintLayout contentsContainer;
    private ImageView saveChangesButton;
    private EditText mTitleEditText, mContentsEditText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_journal_details);
        View rootView = binding.getRoot();

        Toolbar toolbar = rootView.findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mTitleEditText = rootView.findViewById(R.id.journal_title);
        mContentsEditText = rootView.findViewById(R.id.journal_content);
        loaderContainer = rootView.findViewById(R.id.loader_container);
        contentsContainer = rootView.findViewById(R.id.container);
        saveChangesButton = rootView.findViewById(R.id.image_save_changes);
        saveChangesButton.setOnClickListener(this);

        presenter = new JournalDetailsPresenterImpl(this,
                new JournalDetailInteractorImpl(), BaseDatabase.getINSTANCE(this));

        if (getIntent().getExtras() != null){
            currentJournalId = getIntent().getExtras().getLong(JOURNAL_ID);
            presenter.loadJournalWithId(currentJournalId);
        }

    }

    @Override
    public void showProgress() {
        loaderContainer.setVisibility(View.VISIBLE);
        contentsContainer.setVisibility(View.GONE);
    }

    @Override
    public void hideProgress() {
        loaderContainer.setVisibility(View.GONE);
        contentsContainer.setVisibility(View.VISIBLE);
    }

    @Override
    public void setBinder(JournalModel journalModel) {
        binding.setJournal(journalModel);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.image_save_changes:
                presenter.saveEdited(mTitleEditText.getText().toString(), mContentsEditText.getText().toString());
                break;
        }
    }

    @Override
    public void showEditedSuccessfully() {
        new MaterialDialog.Builder(this)
                .title("Edited Successfully")
                .positiveText("OK")
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(MaterialDialog dialog, DialogAction which) {
                        presenter.dismissDialog();
                        dialog.dismiss();
                    }
                })
                .show();
    }

    @Override
    public void showDeleted() {
        new MaterialDialog.Builder(this)
                .title("Deleted!")
                .positiveText("OK")
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(MaterialDialog dialog, DialogAction which) {
                        presenter.dismissDialog();
                        dialog.dismiss();
                    }
                })
                .show();
    }

    @Override
    public void navigateBack() {
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_journal_details, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_delete){
            //delete this journal
            new MaterialDialog.Builder(this)
                    .title("Delete Journal Entry!")
                    .positiveText("OK")
                    .negativeText("CANCEL")
                    .onPositive(new MaterialDialog.SingleButtonCallback() {
                        @Override
                        public void onClick(MaterialDialog dialog, DialogAction which) {
                            presenter.deleteJournal();
                            dialog.dismiss();
                        }
                    })
                    .onNegative(new MaterialDialog.SingleButtonCallback() {
                        @Override
                        public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                            dialog.dismiss();
                        }
                    })
                    .show();
        }

        return true;
    }
}
