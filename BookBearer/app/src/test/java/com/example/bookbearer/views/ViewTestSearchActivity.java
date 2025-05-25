package com.example.bookbearer.views;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.robolectric.Shadows.*;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.bookbearer.CatalogueActivity;
import com.example.bookbearer.R;
import com.example.bookbearer.addreview.AddReviewActivity;
import com.example.bookbearer.beans.LightBook;
import com.example.bookbearer.beans.Review;
import com.example.bookbearer.bookinfo.BookInfoActivity;
import com.example.bookbearer.catalogue.AddBookCatalogueActivity;
import com.example.bookbearer.modmail.ModMailActivity;
import com.example.bookbearer.reviews.ReviewActivity;
import com.example.bookbearer.reviews.ReviewListAdapter;
import com.example.bookbearer.search.ListAdapter;
import com.example.bookbearer.search.SearchActivity;
import com.example.bookbearer.search.SearchMod;
import com.example.bookbearer.search.SearchModel;
import com.example.bookbearer.search.SearchPresenter;
import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.FirebaseFirestore;

import androidx.test.core.app.ApplicationProvider;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.Shadows;
import org.robolectric.annotation.Config;
import org.robolectric.annotation.internal.DoNotInstrument;
import org.robolectric.shadows.ShadowActivity;
import org.robolectric.shadows.ShadowIntent;
import org.robolectric.shadows.ShadowListView;
import org.robolectric.shadows.ShadowToast;

import java.util.ArrayList;

@RunWith(RobolectricTestRunner.class)
@DoNotInstrument
public class ViewTestSearchActivity {

    private SearchActivity searchActivity;
    private Context context;
    private ListView listview;


    @Before
    public void setUp(){
        context = ApplicationProvider.getApplicationContext();
        FirebaseApp.initializeApp(context);
        searchActivity = Robolectric.buildActivity(SearchActivity.class).create().resume().get();
        listview = searchActivity.findViewById(R.id.searchList);
    }

    @Test
    public void testContext(){
        assertNotNull(context);
    }

    @Test
    public void testActivityAndViews(){
        assertNotNull(searchActivity);
        assertNotNull(listview);
    }

    @Test
    public void testLayoutAssociation(){
        assertEquals(R.id.searchList,listview.getId());
    }

    @Test
    public void testToast(){
        searchActivity.searchMessage("This is a message");
        assertEquals("This is a message", ShadowToast.getTextOfLatestToast());
    }

    @Test
    public void testPresenterInvariant(){
        assertNotNull(searchActivity.getPresenter());
    }

    //Testa anche ListAdapter getView(...)
    @Test
    public void isResultUsed(){
        LightBook lb = new LightBook();
        ArrayList<LightBook> arrayLb = new ArrayList<LightBook>();
        lb.setIsbn("123-4563213456");
        lb.setTitle("il ritorno del re");
        lb.setImageUrl("http//...");
        lb.setAuthor("J. R. R. Tolkien");
        arrayLb.add(lb);
        ListAdapter listAdapter = new ListAdapter(context,arrayLb);
        listview.setAdapter(listAdapter);
        ShadowListView shadowListView = Shadows.shadowOf(listview);
        shadowListView.populateItems();
        assertEquals( "123-4563213456", ((LightBook) listview.getAdapter().getItem(0)).getIsbn());
        assertEquals( "il ritorno del re", ((LightBook) listview.getAdapter().getItem(0)).getTitle());
        assertEquals( "http//...", ((LightBook) listview.getAdapter().getItem(0)).getImageUrl());
        assertEquals( "J. R. R. Tolkien", ((LightBook) listview.getAdapter().getItem(0)).getAuthor());
    }



}

