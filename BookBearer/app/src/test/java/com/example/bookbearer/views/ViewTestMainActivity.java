package com.example.bookbearer.views;

import static org.junit.Assert.*;
import static org.robolectric.Shadows.*;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.MenuItem;
import android.widget.Button;

import com.example.bookbearer.MainActivity;
import com.example.bookbearer.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.test.core.app.ApplicationProvider;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.Shadows;
import org.robolectric.annotation.Config;
import org.robolectric.annotation.internal.DoNotInstrument;
import org.robolectric.fakes.RoboMenuItem;
import org.robolectric.shadows.ShadowActivity;
import org.robolectric.shadows.ShadowIntent;

@RunWith(RobolectricTestRunner.class)
@DoNotInstrument
public class ViewTestMainActivity {

    private MainActivity mainActivity;
    private Context context;
    private BottomNavigationView bottomNavigationView;

    @Before
    public void setUp(){
        context = ApplicationProvider.getApplicationContext();
        mainActivity = Robolectric.buildActivity(MainActivity.class).create().resume().get();
        bottomNavigationView = mainActivity.findViewById(R.id.bottomNavigationView);
    }

    @Test
    public void testContext(){
        assertNotNull(context);
    }

    @Test
    public void testActivityAndViews(){
        assertNotNull(mainActivity);
        assertNotNull(bottomNavigationView);
    }

    @Test
    public void testLayoutAssociation(){
        assertEquals(R.id.bottomNavigationView,bottomNavigationView.getId());
    }



}
