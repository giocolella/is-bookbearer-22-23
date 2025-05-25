package com.example.bookbearer.validators;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import com.example.bookbearer.catalogue.AddBookCatalogueActivity;
import com.example.bookbearer.catalogue.CataloguePresenter;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CataloguePresValidatorTest {

    private CataloguePresenter cataloguePresenter;

    @BeforeAll
    private void setUp(){
        AddBookCatalogueActivity addBookCatalogueActivity = mock(AddBookCatalogueActivity.class);
        cataloguePresenter = new CataloguePresenter(addBookCatalogueActivity,true);
    }

    @ParameterizedTest
    @MethodSource("catalogueFalseValidatorProvider")
    @DisplayName("Testando se il validatore di CataloguePresenter ritorna false quando necessario")
    public void catalogueFalseValidator(String ISBN,String titolo, String autore, String genere, String annoUscita, String bookImg, String numPagine){
        boolean actual = cataloguePresenter.validateBook(ISBN,titolo,autore,genere,annoUscita,bookImg,numPagine);
        assertFalse(actual);
    }

    @ParameterizedTest
    @MethodSource("catalogueTrueValidatorProvider")
    @DisplayName("Testando se il validatore di CataloguePresenter ritorna true se i parametri passano tutte le verifiche")
    public void catalogueTrueValidator(String ISBN,String titolo, String autore, String genere, String annoUscita, String bookImg, String numPagine){
        boolean actual = cataloguePresenter.validateBook(ISBN,titolo,autore,genere,annoUscita,bookImg,numPagine);
        assertTrue(actual);
    }

    private static Stream<Arguments> catalogueFalseValidatorProvider(){
        return Stream.of(
                Arguments.of("","","","","","",""),
                Arguments.of("978-8830105289","il ritorno del re","J. R. R. Tolkien","fantasy","1968","http//...",""),
                Arguments.of("978-8830105289","il ritorno del re","J. R. R. Tolkien","fantasy","1968","","120"),
                Arguments.of("978-8830105289","il ritorno del re","J. R. R. Tolkien","fantasy","","http//...","120"),
                Arguments.of("978-8830105289","il ritorno del re","J. R. R. Tolkien","","1968","http//...","120"),
                Arguments.of("978-8830105289","il ritorno del re","","fantasy","1968","http//...","120"),
                Arguments.of("978-8830105289","","J. R. R. Tolkien","fantasy","1968","http//...","120"),
                Arguments.of("","il ritorno del re","J. R. R. Tolkien","fantasy","1968","http//...","120"),
                Arguments.of("978-883010528","il ritorno del re","J. R. R. Tolkien","fantasy","1968","http//...","120"),
                Arguments.of("978-8830105289","il ritorno del re","J. R. R. Tolkien","fantasy","196","http//...","120"),
                Arguments.of("978-8830105289","il ritorno del re","J. R. R. Tolkien","fantasy","196f","http//...","120"),
                Arguments.of("978-8830105289","il ritorno del re","J. R. R. Tolkien","fantasy","1968","http//...","120f")
        );
    }

    private static Stream<Arguments> catalogueTrueValidatorProvider(){
        return Stream.of(
                Arguments.of("978-8830105289","il ritorno del re","J. R. R. Tolkien","fantasy","1968","http//...","120")
        );
    }

}
