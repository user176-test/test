package com.home.test;

import com.home.controllers.UsersController;
import com.home.entity.User;
import com.home.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@SpringBootTest
class UsersControllerTests {

	private UserRepository userRepository = Mockito.mock(UserRepository.class);
	private UsersController usersController = new UsersController(userRepository);

	@Test
	void contextLoads() {
	}

	@Test
	public void testUserListReturns() {
		List<User> users = IntStream.range(1, 4).mapToObj(i -> new User(i, "UserName" + i)).collect(Collectors.toList());
		Mockito.doReturn(users).when(userRepository).getUsers();

		List<User> userList =  usersController.list();
		Assert.isTrue(userList.size() == 3, "list size is wrong");

		Mockito.verify(userRepository).getUsers();
	}

	@Test
	public void testUserGetByIdReturns() {
		User user = new User(1, "UserName" + 1);
		Mockito.doReturn(Optional.of(user)).when(userRepository).findById(ArgumentMatchers.anyLong());

		User userDetail =  usersController.get(user.getId());
		Assert.isTrue(userDetail.getId() == user.getId(), "invalid user");
		Assert.isTrue(userDetail.getName() == user.getName(), "invalid user name");

		Mockito.verify(userRepository).findById(ArgumentMatchers.eq(user.getId()));
	}
}
