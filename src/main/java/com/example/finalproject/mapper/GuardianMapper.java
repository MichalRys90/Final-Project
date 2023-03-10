package com.example.finalproject.mapper;

import com.example.finalproject.domain.Guardian;
import com.example.finalproject.domain.dto.GuardianDto;
import com.example.finalproject.exception.UserWithGivenIdDoesntExist;
import com.example.finalproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class GuardianMapper {

    @Autowired
    private UserRoleMapper userRoleMapper;
    @Autowired
    UserRepository userRepository;

    public GuardianDto mapToGuardianDto(final Guardian guardian) {
        return new GuardianDto(
                guardian.getId(),
                guardian.getUser().getUserId(),
                userRoleMapper.mapToUserRoleDtoSet(guardian.getPatients()));
    }

    public Guardian mapToGuardian(final GuardianDto guardianDto) throws UserWithGivenIdDoesntExist {
        return new Guardian(
                userRepository.findById(guardianDto.getUserId()).orElseThrow(UserWithGivenIdDoesntExist::new),
                new HashSet<>());
    }

    public Set<GuardianDto> mapToGuardianDtoSet(final Set<Guardian> guardians) {
        return guardians.stream()
                .map(this::mapToGuardianDto)
                .collect(Collectors.toSet());
    }
}
