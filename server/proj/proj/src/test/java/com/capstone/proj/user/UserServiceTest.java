package com.capstone.proj.user;

import com.capstone.proj.constituency.Constituency;
import com.capstone.proj.constituency.ConstituencyService;
import com.capstone.proj.exception.BadRequest;
import com.capstone.proj.exception.ResourceNotFound;
import com.capstone.proj.exception.Unauthorized;
import com.capstone.proj.token.Token;
import com.capstone.proj.token.TokenService;
import com.capstone.proj.validator.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

class UserServiceTest {
    private UserService userServiceTest;
    private UserDAO userDAOMock;
    private TokenService tokenServiceMock;
    private ConstituencyService constituencyServiceMock;
    private Validator validatorMock;

    @BeforeEach
    void setUp() {
        userDAOMock = mock(UserDAO.class);
        tokenServiceMock = mock(TokenService.class);
        constituencyServiceMock = mock(ConstituencyService.class);
        validatorMock = mock(Validator.class);
        userServiceTest = new UserService(userDAOMock, tokenServiceMock, constituencyServiceMock, validatorMock);
    }

    @Nested
    class CreateUserTests {

        @Test
        void shouldCreateUserIfInputIsValid() {
            // given
            User userBeforeValidation = new User(
                    1L,
                    "John", "Doe", "john@doe.com", "P@ssword1",
                    "Postcode 1", null, null, null);
            User userAfterValidation = new User(
                    1L,
                    "John", "Doe", "john@doe.com", "P@ssword1",
                    null, 100, "Constituency 1", null);

            when(validatorMock.validateEmail("john@doe.com")).thenReturn(true);
            when(userDAOMock.getUserByEmail("john@doe.com")).thenReturn(Optional.empty());
            when(validatorMock.validatePassword("P@ssword1")).thenReturn(true);
            when(constituencyServiceMock.getConstituencyFromPostcode("Postcode 1")).thenReturn(new Constituency(100, "Constituency 1"));
            when(userDAOMock.createUser(userAfterValidation)).thenReturn(1);

            // when
            int result = userServiceTest.createUser(userBeforeValidation);

            // then
            assertThat(result).isEqualTo(1);

            ArgumentCaptor<User> userArgumentCaptor = ArgumentCaptor.forClass(User.class);
            verify(userDAOMock).createUser(userArgumentCaptor.capture());
            User insertedUser = userArgumentCaptor.getValue();
            assertThat(insertedUser).isEqualTo(userAfterValidation);

            verify(validatorMock).validateEmail("john@doe.com");
            verify(userDAOMock).getUserByEmail("john@doe.com");
            verify(validatorMock).validatePassword("P@ssword1");
            verify(constituencyServiceMock).getConstituencyFromPostcode("Postcode 1");

            verifyNoMoreInteractions(validatorMock);
            verifyNoMoreInteractions(userDAOMock);
            verifyNoMoreInteractions(constituencyServiceMock);
        }

        @Test
        void shouldThrowErrorIfFirstNameIsEmpty() {
            // given
            User userBeforeValidation = new User(
                    1L,
                    "", "Doe", "john@doe.com", "P@ssword1",
                    "Postcode 1", null, null, null);

            // when

            // then
            assertThatThrownBy(() -> userServiceTest.createUser(userBeforeValidation))
                    .hasMessage("First name cannot be empty")
                    .isInstanceOf(BadRequest.class);

            verifyNoInteractions(validatorMock);
            verifyNoInteractions(userDAOMock);
            verifyNoInteractions(constituencyServiceMock);
        }

        @Test
        void shouldThrowErrorIfFirstNameIsNull() {
            // given
            User userBeforeValidation = new User(
                    1L,
                    null, "Doe", "john@doe.com", "P@ssword1",
                    "Postcode 1", null, null, null);

            // when

            // then
            assertThatThrownBy(() -> userServiceTest.createUser(userBeforeValidation))
                    .hasMessage("First name cannot be empty")
                    .isInstanceOf(BadRequest.class);

            verifyNoInteractions(validatorMock);
            verifyNoInteractions(userDAOMock);
            verifyNoInteractions(constituencyServiceMock);
        }

        @Test
        void shouldThrowErrorIfLastNameIsEmpty() {
            // given
            User userBeforeValidation = new User(
                    1L,
                    "John", "", "john@doe.com", "P@ssword1",
                    "Postcode 1", null, null, null);

            // when

            // then
            assertThatThrownBy(() -> userServiceTest.createUser(userBeforeValidation))
                    .hasMessage("Last name cannot be empty")
                    .isInstanceOf(BadRequest.class);

            verifyNoInteractions(validatorMock);
            verifyNoInteractions(userDAOMock);
            verifyNoInteractions(constituencyServiceMock);
        }

