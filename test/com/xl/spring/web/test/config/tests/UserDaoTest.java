package com.xl.spring.web.test.config.tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ActiveProfiles("dev")
@ContextConfiguration(locations = { "classpath:com/xl/spring/web/test/config/datasource.xml", "classpath:com/xl/spring/web/test/config/datasource.xml", "classpath:com/xl/spring/web/config/security-context.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class UserDaoTest {
	
	@Test
	public void testCreateUser(){
		assertEquals("dummy test", 1, 1);
	}
}
