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
    class ValidateEmailTests {

        @Test
        void shouldReturnTrueForValidEmail() {
            // given
            String email = "johndoe@email.com";

            // when
            boolean expected = true;
            boolean actual = validatorTest.validateEmail(email);

            // then
            assertThat(actual).isEqualTo(expected);
        }

        @Test
        void shouldReturnFalseIfEmailIsEmpty() {
            // given
            String email = "";

            // when
            boolean expected = false;
            boolean actual = validatorTest.validateEmail(email);

            // then
            assertThat(actual).isEqualTo(expected);
        }

        @Test
        void shouldReturnFalseIfEmailHasNoAtSign() {
            // given
            String email = "johndoe.com";

            // when
            boolean expected = false;
            boolean actual = validatorTest.validateEmail(email);

            // then
            assertThat(actual).isEqualTo(expected);
        }

        @Test
        void shouldReturnFalseIfEmailHasNoDot() {
            // given
            String email = "johndoe@email";

            // when
            boolean expected = false;
            boolean actual = validatorTest.validateEmail(email);

            // then
            assertThat(actual).isEqualTo(expected);
        }

        @Test
        void shouldReturnFalseIfEmailHasNoTextBeforeAtSign() {
            // given
            String email = "@email.com";

            // when
            boolean expected = false;
            boolean actual = validatorTest.validateEmail(email);

            // then
            assertThat(actual).isEqualTo(expected);
        }

        @Test
        void shouldReturnFalseIfEmailHasNoTextBetweenAtSignAndDot() {
            // given
            String email = "johndoe@.com";

            // when
            boolean expected = false;
            boolean actual = validatorTest.validateEmail(email);

            // then
            assertThat(actual).isEqualTo(expected);
        }

        @Test
        void shouldReturnFalseIfEmailHasNoTextAfterDot() {
            // given
            String email = "johndoe@email.";

            // when
            boolean expected = false;
            boolean actual = validatorTest.validateEmail(email);

            // then
            assertThat(actual).isEqualTo(expected);
        }

        @Test
        void shouldReturnFalseIfEmailHasMultipleAtSigns() {
            // given
            String email = "john@doe@email.com";

            // when
            boolean expected = false;
            boolean actual = validatorTest.validateEmail(email);

            // then
            assertThat(actual).isEqualTo(expected);
        }

        @Test
        void shouldReturnFalseIfEmailHasDotBeforeAtSign() {
            // given
            String email = "john.doe@email.";

            // when
            boolean expected = false;
            boolean actual = validatorTest.validateEmail(email);

            // then
            assertThat(actual).isEqualTo(expected);
        }
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