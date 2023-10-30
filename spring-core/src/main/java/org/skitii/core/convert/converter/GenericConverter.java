package org.skitii.core.convert.converter;

import java.util.Set;

/**
 * @author skitii
 * @since 2023/10/30
 **/
public interface GenericConverter {

        Set<ConvertiblePair> getConvertibleTypes();

        Object convert(Object source, Class sourceType, Class targetType);

        /**
        * Holder for a source-to-target class pair.
        */
        final class ConvertiblePair {

            private final Class<?> sourceType;

            private final Class<?> targetType;

            public ConvertiblePair(Class<?> sourceType, Class<?> targetType) {
                this.sourceType = sourceType;
                this.targetType = targetType;
            }

            public Class<?> getSourceType() {
                return this.sourceType;
            }

            public Class<?> getTargetType() {
                return this.targetType;
            }

            @Override
            public boolean equals(Object other) {
                if (this == other) {
                    return true;
                }
                if (!(other instanceof ConvertiblePair)) {
                    return false;
                }
                ConvertiblePair otherPair = (ConvertiblePair) other;
                return (this.sourceType == otherPair.sourceType && this.targetType == otherPair.targetType);
            }

            @Override
            public int hashCode() {
                return (this.sourceType.hashCode() * 31 + this.targetType.hashCode());
            }

            @Override
            public String toString() {
                return (this.sourceType.getName() + " -> " + this.targetType.getName());
            }
        }
}
