package com.dxc.iba.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dxc.iba.entity.Cards;
import com.dxc.iba.exception.CardsException;
import com.dxc.iba.exception.CustomerException;
import com.dxc.iba.repo.CardsRepository;


@Service
public class CardsServiceImpl implements CardsService {
	
	@Autowired
	private CardsRepository cardRepo;
	

	

	@Transactional
	@Override
	public Cards update(Cards cards) throws CardsException {
		if(cards!=null) {
			if(!cardRepo.existsById(cards.getCard_Number())) {
				throw new CardsException("No Customer Found To Update!");
			}
			
			cardRepo.save(cards);
		}
		return cards;
	}


	

	@Transactional
	@Override
	public List<Cards> getAllCards() {
		return cardRepo.findAll();
	}


	@Transactional
	@Override
	public boolean deleteById(String card_Number) throws CardsException {
		boolean deleted=false;
		if(!cardRepo.existsById(card_Number)) {
			throw new CardsException("No Such Card Found To Delete!");
		}
		
		cardRepo.deleteById(card_Number);
		deleted=true;
		
		return deleted;
	}



	@Transactional
	@Override
	public Cards getById(String card_Number) {
		return cardRepo.findById(card_Number).orElse(null);
	}




	@Override
	public Cards add(Cards cards) throws CardsException {
		if(cards!=null) {
			if(cardRepo.existsById(cards.getCard_Number())) {
				throw new CardsException("An customer with the card number"+cards.getCard_Number()+" already exists!");
			}
			
			cardRepo.save(cards);
		}
		return cards;
	}
	
	

}
