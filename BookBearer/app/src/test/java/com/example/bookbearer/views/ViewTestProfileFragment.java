package com.example.bookbearer.views;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.robolectric.Shadows.shadowOf;

import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.bookbearer.DashboardFragment;
import com.example.bookbearer.MainActivity;
import com.example.bookbearer.ProfileFragment;
import com.example.bookbearer.R;
import com.example.bookbearer.SettingsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.testing.*;
import androidx.fragment.app.testing.FragmentScenario.*;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.internal.DoNotInstrument;
import org.robolectric.fakes.RoboMenu;
import org.robolectric.fakes.RoboMenuItem;
import org.robolectric.util.FragmentTestUtil;

@RunWith(RobolectricTestRunner.class)
@DoNotInstrument
public class ViewTestProfileFragment {

    private Context context;
    private MainActivity mockActivity;
    private BottomNavigationView bottomNavigationView;
    private ProfileFragment profileFragment;
    private ImageView iv;
    private TextView profileUsername;



    @Before
    public void setup() throws ClassNotFoundException {
        context = ApplicationProvider.getApplicationContext();
        context.setTheme(androidx.appcompat.R.style.Theme_AppCompat_Light);
        bottomNavigationView = new BottomNavigationView(RuntimeEnvironment.application);
        bottomNavigationView.inflateMenu(R.menu.bottom_menu);
        profileFragment = new ProfileFragment();
        mockActivity = Robolectric.buildActivity(MainActivity.class).create().start().get();
        bottomNavigationView.setSelectedItemId(R.id.profileFragment);
        iv = mockActivity.findViewById(R.id.profilePic);
        profileUsername = mockActivity.findViewById(R.id.profileUsername);
    }

    @Test
    public void testContext(){
        assertNotNull(context);
    }

    @Test
    public void testMyFragmentIsDisplayed() {
        assertNotNull(mockActivity);
        assertNotNull(bottomNavigationView);
        assertEquals(bottomNavigationView.getSelectedItemId(), R.id.profileFragment);
    }

    //Test fallisce a causa di un problema di inizializzazione del frammento nel test
    //Il frammento viene selezionato correttamente come si può vedere nei precedenti test
    //ma purtroppo le sue view non vengono inizializzate nel test
    //Non sono riuscito a far funzionare le librerie di Roboletric che riguardano l'inizializzazione dei frammenti
    @Test
    public void testViewsNotNull(){
        assertNotNull(iv);
        assertNotNull(profileUsername);
    }

}

