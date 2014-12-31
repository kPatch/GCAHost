package com.sleekcreative.ghost;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class StringListAdapter extends RecyclerView.Adapter<StringListAdapter.ViewHolder> {

    public ArrayList<String> data;
    Random random = new Random();

    public StringListAdapter(ArrayList<String> data) {
        this.data = data;
        setHasStableIds(true);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int ran = random.nextInt(13);
        View view;

        switch (ran){
            case 0:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_feed_v0, parent, false);
                break;
            case 1:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_feed_v1, parent, false);
                break;
            case 2:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_post_record_alone_v0, parent, false);
                break;
            case 3:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_post_record_alone_v1, parent, false);
                break;
            case 4:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_post_record_v0, parent, false);
                break;
            case 5:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_post_record_v1, parent, false);
                break;
            case 6:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_post_text_v0, parent, false);
                break;
            case 7:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_post_text_v1, parent, false);
                break;
            case 8:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_post_text_v2, parent, false);
                break;
            case 9:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_post_text_v3, parent, false);
                break;
            case 10:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_post_v0, parent, false);
                break;
            case 11:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_post_v1, parent, false);
                break;
            case 12:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_feed_v2, parent, false);
                break;
            case 13:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_feed_v3, parent, false);
                break;
            default:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_feed_v1, parent, false);
                break;
        }
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //holder.textView.setText(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void add(String string) {
        insert(string, data.size());
    }

    public void insert(String string, int position) {
        data.add(position, string);
        notifyItemInserted(position);
    }

    public void removeViaSwipe(int position) {
        data.remove(position);

        //Because item is already out of view via swipe do not animate this with default animator
        notifyDataSetChanged();
    }

    public void clear() {
        int size = data.size();
        data.clear();
        notifyItemRangeRemoved(0, size);
    }

    public void addAll(String[] strings) {
        int startIndex = data.size();
        data.addAll(startIndex, Arrays.asList(strings));
        notifyItemRangeInserted(startIndex, strings.length);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.textView);
        }
    }
}