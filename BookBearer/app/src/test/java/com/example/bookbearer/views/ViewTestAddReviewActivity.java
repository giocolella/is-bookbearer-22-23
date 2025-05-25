package com.example.bookbearer.views;

import static org.junit.Assert.*;
import static org.robolectric.Shadows.*;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;


import com.example.bookbearer.R;
import com.example.bookbearer.addreview.AddReviewActivity;
import com.google.firebase.FirebaseApp;

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
import org.robolectric.shadows.ShadowIntent;
import org.robolectric.shadows.ShadowToast;

@RunWith(RobolectricTestRunner.class)
@DoNotInstrument
public class ViewTestAddReviewActivity {

    private AddReviewActivity addReviewActivity;
    private Context context;
    private EditText description;
    private Button ok;
    private Button annulla;

    @Before
    public void setUp(){
        context = ApplicationProvider.getApplicationContext();
        addReviewActivity = Robolectric.buildActivity(AddReviewActivity.class).create().resume().get();
        description = (EditText) addReviewActivity.findViewById(R.id.addDescription);
        ok = (Button) addReviewActivity.findViewById(R.id.okReview);
        annulla = (Button) addReviewActivity.findViewById(R.id.annullaReview);
    }

    @Test
    public void testContext(){
        assertNotNull(context);
    }

    @Test
    public void testActivityAndViews(){
        assertNotNull(addReviewActivity);
        assertNotNull(ok);
        assertNotNull(annulla);
        assertNotNull(description);
    }

    @Test
    public void testLayoutAssociation(){
        assertEquals(R.id.addDescription,description.getId());
        assertEquals(R.id.okReview,ok.getId());
        assertEquals(R.id.annullaReview,annulla.getId());
    }

    @Test
    public void testToast(){
        addReviewActivity.addReviewMessage("This is a message");
        assertEquals("This is a message", ShadowToast.getTextOfLatestToast());
    }

    @Test
    public void testPresenterInvariant(){
        assertNotNull(addReviewActivity.getPresenter());
    }


}

