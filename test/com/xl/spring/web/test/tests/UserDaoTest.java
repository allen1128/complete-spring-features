package com.xl.spring.web.test.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.xl.spring.web.dao.User;
import com.xl.spring.web.dao.UserDao;

@ActiveProfiles("dev")
@ContextConfiguration(locations = { "classpath:com/xl/spring/web/test/config/datasource.xml",
		"classpath:com/xl/spring/web/config/dao-context.xml",
		"classpath:com/xl/spring/web/config/security-context.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class UserDaoTest {

	@Autowired
	private UserDao userDao;

	@Autowired
	private DataSource dataSource;

	@Before
	public void init() {
		JdbcTemplate jdbc = new JdbcTemplate(dataSource);
		jdbc.execute("delete from users");
	}

	@Test
	public void testCreateUser() {
		User user = new User("user", "pass", true, "ROLE_USER", "Jone Joe", "test@test.com");		
		assertTrue("User creation should return true", userDao.create(user));
		List<User> users = userDao.getAllUsers();
		assertEquals("Number of users should be one", 1, users.size());
		assertTrue(userDao.exists("user"));
		assertEquals(user, users.get(0));
	}
}
