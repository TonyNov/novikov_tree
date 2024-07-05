package ru.summer2024.novikov;

import java.util.ArrayList;
import java.util.UUID;

public class Node {
   String name;
   UUID id;
   ArrayList<Node> childs = new ArrayList<>();

   /**
    * Конструктор узал дерева
    * 
    * @param name имя узла
    */
   public Node(String name) {
      this.name = name;
      this.id = UUID.randomUUID();
   }

   @Override
   /**
    * Возвращает информацию об узле
    * 
    * @return Строка с названием текущего узла и информацией о его детях
    */
   public String toString() {
      StringBuilder childList = new StringBuilder();
      childList.append(name).append("\t").append(id.toString()).append("\n");
      for (Node node : childs) {
         childList.append("\t\t").append(node.name).append("\t").append(node.id.toString()).append("\n");
      }
      return childList.toString();
   }

   /**
    * Возвращает иехархию дерева начиная с текущего узла
    * 
    * @return строку с названием текущего узла и иерархией ВСЕХ его потомков
    */
   public String toStringAll() {
      return name + "\n" + toStringAll(1);
   }

   private String toStringAll(int step) {
      StringBuilder allInfo = new StringBuilder();
      StringBuilder tempString = new StringBuilder();
      for (int i = 0; i < step; i++)
         tempString.append("\t");
      for (Node node : childs) {
         allInfo.append(tempString).append(node.name).append("\n").append(node.toStringAll(step + 1));
      }
      return allInfo.toString();
   }

   /**
    * Возвращает иехархию дерева начиная с текущего узла в виде многоуровневого
    * списка HTML
    * 
    * @return строку с названием текущего узла и иерархией ВСЕХ его потомков в HTML
    */
   public String toHTML() {
      return "root<ul>" + toHTMLRec() + "</ul>";
   }

   private String toHTMLRec() {
      StringBuilder allInfo=new StringBuilder();
      for (Node node : childs)
         allInfo.append("<li>").append(node.name).append("<ul>").append(node.toHTMLRec()).append("</ul></li>");
      return allInfo.toString();
   }

   /**
    * Изменение имени узла
    * 
    * @param name новое имя
    */
   public void setName(String name) {
      this.name = name;
   }

   /**
    * @return имя узла
    */
   public String getName() {
      return name;
   }

   /**
    *
    * @return ID узла
    */
   public UUID getId() {
      return id;
   }

   /**
    * Создает новый узел-потомок
    * 
    * @param name имя нового узла
    * @return Ссылку на новый узел
    */
   public Node addChild(String name) {
      Node node = new Node(name);
      childs.add(node);
      return node;
   }

   /**
    * Поиск ребенка дерева.
    * 
    * @param name имя искомого узла
    * @return Ссылку на искомый узел или null, если его не существует
    */
   public Node getChild(String name) {
      int i = 0;
      while (i < childs.size() && !name.equals(childs.get(i).name))
         i++;
      if (i < childs.size())
         return childs.get(i);
      return null;
   }

   /**
    * Удаление потомка по его имени
    * 
    * @param name - имя потомка
    * @return true, если удаление произошло
    */
   public boolean deleteChild(String name) {
      int i = 0;
      while (i < childs.size() && !name.equals(childs.get(i).name))
         i++;
      if (i >= childs.size())
         return false;
      childs.remove(i);
      return true;
   }

   /**
    * Удаление потомка по его ID
    * 
    * @param id потомка
    * @return true, если удаление произошло
    */
   public boolean deleteChild(UUID id) {
      int i = 0;
      while (i < childs.size() && id != childs.get(i).id)
         i++;
      if (i >= childs.size())
         return false;
      childs.remove(i);
      return true;
   }

   /**
    * Удаляет всех потомков текущего узла
    * 
    * @return true, если удаление произошло
    */
   public boolean deleteAllChildren() {
      if (childs.isEmpty())
         return false;
      childs.clear();
      return true;
   }

   /**
    * Возвращает количество потомков
    * 
    * @return количество потомков
    */
   public int count() {
      return childs.size();
   }
}
