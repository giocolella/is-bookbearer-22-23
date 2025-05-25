package com.example.bookbearer.validators;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import com.example.bookbearer.login.LoginActivity;
import com.example.bookbearer.login.LoginPresenter;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;
import org.junit.jupiter.api.TestInstance;
import org.robolectric.shadows.ShadowToast;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class LoginPresValidatorTest {

    private LoginPresenter loginPresenter;

    @BeforeAll
    public void setUp(){
        LoginActivity loginActivity = mock(LoginActivity.class);
        loginPresenter = new LoginPresenter(loginActivity,true);
    }

    @ParameterizedTest
    @MethodSource("loginFalseValidatorProvider")
    @DisplayName("Testando se il validatore di LoginPresenter ritorna false quando necessario")
    public void loginFalseValidator(String uMail,String uPass){
        boolean actual = loginPresenter.validateLogin(uMail,uPass);
        assertFalse(actual);
    }

    @ParameterizedTest
    @MethodSource("loginTrueValidatorProvider")
    @DisplayName("Testando se il validatore di LoginPresenter ritorna true quando necessario")
    public void loginTrueValidator(String uMail,String uPass){
        boolean actual = loginPresenter.validateLogin(uMail,uPass);
        assertTrue(actual);
    }

    private static Stream<Arguments> loginFalseValidatorProvider(){
        return Stream.of(
                Arguments.of("",""),
                Arguments.of("matteo@gmail.com",""),
                Arguments.of("","matteo00")
        );
    }

    private static Stream<Arguments> loginTrueValidatorProvider(){
        return Stream.of(
                Arguments.of("matteo@gmail.com","matteo00")
        );
    }


}
