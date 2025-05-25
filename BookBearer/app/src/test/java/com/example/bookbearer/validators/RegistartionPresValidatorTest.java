package com.example.bookbearer.validators;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import com.example.bookbearer.registration.RegisterActivity;
import com.example.bookbearer.registration.RegistrationPresenter;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class RegistartionPresValidatorTest {

    private RegistrationPresenter registrationPresenter;

    @BeforeAll
    public void setUp(){
        RegisterActivity registerActivity = mock(RegisterActivity.class);
        registrationPresenter = new RegistrationPresenter(registerActivity,true);
    }

    @ParameterizedTest
    @MethodSource("registrationFalseValidatorProvider")
    @DisplayName("Testando se il validatore di RegistartionPresenter ritorna false quando necessario")
    public void registrationFalseValidator(String uName,String uMail, String uPass, String uConfPass){
        boolean actual = registrationPresenter.validateRegistration(uName,uMail,uPass,uConfPass);
        assertFalse(actual);
    }

    @ParameterizedTest
    @MethodSource("registrationTrueValidatorProvider")
    @DisplayName("Testando se il validatore di RegistrationPresenter ritorna true quando necessario")
    public void registrationTrueValidator(String uName,String uMail, String uPass, String uConfPass){
        boolean actual = registrationPresenter.validateRegistration(uName,uMail,uPass,uConfPass);
        assertTrue(actual);
    }

    private static Stream<Arguments> registrationFalseValidatorProvider(){
        return Stream.of(
                Arguments.of("","","",""),
                Arguments.of("matteo","matteo@gmail.com","matteo00",""),
                Arguments.of("matteo","matteo@gmail.com","","matteo00"),
                Arguments.of("matteo","","matteo00","matteo00"),
                Arguments.of("","matteo@gmail.com","matteo00","matteo00"),
                Arguments.of("matteo","matteogmail.com","matteo00","matteo00"),
                Arguments.of("matteo","matteo@gmail.com","matteo00","matteo09"),
                Arguments.of("matteo","matteo@gmail.com","matteo0","matteo0")
        );
    }

    private static Stream<Arguments> registrationTrueValidatorProvider(){
        return Stream.of(
                Arguments.of("matteo","matteo@gmail.com","matteo00","matteo00")
        );
    }

}
