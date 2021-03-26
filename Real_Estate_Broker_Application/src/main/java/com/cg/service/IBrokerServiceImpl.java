package com.cg.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cg.entity.Broker;
import com.cg.exception.BrokerNotFoundException;
import com.cg.repository.IBrokerRepo;

@Service
public class IBrokerServiceImpl implements IBrokerService {

	@Autowired
	IBrokerRepo bDao;

	@Override
	public Broker addBroker(Broker bro) {
		bro.setRole("Broker");
		bDao.saveAndFlush(bro);
		return bro;
	}

	@Override
	public Broker editBroker(Broker bro) throws BrokerNotFoundException {
		bro.setRole("Broker");
		bDao.saveAndFlush(bro);
		return bDao.findById(bro.getUserId()).get();
	}

	@Override
	public Broker removeBroker(int broId) throws BrokerNotFoundException {
		Broker b = bDao.findById(broId).get();
		bDao.deleteById(broId);
		return b;
	}

	@Override
	public Broker viewBroker(int broId) throws BrokerNotFoundException {
		return bDao.findById(broId).get();
	}

	@Override
	public List<Broker> listAllBrokers() {
		return bDao.findAll();
	}

}
