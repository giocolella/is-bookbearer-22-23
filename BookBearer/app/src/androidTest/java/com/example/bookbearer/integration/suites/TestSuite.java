package com.example.bookbearer.integration.suites;


import com.example.bookbearer.integration.AddBookCatalogueTest;
import com.example.bookbearer.integration.AddReviewTest;
import com.example.bookbearer.integration.DeleteAccountTest;
import com.example.bookbearer.integration.ModMailTest;
import com.example.bookbearer.integration.RegistrationAndLoginTest;
import com.example.bookbearer.integration.RemoveReviewTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses(
        {
               RegistrationAndLoginTest.class,
                AddReviewTest.class,
                RemoveReviewTest.class,
                ModMailTest.class,
                DeleteAccountTest.class,
                AddBookCatalogueTest.class,
        })

public class TestSuite {
}
