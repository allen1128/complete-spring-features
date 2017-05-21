package com.xl.spring.web.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
@Component("messagesDao")
public class MessagesDao {

	@Autowired
	private SessionFactory sessionFactory;

	public Session session(){
		return sessionFactory.getCurrentSession();
	}	
	
	@SuppressWarnings("unchecked")
	public List<Message> getMessages() {
		Criteria crit = session().createCriteria(Message.class);
		crit.createAlias("user", "u");
		crit.add(Restrictions.eq("u.enabled",  true));
		return crit.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Message> getMessages(String username) {
		Criteria crit = session().createCriteria(Message.class);
		crit.add(Restrictions.eq("username",  username));
		return crit.list();
	}

	public void saveOrUpdate(Message message) {
		System.out.println("sending message:" + message);
		session().saveOrUpdate(message);		
	}

	public boolean delete(int id) {
		Query query = session().createQuery("delete from Message where id = :id");
		query.setLong("id", id);
		return query.executeUpdate() == 1;		
	}

	public Message getMessage(int id) {
		Criteria crit = session().createCriteria(Message.class);
		crit.createAlias("user", "u");
		crit.add(Restrictions.eq("id", id));
		return (Message) crit.uniqueResult();
	}
}
