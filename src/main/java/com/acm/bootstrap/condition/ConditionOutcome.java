package com.acm.bootstrap.condition;

import org.springframework.util.ObjectUtils;

public class ConditionOutcome {

    private final boolean match;

    private final String message;

    public ConditionOutcome(boolean match, String message) {
        this.match = match;
        this.message = message;
    }

    /**
     * Create a new {@link ConditionOutcome} instance for a 'match'.
     * 
     * @return the {@link ConditionOutcome}
     */
    public static ConditionOutcome match() {
        return match(null);
    }

    /**
     * Create a new {@link ConditionOutcome} instance for 'match'.
     * 
     * @param message the message
     * @return the {@link ConditionOutcome}
     */
    public static ConditionOutcome match(String message) {
        return new ConditionOutcome(true, message);
    }

    /**
     * Create a new {@link ConditionOutcome} instance for 'no match'.
     * 
     * @param message the message
     * @return the {@link ConditionOutcome}
     */
    public static ConditionOutcome noMatch(String message) {
        return new ConditionOutcome(false, message);
    }

    /**
     * Return {@code true} if the outcome was a match.
     * 
     * @return {@code true} if the outcome matches
     */
    public boolean isMatch() {
        return this.match;
    }

    /**
     * Return an outcome message or {@code null}.
     * 
     * @return the message or {@code null}
     */
    public String getMessage() {
        return this.message;
    }

    @Override
    public int hashCode() {
        return ObjectUtils.hashCode(this.match) * 31 + ObjectUtils.nullSafeHashCode(this.message);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() == obj.getClass()) {
            ConditionOutcome other = (ConditionOutcome) obj;
            return (this.match == other.match && ObjectUtils.nullSafeEquals(this.message, other.message));
        }
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return (this.message == null ? "" : this.message);
    }
}
