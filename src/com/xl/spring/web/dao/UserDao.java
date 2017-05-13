package com.xl.spring.web.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component("userDao")
public class UserDao {

	private NamedParameterJdbcTemplate jdbc;

    @Autowired
	public void setDataSource(DataSource jdbc) {
		this.jdbc = new NamedParameterJdbcTemplate(jdbc);
	}
    
	@Transactional
	public int[] create(List<Offer> offers) {

		SqlParameterSource[] params = SqlParameterSourceUtils.createBatch(offers.toArray());

		return jdbc.batchUpdate("insert into offers (id, name, text, email) values (:id, :name, :text, :email)",
				params);
	}
    
    @Transactional
	public boolean create(User user) {		
		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(user);
		jdbc.update("insert into users (username, password, email, enabled) values (:username, :password, :email, :enabled)", params);
		return jdbc.update("insert into authorities (username, authority) values (:username, :authority)", params) == 1;
    }

	public boolean exists(String username) {
		return jdbc.queryForObject("select count(*) from users where username = :username", 
				new MapSqlParameterSource("username", username), Integer.class) == 1;
	}
}