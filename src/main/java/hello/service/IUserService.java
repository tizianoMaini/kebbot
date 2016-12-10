package hello.service;

import hello.model.User;

import java.util.List;

public interface IUserService {
        public List<User> listUser();
        public void addUser(User user);

}

