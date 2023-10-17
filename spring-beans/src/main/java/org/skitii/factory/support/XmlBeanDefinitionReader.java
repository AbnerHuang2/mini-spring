package org.skitii.factory.support;

import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.XmlUtil;
import org.skitii.core.io.Resource;
import org.skitii.factory.PropertyValues;
import org.skitii.factory.config.BeanDefinition;
import org.skitii.factory.config.BeanReference;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.InputStream;
import java.util.ArrayList;

/**
 * @author skitii
 * @since 2023/10/17
 **/
public class XmlBeanDefinitionReader extends AbstractBeanDefinitionReader{

    public XmlBeanDefinitionReader(BeanDefinitionRegistry registry) {
        super(registry);
    }

    @Override
    public int loadBeanDefinitions(Resource resource) {
        try(InputStream inputStream = resource.getInputStream()){
            return doLoadBeanDefinitions(inputStream);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int loadBeanDefinitions(String location) {
        return loadBeanDefinitions(getResourceLoader().getResource(location));
    }

    private int doLoadBeanDefinitions(InputStream inputStream) throws ClassNotFoundException {
        Document doc = XmlUtil.readXML(inputStream);
        Element root = doc.getDocumentElement();
        NodeList childNodes = root.getChildNodes();
        int count = 0;
        for (int i = 0; i < childNodes.getLength(); i++) {
            Node item = childNodes.item(i);
            if ( ! (item instanceof Element) ) continue;
            if (!"bean".equals(item.getNodeName())) continue;
            Element ele = (Element) item;
            String id = ele.getAttribute("id");
            String className = ele.getAttribute("class");
            // 获取 Class，方便获取类中的名称
            Class<?> clazz = Class.forName(className);
            // 优先级 id > name
            String beanName = StrUtil.isNotEmpty(id) ? id : className;
            if (StrUtil.isEmpty(beanName)) {
                beanName = StrUtil.lowerFirst(clazz.getSimpleName());
            }
            // 创建 BeanDefinition
            BeanDefinition beanDefinition = new BeanDefinition(clazz);
            beanDefinition.setPropertyValues(new PropertyValues(new ArrayList<>()));
            // 填充属性
            for (int i1 = 0; i1 < ele.getChildNodes().getLength(); i1++) {
                Node item1 = ele.getChildNodes().item(i1);
                if (!(item1 instanceof Element)) continue;
                if (!"property".equals(item1.getNodeName())) continue;
                Element property = (Element) item1;
                String name = property.getAttribute("name");
                String attrValue = property.getAttribute("value");
                String ref = property.getAttribute("ref");
                Object value = StrUtil.isNotEmpty(ref) ? new BeanReference(ref) : attrValue;

                beanDefinition.getPropertyValues().addPropertyValue(name, value);
            }

            // 注册 BeanDefinition
            getRegistry().registerBeanDefinition(beanName, beanDefinition);
            count++;
        }
        return count;
    }
}