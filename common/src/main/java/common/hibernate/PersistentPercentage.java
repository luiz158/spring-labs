package common.hibernate;

import static org.hibernate.type.StandardBasicTypes.BIG_DECIMAL;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;

import common.math.Percentage;

public class PersistentPercentage extends ImmutableValueUserType {

    @Override
    public int[] sqlTypes() {
        return new int[] { Types.NUMERIC };
    }

    @Override
    public Class returnedClass() {
        return Percentage.class;
    }

    @Override
    public Object nullSafeGet(ResultSet rs, String[] names, SessionImplementor session, Object owner)
            throws HibernateException, SQLException {

        return new Percentage((BigDecimal) BIG_DECIMAL.nullSafeGet(rs, names, session, owner));
    }

    @Override
    public void nullSafeSet(PreparedStatement st, Object value, int index, SessionImplementor session)
            throws HibernateException, SQLException {

        BIG_DECIMAL.nullSafeSet(st, ((Percentage) value).asBigDecimal(), index, session);
    }
}
