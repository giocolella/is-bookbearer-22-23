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
import com.example.bookbearer.modpass.ModPassActivity;
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
public class ViewTestModPassActivity {

    private ModPassActivity modPassActivity;
    private Context context;
    private EditText newPass;
    private EditText oldPassword;
    private Button submit;
    private Button back;


    @Before
    public void setUp(){
        context = ApplicationProvider.getApplicationContext();
        modPassActivity = Robolectric.buildActivity(ModPassActivity.class).create().resume().get();
        newPass = modPassActivity.findViewById(R.id.newPass);
        oldPassword = modPassActivity.findViewById(R.id.oldP);
        submit = modPassActivity.findViewById(R.id.okP);
        back = modPassActivity.findViewById(R.id.annullaP);
    }

    @Test
    public void testContext(){
        assertNotNull(context);
    }

    @Test
    public void testActivityAndViews(){
        assertNotNull(modPassActivity);
        assertNotNull(newPass);
        assertNotNull(oldPassword);
        assertNotNull(submit);
        assertNotNull(back);
    }

    @Test
    public void testLayoutAssociation(){
        assertEquals(R.id.newPass,newPass.getId());
        assertEquals(R.id.oldP,oldPassword.getId());
        assertEquals(R.id.okP,submit.getId());
        assertEquals(R.id.annullaP,back.getId());
    }

    @Test
    public void testToast(){
        modPassActivity.modMessage("This is a message");
        assertEquals("This is a message", ShadowToast.getTextOfLatestToast());
    }

    @Test
    public void isActivityFinishing(){
        modPassActivity.finishActivity();
        assertTrue(modPassActivity.isFinishing());
    }

    @Test
    public void isActivityFinishingWithButton(){
        back.callOnClick();
        assertTrue(modPassActivity.isFinishing());
    }

    @Test
    public void testPresenterInvariant(){
        assertNotNull(modPassActivity.getPresenter());
    }

}


