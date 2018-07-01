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

package apps.issy.com.jono.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import apps.issy.com.jono.R;
import apps.issy.com.jono.model.entities.JournalModel;

/**
 * Created by issy on 30/06/2018.
 *
 * @issyzac issyzac.iz@gmail.com
 * On Project JournalApp
 */

public class JournalRecyclerAdapter extends RecyclerView.Adapter<JournalRecyclerAdapter.ViewHolder>{

    private List<JournalModel> mItems = new ArrayList<>();

    public JournalRecyclerAdapter(List<JournalModel> items){
        this.mItems = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.journal_list_item, parent, false);
        return new JournalRecyclerAdapter.ViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
           holder.bindView(mItems.get(position));
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public JournalModel getJournalAtPosition(int position){
        if (mItems.size() > 0){
            return mItems.get(position);
        }
        else {
            return null;
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView mTitle;
        TextView mContent;
        TextView mDate;

        public ViewHolder(View itemView) {
            super(itemView);

            mTitle = itemView.findViewById(R.id.tv_journal_title);
            mContent = itemView.findViewById(R.id.tv_journal_content_summary);
            mDate = itemView.findViewById(R.id.tv_journal_date);

        }

        void bindView(JournalModel item){
            //Bind the views to the item
            mTitle.setText(item.getTitle() == null? " ": item.getTitle());
            mContent.setText(item.getJournalContents() == null? " " : item.getJournalContents());

        }

    }

}
