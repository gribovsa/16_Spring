package org.gribov.socialnet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;


    public List<User> getAllUsers(){
        userRepository.save(new User(null, "Evgeny","evgen@gh.ru"));
        userRepository.save(new User(null, "Dmitry","dmitr@gh.ru"));
        userRepository.save(new User(null, "Sergey","serg@gh.ru"));
        return userRepository.findAll();
    }

    public User getUserByID(Long id){
        return userRepository.findById(id).get();
    }

}
