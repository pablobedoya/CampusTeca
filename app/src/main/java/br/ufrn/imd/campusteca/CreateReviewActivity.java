package br.ufrn.imd.campusteca;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import br.ufrn.imd.campusteca.api.ReviewRequest;
import br.ufrn.imd.campusteca.model.Book;

public class CreateReviewActivity extends AppCompatActivity {

    private Book book = null;
    private TextView bookCreateReviewTitleTextView;
    private TextView authorCreateReviewBookTextView;
    private TextView editionCreateReviewBookTextView;
    private TextView yearCreateReviewTextView;
    private RatingBar reviewRatingBar;
    private EditText reviewDescriptionEditText;
    private Button sendReviewButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_review);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getIntent().hasExtra("EXTRA_BOOK")) {
            book = (Book) getIntent().getSerializableExtra("EXTRA_BOOK");

            bookCreateReviewTitleTextView = (TextView) findViewById(R.id.bookCreateReviewTitleTextView);
            bookCreateReviewTitleTextView.setText(book.getTitle());

            authorCreateReviewBookTextView = (TextView) findViewById(R.id.authorCreateReviewBookTextView);
            authorCreateReviewBookTextView.setText(book.getAuthor());

            editionCreateReviewBookTextView = (TextView) findViewById(R.id.editionCreateReviewBookTextView);
            editionCreateReviewBookTextView.setText(book.getEdition());

            yearCreateReviewTextView = (TextView) findViewById(R.id.yearCreateReviewTextView);
            yearCreateReviewTextView.setText(book.getTitle());
        }

        reviewRatingBar = (RatingBar) findViewById(R.id.reviewRatingBar);

        reviewDescriptionEditText = (EditText) findViewById(R.id.reviewDescriptionEditText);

        sendReviewButton = (Button) findViewById(R.id.sendReviewButton);
        sendReviewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int rating = reviewRatingBar.getNumStars();
                String description = reviewDescriptionEditText.getText().toString();
                sendReview(book.getRegistry(), 10, rating, description);

                finish();
            }
        });
    }

    private void sendReview(int idBook, int idUser, int rating, String description) {
        try {
            List<String> keys = new ArrayList<String>();
            keys.add("status");

            List<Map<String, String>> result = new ReviewRequest("/create/" + idBook + "/" + idUser + "/" + rating + "/" + URLEncoder.encode(description, "utf-8"), "GET", keys).execute().get();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
