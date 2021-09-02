package com.airfrance.offer.domain.mapper;

import com.airfrance.offer.domain.model.UserBean;
import com.airfrance.offer.domain.repository.model.User;
import org.springframework.stereotype.Component;

/**
 * @author Tahar Kerdoud
 * @apiNote implementation of Inteface mapper
 */
@Component
public class UserMapper implements Mapper<UserBean, User> {

    /**
     * @param userBean
     * @return User
     */
    @Override
    public User map(UserBean userBean) {
        if (userBean != null) {
            return User.builder()
                    .name(userBean.getName())
                    .countryOfResidence(userBean.getCountryOfResidence())
                    .phoneNumber(userBean.getPhoneNumber())
                    .birthDate(userBean.getBirthDate())
                    .gender(userBean.getGender())
                    .build();
        }
        return null;
    }
}
