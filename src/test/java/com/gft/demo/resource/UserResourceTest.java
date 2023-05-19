package com.gft.demo.resource;

import com.gft.demo.repository.UserRepository;
import com.gft.demo.service.UserService;
import org.hamcrest.core.Is;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.nio.charset.Charset;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@WebMvcTest
@AutoConfigureMockMvc
public class UserResourceTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    UserResource userResource;

    @MockBean
    UserService userService;

    @Test
    public void whenUserControllerInjected_thenNotNull() throws Exception {
        assertThat(userResource).isNotNull();
    }

    @Test
    public void whenGetRequestToUsers_thenCorrectResponse() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/users")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
    }


    @Test
    public void whenPostRequestToUsersAndValidUser_thenCorrectResponse() throws Exception {
        String user = "{\"name\": \"Rafael Queiroz\", " +
                "\"email\" : \"rafael.qp@hotmail.com\", " +
                "\"dateOfBirth\": \"1985-02-14\", " +
                "\"address\": \"Rua José Inaldo, 759, Praia do Imperador Guia de Pacobaíba, Magé - RJ\", " +
                "\"skills\" : [\"teste\", \"teste 56\", \"teste 3\"]}";
        mockMvc.perform(MockMvcRequestBuilders.post("/users")
                        .content(user)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void whenPostRequestToUsersAndInValidNameBlank_thenCorrectReponse() throws Exception {
        String user = "{\"name\": \"\", " +
                "\"email\" : \"rafael.qp@hotmail.com\", " +
                "\"dateOfBirth\": \"1985-02-14\", " +
                "\"address\": \"Rua José Inaldo, 759, Praia do Imperador Guia de Pacobaíba, Magé - RJ\", " +
                "\"skills\" : [\"teste\", \"teste 56\", \"teste 3\"]}";
        mockMvc.perform(MockMvcRequestBuilders.post("/users")
                        .content(user)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Is.is("Required name")))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void whenPostRequestToUsersAndInValidNameInvalid_thenCorrectReponse() throws Exception {
        String user = "{\"name\": \"Rafael%\", " +
                "\"email\" : \"rafael.qp@hotmail.com\", " +
                "\"dateOfBirth\": \"1985-02-14\", " +
                "\"address\": \"Rua José Inaldo, 759, Praia do Imperador Guia de Pacobaíba, Magé - RJ\", " +
                "\"skills\" : [\"teste\", \"teste 56\", \"teste 3\"]}";
        mockMvc.perform(MockMvcRequestBuilders.post("/users")
                        .content(user)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Is.is("Only alphanumerics")))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void whenPostRequestToUsersAndInValidEmailBlank_thenCorrectReponse() throws Exception {
        String user = "{\"name\": \"Rafael Queiroz\", " +
                "\"email\" : \"\", " +
                "\"dateOfBirth\": \"1985-02-14\", " +
                "\"address\": \"Rua José Inaldo, 759, Praia do Imperador Guia de Pacobaíba, Magé - RJ\", " +
                "\"skills\" : [\"teste\", \"teste 56\", \"teste 3\"]}";
        mockMvc.perform(MockMvcRequestBuilders.post("/users")
                        .content(user)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.email", Is.is("Required email")))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void whenPostRequestToUsersAndInValidEmailInvalid_thenCorrectReponse() throws Exception {
        String user = "{\"name\": \"Rafael Queiroz\", " +
                "\"email\" : \"rafael.queirozhotmail.com\", " +
                "\"dateOfBirth\": \"1985-02-14\", " +
                "\"address\": \"Rua José Inaldo, 759, Praia do Imperador Guia de Pacobaíba, Magé - RJ\", " +
                "\"skills\" : [\"teste\", \"teste 56\", \"teste 3\"]}";
        mockMvc.perform(MockMvcRequestBuilders.post("/users")
                        .content(user)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.email", Is.is("Invalid email")))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void whenPostRequestToUsersAndInValidAddressBlank_thenCorrectReponse() throws Exception {
        String user = "{\"name\": \"Rafael Queiroz\", " +
                "\"email\" : \"rafael.queiroz@hotmail.com\", " +
                "\"dateOfBirth\": \"1985-02-14\", " +
                "\"address\": \"\", " +
                "\"skills\" : [\"teste\", \"teste 56\", \"teste 3\"]}";
        mockMvc.perform(MockMvcRequestBuilders.post("/users")
                        .content(user)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.address", Is.is("Required address")))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void whenPostRequestToUsersAndInValidAddressInvalid_thenCorrectReponse() throws Exception {
        String user = "{\"name\": \"Rafael Queiroz\", " +
                "\"email\" : \"rafael.queiroz@hotmail.com\", " +
                "\"dateOfBirth\": \"1985-02-14\", " +
                "\"address\": \"Rua José Inaldo, 759, Praia do Imperador Guia de Pacobaíba, Magé - RJ###\", " +
                "\"skills\" : [\"teste\", \"teste 56\", \"teste 3\"]}";
        mockMvc.perform(MockMvcRequestBuilders.post("/users")
                        .content(user)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.address", Is.is("Address cannot contain symbols")))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
    }

}
