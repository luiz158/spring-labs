package common.hibernate;

import java.io.Serializable;

import org.hibernate.HibernateException;
import org.hibernate.usertype.UserType;

public abstract class ImmutableValueUserType implements UserType {

    public Object assemble(Serializable cached, Object owner) throws HibernateException {
        return cached;
    }

    public Object deepCopy(Object value) throws HibernateException {
        return value;
    }

    public Serializable disassemble(Object value) throws HibernateException {
        return (Serializable) value;
    }

    public boolean equals(Object x, Object y) throws HibernateException {
        return x.equals(y);
    }

    public int hashCode(Object x) throws HibernateException {
        return x.hashCode();
    }

    public boolean isMutable() {
        return false;
    }

    public Object replace(Object original, Object target, Object owner) throws HibernateException {
        return original;
    }

}