        @Test
        void shouldThrowErrorIfLastNameIsNull() {
            // given
            User userBeforeValidation = new User(
                    1L,
                    "John", null, "john@doe.com", "P@ssword1",
                    "Postcode 1", null, null, null);

            // when

            // then
            assertThatThrownBy(() -> userServiceTest.createUser(userBeforeValidation))
                    .hasMessage("Last name cannot be empty")
                    .isInstanceOf(BadRequest.class);

            verifyNoInteractions(validatorMock);
            verifyNoInteractions(userDAOMock);
            verifyNoInteractions(constituencyServiceMock);
        }

        @Test
        void shouldThrowErrorIfEmailIsNotValid() {
            // given
            User userBeforeValidation = new User(
                    1L,
                    "John", "Doe", "johndoe.com", "P@ssword1",
                    "Postcode 1", null, null, null);

            when(validatorMock.validateEmail("johndoe.com")).thenReturn(false);

            // when

            // then
            assertThatThrownBy(() -> userServiceTest.createUser(userBeforeValidation))
                    .hasMessage("Invalid email address")
                    .isInstanceOf(BadRequest.class);

            verify(validatorMock).validateEmail("johndoe.com");

            verifyNoMoreInteractions(validatorMock);
            verifyNoInteractions(userDAOMock);
            verifyNoInteractions(constituencyServiceMock);
        }

        @Test
        void shouldThrowErrorIfUserWithEmailAlreadyExists() {
            // given
            User userBeforeValidation = new User(
                    1L,
                    "John", "Doe", "john@doe.com", "P@ssword1",
                    "Postcode 1", null, null, null);

            User userInDatabase = new User(
                    2L,
                    "Johnny", "Doey", "john@doe.com", "P@ssword2",
                    null, 101, "Constituency 2", null);

            when(validatorMock.validateEmail("john@doe.com")).thenReturn(true);
            when(userDAOMock.getUserByEmail("john@doe.com")).thenReturn(Optional.of(userInDatabase));

            // when

            // then
            assertThatThrownBy(() -> userServiceTest.createUser(userBeforeValidation))
                    .hasMessage("User with email already exists")
                    .isInstanceOf(BadRequest.class);

            verify(validatorMock).validateEmail("john@doe.com");
            verify(userDAOMock).getUserByEmail("john@doe.com");

            verifyNoMoreInteractions(validatorMock);
            verifyNoMoreInteractions(userDAOMock);
            verifyNoInteractions(constituencyServiceMock);
        }

        @Test
        void shouldThrowErrorIfPasswordIsNotValid() {
            // given
            User userBeforeValidation = new User(
                    1L,
                    "John", "Doe", "john@doe.com", "password",
                    "Postcode 1", null, null, null);

            when(validatorMock.validateEmail("john@doe.com")).thenReturn(true);
            when(userDAOMock.getUserByEmail("john@doe.com")).thenReturn(Optional.empty());
            when(validatorMock.validatePassword("password")).thenReturn(false);

            // when

            // then
            assertThatThrownBy(() -> userServiceTest.createUser(userBeforeValidation))
                    .hasMessage("Invalid password")
                    .isInstanceOf(BadRequest.class);

            verify(validatorMock).validateEmail("john@doe.com");
            verify(userDAOMock).getUserByEmail("john@doe.com");
            verify(validatorMock).validatePassword("password");

            verifyNoMoreInteractions(validatorMock);
            verifyNoMoreInteractions(userDAOMock);
            verifyNoInteractions(constituencyServiceMock);
        }

        @Test
        void shouldThrowErrorIfPostcodeIsEmpty() {
            // given
            User userBeforeValidation = new User(
                    1L,
                    "John", "Doe", "john@doe.com", "P@ssword1",
                    "", null, null, null);

            when(validatorMock.validateEmail("john@doe.com")).thenReturn(true);
            when(userDAOMock.getUserByEmail("john@doe.com")).thenReturn(Optional.empty());
            when(validatorMock.validatePassword("P@ssword1")).thenReturn(true);

            // when

            // then
            assertThatThrownBy(() -> userServiceTest.createUser(userBeforeValidation))
                    .hasMessage("Postcode cannot be empty")
                    .isInstanceOf(BadRequest.class);

            verify(validatorMock).validateEmail("john@doe.com");
            verify(userDAOMock).getUserByEmail("john@doe.com");
            verify(validatorMock).validatePassword("P@ssword1");

            verifyNoMoreInteractions(validatorMock);
            verifyNoMoreInteractions(userDAOMock);
            verifyNoInteractions(constituencyServiceMock);
        }

