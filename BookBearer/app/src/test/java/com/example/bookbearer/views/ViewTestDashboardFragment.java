package com.example.bookbearer.views;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.*;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import android.content.Context;
import android.widget.Button;
import android.widget.ListView;

import androidx.fragment.app.testing.FragmentScenario;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.bookbearer.DashboardFragment;
import com.example.bookbearer.MainActivity;

import com.example.bookbearer.R;
import com.example.bookbearer.SettingsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.junit.*;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.internal.DoNotInstrument;

import androidx.test.rule.*;


@RunWith(RobolectricTestRunner.class)
@DoNotInstrument
public class ViewTestDashboardFragment {

    private Context context;
    private MainActivity mockActivity;
    private BottomNavigationView bottomNavigationView;
    private DashboardFragment dashboardFragment;

    private Button listaLettiButton;
    private Button listaDaLeggereButton;
    private ListView listview;



    @Before
    public void setup() throws ClassNotFoundException {
        context = ApplicationProvider.getApplicationContext();
        context.setTheme(androidx.appcompat.R.style.Theme_AppCompat_Light);
        bottomNavigationView = new BottomNavigationView(RuntimeEnvironment.application);
        bottomNavigationView.inflateMenu(R.menu.bottom_menu);
        dashboardFragment = new DashboardFragment();
        mockActivity = Robolectric.buildActivity(MainActivity.class).create().start().get();
        listaLettiButton = mockActivity.findViewById(R.id.lettiHomeButton);
        listaDaLeggereButton = mockActivity.findViewById(R.id.daleggereHomeButton);
        listview = mockActivity.findViewById(R.id.listaHome);
    }

    @Test
    public void testContext(){
        assertNotNull(context);
    }

    @Test
    public void testMyFragmentIsDisplayed() {
        assertNotNull(mockActivity);
        assertNotNull(bottomNavigationView);
        assertEquals(bottomNavigationView.getSelectedItemId(), R.id.dashboardFragment);
    }

    @Test
    public void testViewsNotNull(){
        assertNotNull(listaDaLeggereButton);
        assertNotNull(listaLettiButton);
        assertNotNull(listview);
    }

}

