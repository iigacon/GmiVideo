package com.gomind.gmivideo.vmp.view;

import com.gomind.data.entities.Review;

import java.util.List;

/**
 * Created by Duc on 9/8/16.
 */
public interface MovieReviewView extends View{
    void bindReview(List<Review> reviews);

}
