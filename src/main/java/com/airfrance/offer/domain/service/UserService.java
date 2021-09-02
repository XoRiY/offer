package com.airfrance.offer.domain.service;

import com.airfrance.offer.domain.common.exception.BadContentException;
import com.airfrance.offer.domain.common.exception.ResourceNotFoundException;
import com.airfrance.offer.domain.common.model.QueryResponse;
import com.airfrance.offer.domain.common.validation.OfferValidation;
import com.airfrance.offer.domain.mapper.UserBeanMapper;
import com.airfrance.offer.domain.mapper.UserMapper;
import com.airfrance.offer.domain.model.UserBean;
import com.airfrance.offer.domain.repository.UserRepository;
import com.airfrance.offer.domain.repository.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.TreeSet;

/**
 * @author Tahar Kerdoud
 * @apiNote Business class service
 */
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
            throw  new IllegalArgumentException("id value must be positive");
        }

        Optional<User> user = userRepository.findById(id);
        QueryResponse<UserBean> userBeanQueryResponse = new QueryResponse<>();

        if (user.isEmpty()) {
            throw new ResourceNotFoundException("no resource was found for id :" + id);
        }

        UserBean userBean = userBeanMapper.map(user.get());
        userBeanQueryResponse.setObjectBody(userBean);
        userBeanQueryResponse.setStatus(HttpStatus.OK);
        return userBeanQueryResponse;


    }


    /**
     * @param userBean
     * @return {@link QueryResponse<UserBean>}
     * @apiNote save a user on DB, User must be valid
     */
    public QueryResponse<UserBean> saveUser(UserBean userBean) {
        List<String> constraintsList = OfferValidation.validate(userBean);
        if (!constraintsList.isEmpty()) {
            throw new BadContentException(new TreeSet<>(constraintsList));
        }

        userRepository.save(userMapper.map(userBean));
        return new QueryResponse<UserBean>()
                .setStatus(HttpStatus.CREATED);

    }


}
