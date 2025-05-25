package com.example.bookbearer.ui;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withHint;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.not;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import com.example.bookbearer.R;
import com.example.bookbearer.StartActivity;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class Prima_RegisterActivityTestfromUC_1_1to1_5 {

    private View decorView;

    @Rule
    public ActivityScenarioRule<StartActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(StartActivity.class);

    @Before
    public void setUp() {
        mActivityScenarioRule.getScenario().onActivity(new ActivityScenario.ActivityAction<StartActivity>() {
            @Override
            public void perform(StartActivity activity) {
                decorView = activity.getWindow().getDecorView();
            }
        });
    }

    @Test
    public void registerActivityTestfromUC_1_1to1_5() throws InterruptedException {
        ViewInteraction materialButton = onView(
                Matchers.allOf(ViewMatchers.withId(R.id.registerButton), withText("Register"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                4),
                        isDisplayed()));
        materialButton.perform(click());

        Thread.sleep(1000);

        onView(withId(R.id.regUsername)).check(matches(isDisplayed()));
        onView(withId(R.id.regMail)).check(matches(isDisplayed()));
        onView(withId(R.id.regPass)).check(matches(isDisplayed()));
        onView(withId(R.id.regConfPass)).check(matches(isDisplayed()));
        onView(withId(R.id.reg)).check(matches(isDisplayed()));

        onView(withId(R.id.regUsername)).check(matches(withHint("Nome utente")));
        onView(withId(R.id.regMail)).check(matches(withHint("Email")));
        onView(withId(R.id.regPass)).check(matches(withHint("Password di almeno 8 caratteri")));
        onView(withId(R.id.regConfPass)).check(matches(withHint("Conferma Password")));
        onView(withId(R.id.reg)).check(matches(withText("Registrati")));

        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.regUsername),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                2),
                        isDisplayed()));
        appCompatEditText.perform(replaceText("federico"), closeSoftKeyboard());

        ViewInteraction appCompatEditText2 = onView(
                allOf(withId(R.id.regMail),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                3),
                        isDisplayed()));
        appCompatEditText2.perform(replaceText("federicogmail.com"), closeSoftKeyboard());

        ViewInteraction materialButton2 = onView(
                allOf(withId(R.id.reg), withText("Registrati"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                6),
                        isDisplayed()));
        materialButton2.perform(click());

        Thread.sleep(1000);

        onView(withText("Potrebbero esserci dei campi vuoti")).inRoot(withDecorView(not(decorView))).check(matches(isDisplayed()));

        ViewInteraction appCompatEditText3 = onView(
                allOf(withId(R.id.regPass),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                4),
                        isDisplayed()));
        appCompatEditText3.perform(replaceText("federi"), closeSoftKeyboard());

        ViewInteraction appCompatEditText4 = onView(
                allOf(withId(R.id.regConfPass),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                5),
                        isDisplayed()));
        appCompatEditText4.perform(replaceText("feder"), closeSoftKeyboard());

        ViewInteraction materialButton3 = onView(
                allOf(withId(R.id.reg), withText("Registrati"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                6),
                        isDisplayed()));
        materialButton3.perform(click());

        Thread.sleep(1000);

        onView(withText("Email incompleta")).inRoot(withDecorView(not(decorView))).check(matches(isDisplayed()));

        ViewInteraction appCompatEditText5 = onView(
                allOf(withId(R.id.regMail), withText("federicogmail.com"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                3),
                        isDisplayed()));
        appCompatEditText5.perform(click());

        ViewInteraction appCompatEditText6 = onView(
                allOf(withId(R.id.regMail), withText("federicogmail.com"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                3),
                        isDisplayed()));
        appCompatEditText6.perform(replaceText("federico@gmail.com"));

        ViewInteraction appCompatEditText7 = onView(
                allOf(withId(R.id.regMail), withText("federico@gmail.com"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                3),
                        isDisplayed()));
        appCompatEditText7.perform(closeSoftKeyboard());

        ViewInteraction materialButton4 = onView(
                allOf(withId(R.id.reg), withText("Registrati"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                6),
                        isDisplayed()));
        materialButton4.perform(click());

        Thread.sleep(1000);

        onView(withText("Password e Conferma password non coincidono")).inRoot(withDecorView(not(decorView))).check(matches(isDisplayed()));

        ViewInteraction appCompatEditText8 = onView(
                allOf(withId(R.id.regConfPass), withText("feder"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                5),
                        isDisplayed()));
        appCompatEditText8.perform(replaceText("federi"));

        ViewInteraction appCompatEditText9 = onView(
                allOf(withId(R.id.regConfPass), withText("federi"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                5),
                        isDisplayed()));
        appCompatEditText9.perform(closeSoftKeyboard());

        ViewInteraction materialButton5 = onView(
                allOf(withId(R.id.reg), withText("Registrati"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                6),
                        isDisplayed()));
        materialButton5.perform(click());

        Thread.sleep(1000);

        onView(withText("Password troppo corta, almeno 8 caratteri")).inRoot(withDecorView(not(decorView))).check(matches(isDisplayed()));

        ViewInteraction appCompatEditText10 = onView(
                allOf(withId(R.id.regPass), withText("federi"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                4),
                        isDisplayed()));
        appCompatEditText10.perform(replaceText("federico"));

        ViewInteraction appCompatEditText11 = onView(
                allOf(withId(R.id.regPass), withText("federico"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                4),
                        isDisplayed()));
        appCompatEditText11.perform(closeSoftKeyboard());

        ViewInteraction appCompatEditText12 = onView(
                allOf(withId(R.id.regConfPass), withText("federi"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                5),
                        isDisplayed()));
        appCompatEditText12.perform(replaceText("federico"));

        ViewInteraction appCompatEditText13 = onView(
                allOf(withId(R.id.regConfPass), withText("federico"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                5),
                        isDisplayed()));
        appCompatEditText13.perform(closeSoftKeyboard());

        ViewInteraction materialButton6 = onView(
                allOf(withId(R.id.reg), withText("Registrati"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                6),
                        isDisplayed()));
        materialButton6.perform(click());

        Thread.sleep(5000);

        //onView(withText("Registrazione completata")).inRoot(withDecorView(not(decorView))).check(matches(isDisplayed()));

    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
