package com.airfrance.offer.domain.service;

import com.airfrance.offer.domain.common.OfferValidation;
import com.airfrance.offer.domain.common.model.QueryResponse;
import com.airfrance.offer.domain.mapper.UserBeanMapper;
import com.airfrance.offer.domain.mapper.UserMapper;
import com.airfrance.offer.domain.model.UserBean;
import com.airfrance.offer.domain.repository.UserRepository;
import com.airfrance.offer.domain.repository.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Optional;
import java.util.SortedSet;
import java.util.TreeSet;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final UserBeanMapper userBeanMapper;

    private final UserMapper userMapper;

    @Autowired
    public UserService(UserRepository userRepository, UserBeanMapper userBeanMapper, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userBeanMapper = userBeanMapper;
        this.userMapper = userMapper;
    }

    /**
     * @param id
     * @return {@link QueryResponse<UserBean>}
     * @apiNote find user on DB, id must be positive
     */
    public QueryResponse<UserBean> getUser(Long id) {

        if (id == null || id < 1) {
            return new QueryResponse<UserBean>().addError("id value must be positive")
                    .setStatus(HttpStatus.BAD_REQUEST);
        }

        Optional<User> user = userRepository.findById(id);
        QueryResponse<UserBean> userBeanQueryResponse = new QueryResponse<>();

        if (user.isPresent()) {
            UserBean userBean = userBeanMapper.map(user.get());
            userBeanQueryResponse.setObjectBody(userBean);
            userBeanQueryResponse.setStatus(HttpStatus.OK);
            return userBeanQueryResponse;
        }

        return userBeanQueryResponse.setStatus(HttpStatus.NOT_FOUND);

    }


    /**
     * @param userBean
     * @return {@link QueryResponse<UserBean>}
     * @apiNote  save a user on DB, User must be valid
     */
    public QueryResponse<UserBean> saveUser(UserBean userBean) {

        if (userBean == null) {
            return new QueryResponse<UserBean>()
                    .addError("Element userBean is null")
                    .setStatus(HttpStatus.BAD_REQUEST);
        }

        SortedSet<String> errors = new TreeSet<>();
        errors.addAll(OfferValidation.validate(userBean));
        errors.addAll(adultAndLivingInFrance(userBean));

        if (errors.isEmpty()) {
            userRepository.save(userMapper.map(userBean));
            return new QueryResponse<UserBean>()
                    .setStatus(HttpStatus.CREATED);
        }

        return new QueryResponse<UserBean>()
                .setErrors(errors)
                .setStatus(HttpStatus.BAD_REQUEST);
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
