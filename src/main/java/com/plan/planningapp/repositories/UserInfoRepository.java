package com.plan.planningapp.repositories;

import com.plan.planningapp.model.UserInfo;
import org.springframework.data.repository.CrudRepository;

public interface UserInfoRepository extends CrudRepository<UserInfo, Integer> {
    UserInfo findByEmail(String email);
    boolean existsByEmail(String email);
}
