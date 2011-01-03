package shz.eprocurement.domain;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * A base domain object that provides common behaviour and methods.
 *
 * @author Stephan Huez
 *
 */
public abstract class DomainObject {

    @Override
    public String toString() {
        return new ReflectionToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (obj.getClass() != getClass()) {
            return false;
        }

        return specialisedEquals(obj);
    }

    /**
     * Customised validation
     *
     * @param obj
     * @return
     */
    protected abstract boolean specialisedEquals(Object obj);
}
