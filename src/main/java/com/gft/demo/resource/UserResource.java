package com.gft.demo.resource;

import com.gft.demo.domain.model.UserModel;
import com.gft.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserResource {

    final UserService service;

    @PostMapping
    public ResponseEntity<UserModel> create(@Valid @RequestBody UserModel userModel){
        UserModel userModelSaved = service.save(userModel);
        return ResponseEntity.status(HttpStatus.CREATED).contentType(MediaType.APPLICATION_JSON).body(userModelSaved);
    }

    @GetMapping(value = "/{id}", produces = {"application/json", "application/xml", "application/x-yaml"})
    public ResponseEntity<UserModel> getById(@PathVariable(value = "id") Long id){
        return ResponseEntity.ok(service.findModelById(id));
    }



    @GetMapping
    public ResponseEntity<PagedModel<UserModel>> findAllPageable(@RequestParam(value = "page", defaultValue = "0") int page,
                                                                 @RequestParam(value = "limit", defaultValue = "2") int limit,
                                                                 @RequestParam(value = "direction", defaultValue = "asc") String direction,
                                                                 @RequestParam(value = "order", defaultValue = "description") String order) {

        var sortDirection = "desc".equalsIgnoreCase(direction) ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageableRequest = PageRequest.of(page, limit, Sort.by(sortDirection, order));
        PagedModel<UserModel> resources = service.findAllPageable(pageableRequest);
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(resources);
    }

}
