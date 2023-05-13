package com.p6.apps.unitTest.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.p6.apps.controller.UserController;
import com.p6.apps.controller.dto.user.RegisterRequest;
import com.p6.apps.service.FriendService;
import com.p6.apps.service.UserService;
import com.p6.apps.service.data.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = UserController.class)
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @MockBean
    private FriendService friendService;

    @Test
    public void getUsers_ShouldReturnOK() throws Exception {
        List<User> users = new ArrayList<>();
        when(userService.getLogins()).thenReturn(users);
        mockMvc.perform(get("/logins")).andExpect(status().isOk());
    }

    @Test
    public void getUser_ShouldReturnOK() throws Exception {
        User user = new User();
        when(userService.getLogin(any())).thenReturn(user);
        mockMvc.perform(get("/login/1")).andExpect(status().isOk());
    }

    @Test
    public void getUser_ShouldReturnNotFound() throws Exception {
        when(userService.getLogin(any())).thenThrow(new NoSuchElementException());
        mockMvc.perform(get("/login/1")).andExpect(status().isNotFound());
    }

    @Test
    public void createUser_ShouldReturnOkStatus() throws Exception {
        ObjectMapper obj = new ObjectMapper();
        User user = new User();
        user.setIdUser(1L);
        user.setFirst_name("1");
        user.setLast_name("2");
        user.setEmail("3");
        user.setPassword("4");
        user.setBalance(5);

        when(userService.addUser(any())).thenReturn(user);
        mockMvc.perform(post("/signon")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(obj.writeValueAsString(user)))
                .andExpect(status().isOk());
    }

//    @Test
//    public void updateUser_ShouldReturnBadRequest() throws Exception {
//        when(userService.updateUser(any(), any())).thenThrow(new NoSuchElementException());
//        mockMvc.perform(put("/user/1").contentType(MediaType.APPLICATION_JSON).content("{}")).andExpect(status().isBadRequest());
//    }

    @Test
    public void updateUser_ShouldReturnOK() throws Exception {
        User user = new User();
        user.setIdUser(1L);
        user.setFirst_name("1");
        user.setLast_name("2");
        user.setEmail("3");
        user.setPassword("4");
        user.setBalance(5);
        when(userService.updateUser(any(), any())).thenReturn(user);
        mockMvc.perform(put("/user/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{    \"first_name\":\"1\",\n" +
                        "    \"last_name\":\"2\",\n" +
                        "    \"email\":\"3\",\n" +
                        "    \"password\":\"4\",\n" +
                        "    \"balance\":5}"))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteUser_ShouldReturnOk() throws Exception {
        User user = new User();
        doNothing().when(userService).deleteUser(any());
        when(userService.getLogin(any())).thenReturn(user);
        mockMvc.perform(delete("/user/1")).andExpect(status().isOk());
    }

//    @Test
//    public void deleteUser_ShouldReturnNotFound() throws Exception {
//        doNothing().when(userService).deleteUser(any());
//        when(userService.getLogin(any())).thenThrow(new NoSuchElementException());
//        mockMvc.perform(delete("/user/1")).andExpect(status().isNotFound());
//    }

//    @Test
//    public void deleteUser_ShouldReturnBadRequest() throws Exception {
//        doNothing().when(userService).deleteUser(any());
//        when(userService.getLogin(any())).thenThrow(new IllegalArgumentException());
//        mockMvc.perform(delete("/user/1")).andExpect(status().isBadRequest());
//    }

//    @Test
//    public void deleteUsers_ShouldReturnBadRequest() throws Exception {
//        doNothing().when(userService).deleteUsers();
//        when(userService.getLogins()).thenThrow(new IllegalArgumentException());
//        mockMvc.perform(delete("/user/1")).andExpect(status().isBadRequest());
//    }

    @Test
    public void deleteUsers_ShouldNoContent() throws Exception {
        RegisterRequest userRequest = new RegisterRequest();
        userService.addUser(userRequest);
        mockMvc.perform(delete("/users")).andExpect(status().isNoContent());
    }

}
