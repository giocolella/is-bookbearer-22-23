package com.example.bookbearer.integration;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

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
public class RegistrationAndLoginTest {

    @Rule
    public ActivityScenarioRule<StartActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(StartActivity.class);

    @Test
    public void isUserRegistered() throws InterruptedException {
        ViewInteraction materialButton = onView(Matchers.allOf(ViewMatchers.withId(R.id.registerButton), withText("Register")));
        materialButton.perform(click());

        Thread.sleep(1000);

        ViewInteraction appCompatEditText = onView(allOf(withId(R.id.regUsername)));
        appCompatEditText.perform(replaceText("leonardo"), closeSoftKeyboard());

        ViewInteraction appCompatEditText2 = onView(allOf(withId(R.id.regMail)));
        appCompatEditText2.perform(replaceText("leonardo@gmail.com"), closeSoftKeyboard());

        ViewInteraction appCompatEditText3 = onView(allOf(withId(R.id.regPass)));
        appCompatEditText3.perform(replaceText("leonardo"), closeSoftKeyboard());

        ViewInteraction appCompatEditText4 = onView(allOf(withId(R.id.regConfPass)));
        appCompatEditText4.perform(replaceText("leonardo"), closeSoftKeyboard());

        ViewInteraction materialButton2 = onView(allOf(withId(R.id.reg), withText("Registrati")));
        materialButton2.perform(click());

        Thread.sleep(5000);

        ViewInteraction appCompatEditText5 = onView(allOf(withId(R.id.emailText)));
        appCompatEditText5.perform(replaceText("leonardo@gmail.com"), closeSoftKeyboard());

        ViewInteraction appCompatEditText6 = onView(allOf(withId(R.id.passwordText)));
        appCompatEditText6.perform(replaceText("leonardo"), closeSoftKeyboard());

        ViewInteraction materialButton3 = onView(allOf(withId(R.id.log), withText("Login")));
        materialButton3.perform(click());

        Thread.sleep(5000);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        Thread.sleep(1000);
        assertNotNull(user);
        assertNotNull(user.getUid());
        assertEquals("leonardo",user.getDisplayName());
        assertEquals("leonardo@gmail.com",user.getEmail());
    }

}
