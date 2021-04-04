package com.devfirst.admin.epic.domain.mapper;

import com.devfirst.admin.epic.config.auth.dto.SessionUser;
import com.devfirst.admin.epic.domain.user.User;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(builder = @Builder(disableBuilder = true))
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    SessionUser userToSessionUser(User user);
}
