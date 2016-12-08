package com.drpeng.api;

import com.drpeng.persistence.entity.UserEntity;
import com.drpeng.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by liurl3 on 2016/11/21.
 */
@RestController
@RequestMapping("/user")
public class UserResource {
    @Autowired
    private IUserService userService;
    @RequestMapping(method = RequestMethod.POST)
    public Map addUser(@RequestBody UserEntity user){
        Map rtnMap = new HashMap();
        userService.saveUsers(user);
        rtnMap.put("return_code","SUCCESS");
        rtnMap.put("user",user);
        return rtnMap;
    }
    @RequestMapping(value = "/{name}",method = RequestMethod.GET)
    public Map findUser(@PathVariable("name") String name){
        Map rtnMap = new HashMap();
        List<UserEntity> userEntities = null;
        try {
            userEntities = userService.getUsersByName(name);
            rtnMap.put("return_code","SUCCESS");
            rtnMap.put("users",userEntities);
        } catch (Exception e) {
            rtnMap.put("return_code","FAILED");
            rtnMap.put("return_message",e.getMessage());
            e.printStackTrace();
        }
        return rtnMap;
    }
}
