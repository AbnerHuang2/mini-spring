package org.skitii.context.test.support;

/**
 * @author skitii
 * @since 2023/10/23
 **/
public class ClassPathXmlApplicationContext extends AbstractXmlApplicationContext{
    String[] locations;

    public ClassPathXmlApplicationContext(String... locations) {
        this.locations = locations;
        refresh();
    }

    @Override
    protected String[] getResouresLocations() {
        return locations;
    }
}
