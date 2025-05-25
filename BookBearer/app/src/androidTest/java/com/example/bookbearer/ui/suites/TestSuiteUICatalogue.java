package com.example.bookbearer.ui.suites;


import com.example.bookbearer.ui.Decima_CatalogueActivityTest;
import com.example.bookbearer.ui.Dodicesima_AddBookCatalogueActivityTest;
import com.example.bookbearer.ui.Tredicesima_AddBookCatalogueActivityTestUC_6_2;
import com.example.bookbearer.ui.Undicesima_AddBookCatalogueActivityViewTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses(
        {
                Decima_CatalogueActivityTest.class,
                Undicesima_AddBookCatalogueActivityViewTest.class,
                Dodicesima_AddBookCatalogueActivityTest.class,
                Tredicesima_AddBookCatalogueActivityTestUC_6_2.class,
        })
public class TestSuiteUICatalogue {
}
