package com.airfrance.offer.domain.mapper;

import com.airfrance.offer.domain.model.Gender;
import com.airfrance.offer.domain.model.UserBean;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserMapperTest {

    @InjectMocks
    UserMapper userMapper;

    @Test
    void testMap() {

        UserBean userBean = new UserBean(null, "", LocalDate.now(), "", "", Gender.M);
        assertNotNull(userMapper.map(userBean));

    }

    @Test
    void testNullParamMap() {
        assertNull(userMapper.map(null));
    }
}