package apps.issy.com.jono.fragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.github.rahatarmanahmed.cpv.CircularProgressView;

import apps.issy.com.jono.R;
import apps.issy.com.jono.base.BaseDatabase;
import apps.issy.com.jono.base.BaseFragment;
import apps.issy.com.jono.interactor.AddJournalInteractorImpl;
import apps.issy.com.jono.presenter.AddJournalPresenterImpl;
import apps.issy.com.jono.presenter.Presenter;
import apps.issy.com.jono.view.BaseView;

/**
 * Created by issy on 30/06/2018.
 *
 * @issyzac issyzac.iz@gmail.com
 * On Project JournalApp
 */

public class AddJournalFragment extends BaseFragment implements BaseView.AddJournalView, View.OnClickListener {

    private EditText mJournalTitleEditText;
    private EditText mJournalContentsEditText;
    private CircularProgressView progressView;
    private TextView mOkTextView, mCancelTextView, mMessagesTextView;

    private Presenter.AddJournalPresenter presenter;

    public AddJournalFragment(){}

    public static AddJournalFragment newInstance(){
        return new AddJournalFragment();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        presenter = new AddJournalPresenterImpl(this, new AddJournalInteractorImpl(), BaseDatabase.getINSTANCE(this.getContext()));

        View view = LayoutInflater.from(this.getContext()).inflate(R.layout.add_jurnal_custom_view, null);
        mJournalTitleEditText = view.findViewById(R.id.journal_title);
        mJournalContentsEditText = view.findViewById(R.id.journal_content);
        progressView = view.findViewById(R.id.progress_view);
        mMessagesTextView = view.findViewById(R.id.tv_messages);
        mMessagesTextView.setVisibility(View.GONE);
        mOkTextView = view.findViewById(R.id.button_ok);
        mOkTextView.setOnClickListener(this);
        mCancelTextView = view.findViewById(R.id.button_cancel);
        mCancelTextView.setOnClickListener(this);

        return setUpDialogue(view);
    }

    private Dialog setUpDialogue(View view){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
        alertDialogBuilder.setView(view);
        return alertDialogBuilder.create();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button_ok:
                presenter.validateJournalContents(mJournalTitleEditText.getText().toString(),
                        mJournalContentsEditText.getText().toString());
                break;
            case R.id.button_cancel:
                this.dismiss();
                break;
        }
    }

    @Override
    public void showProgress() {
        mOkTextView.setVisibility(View.INVISIBLE);
        progressView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        mOkTextView.setVisibility(View.VISIBLE);
        progressView.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showAddingJournalSuccess() {
        mMessagesTextView.setText("Added Successfullt");
        mMessagesTextView.setTextColor(getResources().getColor(R.color.green_600));
    }

    @Override
    public void showAddingJournalFailed() {
        mMessagesTextView.setText("Error adding Journal");
        mMessagesTextView.setTextColor(getResources().getColor(R.color.red_600));
    }

    @Override
    public void showValidationError() {
        mMessagesTextView.setText("Add journal Content");
        mMessagesTextView.setTextColor(getResources().getColor(R.color.red_600));
    }

    @Override
    public void dismissDialogue() {
        this.dismiss();
    }

    @Override
    public void clearMesages() {
        mMessagesTextView.setText("");
        mMessagesTextView.setTextColor(getResources().getColor(R.color.card_grid_text));
    }
}
