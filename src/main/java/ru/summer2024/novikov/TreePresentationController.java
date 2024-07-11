package ru.summer2024.novikov;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.UUID;

/**
 * Контроллер отвечающий за представление списка.
 */
@Path("/")
public class TreePresentationController {
    private final Node root;

    /**
     * Запоминает список, с которым будет работать.
     * 
     * @param list список, с которым будет работать контроллер.
     */
    public TreePresentationController(Node node) {
        root = node;
    }

    /**
     * Возвращает иехархию дерева начиная с текущего узла в виде многоуровневого
     * списка HTML
     * 
     * @return строку с названием текущего узла и иерархией ВСЕХ его потомков в HTML
     */
    public String toHTML(Node node) {
        return node.getName() + " <a href=\"edit/" + node.getID().toString()
                + "\">Редактировать</a>   <a href=\"add/" + node.getID().toString()
                + "\">Добавить дочерний элемент</a>  <a href=\"test/\">TestFunction</a>"
                + "<ul>" + toHTMLRec(node, node.getID().toString()) + "</ul>";
    }

    /**
     * Возвращает иехархию потомков узла в виде многоуровневого списка HTML
     * 
     * @return строку с иерархией ВСЕХ потомков узла в HTML
     */
    private String toHTMLRec(Node node, String parentID) {
        StringBuilder allInfo = new StringBuilder();
        for (Node node1 : node.getChildren())
            allInfo.append("<li>").append(node1.getName())
                    .append(" <a href=\"edit/" + parentID + ":" + node1.getID().toString()
                            + "\">Редактировать</a>   <a href=\"add/" + parentID + ":" + node1.getID().toString()
                            + "\">Добавить дочерний элемент</a>    <a href=\"del/" + parentID + ":"
                            + node1.getID().toString()
                            + "\">Удалить элемент</a></li>")
                    .append("<ul>").append(toHTMLRec(node1, parentID + ":" + node1.getID().toString()))
                    .append("</ul></li>");
        return allInfo.toString();
    }
    @GET
    @Path("/test/")
    @Produces("text/html")
    public String getTestFunction() {
        String result = "<html>" +
                " <head>" +
                " <title>TestFunction</title>" +
                " </head>" +
                " <body>" +
                " <h1>TestFunction</h1>";
        result += " <form method=\"post\" action=\"/test/\">" +
                " <input type=\"submit\"/>";
        result += " </form>" +
                " </body>" +
                "</html>";
        return result;
    }
    @POST
    @Path("/test/")
    @Produces("text/html")
    public Response postTestFunction() {
        int i=-2000000000;
        while(i<2000000000){
            i+=1;i-=1;}
        try {
            return Response.seeOther(new URI("/")).build();
        } catch (URISyntaxException e) {
            throw new IllegalStateException("Ошибка построения URL для перенаправления");
        }
    }
    /**
     * Выводит HTML-страницу со списком, ссылками на страницы редактирования и
     * копкой добавления записи.
     * 
     * @return HTML-страница со списком.
     */
    @GET
    @Path("/")
    @Produces("text/html")
    public String getList() {
        StringBuilder result = new StringBuilder("<html>" +
                "  <head>" +
                "    <title>Вывод дерева</title>" +
                "  </head>" +
                "  <body>" +
                "    <h1>Дерево</h1>" +
                "    <ul>");
        result.append(toHTML(root));
        result.append("    </ul>").append("      <br/>").append("      </form>").append("  </body>").append("</html>");
        return result.toString();
    }

    /**
     * Выводит страничку для редактирования одного элемента.
     *
     * @param itemId индекс элемента списка.
     * @return страничка для редактирования одного элемента.
     */
    @GET
    @Path("/edit/{id}")
    @Produces("text/html")
    public String getEditPage(@PathParam("id") String itemId) {
        var id = itemId.split(":");
        Node node = root;
        for (int i = 1; i < id.length; i++)
            node = node.getChild(UUID.fromString(id[i]));
        String result = "<html>" +
                " <head>" +
                " <title>Редактирование элемента списка</title>" +
                " </head>" +
                " <body>" +
                " <h1>Редактирование элемента списка</h1>";
        result += " <form method=\"post\" action=\"/edit/" + itemId + "\">" +
                " <p>Значение</p>" +
                " <input type=\"text\" name=\"value\" value=\"" + node.getName() + "\"/>" +
                " <input type=\"submit\"/>";
        result += " </form>" +
                " </body>" +
                "</html>";
        return result;
    }

