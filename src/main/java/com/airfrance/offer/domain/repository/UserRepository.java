package com.airfrance.offer.domain.repository;

import com.airfrance.offer.domain.repository.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Tahar Kerdoud
 * {@link UserRepository}
 */
@Repository
public interface UserRepository extends CrudRepository<User, Long> {

}



