package multi;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.hibernate.service.jdbc.connections.spi.MultiTenantConnectionProvider;

public class ActionFactory {

    private PostgresqlConnectionProvider connectionProvider;
    private SessionFactory sessionFactory;

    private CustomerAction customerAction;
    private HistoryAction historyAction;

    public ActionFactory() {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");

        connectionProvider = new PostgresqlConnectionProvider(configuration);

        ServiceRegistry serviceRegistry = new ServiceRegistryBuilder()
                .applySettings(configuration.getProperties())
                .addService(MultiTenantConnectionProvider.class, connectionProvider)
                .buildServiceRegistry();

        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
    }

    public CustomerAction getCustomerAction() {
        if (customerAction == null) {
            customerAction = new CustomerAction(sessionFactory);
        }

        return customerAction;
    }

    public HistoryAction getHistoryAction() {
        if (historyAction == null) {
            historyAction = new HistoryAction(sessionFactory);
        }

        return historyAction;
    }

    public void close() {
        sessionFactory.close();
        connectionProvider.stop();
    }

}
