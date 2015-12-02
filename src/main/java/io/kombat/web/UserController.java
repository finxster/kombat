package io.kombat.web;

import io.kombat.core.annotation.Controller;
import io.kombat.domain.model.User;
import io.kombat.domain.services.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by ac-bsilva on 13/11/15.
 */
@Controller("/users")
public class UserController extends AbstractCRUDController<User, UserService> {

    public void model(HttpServletRequest request, HttpServletResponse response) throws IOException {
        User user = new User();

        String picturePath = request.getParameter("picture");
        if (picturePath == null || picturePath.trim().equals("")) {
            picturePath = manager.create(request, "_picture", route);
        }

        user.setName(request.getParameter("name"));
        user.setEmail(request.getParameter("email"));

        if (picturePath != null) {
            user.setPicture(picturePath);
        }

        validate(user, request, response);
    }

}
