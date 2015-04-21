package pft.helper;

import org.hibernate.Session;
import pft.utils.HibernateUtil;

/**
 * Created by linka on 20.04.2015.
 */
public class HibernateHelper extends BaseHelper {
    public HibernateHelper(ApplicationManager manager) {
        super(manager);
    }
    public String getUserId(String login) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        return session.createQuery("select id from User where login=?")
                .setParameter(0, login).uniqueResult().toString();
    }

}
