package com.android.mvvm_bottom_nav.ui.dashboard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.mvvm_bottom_nav.Card;
import com.android.mvvm_bottom_nav.R;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class CardListAdapter extends RecyclerView.Adapter<CardListAdapter.CardViewHolder> {

    private List<Card> cardList = new ArrayList<>();
    private Context context;
    private final OnItemClickListener listener;

    public CardListAdapter(Context context, OnItemClickListener listener) {
        this.context = context;
        this.listener = listener;
    }

    @Override
    public CardViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        View trackView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_card, parent, false);

        return new CardViewHolder(trackView);
    }

    @Override
    public void onBindViewHolder(CardViewHolder holder, final int position) {
        // Get the data model based on position
        final Card card = cardList.get(position);

        holder.title.setText(card.getTitle());
        holder.bind(card, listener);
        holder.itemView.setOnClickListener(v -> listener.onItemClick(card));
    }

    @Override
    public int getItemCount() {
        return cardList.size();
    }

    public Card getItemAt(int position) {
        return cardList.get(position);
    }

    public List<Card> getData() {
        return cardList;
    }

    public void setData(List<Card> cardList) {
        this.cardList = cardList;
        notifyDataSetChanged();
    }

    public void clearAdapter() {
        cardList.clear();
        notifyDataSetChanged();
    }

    /**
     * ViewHolder class
     */
    public class CardViewHolder extends RecyclerView.ViewHolder {

        private TextView title;

        public CardViewHolder(View itemView) {
            super(itemView);

            title = (TextView) itemView.findViewById(R.id.title);
        }

        public void bind(final Card item, final OnItemClickListener listener) {
            return;
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Card item);
    }

}
