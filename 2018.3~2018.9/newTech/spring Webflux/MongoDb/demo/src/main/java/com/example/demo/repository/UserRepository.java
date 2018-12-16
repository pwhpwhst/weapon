package com.example.demo.repository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;
import com.example.demo.entity.UserEntity;

    public interface UserRepository extends ReactiveCrudRepository<UserEntity, String> {  // 1
        Mono<UserEntity> findByUsername(String username);     // 2
        Mono<Long> deleteByUsername(String username);
    }
