package com.ktr.offer.domain.user.service;

import com.ktr.offer.domain.common.exception.BadContentException;
import com.ktr.offer.domain.common.exception.ResourceNotFoundException;
import com.ktr.offer.domain.common.model.QueryResponse;
import com.ktr.offer.domain.common.validation.OfferValidation;
import com.ktr.offer.domain.user.mapper.UserBeanMapper;
import com.ktr.offer.domain.user.mapper.UserMapper;
import com.ktr.offer.domain.user.model.UserBean;
import com.ktr.offer.domain.user.repository.UserRepository;
import com.ktr.offer.domain.user.repository.model.User;
import org.springframework.beans.factory.annotation.Autowired;
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
     * @return {@link QueryResponse <UserBean>}
     * @apiNote find user on DB, id must be positive
     */
    public UserBean getUser(Long id) {

        if (id == null || id < 1) {
            throw  new IllegalArgumentException("id value must be positive");
        }

        Optional<User> user = userRepository.findById(id);


        if (user.isEmpty()) {
            throw new ResourceNotFoundException("no resource was found for id :" + id);
        }

         return userBeanMapper.map(user.get());
    }


    /**
     * @param userBean
     * @return {@link QueryResponse<UserBean>}
     * @apiNote save a user on DB, User must be valid
     */
    public UserBean saveUser(UserBean userBean) {
        List<String> constraintsList = OfferValidation.validate(userBean);

        if (!constraintsList.isEmpty()) {
            throw new BadContentException(new TreeSet<>(constraintsList));
        }

        User user = userRepository.save(userMapper.map(userBean));

        userBean.setId(user.getId());
        return userBean;

    }


}
