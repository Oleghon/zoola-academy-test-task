package com.zoolatech.validator;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;

public class PasswordValidatorTest {

    private CommonPasswordChecker commonPasswordCheckerMock = Mockito.mock(CommonPasswordChecker.class);
    private PasswordValidator passwordValidator = new PasswordValidator(commonPasswordCheckerMock);

    @Test
    public void checkPasswordSuccessTest() {
        Mockito.when(commonPasswordCheckerMock.checkCommonPassword(anyString())).thenReturn(true);
        assertTrue(passwordValidator.checkPassword("qwerTy@43"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"qwerf@45553", "qwerty", "putinhuilo"})
    public void checkPasswordFailCasesTest(String param) {
        assertFalse(passwordValidator.checkPassword(param));
    }

    @Test
    public void passwordLengthSuccessTest() {
        assertTrue(passwordValidator.checkPasswordLength("12345678"));
    }

    @ParameterizedTest
    @ValueSource(strings = {" ", "test", "nnsosi@pesyn#putinhahahfm23242ndsn"})
    public void passwordLengthFailTest(String param) {
        assertFalse(passwordValidator.checkPasswordLength(param));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "test"})
    public void passwordNullSuccessTest(String param) {
        assertTrue(passwordValidator.checkPasswordNull(param));
    }

    @Test
    public void passwordNullFailTest() {
        assertFalse(passwordValidator.checkPasswordNull(null));
    }

    @Test
    public void checkPasswordUpperLowerCaseSuccessTest() {
        assertTrue(passwordValidator.checkPasswordUpperLowerCase("tEsT"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "test", "TEST"})
    public void checkPasswordUpperLowerCaseFailCasesTest(String param) {
        assertFalse(passwordValidator.checkPasswordUpperLowerCase(param));
    }

    @ParameterizedTest
    @ValueSource(strings = {"21342", "test1"})
    public void checkPasswordHasNumberSuccessCasesTest(String param) {
        assertTrue(passwordValidator.checkPasswordHasNumber(param));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "test"})
    public void checkPasswordHasNumberFailCasesTest(String param) {
        assertFalse(passwordValidator.checkPasswordHasNumber(param));
    }

    @ParameterizedTest
    @ValueSource(strings = {"test()", "()#$%&@/?!"})
    public void checkPasswordSpecialCharacterSuccessCasesTest(String param) {
        assertTrue(passwordValidator.checkPasswordSpecialCharacter(param));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "test"})
    public void checkPasswordSpecialCharacterFailCasesTest(String param) {
        assertFalse(passwordValidator.checkPasswordSpecialCharacter(param));
    }

    @ParameterizedTest
    @ValueSource(strings = {"t1e2s3t", "test", "test112233", "1test"})
    public void checkPasswordContinuousNumberSuccessCasesTest(String param) {
        assertTrue(passwordValidator.checkPasswordContinuousNumbers(param));
    }

    @ParameterizedTest
    @ValueSource(strings = {"test123", "123test"})
    public void checkPasswordContinuousNumberFailCasesTest(String param) {
        assertFalse(passwordValidator.checkPasswordContinuousNumbers(param));
    }

    @ParameterizedTest
    @ValueSource(strings = {"test", "test123", "112233"})
    public void checkPasswordSameNumberSuccessCasesTest(String param) {
        assertTrue(passwordValidator.checkPasswordSameNumber(param));
    }

    @ParameterizedTest
    @ValueSource(strings = {"1111test", "test1111", "te2222st", "2222"})
    public void checkPasswordSameNumberFailCasesTest(String param) {
        assertFalse(passwordValidator.checkPasswordSameNumber(param));
    }
}