        @Test
        void shouldThrowErrorIfPostcodeIsNull() {
            // given
            User userBeforeValidation = new User(
                    1L,
                    "John", "Doe", "john@doe.com", "P@ssword1",
                    null, null, null, null);

            when(validatorMock.validateEmail("john@doe.com")).thenReturn(true);
            when(userDAOMock.getUserByEmail("john@doe.com")).thenReturn(Optional.empty());
            when(validatorMock.validatePassword("P@ssword1")).thenReturn(true);

            // when

            // then
            assertThatThrownBy(() -> userServiceTest.createUser(userBeforeValidation))
                    .hasMessage("Postcode cannot be empty")
                    .isInstanceOf(BadRequest.class);

            verify(validatorMock).validateEmail("john@doe.com");
            verify(userDAOMock).getUserByEmail("john@doe.com");
            verify(validatorMock).validatePassword("P@ssword1");

            verifyNoMoreInteractions(validatorMock);
            verifyNoMoreInteractions(userDAOMock);
            verifyNoInteractions(constituencyServiceMock);
        }

        @Test
        void shouldThrowErrorIfPostcodeIsNotValid() {
            // given
            User userBeforeValidation = new User(
                    1L,
                    "John", "Doe", "john@doe.com", "P@ssword1",
                    "Postcode 1", null, null, null);

            when(validatorMock.validateEmail("john@doe.com")).thenReturn(true);
            when(userDAOMock.getUserByEmail("john@doe.com")).thenReturn(Optional.empty());
            when(validatorMock.validatePassword("P@ssword1")).thenReturn(true);
            when(constituencyServiceMock.getConstituencyFromPostcode("Postcode 1"))
                    .thenThrow(RuntimeException.class);

            // when

            // then
            assertThatThrownBy(() -> userServiceTest.createUser(userBeforeValidation))
                    .hasMessage("Postcode 1 is not a valid postcode")
                    .isInstanceOf(BadRequest.class);

            verify(validatorMock).validateEmail("john@doe.com");
            verify(userDAOMock).getUserByEmail("john@doe.com");
            verify(validatorMock).validatePassword("P@ssword1");
            verify(constituencyServiceMock).getConstituencyFromPostcode("Postcode 1");

            verifyNoMoreInteractions(validatorMock);
            verifyNoMoreInteractions(userDAOMock);
            verifyNoMoreInteractions(constituencyServiceMock);
        }
    }

    @Nested
    class GetAllUsersTests {

        @Test
        void shouldGetAllUsers() {
            // given
            User user1 = new User(
                    1L,
                    "John", "Doe", "john@doe.com", "P@ssword1",
                    null, 100, "Constituency 1", null);
            User user2 = new User(
                    2L,
                    "Jane", "Do", "jane@do.com", "P@ssword2",
                    null, 101, "Constituency 2", null);
            List<User> users = List.of(user1, user2);

            when(userDAOMock.getAllUsers()).thenReturn(users);

            // when
            List<User> expected = users;
            List<User> actual = userServiceTest.getAllUsers();

            // then
            assertThat(actual).isEqualTo(expected);
        }
    }

    @Nested
    class GetUserByIdTests {

        @Test
        void shouldGetUserIfIdExists() {
            // given
            User user = new User(
                    1L,
                    "John", "Doe", "john@doe.com", "P@ssword1",
                    null, 100, "Constituency 1", null);

            when(userDAOMock.getUserById(1)).thenReturn(Optional.of(user));

            // when
            Optional<User> expected = Optional.of(user);
            Optional<User> actual = userServiceTest.getUserById(1);

            // then
            assertThat(actual).isEqualTo(expected);

            verify(userDAOMock).getUserById(1);

            verifyNoMoreInteractions(userDAOMock);
        }

        @Test
        void shouldThrowErrorIfUserWithIdDoesNotExist() {
            // given
            when(userDAOMock.getUserById(1)).thenReturn(Optional.empty());

            // when

            // then
            assertThatThrownBy(() -> userServiceTest.getUserById(1))
                    .hasMessage("User with id: 1 not found")
                    .isInstanceOf(ResourceNotFound.class);

            verify(userDAOMock).getUserById(1);

            verifyNoMoreInteractions(userDAOMock);
        }
    }

