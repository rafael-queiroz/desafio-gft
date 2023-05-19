package com.gft.demo.domain.model;

import com.gft.demo.domain.entity.Skill;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import javax.persistence.OneToMany;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Relation(collectionRelation = "users", itemRelation = "user")
public class UserModel extends RepresentationModel<UserModel> {

    Long id;

    @NotBlank(message = "Required name")
    @Pattern(regexp = "|^[A-Za-záàâãéèêíïóôõöúçñÁÀÂÃÉÈÍÏÓÔÕÖÚÇÑ ]+$", message = "Only alphanumerics")
    String name;

    @NotBlank(message = "Required email")
    @Email(message = "Invalid email")
    String email;

    LocalDate dateOfBirth;

    @NotBlank(message = "Required address")
    @Pattern(regexp = "|^[a-zA-Z0-9\\s,\\-()záàâãéèêíïóôõöúçñÁÀÂÃÉÈÍÏÓÔÕÖÚÇÑ]+$", message = "Address cannot contain symbols")
    String address;

    @Size(min = 1, message = "At least one skill is required")
    Set<String> skills;

}
