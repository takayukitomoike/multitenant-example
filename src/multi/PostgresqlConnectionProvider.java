package multi;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.hibernate.cfg.Configuration;
import org.hibernate.service.jdbc.connections.internal.DriverManagerConnectionProviderImpl;
import org.hibernate.service.jdbc.connections.spi.MultiTenantConnectionProvider;

public class PostgresqlConnectionProvider implements MultiTenantConnectionProvider {

    private DriverManagerConnectionProviderImpl connectionProvider;

    public PostgresqlConnectionProvider(Configuration configuration) {
        connectionProvider = new DriverManagerConnectionProviderImpl();
        connectionProvider.configure(configuration.getProperties());
    }

    @Override
    public Connection getAnyConnection() throws SQLException {
        return connectionProvider.getConnection();
    }

    @Override
    public Connection getConnection(String tenantIdentifier) throws SQLException {
        Connection connection = connectionProvider.getConnection();

        Statement statement = connection.createStatement();
        statement.execute("SET search_path TO " + tenantIdentifier);

        return connection;
    }

    @Override
    public void releaseAnyConnection(Connection connection) throws SQLException {
        connectionProvider.closeConnection(connection);
    }

    @Override
    public void releaseConnection(String tenantIdentifier, Connection connection) throws SQLException {
        connectionProvider.closeConnection(connection);
    }

    @Override
    public boolean supportsAggressiveRelease() {
        return connectionProvider.supportsAggressiveRelease();
    }

    @Override
    public boolean isUnwrappableAs(Class unwrapType) {
        return connectionProvider.isUnwrappableAs(unwrapType);
    }

    @Override
    public <T> T unwrap(Class<T> unwrapType) {
        return connectionProvider.unwrap(unwrapType);
    }

    public void stop() {
        connectionProvider.stop();
    }

}
