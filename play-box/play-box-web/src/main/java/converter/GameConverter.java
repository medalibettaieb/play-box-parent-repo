package converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import mBeans.AdministrationBean;
import domain.Game;

@FacesConverter("gc")
public class GameConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String string) {
		if (string == null) {
			return null;
		}
		AdministrationBean administrationBean = context.getApplication()
				.evaluateExpressionGet(context, "#{administrationBean}",
						AdministrationBean.class);
		Game game = administrationBean.doFindGameByName(string);
		return game;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object object) {
		String string = null;
		if (object instanceof Game) {
			string = ((Game) object).getName();

		}
		return string;
	}

}
