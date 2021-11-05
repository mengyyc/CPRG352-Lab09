package servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Role;
import models.User;
import services.RoleService;
import services.UserService;

public class UserServlet extends HttpServlet {
	UserService userService = new UserService();
	RoleService roleService = new RoleService();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		List<User> users = null;
		List<Role> roles = null;

		try {
			users = userService.getAll();
			roles = roleService.getAll();
		} catch (SQLException ex) {
			Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
		}

		request.setAttribute("users", users);
		request.setAttribute("roles", roles);

		getServletContext().getRequestDispatcher("/WEB-INF/users.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		String action = request.getParameter("action");

		if (action == null) {
			this.doGet(request, response);
			return;
		}

		String email = request.getParameter("email");
		boolean active = true;
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String password = request.getParameter("password");
		String roleIdString = request.getParameter("roleId");
		int roleId = 1;
		if (roleIdString != null) roleId = Integer.parseInt(roleIdString);

		try {
			switch(action) {
				case "add":
					userService.insert(email, active, firstName, lastName, password, roleId);
					break;
				case "delete":
					userService.delete(email);
					break;
				case "edit":
					userService.update(email, active, firstName, lastName, password, roleId);
					break;
			}
		} catch(SQLException ex) {
			Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
		}

//		this.doGet(request, response);
		try {
			List<Role> roles = roleService.getAll();
			List<User> users = userService.getAll();
			request.setAttribute("users", users);
			request.setAttribute("roles", roles);
		} catch (SQLException ex) {
			Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
		}

		getServletContext().getRequestDispatcher("/WEB-INF/users.jsp").forward(request, response);
	}

}
