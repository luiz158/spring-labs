package savings.repository.impl;

import static org.joda.time.DateTime.now;

import java.sql.*;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SingleColumnRowMapper;
import org.springframework.stereotype.Repository;

import savings.model.AccountIncome;
import savings.model.PaybackConfirmation;
import savings.model.Purchase;
import savings.repository.PaybackRepository;

@Repository
public class JdbcPaybackRepository implements PaybackRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcPaybackRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public PaybackConfirmation save(AccountIncome income, Purchase purchase) {
        String sql =
                "insert into PAYBACK (NUMBER, AMOUNT, DATE, ACCOUNT_NUMBER, MERCHANT_NUMBER, TRANSACTION_AMOUNT, TRANSACTION_DATE) " +
                "values (?, ?, ?, ?, ?, ?, ?)";

        String number = nextConfirmationNumber();
        jdbcTemplate.update(sql,
                number,
                income.getAmount().getAmount(),
                new Date(now().toDate().getTime()),
                income.getAccountNumber(),
                purchase.getMerchantNumber(),
                purchase.getAmount().getAmount(),
                new Date(purchase.getDate().toDate().getTime()));

        return new PaybackConfirmation(number, income);
    }

    private String nextConfirmationNumber() {
        String sql = "select NEXTVAL('SEQ_PAYBACK_CONFIRMATION_NUMBER')";
        return jdbcTemplate.queryForObject(sql, String.class);
    }
}
