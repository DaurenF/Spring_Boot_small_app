package com.TRZ.Taraz.reprository;

import com.TRZ.Taraz.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);


}
