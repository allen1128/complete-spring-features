package com.xl.spring.web.test.tests;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.xl.spring.web.dao.Message;
import com.xl.spring.web.dao.MessagesDao;
import com.xl.spring.web.dao.User;
import com.xl.spring.web.dao.UserDao;

@ActiveProfiles("dev")
@ContextConfiguration(locations = { "classpath:com/xl/spring/web/test/config/datasource.xml",
		"classpath:com/xl/spring/web/config/dao-context.xml",
		"classpath:com/xl/spring/web/config/security-context.xml" })

@RunWith(SpringJUnit4ClassRunner.class)
public class MessageDaoTest {

	@Autowired
	private MessagesDao messagesDao;
	
	@Autowired
	private UserDao userDao;

	@Autowired
	private DataSource dataSource;

	private User user1 = new User("username1", "password1", true, "ROLE_ADMIN", "User One", "user1@test.com");
	
	private Message message1 = new Message("test subject 1", "content 1", "test@test.com", "Anna", "username1");

	@Before
	public void init() {
		JdbcTemplate jdbc = new JdbcTemplate(dataSource);
		jdbc.execute("delete from offers");
		jdbc.execute("delete from messages");
		jdbc.execute("delete from users");
	}

	@Test
	public void testCreateRetrieve() {
		userDao.create(user1);		
		messagesDao.saveOrUpdate(message1);
	}
}
