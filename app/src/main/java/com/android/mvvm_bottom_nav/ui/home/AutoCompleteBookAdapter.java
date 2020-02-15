package com.android.mvvm_bottom_nav.ui.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import com.android.mvvm_bottom_nav.R;
import com.android.mvvm_bottom_nav.data.Book;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;

public class AutoCompleteBookAdapter extends ArrayAdapter<Book> {

    private int itemLayout;
    private List<Book> bookList;

    private BookFilter filter = new BookFilter();

    public AutoCompleteBookAdapter(@NonNull Context context, int resource, @NonNull List<Book> bookList) {
        super(context, resource, bookList);

        this.itemLayout = resource;
        this.bookList = bookList;
    }


    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return bookList.size();
    }

    @Override
    public Book getItem(int position) {
        return bookList.get(position);
    }

    @Override
    public View getView(int position, View view, @NonNull ViewGroup parent) {

        if (view == null) {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(itemLayout, parent, false);
        }

        TextView strName = (TextView) view.findViewById(R.id.search_item);
        strName.setText(getItem(position).getTitle());

        return view;
    }

    @NonNull
    @Override
    public Filter getFilter() {
        return filter;
    }


    public class BookFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();

            if (constraint == null || constraint.length() == 0) {
                results.values = new ArrayList<String>();
                results.count = 0;
            } else {
                final String filterPattern = constraint.toString().toLowerCase().trim();

                List<Book> matchValues = bookList;

                results.values = matchValues;
                results.count = matchValues.size();
            }

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            if (results.values != null) {
                bookList = (ArrayList<Book>) results.values;
            } else {
                bookList = null;
            }
            if (results.count > 0) {
                notifyDataSetChanged();
            } else {
                notifyDataSetInvalidated();
            }
        }
    }
}
