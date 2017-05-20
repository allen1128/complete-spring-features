package com.xl.spring.web.test.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.awt.color.ICC_ColorSpace;
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
			
	private User user1 = new User("username1", "password1", true, "ROLE_ADMIN", "User One", "user1@test.com");
	private User user2 = new User("username2", "password2", true, "ROLE_ADMIN", "User Two", "user2@test.com");
	private User user3 = new User("username3", "password3", true, "ROLE_ADMIN", "User Three", "user3@test.com");
	private User user4 = new User("username4", "password4", true, "ROLE_ADMIN", "User Four", "user4@test.com");

	@Before
	public void init() {
		JdbcTemplate jdbc = new JdbcTemplate(dataSource);
		jdbc.execute("delete from users");
	}
	
	@Test
	public void testCreateRetrieve(){
		userDao.create(user1);
		List<User> users = userDao.getAllUsers();		
		assertEquals(1,  users.size());
		assertEquals(user1, users.get(0));
		userDao.create(user2);
		userDao.create(user3);
		userDao.create(user4);
		
		users = userDao.getAllUsers();		
		assertEquals(4,  users.size());
	}

	@Test
	public void testExists(){
		userDao.create(user2);
		assertTrue(userDao.exists(user2.getUsername()));
		assertFalse(userDao.exists("noexist"));
	}
}