    @Nested
    class UpdateUserTests {

        @Test
        void shouldUpdateUserIfInputIsValid() {
            // given
            User userBeforeValidation = new User(
                    1L,
                    "John", "Doe", "john@doe.com", "P@ssword1",
                    "Postcode 1", null, null, null);
            User userAfterValidation = new User(
                    1L,
                    "John", "Doe", "john@doe.com", "P@ssword1",
                    null, 100, "Constituency 1", null);
            User userInDatabase = new User(
                    1L,
                    "Johnny", "Doey", "john@doe.com", "P@ssword2",
                    null, 101, "Constituency 2", null);

            when(userDAOMock.getUserById(1)).thenReturn(Optional.of(userInDatabase));
            when(validatorMock.validateEmail("john@doe.com")).thenReturn(true);
            when(userDAOMock.getUserByEmail("john@doe.com")).thenReturn(Optional.empty());
            when(validatorMock.validatePassword("P@ssword1")).thenReturn(true);
            when(constituencyServiceMock.getConstituencyFromPostcode("Postcode 1")).thenReturn(new Constituency(100, "Constituency 1"));
            when(userDAOMock.updateUser(1, userAfterValidation)).thenReturn(1);

            // when
            int result = userServiceTest.updateUser(1, userBeforeValidation);

            // then
            assertThat(result).isEqualTo(1);

            ArgumentCaptor<User> userArgumentCaptor = ArgumentCaptor.forClass(User.class);
            ArgumentCaptor<Integer> idArgumentCaptor = ArgumentCaptor.forClass(Integer.class);
            verify(userDAOMock).updateUser(idArgumentCaptor.capture(), userArgumentCaptor.capture());
            Integer insertedId = idArgumentCaptor.getValue();
            assertThat(insertedId).isEqualTo(1);
            User insertedUser = userArgumentCaptor.getValue();
            assertThat(insertedUser).isEqualTo(userAfterValidation);

            verify(userDAOMock).getUserById(1);
            verify(validatorMock).validateEmail("john@doe.com");
            verify(userDAOMock).getUserByEmail("john@doe.com");
            verify(validatorMock).validatePassword("P@ssword1");
            verify(constituencyServiceMock).getConstituencyFromPostcode("Postcode 1");

            verifyNoMoreInteractions(validatorMock);
            verifyNoMoreInteractions(userDAOMock);
            verifyNoMoreInteractions(constituencyServiceMock);
        }

        @Test
        void shouldThrowErrorIfFirstNameIsEmpty() {
            // given
            User userBeforeValidation = new User(
                    1L,
                    "", "Doe", "john@doe.com", "P@ssword1",
                    "Postcode 1", null, null, null);

            when(userDAOMock.getUserById(1)).thenReturn(Optional.empty());

            // when

            // then
            assertThatThrownBy(() -> userServiceTest.updateUser(1, userBeforeValidation))
                    .hasMessage("First name cannot be empty")
                    .isInstanceOf(BadRequest.class);

            verify(userDAOMock).getUserById(1);

            verifyNoInteractions(validatorMock);
            verifyNoMoreInteractions(userDAOMock);
            verifyNoInteractions(constituencyServiceMock);
        }

        @Test
        void shouldThrowErrorIfFirstNameIsNull() {
            // given
            User userBeforeValidation = new User(
                    1L,
                    null, "Doe", "john@doe.com", "P@ssword1",
                    "Postcode 1", null, null, null);
            when(userDAOMock.getUserById(1)).thenReturn(Optional.empty());

            // when

            // then
            assertThatThrownBy(() -> userServiceTest.updateUser(1, userBeforeValidation))
                    .hasMessage("First name cannot be empty")
                    .isInstanceOf(BadRequest.class);

            verify(userDAOMock).getUserById(1);

            verifyNoInteractions(validatorMock);
            verifyNoMoreInteractions(userDAOMock);
            verifyNoInteractions(constituencyServiceMock);
        }

        @Test
        void shouldThrowErrorIfLastNameIsEmpty() {
            // given
            User userBeforeValidation = new User(
                    1L,
                    "John", "", "john@doe.com", "P@ssword1",
                    "Postcode 1", null, null, null);

            when(userDAOMock.getUserById(1)).thenReturn(Optional.empty());

            // when

            // then
            assertThatThrownBy(() -> userServiceTest.updateUser(1, userBeforeValidation))
                    .hasMessage("Last name cannot be empty")
                    .isInstanceOf(BadRequest.class);

            verify(userDAOMock).getUserById(1);

            verifyNoInteractions(validatorMock);
            verifyNoMoreInteractions(userDAOMock);
            verifyNoInteractions(constituencyServiceMock);
        }

