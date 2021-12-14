package com.capstone.proj.validator;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

class ValidatorTest {

    private Validator validatorTest;

    @BeforeEach
    void setUp() {
        validatorTest = new Validator();
    }

    @Nested
    class ValidatePasswordTests {

        @Test
        void shouldReturnTrueForValidPassword() {
            // given
            String password = "P@ssword1";

            // when
            boolean expected = true;
            boolean actual = validatorTest.validatePassword(password);

            // then
            assertThat(actual).isEqualTo(expected);
        }

        @Test
        void shouldReturnFalseIfPasswordIsEmpty() {
            // given
            String password = "";

            // when
            boolean expected = false;
            boolean actual = validatorTest.validatePassword(password);

            // then
            assertThat(actual).isEqualTo(expected);
        }

        @Test
        void shouldReturnFalseIfPasswordIsLessThanEightCharacters() {
            // given
            String password = "P@sswo!";

            // when
            boolean expected = false;
            boolean actual = validatorTest.validatePassword(password);

            // then
            assertThat(actual).isEqualTo(expected);
        }

        @Test
        void shouldReturnFalseIfPasswordHasNoUpperCaseLetters() {
            // given
            String password = "p@ssword1";

            // when
            boolean expected = false;
            boolean actual = validatorTest.validatePassword(password);

            // then
            assertThat(actual).isEqualTo(expected);
        }

        @Test
        void shouldReturnFalseIfPasswordHasNoNumbers() {
            // given
            String password = "P@ssword";

            // when
            boolean expected = false;
            boolean actual = validatorTest.validatePassword(password);

            // then
            assertThat(actual).isEqualTo(expected);
        }

        @Test
        void shouldReturnFalseIfPasswordHasNoSpecialCharacters() {
            // given
            String password = "Password1";

            // when
            boolean expected = false;
            boolean actual = validatorTest.validatePassword(password);

            // then
            assertThat(actual).isEqualTo(expected);
        }

    }
}