package com.ktr.offer.domain.common.mapper;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.ktr.offer.domain.user.model.Gender;
import com.ktr.offer.domain.user.mapper.UserBeanMapper;
import com.ktr.offer.domain.user.repository.model.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

@ExtendWith(MockitoExtension.class)
class UserBeanMapperTest {

    @InjectMocks
    UserBeanMapper userBeanMapper;


    @Test
    void testNullParamMap() {

        assertNull(userBeanMapper.map(null));

    }

    @Test
    void testMap() {
        assertNotNull(userBeanMapper.map(new User(1L, "", LocalDate.now(), "", "", Gender.M)));

    }
}