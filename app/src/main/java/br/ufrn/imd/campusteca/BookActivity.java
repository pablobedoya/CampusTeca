package br.ufrn.imd.campusteca;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;

import br.ufrn.imd.campusteca.model.Book;

public class BookActivity extends AppCompatActivity {

    private Book book = null;
    private TextView bookTitleTextView;
    private TextView authorBookTextView;
    private TextView editionTextView;
    private TextView yearTextView;
    private RatingBar bookIndicatorRatingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getIntent().hasExtra("EXTRA_BOOK")) {
            book = (Book) getIntent().getSerializableExtra("EXTRA_BOOK");

            bookTitleTextView = (TextView) findViewById(R.id.bookTitleTextView);
            bookTitleTextView.setText(book.getTitle());

            authorBookTextView = (TextView) findViewById(R.id.authorBookTextView);
            authorBookTextView.setText(book.getAuthor());

            editionTextView = (TextView) findViewById(R.id.editionTextView);
            editionTextView.setText(book.getEdition());

            yearTextView = (TextView) findViewById(R.id.yearTextView);
            yearTextView.setText(book.getTitle());
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BookActivity.this, CreateReviewActivity.class);
                intent.putExtra("EXTRA_BOOK", book);
                startActivity(intent);
            }
        });
    }

}
