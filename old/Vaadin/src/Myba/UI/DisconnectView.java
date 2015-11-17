package Myba.UI;

import Myba.utils.SessionUtils;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.UI;

/**
 * Created by Mixmania on 01-06-15 at 21:33.
 */

class DisconnectView extends HorizontalLayout implements View
{
    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event)
    {
        SessionUtils.clear();
        UI.getCurrent().getNavigator().navigateTo("login");
    }
}
