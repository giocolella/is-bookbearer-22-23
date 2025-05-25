package com.example.bookbearer.views;

import static org.junit.Assert.*;
import static org.robolectric.Shadows.*;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import com.example.bookbearer.CatalogueActivity;
import com.example.bookbearer.MainActivity;
import com.example.bookbearer.R;
import com.example.bookbearer.catalogue.AddBookCatalogueActivity;
import com.example.bookbearer.login.LoginActivity;

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
public class ViewTestLoginActivity {

    private LoginActivity loginActivity;
    private Context context;
    private EditText email;
    private EditText pass;
    private Button logButton;

    @Before
    public void setUp(){
        context = ApplicationProvider.getApplicationContext();
        loginActivity = Robolectric.buildActivity(LoginActivity.class).create().resume().get();
        email = loginActivity.findViewById(R.id.emailText);
        pass = loginActivity.findViewById(R.id.passwordText);
        logButton = loginActivity.findViewById(R.id.log);
    }

    @Test
    public void testContext(){
        assertNotNull(context);
    }

    @Test
    public void testActivityAndViews(){
        assertNotNull(loginActivity);
        assertNotNull(email);
        assertNotNull(pass);
        assertNotNull(logButton);
    }

    @Test
    public void testLayoutAssociation(){
        assertEquals(R.id.emailText,email.getId());
        assertEquals(R.id.passwordText,pass.getId());
        assertEquals(R.id.log,logButton.getId());
    }

    @Test
    public void testToast(){
        loginActivity.regMessage("This is a message");
        assertEquals("This is a message", ShadowToast.getTextOfLatestToast());
    }


    @Test
    public void testPresenterInvariant(){
        assertNotNull(loginActivity.getPresenter());
    }

    @Test
    public void isMainActivity(){
        loginActivity.StartActivity();
        Intent startedIntent = shadowOf(loginActivity).getNextStartedActivity();
        ShadowIntent shadowIntent = shadowOf(startedIntent);
        assertEquals(MainActivity.class, shadowIntent.getIntentClass());
    }

    @Test
    public void isCatalogueActivity(){
        loginActivity.startCatalogueActivity("sample@gmail.com");
        Intent startedIntent = shadowOf(loginActivity).getNextStartedActivity();
        ShadowIntent shadowIntent = shadowOf(startedIntent);
        assertEquals(CatalogueActivity.class, shadowIntent.getIntentClass());
    }


}