        @Test
        void shouldThrowErrorIfLastNameIsNull() {
            // given
            User userBeforeValidation = new User(
                    1L,
                    "John", null, "john@doe.com", "P@ssword1",
                    "Postcode 1", null, null, null);

            when(userDAOMock.getUserById(1)).thenReturn(Optional.empty());

            // when

            // then
            assertThatThrownBy(() -> userServiceTest.updateUser(1, userBeforeValidation))
                    .hasMessage("Last name cannot be empty")
                    .isInstanceOf(BadRequest.class);

            verify(userDAOMock).getUserById(1);

            verifyNoInteractions(validatorMock);
            verifyNoMoreInteractions(userDAOMock);
            verifyNoInteractions(constituencyServiceMock);
        }

        @Test
        void shouldThrowErrorIfEmailIsNotValid() {
            // given
            User userBeforeValidation = new User(
                    1L,
                    "John", "Doe", "johndoe.com", "P@ssword1",
                    "Postcode 1", null, null, null);

            when(userDAOMock.getUserById(1)).thenReturn(Optional.empty());
            when(validatorMock.validateEmail("johndoe.com")).thenReturn(false);

            // when

            // then
            assertThatThrownBy(() -> userServiceTest.updateUser(1, userBeforeValidation))
                    .hasMessage("Invalid email address")
                    .isInstanceOf(BadRequest.class);

            verify(userDAOMock).getUserById(1);
            verify(validatorMock).validateEmail("johndoe.com");

            verifyNoMoreInteractions(validatorMock);
            verifyNoMoreInteractions(userDAOMock);
            verifyNoInteractions(constituencyServiceMock);
        }

        @Test
        void shouldThrowErrorIfUserWithEmailAlreadyExists() {
            // given
            User userBeforeValidation = new User(
                    1L,
                    "John", "Doe", "john@doe.com", "P@ssword1",
                    "Postcode 1", null, null, null);

            User userInDatabase = new User(
                    2L,
                    "Johnny", "Doey", "john@doe.com", "P@ssword2",
                    null, 101, "Constituency 2", null);

            when(userDAOMock.getUserById(1)).thenReturn(Optional.empty());
            when(validatorMock.validateEmail("john@doe.com")).thenReturn(true);
            when(userDAOMock.getUserByEmail("john@doe.com")).thenReturn(Optional.of(userInDatabase));

            // when

            // then
            assertThatThrownBy(() -> userServiceTest.updateUser(1, userBeforeValidation))
                    .hasMessage("User with email already exists")
                    .isInstanceOf(BadRequest.class);

            verify(userDAOMock).getUserById(1);
            verify(validatorMock).validateEmail("john@doe.com");
            verify(userDAOMock).getUserByEmail("john@doe.com");

            verifyNoMoreInteractions(validatorMock);
            verifyNoMoreInteractions(userDAOMock);
            verifyNoInteractions(constituencyServiceMock);
        }

        @Test
        void shouldThrowErrorIfPasswordIsNotValid() {
            // given
            User userBeforeValidation = new User(
                    1L,
                    "John", "Doe", "john@doe.com", "password",
                    "Postcode 1", null, null, null);

            when(userDAOMock.getUserById(1)).thenReturn(Optional.empty());
            when(validatorMock.validateEmail("john@doe.com")).thenReturn(true);
            when(userDAOMock.getUserByEmail("john@doe.com")).thenReturn(Optional.empty());
            when(validatorMock.validatePassword("password")).thenReturn(false);

            // when

            // then
            assertThatThrownBy(() -> userServiceTest.updateUser(1, userBeforeValidation))
                    .hasMessage("Invalid password")
                    .isInstanceOf(BadRequest.class);

            verify(userDAOMock).getUserById(1);
            verify(validatorMock).validateEmail("john@doe.com");
            verify(userDAOMock).getUserByEmail("john@doe.com");
            verify(validatorMock).validatePassword("password");

            verifyNoMoreInteractions(validatorMock);
            verifyNoMoreInteractions(userDAOMock);
            verifyNoInteractions(constituencyServiceMock);
        }

