package savings.repository.impl;

import static com.googlecode.catchexception.CatchException.catchException;
import static com.googlecode.catchexception.CatchException.caughtException;
import static org.fest.assertions.Assertions.assertThat;
import static savings.PaybackFixture.merchant;
import static savings.PaybackFixture.merchantNumber;

import javax.sql.DataSource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import common.math.Percentage;
import common.sql.TestDataSourceFactory;
import savings.PaybackFixture;
import savings.model.Merchant;

public class JdbcMerchantRepositoryTest {

    JdbcMerchantRepository repository;

    @Before
    public void setUp() throws Exception {
        repository = new JdbcMerchantRepository(new JdbcTemplate(createDataSource()));
        repository.populateCache();
    }

    @After
    public void tearDown() {
        repository.clearCache();
    }

    @Test
    public void shouldThrowWhenMerchantNotFound() {
        catchException(repository, EmptyResultDataAccessException.class).findByNumber("111111111");

        assertThat(caughtException()).isNotNull();
    }

    @Test
    public void shouldFindMerchantByNumber() {
        Merchant merchant = repository.findByNumber(merchantNumber);

        assertThat(merchant).isNotNull();
        assertThat(merchant.getNumber()).isEqualTo(merchantNumber);
        assertThat(merchant.getName()).isEqualTo(merchant().getName());
        assertThat(merchant.getPayback()).isEqualTo(merchant().getPayback());
    }

    @Test
    public void shouldClearCacheOnShutdown() {
        tearDown();

        catchException(repository, EmptyResultDataAccessException.class).findByNumber("1234567890");

        assertThat(caughtException()).isNotNull();
    }

    private DataSource createDataSource() throws Exception {
        return new TestDataSourceFactory(
                new ClassPathResource("/META-INF/sql/schema.sql"),
                new ClassPathResource("/META-INF/sql/data.sql")
        ).getObject();
    }
}
