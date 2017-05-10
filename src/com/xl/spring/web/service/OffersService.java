package com.xl.spring.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xl.spring.web.dao.Offer;
import com.xl.spring.web.dao.OffersDAO;

@Service("offersService")
public class OffersService {

	private OffersDAO offersDAO;
	
	public List<Offer> getCurrent(){
		return offersDAO.getOffers();
	}

	public OffersDAO getOffersDAO() {
		return offersDAO;
	}

	@Autowired
	public void setOffersDAO(OffersDAO offersDAO) {
		this.offersDAO = offersDAO;
	}
	
	public void create(Offer offer){
		offersDAO.create(offer);
	}

	public void throwTestException() {		
		offersDAO.getOffer(999);
	}
}
