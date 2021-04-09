package  com.tea101g3.api;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet(
        urlPatterns={"/helloWorld"},
        name="HelloWorld",
        loadOnStartup=1
)

public class HelloWorld extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        writer.println("<!DOCTYPE html>");
        writer.println("<html>");
        writer.println("<head>");
        writer.println("<title>Hello! Servlet!</title>");
        writer.println("</head>");
        writer.println("<body>");
        writer.println("<h1>Hello! Servlet! 2!</h1>");
        writer.println("</body>");
        writer.println("</html>");
    }
}