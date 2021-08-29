package com.airfrance.offer.domain.service;

import com.airfrance.offer.domain.common.OfferValidation;
import com.airfrance.offer.domain.common.model.QueryResponse;
import com.airfrance.offer.domain.common.model.Status;
import com.airfrance.offer.domain.mapper.UserBeanMapper;
import com.airfrance.offer.domain.mapper.UserMapper;
import com.airfrance.offer.domain.model.UserBean;
import com.airfrance.offer.domain.repository.UserRepository;
import com.airfrance.offer.domain.repository.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    UserBeanMapper userBeanMapper;

    @Autowired
    UserMapper userMapper;

    public QueryResponse<UserBean> getUser(Long id) {

        if (id == null || id < 1) {
            return new QueryResponse<UserBean>().addError("id value must be positive")
                    .setStatus(Status.BAD_CONTENT);
        }

        Optional<User> user = userRepository.findById(id);
        QueryResponse<UserBean> userBeanQueryResponse = new QueryResponse<>();

        if (user.isPresent()) {
            UserBean userBean = userBeanMapper.map(user.get());
            userBeanQueryResponse.setObjectBody(userBean);
            userBeanQueryResponse.setStatus(Status.OK);
            return userBeanQueryResponse;
        }

        return userBeanQueryResponse.setStatus(Status.NO_CONTENT);

    }

    public QueryResponse<UserBean> saveUser(UserBean userBean) {

        if (userBean == null) {
            return new QueryResponse<UserBean>()
                    .addError("Element userBean is null")
                    .setStatus(Status.BAD_CONTENT);
        }

        SortedSet<String> errors = new TreeSet<>();
        errors.addAll(OfferValidation.validate(userBean));
        errors.addAll(adultAndLivingInFrance(userBean));

        if (errors.isEmpty()) {
            userRepository.save(userMapper.map(userBean));
            return new QueryResponse<UserBean>()
                    .setStatus(Status.OK);
        }
        return new QueryResponse<UserBean>()
                .setErrors(errors)
                .setStatus(Status.BAD_CONTENT);
    }


    private SortedSet<String> adultAndLivingInFrance(UserBean userBean) {
        SortedSet<String> errors = new TreeSet<>();
        String errorAdult = userIsAdult(userBean);
        String errorLiveInFrance = userLiveInFrance(userBean);

        if (errorAdult != null)
            errors.add(errorAdult);

        if (errorLiveInFrance != null)
            errors.add(errorLiveInFrance);

        return errors;
    }

    private String userIsAdult(UserBean userBean) {
        if (userBean.getBirthDate() != null && ChronoUnit.YEARS.between(userBean.getBirthDate(), LocalDate.now()) > 18) {
            return null;
        }
        return "user must be adult";
    }

    private String userLiveInFrance(UserBean userBean) {
        if (userBean != null && "france".equalsIgnoreCase(userBean.getCountryOfResidence())) {
            return null;
        }
        return "user must live in France";
    }
}
