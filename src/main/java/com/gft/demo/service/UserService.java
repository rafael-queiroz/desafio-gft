package com.gft.demo.service;

import com.gft.demo.domain.assembler.UserModelAssembler;
import com.gft.demo.domain.entity.Skill;
import com.gft.demo.domain.entity.User;
import com.gft.demo.domain.model.UserModel;
import com.gft.demo.exception.ResourceNotFoundException;
import com.gft.demo.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserService {

    final UserRepository repository;
    final UserModelAssembler assembler;
    final PagedResourcesAssembler<User> pagedResourcesAssembler;

    @Transactional
    public UserModel save(UserModel model) {
        var user = User.toEntity(model);
        user.setSkills(model.getSkills().stream().map(s -> Skill.builder().description(s).user(user).build()).collect(Collectors.toSet()));
        var userSaved =  repository.save(user);
        return assembler.toModel(userSaved);
    }

    public UserModel findModelById(Long id) {
        User entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this User ID"));
        return assembler.toModel(entity);
    }


    public PagedModel<UserModel> findAllPageable(Pageable pageable) {
        List<User> listEntity = repository.findAll();

        int start = (int) pageable.getOffset();
        int end = ((start + pageable.getPageSize()) > listEntity.size() ? listEntity.size() : (start + pageable.getPageSize()));
        Page<User> page = new PageImpl<User>(listEntity.subList(start, end), pageable, listEntity.size());

        return pagedResourcesAssembler.toModel(page, assembler);
    }
}
