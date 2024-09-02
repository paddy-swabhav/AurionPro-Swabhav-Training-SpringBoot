package com.techlab.mapipngapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.techlab.mapipngapp.common.methods.CreateResponse;
import com.techlab.mapipngapp.dto.CustomerUserDto;
import com.techlab.mapipngapp.dto.PageResponse;
import com.techlab.mapipngapp.dto.UserDto;
import com.techlab.mapipngapp.entity.Customer;
import com.techlab.mapipngapp.entity.Profile;
import com.techlab.mapipngapp.entity.User;
import com.techlab.mapipngapp.enums.Role;
import com.techlab.mapipngapp.operation.CustomerOperation;
import com.techlab.mapipngapp.operation.UserOperation;
import com.techlab.mapipngapp.repository.ProfileRepository;
import com.techlab.mapipngapp.repository.UserRepository;

import jakarta.validation.Valid;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private CustomerOperation customerOperation;
	
	@Autowired
	private CreateResponse<User> createResponse;
	
	@Autowired
	private UserOperation userOperation;
	
	@Autowired
	private ProfileRepository profileRepository;

	@Override
	public User createUser(User user) {
		// TODO Auto-generated method stub
		userOperation.isEmailExist(user.getProfile().getEmailId());
		User dbUser=userRepo.save(user);
		if(dbUser.getRole().equals(Role.CUSTOMER)) {
		customerOperation.createCustomer(dbUser);
		}
		return dbUser;
	}

	@Override
	public PageResponse<User> getAllUsers(@Valid Integer pageNo, @Valid Integer pageSize) {
		// TODO Auto-generated method stub
		
		Pageable page = PageRequest.of(pageNo, pageSize); 
		Page <User> userPage= userRepo.findAll(page);
		
		return createResponse.createPageResponse(userPage);
	}

	@Override
	public User updateCustomerUser(int customerId, CustomerUserDto customerDto) {
		// TODO Auto-generated method stub
		Customer customer = customerOperation.isCustomerExist(customerId);
		
		User user = customer.getUser();
		
		if(!user.getPassword().equals(customerDto.getPassword())) {
			throw new RuntimeException("In coorect password");
		}
		
		if(customerDto.getNewPassword()!=null) {
			user.setPassword(customerDto.getNewPassword());
		}
		

			Profile dbProfile=user.getProfile();
	
			if(customerDto.getFirstName()!=null) {
				dbProfile.setFirstName(customerDto.getFirstName());
			}
			
			if(customerDto.getLastName()!=null) {
				dbProfile.setLastName(customerDto.getLastName());
			}
			
			user.setProfile(dbProfile);
		
		
		return userRepo.save(user);
	}

	@Override
	public UserDto getUserByCustomerId(int customerId) {
        Customer customer = customerOperation.isCustomerExist(customerId);
		
		
		return userOperation.toUserDtoMapper(customer.getUser());
	}

	@Override
	public UserDto userLogin(String email, String password) {
		// TODO Auto-generated method stub
		if(profileRepository.findByEmailId(email)==null) {
			throw new RuntimeException("email or password ");
		}
		Profile profile = profileRepository.findByEmailId(email);
		
		User user = userRepo.findByProfile(profile);
		
		if(!user.getPassword().equals(password)) {
			throw new RuntimeException("email or password ");
		}
		return userOperation.toUserDtoMapper(user);
	}
	
	
	
	
	
	 

}
