package com.ktr.offer.domain.user.repository;

import com.ktr.offer.domain.user.repository.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Tahar Kerdoud
 * {@link UserRepository}
 */
@Repository
public interface UserRepository extends CrudRepository<User, Long> {

}



