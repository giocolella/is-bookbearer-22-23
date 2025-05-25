package com.example.bookbearer.integration;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.pressImeActionButton;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withHint;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.*;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.annotation.NonNull;
import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.DataInteraction;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import com.example.bookbearer.R;
import com.example.bookbearer.StartActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.hamcrest.TypeSafeMatcher;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;
import java.util.Map;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class AddReviewTest {

    private Map<String,Object> m = new HashMap<>();

    @Rule
    public ActivityScenarioRule<StartActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(StartActivity.class);


    @After
    public void cleanDatabase() throws InterruptedException {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        db.collection("Books").document("978-8830102729").collection("Reviews").document(user.getUid()).delete();
        Thread.sleep(5000);
    }


    @Test
    public void addReviewActivityTest() throws InterruptedException {
        ViewInteraction materialButton = onView(Matchers.allOf(ViewMatchers.withId(R.id.loginButton), withText("Login")));
        materialButton.perform(click());

        ViewInteraction appCompatEditText = onView(allOf(withId(R.id.emailText)));
        appCompatEditText.perform(replaceText("leonardo@gmail.com"), closeSoftKeyboard());

        ViewInteraction appCompatEditText2 = onView(allOf(withId(R.id.passwordText)));
        appCompatEditText2.perform(replaceText("leonardo"), closeSoftKeyboard());

        ViewInteraction materialButton2 = onView(allOf(withId(R.id.log), withText("Login")));
        materialButton2.perform(click());

        Thread.sleep(5000);

        ViewInteraction actionMenuItemView = onView(allOf(withId(R.id.action_search), withContentDescription("Ricerca")));
        actionMenuItemView.perform(click());

        ViewInteraction imageView = onView(allOf(withClassName(is("android.widget.ImageView")), withContentDescription("Search")));
        imageView.perform(click());

        ViewInteraction searchAutoComplete = onView(allOf(withClassName(is("android.widget.SearchView$SearchAutoComplete"))));
        searchAutoComplete.perform(replaceText("il ritorno del re"), closeSoftKeyboard());

        ViewInteraction searchAutoComplete2 = onView(allOf(withClassName(is("android.widget.SearchView$SearchAutoComplete")), withText("il ritorno del re")));
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

        ViewInteraction materialButton3 = onView(allOf(withId(R.id.addReview), withText("Aggiungi Recensione")));
        materialButton3.perform(click());

        Thread.sleep(5000);

        ViewInteraction materialRadioButton = onView(allOf(withId(R.id.add5), withText("5")));
        materialRadioButton.perform(click());

        ViewInteraction appCompatEditText3 = onView(allOf(withId(R.id.addDescription)));
        appCompatEditText3.perform(replaceText("Questo è un ottimo libro"), closeSoftKeyboard());

        ViewInteraction materialButton4 = onView(allOf(withId(R.id.okReview), withText("OK")));
        materialButton4.perform(click());

        Thread.sleep(5000);

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        db.collection("Books").document("978-8830102729").collection("Reviews").document(user.getUid()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        m = document.getData();
                        assertEquals("leonardo",m.get("autore").toString());
                        assertEquals("5",m.get("punteggio").toString());
                        assertEquals("Questo è un ottimo libro",m.get("descrizione").toString());
                    } else {
                        fail();
                    }
                } else {
                    fail();
                }
            }
        });

        Thread.sleep(5000);

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