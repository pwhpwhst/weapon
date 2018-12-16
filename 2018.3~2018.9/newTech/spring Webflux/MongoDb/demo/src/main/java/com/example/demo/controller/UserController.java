package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import com.example.demo.entity.UserEntity;
import com.example.demo.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;

	@PostMapping("")
	public Mono<UserEntity> save(UserEntity user) {
		return this.userService.save(user);
	}

	@DeleteMapping("/{username}")
	public Mono<Long> deleteByUsername(@PathVariable String username) {
		return this.userService.deleteByUsername(username);
	}

	@GetMapping("/{username}")
	public Mono<UserEntity> findByUsername(@PathVariable String username) {
		return this.userService.findByUsername(username);
	}

	@GetMapping("")
	public Flux<UserEntity> findAll() {
		return this.userService.findAll();
	}
}
