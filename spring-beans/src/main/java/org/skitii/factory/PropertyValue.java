package org.skitii.factory;

import com.sun.istack.internal.Nullable;
import lombok.Data;

import java.io.Serializable;

/**
 * @author skitii
 * @since 2023/10/16
 **/
@Data
public class PropertyValue implements Serializable {
    private final String name;

    @Nullable
    private final Object value;

    public PropertyValue(String name, @Nullable Object value) {
        this.name = name;
        this.value = value;
    }

}