        @Test
        void shouldThrowErrorIfPostcodeIsEmpty() {
            // given
            User userBeforeValidation = new User(
                    1L,
                    "John", "Doe", "john@doe.com", "P@ssword1",
                    "", null, null, null);

            when(userDAOMock.getUserById(1)).thenReturn(Optional.empty());
            when(validatorMock.validateEmail("john@doe.com")).thenReturn(true);
            when(userDAOMock.getUserByEmail("john@doe.com")).thenReturn(Optional.empty());
            when(validatorMock.validatePassword("P@ssword1")).thenReturn(true);

            // when

            // then
            assertThatThrownBy(() -> userServiceTest.updateUser(1, userBeforeValidation))
                    .hasMessage("Postcode cannot be empty")
                    .isInstanceOf(BadRequest.class);

            verify(userDAOMock).getUserById(1);
            verify(validatorMock).validateEmail("john@doe.com");
            verify(userDAOMock).getUserByEmail("john@doe.com");
            verify(validatorMock).validatePassword("P@ssword1");

            verifyNoMoreInteractions(validatorMock);
            verifyNoMoreInteractions(userDAOMock);
            verifyNoInteractions(constituencyServiceMock);
        }

        @Test
        void shouldThrowErrorIfPostcodeIsNull() {
            // given
            User userBeforeValidation = new User(
                    1L,
                    "John", "Doe", "john@doe.com", "P@ssword1",
                    null, null, null, null);

            when(userDAOMock.getUserById(1)).thenReturn(Optional.empty());
            when(validatorMock.validateEmail("john@doe.com")).thenReturn(true);
            when(userDAOMock.getUserByEmail("john@doe.com")).thenReturn(Optional.empty());
            when(validatorMock.validatePassword("P@ssword1")).thenReturn(true);

            // when

            // then
            assertThatThrownBy(() -> userServiceTest.updateUser(1, userBeforeValidation))
                    .hasMessage("Postcode cannot be empty")
                    .isInstanceOf(BadRequest.class);

            verify(userDAOMock).getUserById(1);
            verify(validatorMock).validateEmail("john@doe.com");
            verify(userDAOMock).getUserByEmail("john@doe.com");
            verify(validatorMock).validatePassword("P@ssword1");

            verifyNoMoreInteractions(validatorMock);
            verifyNoMoreInteractions(userDAOMock);
            verifyNoInteractions(constituencyServiceMock);
        }

        @Test
        void shouldThrowErrorIfPostcodeIsNotValid() {
            // given
            User userBeforeValidation = new User(
                    1L,
                    "John", "Doe", "john@doe.com", "P@ssword1",
                    "Postcode 1", null, null, null);

            when(userDAOMock.getUserById(1)).thenReturn(Optional.empty());
            when(validatorMock.validateEmail("john@doe.com")).thenReturn(true);
            when(userDAOMock.getUserByEmail("john@doe.com")).thenReturn(Optional.empty());
            when(validatorMock.validatePassword("P@ssword1")).thenReturn(true);
            when(constituencyServiceMock.getConstituencyFromPostcode("Postcode 1"))
                    .thenThrow(RuntimeException.class);

            // when

            // then
            assertThatThrownBy(() -> userServiceTest.updateUser(1, userBeforeValidation))
                    .hasMessage("Postcode 1 is not a valid postcode")
                    .isInstanceOf(BadRequest.class);

            verify(userDAOMock).getUserById(1);
            verify(validatorMock).validateEmail("john@doe.com");
            verify(userDAOMock).getUserByEmail("john@doe.com");
            verify(validatorMock).validatePassword("P@ssword1");
            verify(constituencyServiceMock).getConstituencyFromPostcode("Postcode 1");

            verifyNoMoreInteractions(validatorMock);
            verifyNoMoreInteractions(userDAOMock);
            verifyNoMoreInteractions(constituencyServiceMock);
        }
    }

    @Nested
    class DeleteUserTests {

        @Test
        void shouldDeleteUserIfIdExists() {
            // given
            User user = new User(
                    1L,
                    "John", "Doe", "john@doe.com", "P@ssword1",
                    null, 100, "Constituency 1", null);

            when(userDAOMock.getUserById(1)).thenReturn(Optional.of(user));
            when(userDAOMock.deleteUser(1)).thenReturn(1);

            // when
            int actual = userServiceTest.deleteUser(1);

            // then
            assertThat(actual).isEqualTo(1);

            verify(userDAOMock).getUserById(1);
            verify(userDAOMock).deleteUser(1);

            verifyNoMoreInteractions(userDAOMock);
        }

