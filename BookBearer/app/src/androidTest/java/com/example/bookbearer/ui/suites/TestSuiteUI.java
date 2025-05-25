package com.example.bookbearer.ui.suites;

import com.example.bookbearer.ui.Diciannovesima_DeleteAccountTest;
import com.example.bookbearer.ui.Diciassettesima_ModMailActivityTest;
import com.example.bookbearer.ui.Diciottesima_ModPassActivityTest;
import com.example.bookbearer.ui.Nona_RemoveReviewTest;
import com.example.bookbearer.ui.Ottava_AddReviewActivityTest;
import com.example.bookbearer.ui.Prima_RegisterActivityTestfromUC_1_1to1_5;
import com.example.bookbearer.ui.Quarta_ProfileFragmentTest;
import com.example.bookbearer.ui.Quattordicesima_ListsActivityTest;
import com.example.bookbearer.ui.Quindicesima_ListsActivityTestUC_4_4;
import com.example.bookbearer.ui.Quinta_SettingsFragmentTest;
import com.example.bookbearer.ui.Seconda_LoginActivityTestfromUC_2_1to2_4;
import com.example.bookbearer.ui.Sedicesima_ListsActivityTestUC_4_92;
import com.example.bookbearer.ui.Sesta_RegisterActivityTestUC_1_2;
import com.example.bookbearer.ui.Settima_BookInfoActivityandReviewActivityTest;
import com.example.bookbearer.ui.Terza_MainActivityandSearchActivityTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses(
        {
                Prima_RegisterActivityTestfromUC_1_1to1_5.class,
                Seconda_LoginActivityTestfromUC_2_1to2_4.class,
                Terza_MainActivityandSearchActivityTest.class,
                Quarta_ProfileFragmentTest.class,
                Quinta_SettingsFragmentTest.class,
                Sesta_RegisterActivityTestUC_1_2.class,
                Settima_BookInfoActivityandReviewActivityTest.class,
                Ottava_AddReviewActivityTest.class,
                Nona_RemoveReviewTest.class,
                Quattordicesima_ListsActivityTest.class,
                Quindicesima_ListsActivityTestUC_4_4.class,
                Sedicesima_ListsActivityTestUC_4_92.class,
                Diciassettesima_ModMailActivityTest.class,
                Diciottesima_ModPassActivityTest.class,
                Diciannovesima_DeleteAccountTest.class
        })
public class TestSuiteUI {
}
