package com.example.bookbearer.views;

import static org.junit.Assert.*;
import static org.robolectric.Shadows.*;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.MenuItem;
import android.widget.Button;

import com.example.bookbearer.CatalogueActivity;
import com.example.bookbearer.R;
import com.example.bookbearer.catalogue.AddBookCatalogueActivity;

import androidx.test.core.app.ApplicationProvider;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.Shadows;
import org.robolectric.annotation.Config;
import org.robolectric.annotation.internal.DoNotInstrument;
import org.robolectric.shadows.ShadowActivity;
import org.robolectric.shadows.ShadowIntent;

@RunWith(RobolectricTestRunner.class)
@DoNotInstrument
public class ViewTestCatalogueActivity {

    private CatalogueActivity catalogueActivity;
    private Context context;
    private Button addBook;
    private Button exitCatalogue;

    @Before
    public void setUp(){
        context = ApplicationProvider.getApplicationContext();
        catalogueActivity = Robolectric.buildActivity(CatalogueActivity.class).create().resume().get();
        addBook = (Button) catalogueActivity.findViewById(R.id.addBook);
        exitCatalogue = (Button) catalogueActivity.findViewById(R.id.exitCatalogue);
    }

    @Test
    public void testContext(){
        assertNotNull(context);
    }

    @Test
    public void testActivityAndViews(){
        assertNotNull(catalogueActivity);
        assertNotNull(addBook);
        assertNotNull(exitCatalogue);
    }

    @Test
    public void testLayoutAssociation(){
        assertEquals(R.id.addBook,addBook.getId());
        assertEquals(R.id.exitCatalogue,exitCatalogue.getId());
    }


    @Test
    public void isAddBookCatalogueActivity(){
        addBook.callOnClick();
        Intent startedIntent = shadowOf(catalogueActivity).getNextStartedActivity();
        ShadowIntent shadowIntent = shadowOf(startedIntent);
        assertEquals(AddBookCatalogueActivity.class, shadowIntent.getIntentClass());
    }

    @Test
    public void isCatalogueActivityFinishing(){
        exitCatalogue.callOnClick();
        assertTrue(catalogueActivity.isFinishing());
    }


}
