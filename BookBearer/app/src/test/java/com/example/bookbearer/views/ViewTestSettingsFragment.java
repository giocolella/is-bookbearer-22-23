package com.example.bookbearer.views;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.robolectric.Shadows.shadowOf;

import android.content.Context;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ListView;

import com.example.bookbearer.DashboardFragment;
import com.example.bookbearer.MainActivity;
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
public class ViewTestSettingsFragment {

    private Context context;
    private MainActivity mockActivity;
    private BottomNavigationView bottomNavigationView;
    private SettingsFragment settingsFragment;
    private Button logout;
    private Button modMail;
    private Button modPass;
    private Button deleteAccount;



    @Before
    public void setup() throws ClassNotFoundException {
        context = ApplicationProvider.getApplicationContext();
        context.setTheme(androidx.appcompat.R.style.Theme_AppCompat_Light);
        bottomNavigationView = new BottomNavigationView(RuntimeEnvironment.application);
        bottomNavigationView.inflateMenu(R.menu.bottom_menu);
        settingsFragment = new SettingsFragment();
        mockActivity = Robolectric.buildActivity(MainActivity.class).create().start().get();
        bottomNavigationView.setSelectedItemId(R.id.settingsFragment);
        logout = mockActivity.findViewById(R.id.exit);
        modMail = mockActivity.findViewById(R.id.modMail);
        modPass = mockActivity.findViewById(R.id.modPass);
        deleteAccount = mockActivity.findViewById(R.id.deleteAccount);
    }

    @Test
    public void testContext(){
        assertNotNull(context);
    }

    @Test
    public void testMyFragmentIsDisplayed() {
        assertNotNull(mockActivity);
        assertNotNull(bottomNavigationView);
        assertEquals(bottomNavigationView.getSelectedItemId(), R.id.settingsFragment);
    }

    //Test fallisce a causa di un problema di inizializzazione del frammento nel test
    //Il frammento viene selezionato correttamente come si può vedere nei precedenti test
    //ma purtroppo le sue view non vengono inizializzate nel test
    //Non sono riuscito a far funzionare le librerie di Roboletric che riguardano l'inizializzazione dei frammenti
    @Test
    public void testViewsNotNull(){
        assertNotNull(logout);
        assertNotNull(modMail);
        assertNotNull(modPass);
        assertNotNull(deleteAccount);
    }

}


