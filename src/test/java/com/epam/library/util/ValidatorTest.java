package com.epam.library.util;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class ValidatorTest {

    private String validPassword;
    private String invalidPassword;
    private String validSurname;
    private String invalidSurname;
    private String validName;
    private String invalidName;

    @BeforeClass
    public void setUp() {
        validPassword = "gfhghJРус154";
        invalidPassword = "HHH_154_d g";
        validSurname = "Germanovich";
        invalidSurname = "I";
        validName = "Dima";
        invalidName = "Invalid 595";
    }

    @AfterClass
    public void tearDown() {
        validPassword = null;
        validName = null;
        invalidPassword = null;
        validSurname = null;
        invalidName = null;
        invalidSurname = null;

    }

    @Test
    public void Validator_validatePassword() {
        assertTrue(Validator.validatePassword(validPassword) &&
                !Validator.validatePassword(invalidPassword));
    }

    @Test
    public void Validator_validateSurname() {
        assertTrue(Validator.validateString(validSurname) &&
                !Validator.validateString(invalidSurname));
    }

    @Test
    public void Validator_validateName() {
        assertTrue(Validator.validateString(validName) &&
                !Validator.validateString(invalidName));
    }
}
