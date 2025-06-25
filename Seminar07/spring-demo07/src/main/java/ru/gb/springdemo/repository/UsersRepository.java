package ru.gb.springdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gb.springdemo.model.Member;

import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<Member,Long> {
    Optional<Member> findByName(String name);
}
