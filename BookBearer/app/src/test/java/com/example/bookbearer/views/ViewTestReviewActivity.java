package com.example.bookbearer.views;

import static org.junit.Assert.*;
import static org.robolectric.Shadows.*;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.bookbearer.CatalogueActivity;
import com.example.bookbearer.R;
import com.example.bookbearer.addreview.AddReviewActivity;
import com.example.bookbearer.beans.Review;
import com.example.bookbearer.bookinfo.BookInfoActivity;
import com.example.bookbearer.catalogue.AddBookCatalogueActivity;
import com.example.bookbearer.modmail.ModMailActivity;
import com.example.bookbearer.reviews.ReviewActivity;
import com.example.bookbearer.reviews.ReviewListAdapter;

import androidx.test.core.app.ApplicationProvider;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.Shadows;
import org.robolectric.annotation.Config;
import org.robolectric.annotation.internal.DoNotInstrument;
import org.robolectric.shadows.ShadowActivity;
import org.robolectric.shadows.ShadowArrayAdapter;
import org.robolectric.shadows.ShadowIntent;
import org.robolectric.shadows.ShadowListView;
import org.robolectric.shadows.ShadowToast;

import java.util.ArrayList;

@RunWith(RobolectricTestRunner.class)
@DoNotInstrument
public class ViewTestReviewActivity {

    private ReviewActivity reviewActivity;
    private Context context;
    private ListView listview;
    private ArrayList<Review> arReview;
    private Review review = new Review();
    private ReviewListAdapter reviewListAdapter;



    @Before
    public void setUp(){
        arReview = new ArrayList<Review>();
        context = ApplicationProvider.getApplicationContext();
        reviewActivity = Robolectric.buildActivity(ReviewActivity.class).create().resume().get();
        listview = reviewActivity.findViewById(R.id.listReviews);
    }

    @Test
    public void testContext(){
        assertNotNull(context);
    }

    @Test
    public void testActivityAndViews(){
        assertNotNull(reviewActivity);
        assertNotNull(listview);
    }

    @Test
    public void testLayoutAssociation(){
        assertEquals(R.id.listReviews,listview.getId());
    }

    @Test
    public void testToast(){
        reviewActivity.reviewMessage("This is a message");
        assertEquals("This is a message", ShadowToast.getTextOfLatestToast());
    }

    //Testa anche ReviewListAdapter getView(...)
    @Test
    public void isResultUsed(){
        review.setAutore("Elena");
        review.setDescrizione("Ottimo libro");
        review.setPunteggio("5");
        arReview.add(review);
        reviewListAdapter = new ReviewListAdapter(context,arReview);
        listview.setAdapter(reviewListAdapter);
        ShadowListView shadowListView = Shadows.shadowOf(listview);
        shadowListView.populateItems();
        assertEquals( "Elena", ((Review) listview.getAdapter().getItem(0)).getAutore());
        assertEquals( "Ottimo libro", ((Review) listview.getAdapter().getItem(0)).getDescrizione());
        assertEquals( "5", ((Review) listview.getAdapter().getItem(0)).getPunteggio());
    }

    @Test
    public void testPresenterInvariant(){
        assertNotNull(reviewActivity.getPresenter());
    }

}