        @Test
        void shouldThrowErrorIfUserWithIdDoesNotExist() {
            // given
            when(userDAOMock.getUserById(1)).thenReturn(Optional.empty());

            // when

            // then
            assertThatThrownBy(() -> userServiceTest.deleteUser(1))
                    .hasMessage("User with id: 1 not found")
                    .isInstanceOf(ResourceNotFound.class);

            verify(userDAOMock).getUserById(1);

            verifyNoMoreInteractions(userDAOMock);
        }
    }

    @Nested
    class AuthenticateLoginTests {

        @Test
        void shouldAuthenticateLoginIfCredentialsAreCorrect() {
            // given
            String email = "john@doe.com";
            String password = "P@ssword1";
            User userInDatabase = new User(
                    1L,
                    "John", "Doe", "john@doe.com", "P@ssword1",
                    null, 100, "Constituency 1", null
            );
            Token generatedToken = new Token(
                    1L, LocalDateTime.of(2000, 1, 1, 0, 0),
                    LocalDateTime.of(2000, 1, 1, 0, 15),
                    "secret"
            );

            when(validatorMock.validateEmail("john@doe.com")).thenReturn(true);
            when(userDAOMock.getUserByEmail("john@doe.com")).thenReturn(Optional.of(userInDatabase));
            when(userDAOMock.authenticateLogin(email, password)).thenReturn(Optional.of(userInDatabase));
            when(tokenServiceMock.generateToken(1L)).thenReturn(generatedToken);

            // when
            Token actual = userServiceTest.authenticateLogin(email, password);
            Token expected = generatedToken;

            // then
            assertThat(actual).isEqualTo(expected);

            verify(validatorMock).validateEmail("john@doe.com");
            verify(userDAOMock).getUserByEmail("john@doe.com");
            verify(userDAOMock).authenticateLogin(email, password);
            verify(tokenServiceMock).generateToken(1L);

            verifyNoMoreInteractions(validatorMock);
            verifyNoMoreInteractions(userDAOMock);
            verifyNoMoreInteractions(tokenServiceMock);
        }

        @Test
        void shouldThrowErrorIfEmailIsEmpty() {
            // given
            String email = "";
            String password = "P@ssword1";

            // when

            // then
            assertThatThrownBy(() -> userServiceTest.authenticateLogin(email, password))
                    .hasMessage("Email cannot be empty")
                    .isInstanceOf(BadRequest.class);

            verifyNoInteractions(validatorMock);
            verifyNoInteractions(userDAOMock);
            verifyNoInteractions(tokenServiceMock);
        }

        @Test
        void shouldThrowErrorIfEmailIsNull() {
            // given
            String email = null;
            String password = "P@ssword1";

            // when

            // then
            assertThatThrownBy(() -> userServiceTest.authenticateLogin(email, password))
                    .hasMessage("Email cannot be empty")
                    .isInstanceOf(BadRequest.class);

            verifyNoInteractions(validatorMock);
            verifyNoInteractions(userDAOMock);
            verifyNoInteractions(tokenServiceMock);
        }

        @Test
        void shouldThrowErrorIfPasswordIsEmpty() {
            // given
            String email = "john@doe.com";
            String password = "";

            // when

            // then
            assertThatThrownBy(() -> userServiceTest.authenticateLogin(email, password))
                    .hasMessage("Password cannot be empty")
                    .isInstanceOf(BadRequest.class);

            verifyNoInteractions(validatorMock);
            verifyNoInteractions(userDAOMock);
            verifyNoInteractions(tokenServiceMock);
        }

        @Test
        void shouldThrowErrorIfPasswordIsNull() {
            // given
            String email = "john@doe.com";
            String password = null;

            // when

            // then
            assertThatThrownBy(() -> userServiceTest.authenticateLogin(email, password))
                    .hasMessage("Password cannot be empty")
                    .isInstanceOf(BadRequest.class);

            verifyNoInteractions(validatorMock);
            verifyNoInteractions(userDAOMock);
            verifyNoInteractions(tokenServiceMock);
        }

        @Test
        void shouldThrowErrorIfEmailIsNotValid() {
            // given
            String email = "johndoe.com";
            String password = "P@ssword1";

            when(validatorMock.validateEmail(email)).thenReturn(false);

            // when

            // then
            assertThatThrownBy(() -> userServiceTest.authenticateLogin(email, password))
                    .hasMessage("Invalid email address")
                    .isInstanceOf(BadRequest.class);

            verify(validatorMock).validateEmail(email);

            verifyNoMoreInteractions(validatorMock);
            verifyNoInteractions(userDAOMock);
            verifyNoInteractions(tokenServiceMock);
        }

