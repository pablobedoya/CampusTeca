package br.ufrn.imd.campusteca.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import br.ufrn.imd.campusteca.R;
import br.ufrn.imd.campusteca.model.Book;

/**
 * Created by Pablo Gabriel on 07/10/2015.
 */
public class BookAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    private List<Book> books;

    public BookAdapter(Context context, List<Book> books) {
        this.mInflater = LayoutInflater.from(context);
        this.books = books;
    }

    public int getCount() {
        return books.size();
    }

    public Book getItem(int position) {
        return books.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View view, ViewGroup parent) {
        Book book = books.get(position);

        view = mInflater.inflate(R.layout.book_list_view, null);

        ((ImageView) view.findViewById(R.id.bookImageView)).setImageResource(book.getImage());
        ((TextView) view.findViewById(R.id.bookTitleTextView)).setText(book.getTitle());
        ((TextView) view.findViewById(R.id.bookAuthorTextView)).setText(book.getAuthor());
        ((TextView) view.findViewById(R.id.bookEditionTextView)).setText(book.getEdition());
        ((TextView) view.findViewById(R.id.bookYearTextView)).setText(book.getYear());

        return view;
    }
}
