package com.airfrance.offer.domain.mapper;

import com.airfrance.offer.domain.model.UserBean;
import com.airfrance.offer.domain.repository.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserBeanMapper implements Mapper<User, UserBean> {

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
