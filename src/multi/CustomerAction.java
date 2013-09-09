package multi;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class CustomerAction {

    private SessionFactory sessionFactory;

    public CustomerAction(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Customer get(String tenant, long id) {
        Customer customer = null;

        Session session = sessionFactory.withOptions().tenantIdentifier(tenant).openSession();

        try {
            Transaction transaction = session.beginTransaction();

            customer = (Customer) session.load(Customer.class, id);

            transaction.commit();

        } finally {
            session.close();
        }

        return customer;
    }

}
