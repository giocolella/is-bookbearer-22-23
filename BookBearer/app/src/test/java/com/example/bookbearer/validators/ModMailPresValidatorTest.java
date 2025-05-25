package com.example.bookbearer.validators;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import com.example.bookbearer.login.LoginActivity;
import com.example.bookbearer.login.LoginPresenter;
import com.example.bookbearer.modmail.ModMailActivity;
import com.example.bookbearer.modmail.ModMailPresenter;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ModMailPresValidatorTest {

    private ModMailPresenter modMailPresenter;

    @BeforeAll
    public void setUp(){
        ModMailActivity modMailActivity = mock(ModMailActivity.class);
        modMailPresenter = new ModMailPresenter(modMailActivity,true);
    }

    @ParameterizedTest
    @MethodSource("modMailFalseValidatorProvider")
    @DisplayName("Testando se il validatore di ModMailPresenter ritorna false quando necessario")
    public void modMailFalseValidator(String uMail,String uPass){
        boolean actual = modMailPresenter.validateFields(uMail,uPass);
        assertFalse(actual);
    }

    @ParameterizedTest
    @MethodSource("modMailTrueValidatorProvider")
    @DisplayName("Testando se il validatore di ModMailPresenter ritorna true quando necessario")
    public void modMailTrueValidator(String uMail,String uPass){
        boolean actual = modMailPresenter.validateFields(uMail,uPass);
        assertTrue(actual);
    }

    private static Stream<Arguments> modMailFalseValidatorProvider(){
        return Stream.of(
                Arguments.of("",""),
                Arguments.of("matteo@gmail.com",""),
                Arguments.of("","matteo00"),
                Arguments.of("matteo@gmail.com","matteo0"),
                Arguments.of("matteogmail.com","matteo00")
        );
    }

    private static Stream<Arguments> modMailTrueValidatorProvider(){
        return Stream.of(
                Arguments.of("matteo1@gmail.com","matteo00")
        );
    }


}
