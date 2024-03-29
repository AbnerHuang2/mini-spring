package org.skitii.context.support;

/**
 * @author skitii
 * @since 2023/10/23
 **/
public class ClassPathXmlApplicationContext extends AbstractXmlApplicationContext{
    String[] locations;

    public ClassPathXmlApplicationContext(String... locations) {
        this.locations = locations;
        // 加载相关bean到容器中
        refresh();
    }

    @Override
    protected String[] getResouresLocations() {
        return locations;
    }
}
