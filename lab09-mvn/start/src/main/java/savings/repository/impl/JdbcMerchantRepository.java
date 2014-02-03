package savings.repository.impl;

import common.math.Percentage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import savings.model.Merchant;
import savings.repository.MerchantRepository;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

//@Repository
public class JdbcMerchantRepository implements MerchantRepository {

    private static final Logger LOG = LoggerFactory.getLogger(JdbcMerchantRepository.class);

    private final Map<String, Merchant> cache = new HashMap<>();

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcMerchantRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @PostConstruct
    void populateCache() {
        LOG.info("Populating merchants cache...");
        String sql =
                "select * " +
                "from MERCHANT";
        for (Merchant merchant : jdbcTemplate.query(sql, merchantMapper)) {
            cache.put(merchant.getNumber(), merchant);
        }
    }

    @PreDestroy
    void clearCache() {
        LOG.info("Clearing merchants cache...");
        cache.clear();
    }

    @Override
    public Merchant findByNumber(String number) {
        Merchant merchant = cache.get(number);
        if (merchant == null) {
            throw new EmptyResultDataAccessException(1);
        }
        return merchant;
    }

    private static final RowMapper<Merchant> merchantMapper = new RowMapper<Merchant>() {
        @Override
        public Merchant mapRow(ResultSet resultSet, int i) throws SQLException {
            Merchant merchant = new Merchant(
                    resultSet.getString("NUMBER"),
                    resultSet.getString("NAME"),
                    new Percentage(resultSet.getBigDecimal("PAYBACK")),
                    PaybackPolices.valueOf(resultSet.getString("PAYBACK_POLICY").toUpperCase()));
            merchant.setId(resultSet.getLong("ID"));
            return merchant;
        }
    };
}
