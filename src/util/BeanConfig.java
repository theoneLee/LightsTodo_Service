package util;

import bean.TodoItem;
import bean.User;
import bean.ZoneComment;
import bean.ZoneMessage;
import biz.test;
import dao.TodoItemDAO;
import dao.UserDAO;
import dao.ZoneCommentDAO;
import dao.ZoneMessageDAO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * spring配置文件，已启动组件扫描相应的包
 * Created by Lee on 2016/12/1 0001.
 */
@Configuration
@ComponentScan(basePackageClasses = {TodoItem.class, User.class, ZoneComment.class, ZoneMessage.class,
        test.class,
        TodoItemDAO.class, UserDAO.class, ZoneMessageDAO.class, ZoneCommentDAO.class})
public class BeanConfig {

    @Bean
    public User nullUser(){
        return new User();
    }
}
