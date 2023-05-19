package com.gft.demo.domain.assembler;

import com.gft.demo.resource.UserResource;
import com.gft.demo.domain.entity.User;
import com.gft.demo.domain.model.UserModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class UserModelAssembler extends RepresentationModelAssemblerSupport<User, UserModel> {

	public UserModelAssembler() {
        super(UserResource.class, UserModel.class);
    }
	
	@Override
	public UserModel toModel(User entity) {
		UserModel model = instantiateModel(entity);

		model.add(linkTo(
                methodOn(UserResource.class)
                        .getById(entity.getId()))
                .withSelfRel());

		model.setId(entity.getId());
		model.setName(entity.getName());
		model.setEmail(entity.getEmail());
		model.setDateOfBirth(entity.getDateOfBirth());
		model.setAddress(entity.getAddress());
		model.setSkills(entity.getSkills().stream().map(s -> new String(s.getDescription())).collect(Collectors.toSet()));
		return model;
	}

}
