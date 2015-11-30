package br.ufrn.imd.campusteca.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.List;

import br.ufrn.imd.campusteca.R;
import br.ufrn.imd.campusteca.model.Review;

/**
 * Created by Pablo Gabriel on 29/11/2015.
 */
public class ReviewAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    private List<Review> reviews;

    public ReviewAdapter(Context context, List<Review> reviews) {
        this.mInflater = LayoutInflater.from(context);
        this.reviews = reviews;
    }

    public int getCount() {
        return reviews.size();
    }

    public Review getItem(int position) {
        return reviews.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View view, ViewGroup parent) {
        Review review = reviews.get(position);

        view = mInflater.inflate(R.layout.review_list_view, null);

        ((TextView) view.findViewById(R.id.reviewUsernameTextViewListView)).setText(review.getUsername());
        ((RatingBar) view.findViewById(R.id.reviewRatingBarListView)).setRating(review.getRating());
        ((TextView) view.findViewById(R.id.reviewDescriptionTextViewListView)).setText(review.getDescription());

        return view;
    }
}
