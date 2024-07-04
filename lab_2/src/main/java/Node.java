import java.util.ArrayList;
import java.util.UUID;

public class Node {
   String name;
   UUID ID;
   ArrayList<Node> childs = new ArrayList<>();

   /**
    * ����������� ���� ������
    * @param name ��� ����
    */
   public Node(String name) {
      this.name = name;
      this.ID = UUID.randomUUID();
   }

   @Override
   /**
    * ���������� ���������� �� ����
    * @return ������ � ��������� �������� ���� � ����������� � ��� �����
    */
   public String toString() {
      String childList = name + "\t" + ID.toString() + "\n";
      for (Node node : childs) {
         childList += "\t\t" + node.name + "\t" + node.ID.toString() + "\n";
      }
      return childList;
   }

   /**
    * ���������� �������� ������ ������� � �������� ����
    * @return ������ � ��������� �������� ���� � ��������� ���� ��� ��������
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
    * ��������� ����� ����
    * @param name ����� ���
    */
   public void setName(String name) {
      this.name = name;
   }

   /**
    * @return ��� ����
    */
   public String getName() {
      return name;
   }

   /**
    *
    * @return ID ����
    */
   public UUID getID() {
      return ID;
   }

   /**
    * ������� ����� ����-�������
    * @param name ��� ������ ����
    * @return ������ �� ����� ����
    */
   public Node addChild(String name) {
      Node node = new Node(name);
      childs.add(node);
      return node;
   }

   /**
    * ����� ������� ������.
    * @param name ��� �������� ����
    * @return ������ �� ������� ���� ��� null, ���� ��� �� ����������
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
    * �������� ������� �� ��� �����
    * @param name - ��� �������
    * @return true, ���� �������� ���������
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
    * �������� ������� �� ��� ID
    * @param ID �������
    * @return true, ���� �������� ���������
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
    * ������� ���� �������� �������� ����
    * @return true, ���� �������� ���������
    */
   public boolean deleteAllChildren() {
      if (childs.size() <= 0)
         return false;
      childs.clear();
      return true;
   }

   /**
    * ���������� ���������� ��������
    * @return ���������� ��������
    */
   public int count() {
      return childs.size();
   }
}
