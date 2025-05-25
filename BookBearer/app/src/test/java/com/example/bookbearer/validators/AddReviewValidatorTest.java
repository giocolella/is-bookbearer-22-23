package com.example.bookbearer.validators;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import com.example.bookbearer.addreview.AddReviewActivity;
import com.example.bookbearer.addreview.AddReviewPresenter;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AddReviewValidatorTest {

    private AddReviewPresenter addReviewPresenter;

    @BeforeAll
    public void setUp(){
        AddReviewActivity addReviewActivity = mock(AddReviewActivity.class);
        addReviewPresenter = new AddReviewPresenter(addReviewActivity,true);
    }

    @ParameterizedTest
    @MethodSource("addReviewFalseValidatorProvider")
    @DisplayName("Testando se il validatore di AddReviewPresenter ritorna false quando necessario")
    public void addReviewFalseValidator(String score, String description){
        boolean actual = addReviewPresenter.validateReview(score,description);
        assertFalse(actual);
    }

    @ParameterizedTest
    @MethodSource("addReviewTrueValidatorProvider")
    @DisplayName("Testando se il validatore di AddReviewPresenter ritorna true quando necessario")
    public void addReviewTrueValidator(String score, String description){
        boolean actual = addReviewPresenter.validateReview(score,description);
        assertTrue(actual);
    }

    private static Stream<Arguments> addReviewFalseValidatorProvider(){
        return Stream.of(
                Arguments.of("5",""),
                Arguments.of("NP",""),
                Arguments.of("NP","Ottimo libro")
        );
    }

    private static Stream<Arguments> addReviewTrueValidatorProvider(){
        return Stream.of(
                Arguments.of("5","Ottimo libro")
        );
    }


}
