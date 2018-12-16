package com.example.demo.service;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Flux;
import com.example.demo.entity.UserEntity;
import com.example.demo.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;

	/**
	 * 保存或更新。
	 * 如果传入的user没有id属性，由于username是unique的，在重复的情况下有可能报错，
	 * 这时找到以保存的user记录用传入的user更新它。
	 */
	public Mono<UserEntity> save(UserEntity user) {
		return userRepository.save(user)
				.onErrorResume(e ->     // 1
						userRepository.findByUsername(user.getUsername())   // 2
								.flatMap(originalUser -> {      // 4
									user.setId(originalUser.getId());
									return userRepository.save(user);   // 3
								}));
	}

	public Mono<Long> deleteByUsername(String username) {
		return userRepository.deleteByUsername(username);
	}

	public Mono<UserEntity> findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	public Flux<UserEntity> findAll() {
		return userRepository.findAll();
	}
}
