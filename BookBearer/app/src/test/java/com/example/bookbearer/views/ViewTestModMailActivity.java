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
import android.widget.TextView;

import com.example.bookbearer.CatalogueActivity;
import com.example.bookbearer.R;
import com.example.bookbearer.addreview.AddReviewActivity;
import com.example.bookbearer.bookinfo.BookInfoActivity;
import com.example.bookbearer.catalogue.AddBookCatalogueActivity;
import com.example.bookbearer.modmail.ModMailActivity;
import com.example.bookbearer.reviews.ReviewActivity;

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
public class ViewTestModMailActivity {

    private ModMailActivity modMailActivity;
    private Context context;
    private EditText email;
    private EditText oldPassword;
    private Button submit;
    private Button back;


    @Before
    public void setUp(){
        context = ApplicationProvider.getApplicationContext();
        modMailActivity = Robolectric.buildActivity(ModMailActivity.class).create().resume().get();
        email = modMailActivity.findViewById(R.id.newMail);
        oldPassword = modMailActivity.findViewById(R.id.oldPassword);
        submit = modMailActivity.findViewById(R.id.ok);
        back = modMailActivity.findViewById(R.id.annulla);
    }

    @Test
    public void testContext(){
        assertNotNull(context);
    }

    @Test
    public void testActivityAndViews(){
        assertNotNull(modMailActivity);
        assertNotNull(email);
        assertNotNull(oldPassword);
        assertNotNull(submit);
        assertNotNull(back);
    }

    @Test
    public void testLayoutAssociation(){
        assertEquals(R.id.newMail,email.getId());
        assertEquals(R.id.oldPassword,oldPassword.getId());
        assertEquals(R.id.ok,submit.getId());
        assertEquals(R.id.annulla,back.getId());
    }

    @Test
    public void testToast(){
        modMailActivity.modMessage("This is a message");
        assertEquals("This is a message", ShadowToast.getTextOfLatestToast());
    }

    @Test
    public void isActivityFinishing(){
        modMailActivity.finishActivity();
        assertTrue(modMailActivity.isFinishing());
    }

    @Test
    public void isActivityFinishingWithButton(){
        back.callOnClick();
        assertTrue(modMailActivity.isFinishing());
    }

    @Test
    public void testPresenterInvariant(){
        assertNotNull(modMailActivity.getPresenter());
    }

}

