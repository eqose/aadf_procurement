package com.team.repository;

import com.team.entity.UserInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserInformationRepository extends JpaRepository<UserInformation, Long> {

    UserInformation findByUser_Id(Long id);

}
