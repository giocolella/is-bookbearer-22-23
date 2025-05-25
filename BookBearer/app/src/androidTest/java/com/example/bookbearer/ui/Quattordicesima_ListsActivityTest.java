package com.example.bookbearer.ui;


import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.pressImeActionButton;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.DataInteraction;
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
public class Quattordicesima_ListsActivityTest {

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
    public void listsActivityTest() throws InterruptedException {
        ViewInteraction materialButton = onView(
                Matchers.allOf(ViewMatchers.withId(R.id.loginButton), withText("Login"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                3),
                        isDisplayed()));
        materialButton.perform(click());

        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.emailText),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                2),
                        isDisplayed()));
        appCompatEditText.perform(replaceText("federico@gmail.com"), closeSoftKeyboard());

        ViewInteraction appCompatEditText2 = onView(
                allOf(withId(R.id.passwordText),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                3),
                        isDisplayed()));
        appCompatEditText2.perform(replaceText("federico"), closeSoftKeyboard());

        ViewInteraction materialButton2 = onView(
                allOf(withId(R.id.log), withText("Login"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                4),
                        isDisplayed()));
        materialButton2.perform(click());

        Thread.sleep(5000);

        ViewInteraction actionMenuItemView = onView(
                allOf(withId(R.id.action_search), withContentDescription("Ricerca"),
                        childAtPosition(
                                childAtPosition(
                                        withId(com.bumptech.glide.R.id.action_bar),
                                        1),
                                0),
                        isDisplayed()));
        actionMenuItemView.perform(click());

        ViewInteraction imageView = onView(
                allOf(withClassName(is("android.widget.ImageView")), withContentDescription("Search"),
                        childAtPosition(
                                allOf(withClassName(is("android.widget.LinearLayout")),
                                        childAtPosition(
                                                withId(R.id.action_search),
                                                0)),
                                1),
                        isDisplayed()));
        imageView.perform(click());

        ViewInteraction searchAutoComplete = onView(
                allOf(withClassName(is("android.widget.SearchView$SearchAutoComplete")),
                        childAtPosition(
                                allOf(withClassName(is("android.widget.LinearLayout")),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                1)),
                                0),
                        isDisplayed()));
        searchAutoComplete.perform(replaceText("il ritorno del re"), closeSoftKeyboard());

        ViewInteraction searchAutoComplete2 = onView(
                allOf(withClassName(is("android.widget.SearchView$SearchAutoComplete")), withText("il ritorno del re"),
                        childAtPosition(
                                allOf(withClassName(is("android.widget.LinearLayout")),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                1)),
                                0),
                        isDisplayed()));
        searchAutoComplete2.perform(pressImeActionButton());

        Thread.sleep(5000);

        DataInteraction relativeLayout = onData(anything())
                .inAdapterView(allOf(withId(R.id.searchList),
                        childAtPosition(
                                withClassName(is("android.widget.RelativeLayout")),
                                0)))
                .atPosition(0);
        relativeLayout.perform(click());

        Thread.sleep(5000);

        ViewInteraction materialButton3 = onView(
                allOf(withId(R.id.addToTheToBeReadList), withText("+"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                6),
                        isDisplayed()));
        materialButton3.perform(click());

        Thread.sleep(1000);

        onView(withText("Libro aggiunto alla lista dei libri da leggere")).inRoot(withDecorView(not(decorView))).check(matches(isDisplayed()));

        pressBack();

        pressBack();

        ViewInteraction materialButton4 = onView(
                allOf(withId(R.id.daleggereHomeButton), withText("Lista da leggere"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.fragment),
                                        0),
                                1),
                        isDisplayed()));
        materialButton4.perform(click());

        Thread.sleep(2000);

        ViewInteraction textView = onView(
                allOf(withId(R.id.tobereadlistTitle), withText("il ritorno del re"), isDisplayed()));
        textView.check(matches(withText("il ritorno del re")));

        ViewInteraction textView2 = onView(
                allOf(withId(R.id.tobereadlistAuthor), withText("J. R. R. Tolkien"), isDisplayed()));
        textView2.check(matches(withText("J. R. R. Tolkien")));

        ViewInteraction imageView2 = onView(
                allOf(withId(R.id.tobereadlistImage), isDisplayed()));
        imageView2.check(matches(isDisplayed()));

        ViewInteraction button = onView(
                allOf(withId(R.id.addToRead), withText("+"), isDisplayed()));
        button.check(matches(isDisplayed()));

        ViewInteraction button2 = onView(
                allOf(withId(R.id.deleteToBeRead), withText("-"), isDisplayed()));
        button2.check(matches(isDisplayed()));

        ViewInteraction materialButton5 = onView(
                allOf(withId(R.id.addToRead), withText("+"),
                        childAtPosition(
                                withParent(withId(R.id.listaHome)),
                                3),
                        isDisplayed()));
        materialButton5.perform(click());

        Thread.sleep(1000);

        ViewInteraction materialButton6 = onView(
                allOf(withId(R.id.lettiHomeButton), withText("Lista letti"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.fragment),
                                        0),
                                0),
                        isDisplayed()));
        materialButton6.perform(click());

        Thread.sleep(2000);

        ViewInteraction textView3 = onView(
                allOf(withId(R.id.readlistTitle), withText("il ritorno del re"), isDisplayed()));
        textView3.check(matches(withText("il ritorno del re")));

        ViewInteraction textView4 = onView(
                allOf(withId(R.id.readlistAuthor), withText("J. R. R. Tolkien"), isDisplayed()));
        textView4.check(matches(withText("J. R. R. Tolkien")));

        ViewInteraction imageView3 = onView(
                allOf(withId(R.id.readlistImage), isDisplayed()));
        imageView3.check(matches(isDisplayed()));

        ViewInteraction button3 = onView(
                allOf(withId(R.id.deleteRead), withText("-"), isDisplayed()));
        button3.check(matches(isDisplayed()));

        ViewInteraction materialButton7 = onView(
                allOf(withId(R.id.deleteRead), withText("-"),
                        childAtPosition(
                                withParent(withId(R.id.listaHome)),
                                3),
                        isDisplayed()));
        materialButton7.perform(click());

        Thread.sleep(1000);
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
