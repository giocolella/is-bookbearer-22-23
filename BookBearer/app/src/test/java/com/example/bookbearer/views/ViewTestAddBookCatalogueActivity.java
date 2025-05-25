package com.example.bookbearer.views;

import static org.junit.Assert.*;

import android.content.Context;
import android.widget.Button;
import android.widget.EditText;


import com.example.bookbearer.R;
import com.example.bookbearer.addreview.AddReviewActivity;
import com.example.bookbearer.catalogue.AddBookCatalogueActivity;

import androidx.test.core.app.ApplicationProvider;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.internal.DoNotInstrument;
import org.robolectric.shadows.ShadowToast;

@RunWith(RobolectricTestRunner.class)
@DoNotInstrument
public class ViewTestAddBookCatalogueActivity {

    private AddBookCatalogueActivity addBookCatalogueActivity;
    private Context context;
    private EditText ISBN;
    private EditText titolo;
    private EditText autore;
    private EditText genere;
    private EditText annoUscita;
    private EditText numPagine;
    private EditText linkImg;
    private Button catalogueOk;
    private Button catalogueAnnulla;

    @Before
    public void setUp(){
        context = ApplicationProvider.getApplicationContext();
        addBookCatalogueActivity = Robolectric.buildActivity(AddBookCatalogueActivity.class).create().resume().get();
        ISBN = (EditText) addBookCatalogueActivity.findViewById(R.id.catalogueISBN);
        titolo = (EditText) addBookCatalogueActivity.findViewById(R.id.catalogueTitle);
        autore = (EditText) addBookCatalogueActivity.findViewById(R.id.catalogueAuthor);
        genere = (EditText) addBookCatalogueActivity.findViewById(R.id.catalogueGenre);
        annoUscita = (EditText) addBookCatalogueActivity.findViewById(R.id.catalogueDate);
        numPagine = (EditText) addBookCatalogueActivity.findViewById(R.id.cataloguePages);
        linkImg = (EditText) addBookCatalogueActivity.findViewById(R.id.catalogueImg);
        catalogueOk = (Button) addBookCatalogueActivity.findViewById(R.id.catalogueOk);
        catalogueAnnulla = (Button) addBookCatalogueActivity.findViewById(R.id.catalogueAnnulla);
    }

    @Test
    public void testContext(){
        assertNotNull(context);
    }

    @Test
    public void testActivityAndViews(){
        assertNotNull(addBookCatalogueActivity);
        assertNotNull(ISBN);
        assertNotNull(titolo);
        assertNotNull(autore);
        assertNotNull(genere);
        assertNotNull(annoUscita);
        assertNotNull(numPagine);
        assertNotNull(linkImg);
        assertNotNull(catalogueOk);
        assertNotNull(catalogueAnnulla);
    }

    @Test
    public void testLayoutAssociation(){
        assertEquals(R.id.catalogueISBN,ISBN.getId());
        assertEquals(R.id.catalogueTitle,titolo.getId());
        assertEquals(R.id.catalogueAuthor,autore.getId());
        assertEquals(R.id.catalogueGenre,genere.getId());
        assertEquals(R.id.catalogueDate,annoUscita.getId());
        assertEquals(R.id.cataloguePages,numPagine.getId());
        assertEquals(R.id.catalogueImg,linkImg.getId());
        assertEquals(R.id.catalogueOk,catalogueOk.getId());
        assertEquals(R.id.catalogueAnnulla,catalogueAnnulla.getId());
    }

    @Test
    public void testToast(){
        addBookCatalogueActivity.catalogueMessage("This is a message");
        assertEquals("This is a message", ShadowToast.getTextOfLatestToast());
    }

    @Test
    public void isActivityFinishing(){
        addBookCatalogueActivity.bookAdded();
        assertTrue(addBookCatalogueActivity.isFinishing());
    }


    @Test
    public void testPresenterInvariant(){
        assertNotNull(addBookCatalogueActivity.getPresenter());
    }



}
