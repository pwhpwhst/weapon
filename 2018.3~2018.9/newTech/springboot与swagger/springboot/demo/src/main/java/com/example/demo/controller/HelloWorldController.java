package com.example.demo.controller;
import org.springframework.web.bind.annotation.*;
import com.example.demo.dto.User;
import com.example.demo.dao.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.dto.QueryParamDTO;
import com.example.demo.dto.ResponseDto;
import com.example.demo.dto.SQLDTO;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiImplicitParams; 
import io.swagger.annotations.ApiImplicitParam; 

@RestController
public class HelloWorldController {


// 数据库调用，解注释后可用 begin

//	@Autowired
//	private UserMapper userDao;
//
//	@ApiOperation(value = "/hello",notes = "firstDemo")
//    @RequestMapping(value = "/hello/{userId}",method = RequestMethod.GET)
//    public String index(@ApiParam(value = "用户ID",required = true)
//			                     @PathVariable(value = "userId")Integer userId) {
//
//        User user=userDao.getUser(userId);
//		System.out.println("id:" + user.getId() + " name:" + user.getName() + " password:" + user.getPassword());
//
//        return "Hello World";
//    }

// 数据库调用，解注释后可用 end


/**
* 根据查询条件获取单条数据
* 
* @param user
* @param sqlId 对应后台系统中某条SQL
* @param queryParamDTO 查询参数
* @return 单条记录
* @throws OsBizException
*/




@ApiOperation(value = "/get",notes = "get Demo",httpMethod="POST")
@RequestMapping(value = "/hello/{queryParamDTO}/{user}/{sqlId}",method = RequestMethod.POST)
	    @ApiImplicitParams({
            @ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, dataType = "User"),
			@ApiImplicitParam(name = "sqlId", value = "对应后台系统中某条SQL", required = true, dataType = "Long",paramType = "path")
//			@ApiImplicitParam(name = "queryParamDTO", value = "查询参数", required = true, dataType = "QueryParamDTO")
    })
public String get(@RequestBody User user,
				@PathVariable(value = "sqlId") Long sqlId
//				@RequestBody QueryParamDTO queryParamDTO
						){

System.out.println("fsdfdsg");

//	ResponseDto<SQLDTO> response=new ResponseDto<SQLDTO>();
//
//			response.setResult("SUCCESS");
//			response.setErrorCode("OK");
//			response.setErrorMsg("OK");
//
//
//			SQLDTO sqlDTO=new SQLDTO();
//
//			String sqlContent = "select * from test;";
//			sqlDTO.setSqlContent(sqlContent);
//			response.setData(sqlDTO);
//QueryParamDTO queryParamDTO1 =new QueryParamDTO();
//				return queryParamDTO1;
return "fsdfs";
}

}





