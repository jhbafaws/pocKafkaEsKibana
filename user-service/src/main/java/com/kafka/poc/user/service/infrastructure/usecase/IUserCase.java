package com.kafka.poc.user.service.infrastructure.usecase;

import com.kafka.poc.user.service.infrastructure.dto.UserDto;

public interface IUserCase {

    UserDto createUser(UserDto userDto);

    UserDto findbyEmail(String email);

}
