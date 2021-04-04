
package com.cg.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.cg.entity.User;
import com.cg.exception.PasswordNotMatchException;
import com.cg.exception.UserNotFoundException;
import com.cg.repository.IUserRepository;

@Service
public class IUserServiceImpl implements IUserService {

	@Autowired
	private IUserRepository uDao;

	@Override
	public Boolean signIn(User user) throws UserNotFoundException {
		Boolean status= false;
//		Optional<User> resultUser= userRepository.findById(user.getUserId());
		Optional<User> resultBroker=uDao.findById(user.getUserId());
		if (resultBroker.isPresent()) {
			if((resultBroker.get().getPassword().equals(user.getPassword()))) 
			{
				status=true;

		} 
			else 
			
			throw new UserNotFoundException("Broker Not Found");
		}
		return status;
	}

	@Override
	public Boolean  signOut(User user) throws UserNotFoundException {
		Boolean status=false;
		Optional<User> resultBroker=uDao.findById(user.getUserId());

		if (resultBroker.isEmpty()) {
			throw new UserNotFoundException("Broker Not Found");
		}
		else if(resultBroker.get().getPassword().equals(user.getPassword())) {
			 status = true;
		}
		return status;
	}
	/******************************************************************************
	 * Method                        changePassword
	 * Description                   It is used to change the password
	 * @param broker                 Broker's refernce variable
	 * @throws PasswordNotMatchException 
	 * @BrokerNotFoundException        It is raised due to invalid user details
	 * created by                    Sidda Reddy Partha Saradhi
	 * created date                  24-03-2021
	 ********************************************************************************/

	

	@Override
	public User changePassword(int userid, User user) throws UserNotFoundException, PasswordNotMatchException {

		Optional<User> resultUser=uDao.findById(user.getUserId());
		if(resultUser.isPresent()) {
				resultUser.get().setPassword(user.getPassword());
				return uDao.save(resultUser.get());
			
		}
		else
		{
			throw new UserNotFoundException("Broker Not Found");
		}	

}
}