    /**
     * Редактирует элемент списка на основе полученных данных.
     *
     * @param itemId индекс элемента списка.
     * @return перенаправление на основную страницу со списком.
     */
    @POST
    @Path("/edit/{id}")
    @Produces("text/html")
    public Response editItem(@PathParam("id") String itemId, @FormParam("value") String itemValue) {
        var id = itemId.split(":");
        Node node = root;
        for (int i = 1; i < id.length; i++)
            node = node.getChild(UUID.fromString(id[i]));
        node.setName(itemValue);
        try {
            return Response.seeOther(new URI("/")).build();
        } catch (URISyntaxException e) {
            throw new IllegalStateException("Ошибка построения URL для перенаправления");
        }
    }

    /**
     * Выводит страничку для добавления дочернего элемента.
     *
     * @param itemId индекс элемента списка.
     * @return страничка для добавления дочернего элемента.
     */
    @GET
    @Path("/add/{id}")
    @Produces("text/html")
    public String getAddPage(@PathParam("id") String itemId) {
        var id = itemId.split(":");
        Node node = root;
        for (int i = 1; i < id.length; i++)
            node = node.getChild(UUID.fromString(id[i]));
        String result = "<html>" +
                " <head>" +
                " <title>Добавление дочернего элемента</title>" +
                " </head>" +
                " <body>" +
                " <h1>Добавление дочернего элемента</h1>" +
                " <form method=\"post\" action=\"/add/" + itemId + "\">" +
                " <p>Значение</p>" +
                " <input type=\"text\" name=\"value\" value=\"" + node.getName() + "\"/>" +
                " <input type=\"submit\"/>";
        result += " </form>" +
                " </body>" +
                "</html>";
        return result;
    }

    /**
     * Добавляет дочерний элемент на основе полученных данных
     *
     * @param itemId индекс элемента списка.
     * @return перенаправление на основную страницу со списком.
     */
    @POST
    @Path("/add/{id}")
    @Produces("text/html")
    public Response addItem(@PathParam("id") String itemId, @FormParam("value") String itemValue) {
        var id = itemId.split(":");
        Node node = root;
        for (int i = 1; i < id.length; i++)
            node = node.getChild(UUID.fromString(id[i]));
        node.addChild(itemValue);
        try {
            return Response.seeOther(new URI("/")).build();
        } catch (URISyntaxException e) {
            throw new IllegalStateException("Ошибка построения URI для перенаправления");
        }
    }

    /**
     * Выводит страничку для добавления дочернего элемента.
     *
     * @param itemId индекс элемента списка.
     * @return страничка для добавления дочернего элемента.
     */
    @GET
    @Path("/del/{id}")
    @Produces("text/html")
    public String getRemovePage(@PathParam("id") String itemId) {
        String result = "<html>" +
                " <head>" +
                " <title>Удаление элемента</title>" +
                " </head>" +
                " <body>" +
                " <h1>Удаление элемента</h1>" +
                " <form method=\"post\" action=\"/del/" + itemId + "\">" +
                " <p>Нажмите \"Отправить\" для подтверждения удаления</p>" +
                " <input type=\"submit\"/>";
        result += " </form>" +
                " </body>" +
                "</html>";
        return result;
    }

    /**
     * Удаляет элемент
     *
     * @param itemId индекс элемента.
     * @return перенаправление на основную страницу со списком.
     */
    @POST
    @Path("/del/{id}")
    @Produces("text/html")
    public Response removeItem(@PathParam("id") String itemId) {
        var id = itemId.split(":");
        Node node = root;
        int i;
        for (i = 1; i < id.length - 1; i++)
            node = node.getChild(UUID.fromString(id[i]));
        node.deleteChild(UUID.fromString(id[id.length - 1]));
        try {
            return Response.seeOther(new URI("/")).build();
        } catch (URISyntaxException e) {
            throw new IllegalStateException("Ошибка построения URI для перенаправления");
        }
    }

}
