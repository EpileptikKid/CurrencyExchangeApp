package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Controller
public class HelloController {
    private DataSource dataSource;

    @Autowired
    public HelloController(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @GetMapping("/hello")
    @ResponseBody
    public People hello() {
        People people = new People();
        people.setName("Andrii");
        people.setSurname("Voloskovets");
        return people;
    }

    @GetMapping("test")
    public String test(ModelMap model) {
        String sql = "SELECT name FROM pubinfo WHERE id = 1";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            if (resultSet.next()) {
                String name = resultSet.getString("name");
                model.addAttribute("message", "Hello " + name);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return "hello";
    }
}
