package web;

import bean.User;
import dao.UserDAO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * User 资源 控制器
 * Created by Lee on 2016/12/8 0008.
 */
@RestController
@RequestMapping("/users")
public class UserController {

    private UserDAO userDAO=new UserDAO();//这里没有用spring自动装载

//    @RequestMapping(method = RequestMethod.GET)
//    public List<User> getUsers(){
//        return userDAO.getUserList();
//    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<User> getUserById(@PathVariable int id){
        User user=userDAO.getUserById(id);
        HttpStatus status= user!=null ? HttpStatus.OK:HttpStatus.NOT_FOUND;//如果user为空就返回一个404状态码
        return new ResponseEntity<User>(user,status);
    }

    @RequestMapping(method = RequestMethod.POST,consumes="application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public int saveUser(@RequestBody User user){
        return userDAO.addUser(user);
    }
}
