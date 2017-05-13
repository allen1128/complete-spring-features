package com.xl.spring.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xl.spring.web.dao.Offer;
import com.xl.spring.web.dao.OffersDao;

@Service("offersService")
public class OffersService {

	private OffersDao offersDao;
	
	public List<Offer> getCurrent(){
		return offersDao.getOffers();
	}

	public OffersDao getOffersDAO() {
		return offersDao;
	}

	@Autowired
	public void setOffersDAO(OffersDao offersDAO) {
		this.offersDao = offersDAO;
	}
	
	public void create(Offer offer){
		offersDao.create(offer);
	}

	public void throwTestException() {		
		offersDao.getOffer(999);
	}
}
