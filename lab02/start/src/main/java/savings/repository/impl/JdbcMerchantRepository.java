package savings.repository.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;

import common.math.Percentage;
import savings.model.Merchant;
import savings.repository.MerchantRepository;

public class JdbcMerchantRepository implements MerchantRepository {

    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Merchant findByNumber(String number) {
        String sql =
                "select * " +
                "from MERCHANT m " +
                "where m.NUMBER = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, number);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return readMerchantFrom(resultSet);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error in findByNumber!", e);
        }
        throw new EmptyResultDataAccessException(1);
    }

    private Merchant readMerchantFrom(ResultSet resultSet) throws SQLException {
        Merchant account = new Merchant(
                resultSet.getString("NUMBER"),
                resultSet.getString("NAME"),
                new Percentage(resultSet.getBigDecimal("PAYBACK"))
        );
        account.setId(resultSet.getLong("ID"));
        return account;
    }
}
