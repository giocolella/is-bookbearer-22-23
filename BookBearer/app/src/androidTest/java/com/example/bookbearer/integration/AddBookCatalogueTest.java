package com.example.bookbearer.integration;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.annotation.NonNull;
import androidx.test.espresso.ViewInteraction;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import com.example.bookbearer.R;
import com.example.bookbearer.StartActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;
import java.util.Map;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class AddBookCatalogueTest {

    private Map<String,Object> m = new HashMap<>();

    @Rule
    public ActivityScenarioRule<StartActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(StartActivity.class);

    @After
    public void cleanDatabase() throws InterruptedException {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("Books").document("123-4567899999").delete();
        Thread.sleep(5000);
    }

    @Test
    public void addBookTest() throws InterruptedException {
        ViewInteraction materialButton = onView(allOf(withId(R.id.loginButton), withText("Login")));
        materialButton.perform(click());

        ViewInteraction appCompatEditText = onView(allOf(withId(R.id.emailText)));
        appCompatEditText.perform(replaceText("carmela@gmail.com"), closeSoftKeyboard());

        ViewInteraction appCompatEditText2 = onView(allOf(withId(R.id.passwordText)));
        appCompatEditText2.perform(replaceText("carmelacar"), closeSoftKeyboard());

        ViewInteraction materialButton2 = onView(allOf(withId(R.id.log), withText("Login")));
        materialButton2.perform(click());

        Thread.sleep(5000);

        ViewInteraction materialButton3 = onView(allOf(withId(R.id.addBook), withText("Aggiungi libro")));
        materialButton3.perform(click());

        Thread.sleep(5000);

        ViewInteraction appCompatEditText3 = onView(allOf(withId(R.id.catalogueISBN)));
        appCompatEditText3.perform(replaceText("123-4567899999"), closeSoftKeyboard());

        ViewInteraction appCompatEditText4 = onView(allOf(withId(R.id.catalogueImg)));
        appCompatEditText4.perform(replaceText("linkImg"), closeSoftKeyboard());

        ViewInteraction appCompatEditText5 = onView(allOf(withId(R.id.catalogueTitle)));
        appCompatEditText5.perform(replaceText("libro ricette"), closeSoftKeyboard());

        ViewInteraction appCompatEditText6 = onView(allOf(withId(R.id.catalogueAuthor)));
        appCompatEditText6.perform(replaceText("Matteo Rossi"), closeSoftKeyboard());

        ViewInteraction appCompatEditText7 = onView(allOf(withId(R.id.catalogueGenre)));
        appCompatEditText7.perform(replaceText("cucina"), closeSoftKeyboard());

        ViewInteraction appCompatEditText8 = onView(allOf(withId(R.id.catalogueDate)));
        appCompatEditText8.perform(replaceText("2022"), closeSoftKeyboard());

        ViewInteraction appCompatEditText9 = onView(allOf(withId(R.id.cataloguePages)));
        appCompatEditText9.perform(replaceText("190"), closeSoftKeyboard());

        ViewInteraction materialButton4 = onView(allOf(withId(R.id.catalogueOk), withText("OK")));
        materialButton4.perform(click());

        Thread.sleep(5000);

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("Books").document("123-4567899999").get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        m = document.getData();
                        assertEquals("123-4567899999",m.get("ISBN").toString());
                        assertEquals("libro ricette",m.get("titolo").toString());
                        assertEquals("Matteo Rossi",m.get("autore").toString());
                        assertEquals("linkImg",m.get("bookImg").toString());
                        assertEquals("cucina",m.get("genere").toString());
                        assertEquals("2022",m.get("annoUscita").toString());
                        assertEquals("190",m.get("NumPagine").toString());
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

}
