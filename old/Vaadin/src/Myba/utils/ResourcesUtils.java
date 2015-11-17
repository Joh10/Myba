package Myba.utils;

import com.vaadin.server.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;

/**
 * Created by Mixmania on 24-05-15 at 13:19.
 */
public final class ResourcesUtils
{
    private ResourcesUtils()
    {
    }

    public static Resource load(String name) throws FileNotFoundException
    {
        File file = new File(VaadinService.getCurrent().getBaseDirectory().getAbsolutePath() + "/WEB-INF/img/" + name);

        if (!file.exists()) throw new FileNotFoundException();

        return new FileResource(file);
    }

    public static Resource getPageURL(String navigatorPageName)
    {
        try
        {
            String current = Page.getCurrent().getLocation().toURL().toString();

            if (current.contains("#!")) current = current.substring(0, current.indexOf("#!"));

            return new ExternalResource(current + "#!" + navigatorPageName);
        }
        catch (MalformedURLException e)
        {
            e.printStackTrace();
        }

        return null;
    }

    public static String getFullPath(String name)
    {
        return VaadinService.getCurrent().getBaseDirectory().getAbsolutePath() + "/WEB-INF/img/" + name;
    }
}
