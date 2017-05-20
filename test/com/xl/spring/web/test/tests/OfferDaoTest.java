package com.xl.spring.web.test.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.xl.spring.web.dao.Offer;
import com.xl.spring.web.dao.OffersDao;
import com.xl.spring.web.dao.User;
import com.xl.spring.web.dao.UserDao;

@ActiveProfiles("dev")
@ContextConfiguration(locations = { "classpath:com/xl/spring/web/test/config/datasource.xml",
		"classpath:com/xl/spring/web/config/dao-context.xml",
		"classpath:com/xl/spring/web/config/security-context.xml" })

@RunWith(SpringJUnit4ClassRunner.class)
public class OfferDaoTest {

	@Autowired
	private OffersDao offersDao;
	
	@Autowired
	private UserDao userDao;

	@Autowired
	private DataSource dataSource;

	private User user1 = new User("username1", "password1", true, "ROLE_ADMIN", "User One", "user1@test.com");
	private User user2 = new User("username2", "password2", true, "ROLE_ADMIN", "User Two", "user2@test.com");
	private User user3 = new User("username3", "password3", false, "ROLE_ADMIN", "User Three", "user3@test.com");
	Offer offer1 = new Offer(user1, "offer 1");
	Offer offer2 = new Offer(user2, "offer 2");
	Offer offer3 = new Offer(user3, "offer 3");

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
		userDao.create(user2);
		userDao.create(user3);
		offersDao.saveOrUpdate(offer1);
		offersDao.saveOrUpdate(offer2);
		offersDao.saveOrUpdate(offer3);
		assertEquals(1, offersDao.getOffers(user1.getUsername()).size());
		assertEquals(2, offersDao.getOffers().size());
	}
	
	@Test 
	public void testUpdate(){
		String text = "changed";
		userDao.create(user1);
		offersDao.saveOrUpdate(offer1);
		
		assertEquals("offer 1", offersDao.getOffer(offer1.getId()).getText());
		offer1.setText(text);
		
		offersDao.saveOrUpdate(offer1);
		assertEquals(text, offersDao.getOffer(offer1.getId()).getText());
	}
	
	@Test 
	public void testDelete(){
		userDao.create(user1);
		offersDao.saveOrUpdate(offer1);
		
		offersDao.delete(offer1.getId());
		Offer offer = offersDao.getOffer(offer1.getId());
		assertNull(offer);
	}
}
