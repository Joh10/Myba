package managers.hibernate;

import org.hibernate.Session;

/**
 * Created by Mixmania on 05-01-16 at 14:46.
 *
 */

public interface IFunction<T>
{
    T execute(Session s);
}
