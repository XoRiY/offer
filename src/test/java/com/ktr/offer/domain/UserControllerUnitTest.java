package com.ktr.offer.domain;

import com.ktr.offer.domain.common.exception.BadContentException;
import com.ktr.offer.domain.user.model.Gender;
import com.ktr.offer.domain.user.model.UserBean;
import com.ktr.offer.domain.user.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserControllerUnitTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    @Test
    void testGet() {

        Mockito.when(userService.
                getUser(1L)).thenReturn(getUserBean());

        ResponseEntity<UserBean> queryResponseResponseEntity = userController.get(1L);

        assertNotNull(queryResponseResponseEntity);
        UserBean body = queryResponseResponseEntity.getBody();
        assertNotNull(body);
        Assertions.assertEquals(Gender.M, queryResponseResponseEntity.getBody().getGender());
        assertEquals(LocalDate.of(2000, 12, 12), body.getBirthDate());
        assertEquals("france", body.getCountryOfResidence());
        assertEquals("0606060606", body.getPhoneNumber());
        assertEquals("john", body.getName());
        assertEquals(1L, body.getId());

        Mockito.verify(userService).getUser(1L);
        Mockito.verifyNoMoreInteractions(userService);


    }

    @Test
    void testGetWithNullParam() {
        Mockito.when(userService.
                getUser(null)).thenCallRealMethod();

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            userController.get(null);
        });

        assertEquals("id value must be positive", exception.getMessage());

        Mockito.verify(userService).getUser(null);
        Mockito.verifyNoMoreInteractions(userService);

    }


    @Test
    void testGetWithNegativeParam() {
        Mockito.when(userService.
                getUser(-1L)).thenCallRealMethod();

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            userController.get(-1L);
        });

        assertEquals("id value must be positive", exception.getMessage());


        Mockito.verify(userService).getUser(-1L);
        Mockito.verify(userService).getUser(Mockito.anyLong());
        Mockito.verifyNoMoreInteractions(userService);

    }

    @Test
    void testGetWithAnotherNegativeParam() {
        Mockito.when(userService.
                getUser(-999L)).thenCallRealMethod();
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            userController.get(-999L);
        });

        assertEquals("id value must be positive", exception.getMessage());


        Mockito.verify(userService).getUser(-999L);
        Mockito.verify(userService).getUser(Mockito.anyLong());
        Mockito.verifyNoMoreInteractions(userService);
    }

    @Test
    void testPost() {
        Mockito.when(userService.
                saveUser(Mockito.any(UserBean.class))).thenReturn(getUserBean());

        UserBean userBean = getUserBean();
        ResponseEntity<UserBean> queryResponseResponseEntity = userController.post(userBean);
        assertNotNull(queryResponseResponseEntity);
        assertNotNull(queryResponseResponseEntity.getBody());
        assertNotNull(queryResponseResponseEntity.getBody());

        Mockito.verify(userService).saveUser(Mockito.any(UserBean.class));
        Mockito.verify(userService).saveUser(userBean);
        Mockito.verifyNoMoreInteractions(userService);

    }

    @Test
    void testPostWithNullParam() {

        Mockito.when(userService.
                saveUser(null)).thenCallRealMethod();

        BadContentException exception = assertThrows(BadContentException.class, () -> {
            userController.post(null);
        });

        assertEquals(new TreeSet<>(List.of("object may not be null")), exception.getMsgs());


        Mockito.verify(userService).saveUser(null);
        Mockito.verifyNoMoreInteractions(userService);
    }

    @Test
    void testPostWithMinObject() {

        Mockito.when(userService.
                saveUser(Mockito.any(UserBean.class))).thenCallRealMethod();

        UserBean givenUserBean = UserBean.builder().phoneNumber("0606060606").build();
        BadContentException exception = assertThrows(BadContentException.class, () -> {
            userController.post(givenUserBean);
        });

        SortedSet<String> expectedErrorList = new TreeSet<>(List.of("Birth Date may not be Null", "Country Of Residence may not be Null or Blank", "Name may not be Null or Blank", "user must be adult", "user must live in France"));

        assertEquals(expectedErrorList, exception.getMsgs());


        Mockito.verify(userService).saveUser(givenUserBean);
        Mockito.verify(userService).saveUser(Mockito.any(UserBean.class));

        Mockito.verifyNoMoreInteractions(userService);
    }

    private static UserBean getUserBean() {

        return UserBean.builder()
                .name("john")
                .birthDate(LocalDate.of(2000, 12, 12))
                .gender(Gender.M)
                .phoneNumber("0606060606")
                .countryOfResidence("france")
                .id(1L)
                .build();

    }


}