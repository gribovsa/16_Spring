package org.gribov.socialnet;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Component
public class UserRepository {

    public UserRepository() {
        this.save(new User(null, "Evgeny","evgen@gh.ru"));
        this.save(new User(null, "Dmitry","dmitr@gh.ru"));
        this.save(new User(null, "Sergey","serg@gh.ru"));
    }

    //ConcurrentHashMap - имитирует многопоточность
    private Map<Long, User> users = new ConcurrentHashMap<>();
    //Потокобезопасный тип AtomicLong
    private AtomicLong counter = new AtomicLong();


    public List<User> findAll(){
        return new ArrayList<>(users.values());
    }


    public User findById(Long id){
        return users.get(id);
    }


    public User save(User user){
        if (user.getId() == null){
            //incrementAndGet - увеличивает на 1 и возвращает
            user.setId(counter.incrementAndGet());
        }
        users.put(user.getId(), user);
        return user;
    }

}
