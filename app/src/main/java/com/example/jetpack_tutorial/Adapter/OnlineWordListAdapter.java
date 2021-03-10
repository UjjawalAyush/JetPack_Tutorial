package com.example.jetpack_tutorial.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jetpack_tutorial.Data.OnlineWord;
import com.example.jetpack_tutorial.R;

import java.util.List;

public class OnlineWordListAdapter extends RecyclerView.Adapter<OnlineWordListAdapter.OnlineWordViewHolder> {
    private final LayoutInflater mInflater;
    private List<OnlineWord> mWords;
    public OnlineWordListAdapter(Context context) { mInflater = LayoutInflater.from(context); }
    @Override
    public OnlineWordViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recycler_view_items, parent, false);
        return new OnlineWordViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(OnlineWordViewHolder holder, int position) {
        if (mWords != null) {
            OnlineWord current = mWords.get(position);
            holder.wordItemView.setText(current.getWord());
        } else {
            holder.wordItemView.setText("No Word");
        }
    }
    public void setWords(List<OnlineWord> words){
        mWords = words;
        notifyDataSetChanged();
    }
    @Override
    public int getItemCount() {
        if (mWords != null)
            return mWords.size();
        else return 0;
    }
    class OnlineWordViewHolder extends RecyclerView.ViewHolder {
        private final TextView wordItemView;
        private OnlineWordViewHolder(View itemView) {
            super(itemView);
            wordItemView = itemView.findViewById(R.id.textView);
        }
    }
}
