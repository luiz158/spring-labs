package common.sql;

import static org.h2.util.IOUtils.getBufferedReader;
import static org.h2.util.IOUtils.readStringAndClose;
import static org.joda.time.DateTimeUtils.currentTimeMillis;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.h2.Driver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

public class TestDataSourceFactory implements FactoryBean<DataSource> {

    private static final Logger LOG = LoggerFactory.getLogger(TestDataSourceFactory.class);

    private final Resource schemaLocation;

    private final Resource dataLocation;

    private DataSource dataSource;

    public TestDataSourceFactory(Resource schemaLocation, Resource dataLocation) {
        this.schemaLocation = schemaLocation;
        this.dataLocation = dataLocation;
    }

    @Override
    public DataSource getObject() throws Exception {
        if (dataSource == null) {
            createDataSource();
        }
        return dataSource;
    }

    @Override
    public Class<?> getObjectType() {
        return DataSource.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    private void createDataSource() throws IOException, SQLException {
        dataSource = new SimpleDriverDataSource(
                Driver.load(), "jdbc:h2:mem:test" + currentTimeMillis() + ";DB_CLOSE_DELAY=-1",  "sa", ""
        );

        populateDataSource();
    }

    private void populateDataSource() throws IOException, SQLException {
        try (Connection connection = dataSource.getConnection()) {
            executeSql(connection, readSqlFrom(schemaLocation));
            executeSql(connection, readSqlFrom(dataLocation));
        }
    }

    private String readSqlFrom(Resource resource) throws IOException {
        return readStringAndClose(getBufferedReader(resource.getInputStream()), -1);
    }

    private void executeSql(Connection connection, String sql) throws IOException, SQLException {
        LOG.debug("Executing script:\n" + sql);
        try (Statement statement = connection.createStatement()) {
            statement.execute(sql);
        }
    }
}
