package com.team.services;

import com.team.entity.UserInformation;
import com.team.repository.UserInformationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserInformationService {

    private final UserInformationRepository userInformationRepository;

    @Autowired
    public UserInformationService(UserInformationRepository userInformationRepository) {
        this.userInformationRepository = userInformationRepository;
    }

    public List<UserInformation> getAllUsersInfo(){
        return this.userInformationRepository.findAll();
    }

    public UserInformation saveUserInfo (UserInformation userInfo){
        return this.userInformationRepository.save(userInfo);
    }

    public UserInformation getUserInfoById (Long id){
        return this.userInformationRepository.findByUser_Id(id);
    }

}
