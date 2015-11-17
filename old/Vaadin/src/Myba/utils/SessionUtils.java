package Myba.utils;

import Server.Rights.Role;
import com.vaadin.server.VaadinService;
import com.vaadin.server.WrappedSession;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mixmania on 25-05-15 at 21:04.
 */

public final class SessionUtils
{
    private static final WrappedSession session;

    static
    {
        session = VaadinService.getCurrentRequest().getWrappedSession();
    }

    private SessionUtils()
    {
    }

    public static boolean isAlreadyLogged()
    {
        return session.getAttribute("Username") != null;
    }

    public static void add(String name, Object data)
    {
        session.setAttribute(name, data);
    }

    @SuppressWarnings("unchecked")
    public static List<Role> getRoles()
    {
        if (session.getAttribute("Roles") == null) return new ArrayList<>();
        else return (List<Role>) session.getAttribute("Roles");
    }

    public static void clear()
    {
        session.removeAttribute("Username");
        session.removeAttribute("Password");
        session.removeAttribute("Roles");
        session.removeAttribute("Name");
        session.removeAttribute("Surname");
    }

    public static String getNomPrenom()
    {
        return session.getAttribute("Surname") + " " + session.getAttribute("Name");
    }

    public static String getUsername()
    {
        return (String) session.getAttribute("Username");
    }

    public static String getPassword()
    {
        return (String) session.getAttribute("Password");
    }
}
