package kr.or.ddit.users.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.or.ddit.users.model.UsersVO;
import kr.or.ddit.users.repository.IUserDao;

@Service
public class UserService implements IUserService{
	
	@Resource(name = "userDao")
	private IUserDao userDao;
	
	public UserService() {
	}
	
	public UserService(IUserDao userDao) {
		this.userDao = userDao;
	}
	
	@Override
	public UsersVO getUser(String userId) {
		return userDao.getUser(userId);
	}

}
