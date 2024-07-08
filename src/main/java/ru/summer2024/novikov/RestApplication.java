package ru.summer2024.novikov;

import javax.ws.rs.core.Application;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Web-приложение в котором регистрируются все ресурсы.
 */
public class RestApplication extends Application {
    private Node root = new Node("root");
    private List<String> list = new ArrayList<>();

    public RestApplication() {
        Node firstChild = root.addChild("1");
        Node secondChild = root.addChild("2");
        firstChild.addChild("1.1");
        firstChild.addChild("1.2");
        secondChild.addChild("2.1");
        list.add("aaa");
        list.add("bbb");
        list.add("ccc");
        list.add("ddd");
    }

    /**
     * Возвращает список всех ресурсов web-приложения.
     * 
     * @return список всех ресурсов web-приложения.
     */
    @Override
    public Set<Object> getSingletons() {
        Set<Object> resources = new HashSet<>();
        resources.add(new ListPresentationController(list));
        resources.add(new TreePresentationController(root));
        resources.add(new LoginController());
        return resources;
    }
}
