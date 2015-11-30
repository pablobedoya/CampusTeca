package br.ufrn.imd.campusteca;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import br.ufrn.imd.campusteca.adapter.ReviewAdapter;
import br.ufrn.imd.campusteca.dao.ReviewDAO;
import br.ufrn.imd.campusteca.model.Book;
import br.ufrn.imd.campusteca.model.Review;

public class ReviewActivity extends AppCompatActivity {

    private Book book = null;
    private ListView reviewsListView;
    private ReviewAdapter reviewAdapter;
    private List<Review> reviews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        book = (Book) getIntent().getSerializableExtra("EXTRA_BOOK");

        ReviewDAO dao = new ReviewDAO();
        reviews = dao.getReviewsByBook(book.getRegistry());

        reviewsListView = (ListView) findViewById(R.id.reviewsListView);
        reviewAdapter = new ReviewAdapter(ReviewActivity.this, reviews);
        reviewsListView.setAdapter(reviewAdapter);
    }

}
