package br.ufrn.imd.campusteca.dao;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import br.ufrn.imd.campusteca.api.ReviewRequest;
import br.ufrn.imd.campusteca.model.Review;

/**
 * Created by Pablo Gabriel on 29/11/2015.
 */
public class ReviewDAO {
    public void sendReview(int idBook, String username, float rating, String description) {
        try {
            new ReviewRequest("/create/" + idBook + "/" + username + "/" + (int) rating + "/" + URLEncoder.encode(description, "utf-8"), "POST", sendReviewKeys()).execute().get();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public float getCurrentRating(int idBook) {
        float rating = 0;
        try {
            List<Map<String, String>> result = new ReviewRequest("/current_rating/" + idBook, "GET", currentRatingkeys()).execute().get();

            Map<String, String> map = result.get(0);
            rating = Float.parseFloat(map.get("rating"));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return rating;
    }

    public Review getReview(int idBook, String username) {
        Review review = new Review();
        try {
            List<Map<String, String>> result = new ReviewRequest("/review/" + idBook + "/" + username, "GET", reviewKeys()).execute().get();

            Map<String, String> map = result.get(0);
            review.setIdBook(Integer.parseInt(map.get("idBook")));
            review.setUsername(map.get("username"));
            review.setRating(Float.parseFloat(map.get("rating")));
            review.setDescription(map.get("description"));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return review;
    }

    public List<Review> getReviewsByBook(int idBook) {
        List<Review> reviews = new ArrayList<Review>();
        try {
            List<Map<String, String>> result = new ReviewRequest("/book_reviews/" + idBook, "GET", reviewKeys()).execute().get();

            for (Map<String, String> map : result) {
                Review review = new Review();
                review.setIdBook(Integer.parseInt(map.get("idBook")));
                review.setUsername(map.get("username"));
                review.setRating(Float.parseFloat(map.get("rating")));
                review.setDescription(map.get("description"));

                reviews.add(review);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return reviews;
    }

    public List<Review> getReviewsByUser(String username) {
        List<Review> reviews = new ArrayList<Review>();
        try {
            List<Map<String, String>> result = new ReviewRequest("/book_reviews/" + username, "GET", reviewKeys()).execute().get();

            for (Map<String, String> map : result) {
                Review review = new Review();
                review.setIdBook(Integer.parseInt(map.get("idBook")));
                review.setUsername(map.get("username"));
                review.setRating(Float.parseFloat(map.get("rating")));
                review.setDescription(map.get("description"));

                reviews.add(review);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return reviews;
    }

    public static List<String> sendReviewKeys() {
        List<String> keys = new ArrayList<String>();

        keys.add("status");

        return keys;
    }

    public static List<String> currentRatingkeys() {
        List<String> keys = new ArrayList<String>();

        keys.add("rating");

        return keys;
    }

    public static List<String> reviewKeys() {
        List<String> keys = new ArrayList<String>();

        keys.add("idBook");
        keys.add("username");
        keys.add("rating");
        keys.add("description");

        return keys;
    }
}
