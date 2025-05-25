package com.example.bookbearer.validators;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import com.example.bookbearer.login.LoginActivity;
import com.example.bookbearer.login.LoginPresenter;
import com.example.bookbearer.modmail.ModMailActivity;
import com.example.bookbearer.modmail.ModMailPresenter;
import com.example.bookbearer.modpass.ModPassActivity;
import com.example.bookbearer.modpass.ModPassPresenter;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ModPassPresValidatorTest {

    private ModPassPresenter modPassPresenter;

    @BeforeAll
    public void setUp(){
        ModPassActivity modPassActivity = mock(ModPassActivity.class);
        modPassPresenter = new ModPassPresenter(modPassActivity,true);
    }

    @ParameterizedTest
    @MethodSource("modPassFalseValidatorProvider")
    @DisplayName("Testando se il validatore di ModPassPresenter ritorna false quando necessario")
    public void modPassFalseValidator(String uNPass, String uOPass){
        boolean actual = modPassPresenter.validateFields(uNPass,uOPass);
        assertFalse(actual);
    }

    @ParameterizedTest
    @MethodSource("modPassTrueValidatorProvider")
    @DisplayName("Testando se il validatore di ModPassPresenter ritorna true quando necessario")
    public void modPassTrueValidator(String uNPass, String uOPass){
        boolean actual = modPassPresenter.validateFields(uNPass,uOPass);
        assertTrue(actual);
    }

    private static Stream<Arguments> modPassFalseValidatorProvider(){
        return Stream.of(
                Arguments.of("",""),
                Arguments.of("matteo00",""),
                Arguments.of("","matteo00"),
                Arguments.of("matteor","matteo00"),
                Arguments.of("matteor0","matteo0")
        );
    }

    private static Stream<Arguments> modPassTrueValidatorProvider(){
        return Stream.of(
                Arguments.of("matteor00","matteo00")
        );
    }


}
