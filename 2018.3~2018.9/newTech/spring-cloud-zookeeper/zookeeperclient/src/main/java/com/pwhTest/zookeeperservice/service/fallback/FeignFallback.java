package com.pwhTest.zookeeperservice.service.fallback;

import com.pwhTest.zookeeperservice.service.ServiceFeign;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;


@Component
public class FeignFallback implements ServiceFeign {

	@Override
	public String sayHello(@RequestParam(name = "name") String name) {
		return null;
	}
}
