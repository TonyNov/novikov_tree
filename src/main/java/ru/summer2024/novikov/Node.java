package ru.summer2024.novikov;

import java.util.ArrayList;
import java.util.UUID;

public class Node {
   String name;
   UUID id;
   ArrayList<Node> children = new ArrayList<>();

   /**
    * Конструктор узла дерева
    * 
    */
   public Node() {
   }

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
      for (Node node : children) {
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
/**
    * Возвращает иехархию потомков узла
    * 
    * @return строку с иерархией ВСЕХ потомков узла
    */
   private String toStringAll(int step) {
      StringBuilder allInfo = new StringBuilder();
      StringBuilder tempString = new StringBuilder();
      for (int i = 0; i < step; i++)
         tempString.append("\t");
      for (Node node : children) {
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

   /**
    * Возвращает иехархию потомков узла в виде многоуровневого списка HTML
    * 
    * @return строку с иерархией ВСЕХ потомков узла в HTML
    */
   private String toHTMLRec() {
      StringBuilder allInfo = new StringBuilder();
      for (Node node : children)
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
    * Возвращает имя узла
    * 
    * @return имя узла
    */
   public String getName() {
      return name;
   }

   /**
    * Изменение id узла
    * 
    * @param id
    */
   private void setID(UUID id) {
      this.id = id;
   }

   /**
    *
    * @return ID узла
    */
   public UUID getId() {
      return id;
   }

   /**
    * Возвращает список дочерних элементов
    * 
    * @return ArrayList<Node> - список дочерних элементов
    */
   public ArrayList<Node> getChildren() {
      return children;
   }

   /**
    * Изменение списка потомков. Используется для загрузки в файл и выгрузки из
    * файла
    * 
    * @param list новый список дочерних элементов
    */
   private void setChildren(ArrayList<Node> list) {
      this.children = list;
   }

   /**
    * Создает новый узел-потомок
    * 
    * @param name имя нового узла
    * @return Ссылку на новый узел
    */
   public Node addChild(String name) {
      Node node = new Node(name);
      children.add(node);
      return node;
   }

   /**
    * Поиск ребенка дерева.
    * 
    * @param name имя искомого узла
    * @return Ссылку на искомый узел или null, если его не существует
    */
   public Node getChild(String name) {
      for (int i = 0; i < children.size(); i++)
         if (name.equals(children.get(i).name))
            return children.get(i);
      return null;
   }

   /**
    * Удаление потомка по его имени
    * 
    * @param name - имя потомка
    * @return true, если удаление произошло
    */
   public boolean deleteChild(String name) {
      for (int i = 0; i < children.size(); i++) {
         if (name.equals(children.get(i).name)) {
            children.remove(i);
            return true;
         }
      }
      return false;
   }

   /**
    * Удаление потомка по его ID
    * 
    * @param id потомка
    * @return true, если удаление произошло
    */
   public boolean deleteChild(UUID id) {
      for (int i = 0; i < children.size(); i++) {
         if (id.equals(children.get(i).id)) {
            children.remove(i);
            return true;
         }
      }
      return false;
   }

   /**
    * Удаляет всех потомков текущего узла
    * 
    * @return true, если удаление произошло
    */
   public boolean deleteAllChildren() {
      if (children.isEmpty())
         return false;
      children.clear();
      return true;
   }

   /**
    * Возвращает количество потомков
    * 
    * @return количество потомков
    */
   public int count() {
      return children.size();
   }
}
