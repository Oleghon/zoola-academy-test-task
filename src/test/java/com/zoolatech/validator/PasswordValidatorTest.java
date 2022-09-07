package com.zoolatech.validator;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class PasswordValidatorTest {

    private PasswordValidator passwordValidator = new PasswordValidator(new CommonPasswordChecker());

    @Test
    public void checkPasswordTest() {
        assertAll(
                () -> assertTrue(passwordValidator.checkPassword("qwerTy@43")),
                () -> assertFalse(passwordValidator.checkPassword("qwerf@45553")),
                () -> assertFalse(passwordValidator.checkPassword("qwerty")),
                () -> assertFalse(passwordValidator.checkPassword("putinhuilo"))
        );
    }

    @Test
    public void passwordLengthTest() {
        assertAll(
                () -> assertFalse(passwordValidator.checkPasswordLength("nnsosi@pesyn#putinhahahfm23242ndsn")),
                () -> assertFalse(passwordValidator.checkPasswordLength("1234")),
                () -> assertTrue(passwordValidator.checkPasswordLength("12345678"))
        );
    }

    @Test
    public void passwordNullTest() {
        assertAll(
                () -> assertFalse(passwordValidator.checkPasswordNull(null)),
                () -> assertTrue(passwordValidator.checkPasswordNull("")),
                () -> assertTrue(passwordValidator.checkPasswordNull(" ")),
                () -> assertTrue(passwordValidator.checkPasswordNull("test"))
        );
    }

    @Test
    public void checkPasswordUpperLowerCaseTest() {
        assertAll(
                () -> assertThrows(RuntimeException.class, () -> passwordValidator.checkPasswordUpperLowerCase(null)),
                () -> assertFalse(passwordValidator.checkPasswordUpperLowerCase("")),
                () -> assertFalse(passwordValidator.checkPasswordUpperLowerCase(" ")),
                () -> assertFalse(passwordValidator.checkPasswordUpperLowerCase("test")),
                () -> assertFalse(passwordValidator.checkPasswordUpperLowerCase("TEST")),
                () -> assertTrue(passwordValidator.checkPasswordUpperLowerCase("tEsT"))
        );
    }

    @Test
    public void checkPasswordHasNumberTest() {
        assertAll(
                () -> assertThrows(RuntimeException.class, () -> passwordValidator.checkPasswordHasNumber(null)),
                () -> assertFalse(passwordValidator.checkPasswordHasNumber("")),
                () -> assertFalse(passwordValidator.checkPasswordHasNumber(" ")),
                () -> assertFalse(passwordValidator.checkPasswordHasNumber("test")),
                () -> assertTrue(passwordValidator.checkPasswordHasNumber("test1")),
                () -> assertTrue(passwordValidator.checkPasswordHasNumber("21342"))
        );
    }

    @Test
    public void checkPasswordSpecialCharacterTest() {
        assertAll(
                () -> assertThrows(RuntimeException.class, () -> passwordValidator.checkPasswordSpecialCharacter(null)),
                () -> assertFalse(passwordValidator.checkPasswordSpecialCharacter("")),
                () -> assertFalse(passwordValidator.checkPasswordSpecialCharacter(" ")),
                () -> assertFalse(passwordValidator.checkPasswordSpecialCharacter("test")),
                () -> assertTrue(passwordValidator.checkPasswordSpecialCharacter("test()")),
                () -> assertTrue(passwordValidator.checkPasswordSpecialCharacter("()#$%&@/?!"))
        );
    }

    @Test
    public void checkPasswordContinuousNumbersTest() {
        assertAll(
                () -> assertThrows(RuntimeException.class, () -> passwordValidator.checkPasswordContinuousNumbers(null)),
                () -> assertFalse(passwordValidator.checkPasswordContinuousNumbers("test123")),
                () -> assertFalse(passwordValidator.checkPasswordContinuousNumbers("123test")),
                () -> assertTrue(passwordValidator.checkPasswordContinuousNumbers("te12st")),
                () -> assertTrue(passwordValidator.checkPasswordContinuousNumbers("142")),
                () -> assertTrue(passwordValidator.checkPasswordContinuousNumbers("")),
                () -> assertTrue(passwordValidator.checkPasswordContinuousNumbers("test1")),
                () -> assertTrue(passwordValidator.checkPasswordContinuousNumbers("1test")),
                () -> assertTrue(passwordValidator.checkPasswordContinuousNumbers("test")),
                () -> assertTrue(passwordValidator.checkPasswordContinuousNumbers("t1e2s3t"))
        );
    }

    @Test
    public void checkPasswordSameNumberTest() {
        assertAll(
                () -> assertThrows(RuntimeException.class, () -> passwordValidator.checkPasswordSameNumber(null)),
                () -> assertFalse(passwordValidator.checkPasswordSameNumber("1111test")),
                () -> assertFalse(passwordValidator.checkPasswordSameNumber("test1111")),
                () -> assertFalse(passwordValidator.checkPasswordSameNumber("te2222st")),
                () -> assertFalse(passwordValidator.checkPasswordSameNumber("2222")),
                () -> assertTrue(passwordValidator.checkPasswordSameNumber("")),
                () -> assertTrue(passwordValidator.checkPasswordSameNumber(" ")),
                () -> assertTrue(passwordValidator.checkPasswordSameNumber("test")),
                () -> assertTrue(passwordValidator.checkPasswordSameNumber("test123")),
                () -> assertTrue(passwordValidator.checkPasswordSameNumber("111222333")),
                () -> assertTrue(passwordValidator.checkPasswordSameNumber("test111222333"))
        );
    }
}
