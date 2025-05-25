package com.example.bookbearer.integration;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.junit.Assert.*;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import com.example.bookbearer.R;
import com.example.bookbearer.StartActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class ModMailTest {

    @Rule
    public ActivityScenarioRule<StartActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(StartActivity.class);

    @Test
    public void modMailTest() throws InterruptedException {
        ViewInteraction materialButton = onView(Matchers.allOf(ViewMatchers.withId(R.id.loginButton), withText("Login")));
        materialButton.perform(click());

        ViewInteraction appCompatEditText = onView(allOf(withId(R.id.emailText)));
        appCompatEditText.perform(replaceText("leonardo@gmail.com"), closeSoftKeyboard());

        ViewInteraction appCompatEditText2 = onView(allOf(withId(R.id.passwordText)));
        appCompatEditText2.perform(replaceText("leonardo"), closeSoftKeyboard());

        ViewInteraction materialButton2 = onView(allOf(withId(R.id.log), withText("Login")));
        materialButton2.perform(click());

        Thread.sleep(5000);

        ViewInteraction bottomNavigationItemView = onView(allOf(withId(R.id.settingsFragment), withContentDescription("Settings")));
        bottomNavigationItemView.perform(click());

        Thread.sleep(5000);

        ViewInteraction materialButton3 = onView(allOf(withId(R.id.modMail), withText("Modifica E-mail")));
        materialButton3.perform(click());

        Thread.sleep(5000);

        ViewInteraction appCompatEditText3 = onView(allOf(withId(R.id.oldPassword)));
        appCompatEditText3.perform(replaceText("leonardo"), closeSoftKeyboard());

        ViewInteraction appCompatEditText4 = onView(allOf(withId(R.id.newMail)));
        appCompatEditText4.perform(replaceText("leonardo2@gmail.com"), closeSoftKeyboard());

        ViewInteraction materialButton4 = onView(allOf(withId(R.id.ok), withText("OK")));
        materialButton4.perform(click());

        Thread.sleep(5000);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        assertNotNull(user);
        assertEquals("leonardo2@gmail.com",user.getEmail());

        Thread.sleep(2000);

    }
}
