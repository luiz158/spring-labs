package savings.repository.impl;

import static com.googlecode.catchexception.CatchException.catchException;
import static com.googlecode.catchexception.CatchException.caughtException;
import static org.fest.assertions.Assertions.assertThat;

import javax.sql.DataSource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.dao.EmptyResultDataAccessException;

import common.math.Percentage;
import common.sql.TestDataSourceFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import savings.ConfiguredDatabaseTest;
import savings.model.Merchant;

@RunWith(SpringJUnit4ClassRunner.class)
public class JdbcMerchantRepositoryTest extends ConfiguredDatabaseTest {

    @Autowired
    JdbcMerchantRepository repository = null;

    @Before
    public void setUp(){
        repository.populateCache();
    }

    @After
    public void cleanUp(){
        repository.clearCache();
    }

    @Test
    public void shouldThrowWhenMerchantNotFound() {
        catchException(repository, EmptyResultDataAccessException.class).findByNumber("111111111");

        assertThat(caughtException()).isNotNull();
    }

    @Test
    public void shouldFindMerchantByNumber() {
        Merchant merchant = repository.findByNumber("1234567890");

        assertThat(merchant).isNotNull();
        assertThat(merchant.getNumber()).isEqualTo("1234567890");
        assertThat(merchant.getName()).isEqualTo("Guns & Bombs");
        assertThat(merchant.getPayback()).isEqualTo(Percentage.of("6%"));
    }

    @Test
    public void shouldClearCacheOnShutdown() {
        cleanUp();

        catchException(repository, EmptyResultDataAccessException.class).findByNumber("1234567890");

        assertThat(caughtException()).isNotNull();
    }

}