        @Test
        void shouldThrowErrorIfUserWithEmailDoesNotExist() {
            // given
            String email = "johndoe.com";
            String password = "P@ssword1";

            when(validatorMock.validateEmail(email)).thenReturn(true);
            when(userDAOMock.getUserByEmail(email)).thenReturn(Optional.empty());

            // when

            // then
            assertThatThrownBy(() -> userServiceTest.authenticateLogin(email, password))
                    .hasMessage("No user with this email exists")
                    .isInstanceOf(BadRequest.class);

            verify(validatorMock).validateEmail(email);
            verify(userDAOMock).getUserByEmail(email);

            verifyNoMoreInteractions(validatorMock);
            verifyNoMoreInteractions(userDAOMock);
            verifyNoInteractions(tokenServiceMock);
        }

        @Test
        void shouldThrowErrorIfPasswordIsIncorrect() {
            // given
            String email = "johndoe.com";
            String password = "password";
            User userInDatabase = new User(
                    1L,
                    "John", "Doe", "john@doe.com", "P@ssword1",
                    null, 100, "Constituency 1", null);

            when(validatorMock.validateEmail(email)).thenReturn(true);
            when(userDAOMock.getUserByEmail(email)).thenReturn(Optional.of(userInDatabase));
            when(userDAOMock.authenticateLogin(email, password)).thenReturn(Optional.empty());

            // when

            // then
            assertThatThrownBy(() -> userServiceTest.authenticateLogin(email, password))
                    .hasMessage("Incorrect password")
                    .isInstanceOf(BadRequest.class);

            verify(validatorMock).validateEmail(email);
            verify(userDAOMock).getUserByEmail(email);
            verify(userDAOMock).authenticateLogin(email, password);

            verifyNoMoreInteractions(validatorMock);
            verifyNoMoreInteractions(userDAOMock);
            verifyNoInteractions(tokenServiceMock);
        }
    }

    @Nested
    class GetLoggedInUserByIdTests {

        @Test
        void shouldGetUserIfAuthenticated() {
            // given
            UserService userServiceSpy = spy(userServiceTest);


            Token token = new Token(
                    1L, LocalDateTime.of(2000, 1, 1, 0, 0),
                    LocalDateTime.of(2000, 1, 1, 0, 15),
                    "secret"
            );
            User userInDatabase = new User(
                    1L,
                    "John", "Doe", "john@doe.com", "P@ssword1",
                    null, 100, "Constituency 1", null
            );

            when(tokenServiceMock.authenticateToken(1, token)).thenReturn(1);
            Mockito.doReturn(Optional.of(userInDatabase)).when(userServiceSpy).getUserById(1);

            // when
            Optional<User> actual = userServiceSpy.getLoggedInUserById(1, token);
            Optional<User> expected = Optional.of(userInDatabase);

            // then
            assertThat(actual).isEqualTo(expected);

            verify(tokenServiceMock).authenticateToken(1, token);
            verify(userServiceSpy).getUserById(1);

            verifyNoMoreInteractions(tokenServiceMock);
        }

        @Test
        void shouldThrowErrorIfTokenFailsAuthentication() {
            // given
            Token token = new Token(
                    1L, LocalDateTime.of(2000, 1, 1, 0, 0),
                    LocalDateTime.of(2000, 1, 1, 0, 15),
                    "secret"
            );

            when(tokenServiceMock.authenticateToken(1, token)).thenThrow(new RuntimeException());

            // when

            // then
            assertThatThrownBy(() -> userServiceTest.getLoggedInUserById(1, token))
                    .hasMessage("User is not authorized")
                    .isInstanceOf(Unauthorized.class);

//            verify(tokenServiceMock.authenticateToken(1, token));

//            verifyNoMoreInteractions(tokenServiceMock);
        }
    }

    @Nested
    class LogOutTests {

        @Test
        void shouldLogOut() {
            // given
            Token token = new Token(
                    1L, LocalDateTime.of(2000, 1, 1, 0, 0),
                    LocalDateTime.of(2000, 1, 1, 0, 15),
                    "secret"
            );

            when(tokenServiceMock.blackListToken(token)).thenReturn(1);

            // when
            int actual = userServiceTest.logOut(token);

            // then
            assertThat(actual).isEqualTo(1);

            verify(tokenServiceMock).blackListToken(token);
            verifyNoMoreInteractions(tokenServiceMock);
        }
    }
}