package multi;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;

public class HistoryAction {

    private SessionFactory sessionFactory;

    public HistoryAction(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<History> load(String tenant, int amount) {
        List<History> histories = null;

        Session session = sessionFactory.withOptions().tenantIdentifier(tenant).openSession();
        Transaction transaction;

        try {
            transaction = session.beginTransaction();

            Criteria criteria = session.createCriteria(History.class);
            criteria.addOrder(Order.desc("id"));
            criteria.setMaxResults(amount);

            histories = (List<History>) criteria.list();

            transaction.commit();

        } finally {
            session.close();
        }

        return histories;
    }

}
