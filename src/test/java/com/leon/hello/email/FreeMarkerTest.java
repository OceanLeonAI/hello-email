package com.leon.hello.email;

import com.leon.hello.email.entity.Car;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.Version;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @PROJECT_NAME: hello-email
 * @CLASS_NAME: FreeMarkerTest
 * @AUTHOR: OceanLeonAI
 * @CREATED_DATE: 2022/5/17 18:00
 * @Version 1.0
 * @DESCRIPTION: freemarker
 * <a>https://geek-docs.com/java/java-tutorial/freemarker.html</a>
 **/
public class FreeMarkerTest {

    @Test
    public void freeMarkerSimpleStringTest() throws IOException, TemplateException {

        // Configuration用于设置 FreeMarker 设置； 它以 FreeMarker 库的版本为参数。
        Configuration cfg = new Configuration(new Version("2.3.31"));

        // setClassForTemplateLoading()设置将使用其方法来加载模板的类。
        cfg.setClassForTemplateLoading(FreeMarkerTest.class, "/");
        cfg.setDefaultEncoding("UTF-8");

        // 使用getTemplate()方法，我们检索test.ftl模板文件。
        Template template = cfg.getTemplate("freemarker/simpleString.ftl");

        // 数据模型已创建。 来自模型的数据将被动态放置到 FreeMarker 模板文件中。
        Map<String, Object> templateData = new HashMap<>();
        templateData.put("msg", "Today is a beautiful day");

        // process()方法使用提供的数据模型执行模板，并将生成的输出写入提供的写入器。
        try (StringWriter out = new StringWriter()) {

            template.process(templateData, out);
            System.out.println(out.getBuffer().toString());

            out.flush();
        }
    }

    @Test
    public void freeMarkerListTest() throws IOException, TemplateException {

        // Configuration用于设置 FreeMarker 设置； 它以 FreeMarker 库的版本为参数。
        Configuration cfg = new Configuration(new Version("2.3.31"));

        // setClassForTemplateLoading()设置将使用其方法来加载模板的类。
        cfg.setClassForTemplateLoading(FreeMarkerTest.class, "/");
        cfg.setDefaultEncoding("UTF-8");

        // 使用getTemplate()方法，我们检索test.ftl模板文件。
        Template template = cfg.getTemplate("freemarker/list.ftl");

        // 数据模型已创建。 来自模型的数据将被动态放置到 FreeMarker 模板文件中。
        Map<String, Object> templateData = new HashMap<>();

        Car c1 = new Car("Audi", 52642);
        Car c2 = new Car("Volvo", 29000);
        Car c3 = new Car("Skoda", 9000);

        List<Car> cars = new ArrayList<>();
        cars.add(c1);
        cars.add(c2);
        cars.add(c3);

        templateData.put("cars", cars);

        // process()方法使用提供的数据模型执行模板，并将生成的输出写入提供的写入器。
        try (StringWriter out = new StringWriter()) {

            template.process(templateData, out);
            System.out.println(out.getBuffer().toString());

            out.flush();
        }
    }

}
