package  com.tea101g3.api;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tea101g3.entity.*;
import com.tea101g3.DAO.TodoDao;

@WebServlet(
        urlPatterns={"/todolist"},
        name="TodoApi"
)

public class TodoApi extends HttpServlet{
    ObjectMapper objectMapper = new  ObjectMapper();
    TodoDao todoDao = new TodoDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("todo_get");
        PrintWriter writer = resp.getWriter();
        resp.setContentType("text/json");
        resp.setCharacterEncoding("utf-8");
        List<ListBean> todolist = todoDao.getAll();
//        ListBean todolist = new ListBean();
//        todolist.setTitle("test");
//        todolist.setOrderBy(0);
//        todolist.setPriority(0);
//        List<ListBean> list = new ArrayList<ListBean>();
//        list.add(todolist);
        String resultJson = objectMapper.writeValueAsString(todolist);
        System.out.println(resultJson);

        writer.print(resultJson);
        System.out.println("todo_get.end");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("todo_post");
        BufferedReader reader = req.getReader();
        String reqJson = "";
        String str = "";
        while((str = reader.readLine())!= null) {
            reqJson += str;
        }

        ListBean bean = objectMapper.readValue(reqJson, new TypeReference<ListBean>() {});
        System.out.println(bean);
        todoDao.createOne(bean);
    }
}