package  com.tea101g3.api;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tea101g3.entity.*;
import com.tea101g3.DAO.TodoDao;
import com.tea101g3.service.TodoService;

@WebServlet(
        urlPatterns={"/todolist"},
        name="TodoApi"
)

public class TodoApi extends HttpServlet{
    ObjectMapper objectMapper = new  ObjectMapper();
    TodoDao todoDao = new TodoDao();
    TodoService todoService = new TodoService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        PrintWriter writer = resp.getWriter();
        resp.setContentType("text/json");
        resp.setCharacterEncoding("utf-8");
        writer.print( getAll());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BufferedReader reader = req.getReader();
        String reqJson = "";
        String str = "";
        while((str = reader.readLine())!= null) {
            reqJson += str;
        }

        ListBean creatBean = objectMapper.readValue(reqJson, new TypeReference<ListBean>() {});
        todoDao.createOne(creatBean);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String srcId = req.getParameter("deleteId");
        todoService.deleteOne(srcId);

        PrintWriter writer = resp.getWriter();
        resp.setContentType("text/json");
        resp.setCharacterEncoding("utf-8");
        writer.print(getAll());

    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BufferedReader reader = req.getReader();
        String reqJson = "";
        String str = "";
        while((str = reader.readLine())!= null) {
            reqJson += str;
        }
        System.out.println(reqJson);
        ListBean updateBean = objectMapper.readValue(reqJson, new TypeReference<ListBean>() {});
        todoService.updateOne(updateBean);
        PrintWriter writer = resp.getWriter();
        resp.setContentType("text/json");
        resp.setCharacterEncoding("utf-8");
        writer.print(getAll());

    }

    String getAll() {
        List<ListBean> todolist = todoDao.getAll();
        String resultJson = null;
        try {
            resultJson = objectMapper.writeValueAsString(todolist);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return resultJson;
    }
}