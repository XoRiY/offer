package com.ktr.offer.domain.user.mapper;

import com.ktr.offer.domain.common.mapper.Mapper;
import com.ktr.offer.domain.user.model.UserBean;
import com.ktr.offer.domain.user.repository.model.User;
import org.springframework.stereotype.Component;

/**
 * @author Tahar Kerdoud
 * @apiNote implementation of Inteface mapper
 */
@Component
public class UserBeanMapper implements Mapper<User, UserBean> {


    /**
     * @param user
     * @return UserBean
     */
    @Override
    public UserBean map(User user) {
        if (user != null){
            return UserBean.builder()
                    .id(user.getId())
                    .name(user.getName())
                    .countryOfResidence(user.getCountryOfResidence())
                    .phoneNumber(user.getPhoneNumber())
                    .gender(user.getGender())
                    .birthDate(user.getBirthDate())
                    .build();
        }
        return null;
    }
}
