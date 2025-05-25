package com.example.bookbearer.integration;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.platform.app.InstrumentationRegistry;

import com.example.bookbearer.R;
import com.example.bookbearer.StartActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class DeleteAccountTest {

    @Rule
    public ActivityScenarioRule<StartActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(StartActivity.class);

    @Test
    public void deleteAccountTest() throws InterruptedException {
        ViewInteraction materialButton = onView(Matchers.allOf(ViewMatchers.withId(R.id.loginButton), withText("Login")));
        materialButton.perform(click());

        ViewInteraction appCompatEditText = onView(allOf(withId(R.id.emailText)));
        appCompatEditText.perform(replaceText("leonardo2@gmail.com"), closeSoftKeyboard());

        ViewInteraction appCompatEditText2 = onView(allOf(withId(R.id.passwordText)));
        appCompatEditText2.perform(replaceText("leonardo"), closeSoftKeyboard());

        ViewInteraction materialButton2 = onView(allOf(withId(R.id.log), withText("Login")));
        materialButton2.perform(click());

        Thread.sleep(5000);

        ViewInteraction bottomNavigationItemView = onView(allOf(withId(R.id.settingsFragment), withContentDescription("Settings")));
        bottomNavigationItemView.perform(click());

        Thread.sleep(5000);

        ViewInteraction materialButton3 = onView(allOf(withId(R.id.deleteAccount), withText("Elimina Account")));
        materialButton3.perform(click());

        Thread.sleep(5000);

        assertNull(FirebaseAuth.getInstance().getCurrentUser());
    }

}
