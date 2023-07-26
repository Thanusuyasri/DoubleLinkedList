import java.util.Scanner;

class Node{
    int value;
    Node next;
    Node prev;
    public Node(int value)
    {
        this.value=value;
    }
}
public class Doublelinkedlist {
    Node head;
    Node tail;
    int length;

    void append(int value)
    {
        Node newNode = new Node(value);
        if(length == 0)
        {
            head = newNode;
            tail = newNode;
        }
        else {
            tail.next = newNode;
            newNode.prev=tail;
            tail = newNode;
        }
        length++;
    }

    Node removeFromLast(){
        if(length ==0)
        {
            return null;
        }
        Node temp = tail;
        if(length ==1)
        {
            head = null;
            tail = null;
        }
        else{
            tail = tail.prev;
            tail.next = null;
            temp.prev = null;
        }
        length--;
        return temp;
    }

    void prepend(int value)
    {
        Node newNode = new Node(value);
        if(length ==0)
        {
            head = newNode;
            tail = newNode;
        }
        else {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        }
        length ++;
    }

    Node removeFromFirst()
    {
        if(length == 0)
        {
            return null;
        }
        Node temp = head;
        if(length ==1)
        {
            head = null;
            tail = null;
        }
        else {
            head = head.next;
            head.prev = null;
            temp.next = null;
        }
        length --;
        return temp;
    }

    Node getNode(int index)
    {
        if(index<0 || index>=length)
        {
            return null;
        }
        Node temp = head;
        if(index<length/2)
        {
            for(int i=0;i<index;i++)
            {
                temp = temp.next;
            }
        }
        else {
            temp = tail;
            for(int i = length-1;i>index;i--)
            {
                temp = temp.prev;
            }
        }
        return temp;
    }

    boolean set(int index,int value)
    {
        Node temp = getNode(index);
        if(temp!=null)
        {
            temp.value = value;
            return true;
        }
        return false;
    }

    boolean insertAtIndex(int index,int value)
    {
        if(index<0 || index>length)
        {
            return false;
        }
        if(index==0)
        {
            prepend(value);
            return true;
        }
        if(index==length)
        {
            append(value);
            return true;
        }
        Node newNode = new Node(value);
        Node before = getNode(index-1);
        Node after = before.next;
        before.next = newNode;
        newNode.prev = before;
        after.prev = newNode;
        newNode.next = after;
        length ++;
        return true;
    }

    Node removeAtIndex(int index)
    {
        if(index<0 || index>= length)
        {
            return null;
        }
        if(index == 0)
        {
            return removeFromFirst();
        }
        if(index==length-1)
        {
            return removeFromLast();
        }
        Node temp = getNode(index);
        temp.prev.next = temp.next;
        temp.next.prev = temp.prev;
        temp.next = null;
        temp.prev = null;
        length--;
        return temp;
    }
    void print()
    {
        Node temp = head;
        while (temp!=null)
        {
            System.out.println(temp.value);
            temp = temp.next;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int num = scanner.nextInt();
        Doublelinkedlist dll = new Doublelinkedlist();
        while (num!=-1)
        {
            dll.append(num);
            num = scanner.nextInt();
        }
        int index,value;
        System.out.println("1-Prepend\n2-Remove from last\n3-Remove from first\n4-Get Node\n5-Update Node\n6-Insert at an index\n7-Remove at an index\n8-print\n9-Length\n10-Exit");
        int choice = scanner.nextInt();
        while(choice!=10){
            switch(choice) {
                case 1:
                    System.out.println("Enter a value to append at first:");
                    value = scanner.nextInt();
                    dll.prepend(value);
                    break;
                case 2:
                    System.out.println("The removed element at last is:"+dll.removeFromLast().value);
                    break;
                case 3:
                    System.out.println("The removed element at first is:"+dll.removeFromFirst().value);
                    break;
                case 4:
                    System.out.println("Enter an index to view the element:");
                    index = scanner.nextInt();
                    System.out.println("The element is:"+dll.getNode(index).value);
                    break;
                case 5:
                    System.out.println("Enter an index and element to update:");
                    index = scanner.nextInt();
                    value = scanner.nextInt();
                    dll.set(index,value);
                    break;
                case 6:
                    System.out.println("Enter an index and element to insert an index");
                    index = scanner.nextInt();
                    value = scanner.nextInt();
                    dll.insertAtIndex(index,value);
                    break;
                case 7:
                    System.out.println("Enter an index to remove at an index");
                    index = scanner.nextInt();
                    dll.removeAtIndex(index);
                    break;
                case 8:
                    System.out.println("The elements are:");
                    dll.print();
                    break;
                case 9:
                    System.out.println("The length of the double linked list:"+dll.length);
                    break;
                default:
                    System.out.println("Worng choice.");
            }
            System.out.println("1-Prepend\n2-Remove from last\n3-Remove from first\n4-Get Node\n5-Update Node\n6-Insert at an index\n7-Remove at an index\n8-print\n9-Length\n10-Exit");
            choice = scanner.nextInt();
        }
    }
}
