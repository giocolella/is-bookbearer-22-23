package com.example.bookbearer.views;

import static org.junit.Assert.*;
import static org.robolectric.Shadows.*;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.widget.Button;

import androidx.test.core.app.ApplicationProvider;

import com.example.bookbearer.R;
import com.example.bookbearer.StartActivity;
import com.example.bookbearer.login.LoginActivity;
import com.example.bookbearer.registration.RegisterActivity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.annotation.internal.DoNotInstrument;
import org.robolectric.shadows.ShadowIntent;

@RunWith(RobolectricTestRunner.class)
//@Config (manifest="./app/src/main/AndroidManifest.xml")
@DoNotInstrument
public class ViewTestStartActivity {

    private StartActivity startActivity;
    private Button register;
    private Button login;
    private Context context;

    @Before
    public void setUp(){
        context = ApplicationProvider.getApplicationContext();
        //context.setTheme(androidx.appcompat.R.style.Theme_AppCompat_Light);
        startActivity = Robolectric.buildActivity(StartActivity.class).create().resume().get();
        register = (Button) startActivity.findViewById(R.id.registerButton);
        login = (Button) startActivity.findViewById(R.id.loginButton);
    }

    @Test
    public void testContext(){
        assertNotNull(context);
    }

    @Test
    public void testActivityAndViews(){
        assertNotNull(startActivity);
        assertNotNull(register);
        assertNotNull(login);
    }

    @Test
    public void testLayoutAssociation(){
        assertEquals(R.id.registerButton,register.getId());
        assertEquals(R.id.loginButton,login.getId());
    }

    @Test
    public void isRegisterActivity(){
        register.callOnClick();
        assertTrue(startActivity.isFinishing());
        Intent startedIntent = shadowOf(startActivity).getNextStartedActivity();
        ShadowIntent shadowIntent = shadowOf(startedIntent);
        assertEquals(RegisterActivity.class, shadowIntent.getIntentClass());
    }

    @Test
    public void isLoginActivity(){
        login.callOnClick();
        assertTrue(startActivity.isFinishing());
        Intent startedIntent = shadowOf(startActivity).getNextStartedActivity();
        ShadowIntent shadowIntent = shadowOf(startedIntent);
        assertEquals(LoginActivity.class, shadowIntent.getIntentClass());
    }

}
