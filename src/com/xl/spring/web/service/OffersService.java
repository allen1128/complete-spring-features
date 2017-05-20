package com.xl.spring.web.service;

import static org.hamcrest.CoreMatchers.nullValue;

import java.util.List;
import java.util.jar.Attributes.Name;

import org.eclipse.jdt.internal.compiler.ast.NumberLiteral;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import com.xl.spring.web.dao.Offer;
import com.xl.spring.web.dao.OffersDao;

@Service("offersService")
public class OffersService {

	private OffersDao offersDao;

	public List<Offer> getCurrent() {
		return offersDao.getOffers();
	}

	public OffersDao getOffersDAO() {
		return offersDao;
	}

	@Autowired
	public void setOffersDAO(OffersDao offersDAO) {
		this.offersDao = offersDAO;
	}

	@Secured({ "ROLE_USER", "ROLE_ADMIN" })
	public void create(Offer offer) {
		offersDao.saveOrUpdate(offer);
	}

	public boolean hasOffer(String name) {
		if (name == null) {
			return false;
		}
		return getOffer(name) != null;
	}

	public Offer getOffer(String username) {
		if (username == null){
			return null;
		}
		
		List<Offer> offers = offersDao.getOffers(username);
		if (offers.size() == 0){
			return null;
		} else {
			return offers.get(0);
		}
	}

	public void saveOrUpdate(Offer offer) {
		offersDao.saveOrUpdate(offer);
	}

	public void delete(int id) {
		offersDao.delete(id);		
	}
}
