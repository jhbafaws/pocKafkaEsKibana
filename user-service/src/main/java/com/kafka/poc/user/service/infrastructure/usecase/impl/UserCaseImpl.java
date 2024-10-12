package com.kafka.poc.user.service.infrastructure.usecase.impl;


import com.kafka.poc.user.service.domain.model.Activity;
import com.kafka.poc.user.service.domain.model.User;
import com.kafka.poc.user.service.domain.service.IUserService;
import com.kafka.poc.user.service.infrastructure.dto.ActivityDto;
import com.kafka.poc.user.service.infrastructure.dto.UserDto;
import com.kafka.poc.user.service.infrastructure.exception.BadRequestException;
import com.kafka.poc.user.service.infrastructure.usecase.IUserCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserCaseImpl implements IUserCase {

    private static final Logger log = LoggerFactory.getLogger(UserCaseImpl.class);

    @Autowired
    IUserService iUserService;

    @Override
    public UserDto createUser(UserDto userDto) {

  //      findbyEmail(userDto.getEmail());

        log.info("Input Service createUser: {}", userDto);

        User userEntity = new User();
        userEntity.setUserName(userDto.getUserName());
        userEntity.setName(userDto.getName());
        userEntity.setEmail(userDto.getEmail());
        userEntity.setLastName(userDto.getLastName());

        List<Activity> activityEntityList = new ArrayList<>();

        for (int i = 0; i < userDto.getActivities().size(); i++) {
            Activity activityEntity = new Activity();
            activityEntity.setUser(userEntity);
            activityEntity.setType(userDto.getActivities().get(i).getType());
            activityEntity.setDescription(userDto.getActivities().get(i).getDescription());
            activityEntityList.add(activityEntity);
        }
        userEntity.setActivities(activityEntityList);

        User saveUser = iUserService.saveUser(userEntity);

        UserDto resp = new UserDto();
        resp.setId(saveUser.getId());
        resp.setUserName(saveUser.getUserName());
        resp.setEmail(saveUser.getEmail());
        resp.setName(saveUser.getName());
        resp.setLastName(saveUser.getLastName());

        List<ActivityDto> list = new ArrayList<>();

        for (int i = 0; i < saveUser.getActivities().size(); i++) {
            ActivityDto activityDto = new ActivityDto();
            activityDto.setId(saveUser.getActivities().get(i).getId());
            activityDto.setType(saveUser.getActivities().get(i).getType());
            activityDto.setDescription(saveUser.getActivities().get(i).getDescription());

            list.add(activityDto);
        }

        resp.setActivities(list);
        log.info("Output Service createUser: {}", resp);
        return resp;
    }

    @Override
    public UserDto findbyEmail(String email) {

        Optional<User> respByEmail = iUserService.getByEmail(email);

        if (respByEmail.isPresent()) {
            return null;
        }

        UserDto resp = new UserDto();

        resp.setId(respByEmail.get().getId());
        resp.setName(respByEmail.get().getName());
        resp.setEmail(respByEmail.get().getEmail());
        resp.setLastName(respByEmail.get().getLastName());
        return resp;

    }
}
