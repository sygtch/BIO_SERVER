package com.guo.sky;

/**
 * @author GuoTianchi
 * @version 1.0
 * @date 2020/4/8 13:09
 */
public class ServerServiceImpl implements ServerService {


    public String getUser() {
        User user = new User();
        user.setAge(18);
        user.setName("GuoTianChi");
        return user.toString();
    }

    public String saveUser(User user) {
        System.out.println("save......Yeah");
        return "OK";
    }
}
