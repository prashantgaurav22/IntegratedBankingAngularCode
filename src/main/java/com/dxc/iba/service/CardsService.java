package com.dxc.iba.service;

import java.util.List;

import com.dxc.iba.entity.Cards;
import com.dxc.iba.exception.CardsException;


public interface CardsService {

	Cards add(Cards cards) throws CardsException;
	Cards update(Cards cards) throws CardsException;
	boolean deleteById(String card_Number) throws CardsException;
	Cards getById(String card_Number);
	List<Cards> getAllCards();

}

