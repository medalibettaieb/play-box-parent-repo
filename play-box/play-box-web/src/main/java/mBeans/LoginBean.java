package mBeans;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import services.interfaces.AccountManagementLocal;
import domain.Admin;
import domain.User;

@ManagedBean
@SessionScoped
public class LoginBean {
	private User user = new User();
	@EJB
	private AccountManagementLocal accountManagementLocal;

	public String doLogin() {
		String navigateTo = "";
		User userFound = accountManagementLocal.login(user.getLogin(),
				user.getPassword());
		if (userFound != null) {
			if (userFound instanceof Admin) {
				navigateTo = "/pages/admin/adminHome?faces-redirect=true";
			} else {
				navigateTo = "/pages/player/playerHome?faces-redirect=true";
			}

		} else {
			navigateTo = "/error?faces-redirect=true";
		}
		return navigateTo;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
