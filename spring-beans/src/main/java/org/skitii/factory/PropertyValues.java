package org.skitii.factory;


import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * @author skitii
 * @since 2023/10/16
 **/
public class PropertyValues implements Iterable<PropertyValue> {

    private final List<PropertyValue> propertyValueList;

    public PropertyValues(List<PropertyValue> propertyValueList) {
        this.propertyValueList = propertyValueList;
    }

    @Override
    public Iterator<PropertyValue> iterator() {
        return Arrays.asList(getPropertyValues()).iterator();
    }

    PropertyValue[] getPropertyValues() {
        return propertyValueList.toArray(new PropertyValue[0]);
    }

    public void addPropertyValue(PropertyValue pv) {
        this.propertyValueList.add(pv);
    }

    public void addPropertyValue(String name, Object value) {
        addPropertyValue(new PropertyValue(name, value));
    }

}
