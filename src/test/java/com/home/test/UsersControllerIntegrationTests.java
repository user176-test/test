package com.home.test;

import com.home.controllers.UsersController;
import com.home.entity.User;
import com.home.repository.UserRepository;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.util.Assert;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class UsersControllerIntegrationTests {

	@Autowired
	private MockMvc mockMvc;

	@SpyBean
	private UserRepository userRepository;

	@Before
	public void setup() {
		userRepository.getUsers().clear();
		List<User> users = IntStream.range(1, 3).mapToObj(i -> new User(i, "UserName" + i)).collect(Collectors.toList());
		userRepository.getUsers().addAll(users);
	}

	@Test
	public void testUserListReturns() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/users/list"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));

		Mockito.verify(userRepository).getUsers();
	}
}
