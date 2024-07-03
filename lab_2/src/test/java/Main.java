public class Main {
   public static void main(String[] args) {
      Node root = new Node("root");
      Node firstChild = root.addChild("1");
      Node secondChild = root.addChild("2");
      Node node1 = firstChild.addChild("1.1");
      Node node2 = firstChild.addChild("1.2");
      Node node3 = secondChild.addChild("2.1");
      System.out.println(root.toStringAll());
   }
}
