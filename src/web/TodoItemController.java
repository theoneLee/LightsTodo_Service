package web;

import bean.TodoItem;
import dao.TodoItemDAO;
import dao.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lee on 2016/12/9 0009.
 */
@RestController
@RequestMapping("/todoItems")
public class TodoItemController {
    @Autowired
    private TodoItemDAO todoItemDAO;

    @Autowired
    private UserDAO userDAO;

    /**
     * 处理/todoItems/user/1的get
     * @param uid
     * @return
     */
    @RequestMapping(value = "/user/{uid}",method = RequestMethod.GET)
    public List<TodoItem> getUserTodoItemList(@PathVariable int uid){
        return userDAO.getTodoItemList(uid);
    }

    /**
     * 处理/todoItems/user/1的post
     * @param list 传过来的json要求能转化为List<TodoItem>
     * @param uid
     * @return
     */
    @RequestMapping(value = "/user/{uid}",method = RequestMethod.POST,consumes = "application/json")
    public int saveTodoItemList(@RequestBody List<TodoItem> list,@PathVariable int uid){
        if(uid==-1)
            return -1;
        ArrayList<TodoItem> list1= (ArrayList<TodoItem>) list;
        todoItemDAO.deleteTodoItemByUid(uid);//存储TodoItems前把之前的数据删除，在添加TodoItems和与User关联
        return userDAO.connectTodoItem(uid, list1);
    }


}
