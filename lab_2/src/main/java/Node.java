import java.util.ArrayList;
import java.util.UUID;

public class Node {
   String name;
   UUID ID;
   ArrayList<Node> childs = new ArrayList<>();

   /**
    * Конструктор узал дерева
    * @param name имя узла
    */
   public Node(String name) {
      this.name = name;
      this.ID = UUID.randomUUID();
   }

   @Override
   /**
    * Возвращает информацию об узле
    * @return Строка с названием текущего узла и информацией о его детях
    */
   public String toString() {
      String childList = name + "\t" + ID.toString() + "\n";
      for (Node node : childs) {
         childList += "\t\t" + node.name + "\t" + node.ID.toString() + "\n";
      }
      return childList;
   }

   /**
    * Возвращает иехархию дерева начиная с текущего узла
    * @return строку с названием текущего узла и иерархией ВСЕХ его потомков
    */
   public String toStringAll() {
      return name + "\n" + toStringAll(1);
   }

   private String toStringAll(int step) {
      String allInfo = "";
      for (Node node : childs) {
         for (int i = 0; i < step; i++)
            allInfo += '\t';
         allInfo += node.name + "\n" + node.toStringAll(step + 1);
      }
      return allInfo;
   }

   /**
    * Изменение имени узла
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
   public UUID getID() {
      return ID;
   }

   /**
    * Создает новый узел-потомок
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
    * @param ID потомка
    * @return true, если удаление произошло
    */
   public boolean deleteChild(UUID ID) {
      int i = 0;
      while (i < childs.size() && ID != childs.get(i).ID)
         i++;
      if (i >= childs.size())
         return false;
      childs.remove(i);
      return true;
   }

   /**
    * Удаляет всех потомков текущего узла
    * @return true, если удаление произошло
    */
   public boolean deleteAllChildren() {
      if (childs.size() <= 0)
         return false;
      childs.clear();
      return true;
   }

   /**
    * Возвращает количество потомков
    * @return количество потомков
    */
   public int count() {
      return childs.size();
   }
}
