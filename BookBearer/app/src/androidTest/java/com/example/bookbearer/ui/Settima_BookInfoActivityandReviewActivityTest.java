package com.example.bookbearer.ui;


import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.pressImeActionButton;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.is;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

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
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class Settima_BookInfoActivityandReviewActivityTest {

    @Rule
    public ActivityScenarioRule<StartActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(StartActivity.class);

    @Test
    public void bookInfoActivityandReviewActivityTest() throws InterruptedException {
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
        searchAutoComplete2.perform(click());

        ViewInteraction searchAutoComplete3 = onView(
                allOf(withClassName(is("android.widget.SearchView$SearchAutoComplete")), withText("il ritorno del re"),
                        childAtPosition(
                                allOf(withClassName(is("android.widget.LinearLayout")),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                1)),
                                0),
                        isDisplayed()));
        searchAutoComplete3.perform(pressImeActionButton());

        Thread.sleep(5000);

        DataInteraction relativeLayout = onData(anything())
                .inAdapterView(allOf(withId(R.id.searchList),
                        childAtPosition(
                                withClassName(is("android.widget.RelativeLayout")),
                                0)))
                .atPosition(0);
        relativeLayout.perform(click());

        Thread.sleep(5000);

        ViewInteraction textView = onView(
                allOf(withId(R.id.bookTitle), withText("Titolo: il ritorno del re"), isDisplayed()));
        textView.check(matches(withText("Titolo: il ritorno del re")));

        ViewInteraction textView2 = onView(
                allOf(withId(R.id.bookAuthor), withText("Autore: J. R. R. Tolkien"), isDisplayed()));
        textView2.check(matches(withText("Autore: J. R. R. Tolkien")));

        ViewInteraction textView3 = onView(
                allOf(withId(R.id.bookDate), withText("Anno d'uscita: 1955"), isDisplayed()));
        textView3.check(matches(withText("Anno d'uscita: 1955")));

        ViewInteraction textView4 = onView(
                allOf(withId(R.id.bookGenre), withText("genere: fantasy"), isDisplayed()));
        textView4.check(matches(withText("genere: fantasy")));

        ViewInteraction textView5 = onView(
                allOf(withId(R.id.bookPages), withText("Numero di pagine: 700"), isDisplayed()));
        textView5.check(matches(withText("Numero di pagine: 700")));

        ViewInteraction textView6 = onView(
                allOf(withId(R.id.bookReviews), withText("Recensioni: "), isDisplayed()));
        textView6.check(matches(withText("Recensioni: ")));

        ViewInteraction textView7 = onView(
                allOf(withId(R.id.theBearers), withText("The bearers: "), isDisplayed()));
        textView7.check(matches(withText("The bearers: ")));

        ViewInteraction imageView2 = onView(
                allOf(withId(R.id.bookImg), isDisplayed()));
        imageView2.check(matches(isDisplayed()));

        ViewInteraction button = onView(
                allOf(withId(R.id.addToTheToBeReadList), withText("+"), isDisplayed()));
        button.check(matches(isDisplayed()));

        ViewInteraction button2 = onView(
                allOf(withId(R.id.readReviews), withText("LEGGI"), isDisplayed()));
        button2.check(matches(isDisplayed()));

        ViewInteraction button3 = onView(
                allOf(withId(R.id.addReview), withText("AGGIUNGI RECENSIONE"), isDisplayed()));
        button3.check(matches(isDisplayed()));

        ViewInteraction button4 = onView(
                allOf(withId(R.id.removeReview), withText("RIMUOVI RECENSIONE"), isDisplayed()));
        button4.check(matches(isDisplayed()));

        ViewInteraction button5 = onView(
                allOf(withId(R.id.removeReview), withText("RIMUOVI RECENSIONE"), isDisplayed()));
        button5.check(matches(isDisplayed()));

        ViewInteraction materialButton3 = onView(
                allOf(withId(R.id.readReviews), withText("Leggi"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                9),
                        isDisplayed()));
        materialButton3.perform(click());

        Thread.sleep(5000);

        ViewInteraction listView = onView(
                allOf(withId(R.id.listReviews), isDisplayed()));
        listView.check(matches(isDisplayed()));

        ViewInteraction listView2 = onView(
                allOf(withId(R.id.listReviews), isDisplayed()));
        listView2.check(matches(isDisplayed()));
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
