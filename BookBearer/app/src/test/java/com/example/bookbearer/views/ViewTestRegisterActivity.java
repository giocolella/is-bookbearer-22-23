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
import com.example.bookbearer.registration.RegisterActivity;

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
public class ViewTestRegisterActivity {

    private RegisterActivity registerActivity;
    private Context context;
    private EditText nomeUtente;
    private EditText email;
    private EditText pass;
    private EditText confPass;
    private Button regButton;

    @Before
    public void setUp(){
        context = ApplicationProvider.getApplicationContext();
        registerActivity = Robolectric.buildActivity(RegisterActivity.class).create().resume().get();
        nomeUtente = registerActivity.findViewById(R.id.regUsername);
        email = registerActivity.findViewById(R.id.regMail);
        pass = registerActivity.findViewById(R.id.regPass);
        confPass = registerActivity.findViewById(R.id.regConfPass);
        regButton = registerActivity.findViewById(R.id.reg);
    }

    @Test
    public void testContext(){
        assertNotNull(context);
    }

    @Test
    public void testActivityAndViews(){
        assertNotNull(registerActivity);
        assertNotNull(nomeUtente);
        assertNotNull(email);
        assertNotNull(pass);
        assertNotNull(confPass);
        assertNotNull(regButton);
    }

    @Test
    public void testLayoutAssociation(){
        assertEquals(R.id.regMail,email.getId());
        assertEquals(R.id.regPass,pass.getId());
        assertEquals(R.id.regUsername,nomeUtente.getId());
        assertEquals(R.id.regConfPass,confPass.getId());
        assertEquals(R.id.reg,regButton.getId());
    }

    @Test
    public void testToast(){
        registerActivity.regMessage("This is a message");
        assertEquals("This is a message", ShadowToast.getTextOfLatestToast());
    }


    @Test
    public void testPresenterInvariant(){
        assertNotNull(registerActivity.getPresenter());
    }

    @Test
    public void isLoginActivity(){
        registerActivity.goToLogin();
        Intent startedIntent = shadowOf(registerActivity).getNextStartedActivity();
        ShadowIntent shadowIntent = shadowOf(startedIntent);
        assertEquals(LoginActivity.class, shadowIntent.getIntentClass());
        assertEquals("Registrazione completata", ShadowToast.getTextOfLatestToast());
    }

}
