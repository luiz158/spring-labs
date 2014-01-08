package savings.repository.impl;

import static org.joda.money.CurrencyUnit.EUR;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.joda.money.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import common.math.Percentage;
import savings.model.Account;
import savings.model.Objective;
import savings.repository.AccountRepository;

@Repository
public class JdbcAccountRepository implements AccountRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcAccountRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Account findByCreditCard(String creditCardNumber) {
        Account account = findAccountByCreditCard(creditCardNumber);
        loadAccountObjectives(account);
        return account;
    }

    private Account findAccountByCreditCard(String creditCardNumber) {
        String sql =
            "select * " +
            "from ACCOUNT a " +
                "join ACCOUNT_CREDIT_CARD c on (a.ID = c.ACCOUNT_ID) " +
            "where c.NUMBER = ?";
        Account account = jdbcTemplate.queryForObject(sql, accountMapper, creditCardNumber);
        if (account == null) {
            throw new EmptyResultDataAccessException(1);
        }
        return account;
    }

    private static final RowMapper<Account> accountMapper = new RowMapper<Account>() {
        @Override
        public Account mapRow(ResultSet resultSet, int i) throws SQLException {
            Account account = new Account(
                    resultSet.getString("NUMBER"),
                    resultSet.getString("NAME")
            );
            account.setId(resultSet.getLong("ID"));
            return account;
        }
    };

    private void loadAccountObjectives(Account account) {
        String sql =
                "select * " +
                "from ACCOUNT_OBJECTIVE o " +
                "where o.ACCOUNT_ID = ?";
        for (Objective objective : jdbcTemplate.query(sql, objectiveMapper, account.getId())) {
            account.withObjective(objective);
        }
    }

    private static final RowMapper<Objective> objectiveMapper = new RowMapper<Objective>() {
        @Override
        public Objective mapRow(ResultSet resultSet, int i) throws SQLException {
            Objective objective = new Objective(
                    resultSet.getString("NAME"),
                    new Percentage(resultSet.getBigDecimal("ALLOCATION")),
                    Money.of(EUR, resultSet.getBigDecimal("SAVINGS"))
            );
            objective.setId(resultSet.getLong("ID"));
            return objective;
        }
    };

    @Override
    public void update(Account account) {
    }
}
