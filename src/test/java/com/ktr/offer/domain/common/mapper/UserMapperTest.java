package com.ktr.offer.domain.common.mapper;

import com.ktr.offer.domain.user.model.Gender;
import com.ktr.offer.domain.user.model.UserBean;
import com.ktr.offer.domain.user.mapper.UserMapper;
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