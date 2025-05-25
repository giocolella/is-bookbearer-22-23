package com.example.bookbearer.views;

import static org.junit.Assert.*;
import static org.robolectric.Shadows.*;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bookbearer.CatalogueActivity;
import com.example.bookbearer.R;
import com.example.bookbearer.addreview.AddReviewActivity;
import com.example.bookbearer.bookinfo.BookInfoActivity;
import com.example.bookbearer.catalogue.AddBookCatalogueActivity;
import com.example.bookbearer.reviews.ReviewActivity;

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
import org.robolectric.shadows.ShadowToast;

import java.util.HashMap;
import java.util.Map;

@RunWith(RobolectricTestRunner.class)
@DoNotInstrument
public class ViewTestBookInfoActivity {

    private BookInfoActivity bookInfoActivity;
    private Context context;

    private ImageView iv;
    private TextView title;
    private TextView author;
    private TextView genere;
    private TextView annoUscita;
    private TextView numPagine;
    private TextView theBearers;
    private Button addToBeRead;
    private Button readReview;
    private Button addReview;
    private Button removeReview;
    private Map<String,Object> map = new HashMap<>();


    @Before
    public void setUp(){
        context = ApplicationProvider.getApplicationContext();
        bookInfoActivity = Robolectric.buildActivity(BookInfoActivity.class).create().resume().get();
        iv = (ImageView) bookInfoActivity.findViewById(R.id.bookImg);
        title = (TextView) bookInfoActivity.findViewById(R.id.bookTitle);
        author = (TextView) bookInfoActivity.findViewById(R.id.bookAuthor);
        genere = (TextView) bookInfoActivity.findViewById(R.id.bookGenre);
        annoUscita = (TextView) bookInfoActivity.findViewById(R.id.bookDate);
        numPagine = (TextView) bookInfoActivity.findViewById(R.id.bookPages);
        theBearers = (TextView) bookInfoActivity.findViewById(R.id.theBearers);
        addToBeRead = (Button) bookInfoActivity.findViewById(R.id.addToTheToBeReadList);
        readReview = (Button) bookInfoActivity.findViewById(R.id.readReviews);
        addReview = (Button) bookInfoActivity.findViewById(R.id.addReview);
        removeReview = (Button) bookInfoActivity.findViewById(R.id.removeReview);
    }

    @Test
    public void testContext(){
        assertNotNull(context);
    }

    @Test
    public void testActivityAndViews(){
        assertNotNull(bookInfoActivity);
        assertNotNull(iv);
        assertNotNull(title);
        assertNotNull(author);
        assertNotNull(genere);
        assertNotNull(annoUscita);
        assertNotNull(numPagine);
        assertNotNull(theBearers);
        assertNotNull(addToBeRead);
        assertNotNull(readReview);
        assertNotNull(addReview);
        assertNotNull(removeReview);
    }

    @Test
    public void testLayoutAssociation(){
        assertEquals(R.id.bookImg,iv.getId());
        assertEquals(R.id.bookTitle,title.getId());
        assertEquals(R.id.bookAuthor,author.getId());
        assertEquals(R.id.bookGenre,genere.getId());
        assertEquals(R.id.bookDate,annoUscita.getId());
        assertEquals(R.id.bookPages,numPagine.getId());
        assertEquals(R.id.theBearers,theBearers.getId());
        assertEquals(R.id.addToTheToBeReadList,addToBeRead.getId());
        assertEquals(R.id.readReviews,readReview.getId());
        assertEquals(R.id.addReview,addReview.getId());
        assertEquals(R.id.removeReview,removeReview.getId());
    }

    @Test
    public void testToast(){
        bookInfoActivity.bookInfoMessage("This is a message");
        assertEquals("This is a message", ShadowToast.getTextOfLatestToast());
    }

    @Test
    public void isReadReviewActivity(){
        readReview.callOnClick();
        Intent startedIntent = shadowOf(bookInfoActivity).getNextStartedActivity();
        ShadowIntent shadowIntent = shadowOf(startedIntent);
        assertEquals(ReviewActivity.class, shadowIntent.getIntentClass());
    }

    @Test
    public void isAddReviewActivity(){
        bookInfoActivity.sendToAddReview();
        Intent startedIntent = shadowOf(bookInfoActivity).getNextStartedActivity();
        ShadowIntent shadowIntent = shadowOf(startedIntent);
        assertEquals(AddReviewActivity.class, shadowIntent.getIntentClass());
    }

    @Test
    public void isResultUsed(){
        map.put("titolo","il ritorno del re");
        map.put("autore","J. R. R. Tolkien");
        map.put("genere","fantasy");
        map.put("annoUscita","1968");
        map.put("NumPagine","540");
        bookInfoActivity.useBookResult(map);
        assertEquals("Titolo: " + String.valueOf(map.get("titolo")),title.getText().toString());
        assertEquals("Autore: " + String.valueOf(map.get("autore")),author.getText().toString());
        assertEquals("genere: " + String.valueOf(map.get("genere")),genere.getText().toString());
        assertEquals("Anno d'uscita: " + String.valueOf(map.get("annoUscita")),annoUscita.getText().toString());
        assertEquals("Numero di pagine: " + String.valueOf(map.get("NumPagine")),numPagine.getText().toString());
    }

    @Test
    public void testPresenterInvariant(){
        assertNotNull(bookInfoActivity.getPresenter());
    }

}
