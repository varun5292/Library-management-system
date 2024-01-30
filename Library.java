import java.util.Scanner;

import javax.lang.model.util.ElementScanner6;

public class Library {
Scanner scan = new Scanner(System.in);
   class Node{  
        String name;
        int code;  
        String author;
        String type; 
        Node next;  
        int available;
        public Node(String name,int code,String author,String type) {  
            this.name = name;  
            this.code=code;
            this.author=author;
            this.type=type;
            this.available=1;
        }
    }
    Node head, tail = null;
    int front = -1;int rear = -1;//For user Queue
    int f=-1;int r=-1;//For book cart queue
    int size = 10;

    String[] userQueue = new String[size];
    String[] cartQueue=new String [size];
    void addToUserQueue(String userName) {
        if (isFull()) {
            System.out.println("User queue is full");
        } else {
            if (front == -1) {
                front = 0;
            }
            rear++;
            userQueue[rear] = userName;
            System.out.println("Added " + userName + " to the user queue");
            System.out.println("---------------------------------------");
        }
    }
    void user(){
        System.out.println("What do you wanna Today?\n1)Borrow\n2)Return\n3)View Collection\n4)Search book\n5)View Cart\n6)Leave");
        String input=scan.nextLine();
        if(input.equalsIgnoreCase("Borrow")|input.equalsIgnoreCase("1")){
            borrow();user();
        }else if(input.equalsIgnoreCase("Return")|input.equalsIgnoreCase("2")){
            Return();user();
        }else if(input.equalsIgnoreCase("View Collection")|input.equalsIgnoreCase("3")){
            displayBooks();user();
        }else if(input.equalsIgnoreCase("Search book")|input.equalsIgnoreCase("4")){
            searchBook();user();
        }else if(input.equalsIgnoreCase("View Cart")|input.equalsIgnoreCase("5")){
            cart();user();
        }else return;
    }
    void addBook(String name,int code,String author,String type) {  
        Node newNode = new Node(name,code,author,type);    
        if(head == null) {  
            head = tail = newNode;  
        }  
        else {   
            tail.next = newNode; 
            tail = newNode;  
        }  
    }  

    void Return(){
        Scanner scan  = new Scanner(System.in);
        Node temp=head;
        System.out.println("What is the code of the book you want to return?");
        int code1 = scan.nextInt();
        while(temp.code!=code1){
            temp=temp.next;
        }
        System.out.println("Book name: "+temp.name);
        System.out.println("Code : "+temp.code);
        System.out.println("Author name: "+temp.author);
        System.out.println("Subject: "+temp.type);
        System.out.println("Do you want to return this book?");
        String ans=scan.next();
        temp.available=1;
        System.out.println("Your Book has now been returned!.Thank you!");
        System.out.println("-----------------------------------------");
    }
    void displayBooks() {    
        Node temp= head; 
        System.out.println("Books available: "); 
        System.out.println("------------------------------------------------------");
        while(temp != null) {  
            System.out.println("BookName:"+temp.name);
            System.out.println("Author:"+temp.author);  
            System.out.println("Book Code:"+temp.code);
            System.out.println("Subject:"+temp.type);
            System.out.println("------------------------------------------------------");
            temp = temp.next;  
        }  
    }    
    void searchBook() {
        System.out.println("Which type of search would you prefer?\n1)Author\n2)Title\n3)Code\n4)Subject\n");
        String Look=scan.nextLine();
        if(Look.equalsIgnoreCase("Author")|Look.equalsIgnoreCase("1")){
            Node temp=head;
            System.out.println("Which Author do do you want a book from?\n");
            int bookcount=0;
            String input=scan.nextLine();
            while(temp!=null){
                if(temp.author.equalsIgnoreCase(input)){
                    System.out.println("------------------------------------------------------");
                                System.out.println("BookName:"+temp.name);
                                System.out.println("Author:"+temp.author);  
                                System.out.println("Book Code:"+temp.code);
                                System.out.println("Subject:"+temp.type);
                                System.out.println("------------------------------------------------------");
                                bookcount++;
                                System.out.println("Would you like to add to cart");
                                String ans1=scan.nextLine();
                                if(ans1.equals("yes")&&temp.available==1){
                                    enQueue1(temp.name);
                                    System.out.println("Book successfully added to cart");
                                    System.out.println("------------------------------------------------------");
                                }
                }
                temp=temp.next;
            }
            if(bookcount==0){
                System.out.println("Sorry!We dont have any of their book!");
                return;
            }
            System.out.println("GO TO \"View Cart\" to borrow the book!");
            System.out.println("------------------------------------------------------");
        }
        else if(Look.equalsIgnoreCase("Title")|Look.equalsIgnoreCase("2")){
            Node temp=head;
            System.out.println("Which Book do you want?");
            String input=scan.nextLine();
            int bookcount=0;
            while(temp!=null){
                if(temp.name.equalsIgnoreCase(input)){
                                System.out.println("BookName:"+temp.name);
                                System.out.println("Author:"+temp.author);  
                                System.out.println("Book Code:"+temp.code);
                                System.out.println("Subject:"+temp.type);
                                System.out.println("------------------------------------------------------");
                                bookcount++;
                                System.out.println("Would you like to add to cart");
                                String ans1=scan.nextLine();
                                if(ans1.equals("yes")&&temp.available==1){
                                    enQueue1(temp.name);
                                    System.out.println("Book successfully added to cart");
                                    System.out.println("------------------------------------------------------");
                                }
                }
                temp=temp.next;
            }
            if(bookcount==0){
                System.out.println("Sorry!We dont have any book with this title!");
                return;
            }
            System.out.println("GO TO \"View Cart\" to borrow the book!");
        }
        else if(Look.equalsIgnoreCase("Code")|Look.equalsIgnoreCase("3")){
            Node temp=head;
            System.out.println("Type the code of the book you want.");
            int input=scan.nextInt();
            int bookcount=0;
            while(temp.code!=input){temp=temp.next;}
                            System.out.println("BookName:"+temp.name);
                            System.out.println("Author:"+temp.author);  
                            System.out.println("Book Code:"+temp.code);
                            System.out.println("Subject:"+temp.type);
                            System.out.println("------------------------------------------------------");
                            bookcount++;
                            System.out.println("Would you like to add to cart");
                                String ans1=scan.nextLine();
                                if(ans1.equals("yes")&&temp.available==1){
                                    enQueue1(temp.name);
                                    System.out.println("Book successfully added to cart");
                                }
                            if(bookcount==0){
                System.out.println("Sorry!We dont have any of book this code!");
                return;
            }
            System.out.println("GO TO \"View Cart\" to borrow the book!");
        }else if(Look.equalsIgnoreCase("Subject")|Look.equalsIgnoreCase("4")){
            Node temp=head;
            int bookcount=0;
            System.out.println("Type the subject of the book you want.");
            String input=scan.nextLine();
            while(temp!=null){
                if(temp.type.equalsIgnoreCase(input)){
                                System.out.println("BookName:"+temp.name);
                                System.out.println("Author:"+temp.author);  
                                System.out.println("Book Code:"+temp.code);
                                System.out.println("Subject:"+temp.type);
                                System.out.println("------------------------------------------------------");
                                bookcount++;
                                System.out.println("Would you like to add to cart");
                                String ans1=scan.nextLine();
                                if(ans1.equals("yes")&&temp.available==1){
                                    enQueue1(temp.name);
                                    System.out.println("Book successfully added to cart");
                                    System.out.println("------------------------------------------------------");
                                }
                }
                temp=temp.next;
            }
            if(bookcount==0){
                System.out.println("Sorry!We dont have any book in this subject!");
                return;
            }
            System.out.println("GO TO \"View Cart\" to borrow the books!");
        }
    } 

    void cart(){
        Node temp=head;
        System.out.println("Following are the books in your cart!");
        System.out.println("------------------------------------------------------");  
            for(int i=0;i<=r;i++){ 
                System.out.println("BookName:"+cartQueue[i]);
                System.out.println("------------------------------------------------------");  
            }  
       System.out.println("Do you want to borrow all books in cart?");
       String res=scan.nextLine();
       if(res.equalsIgnoreCase("yes")){
       for(int i=0;i<=r;i++){
         while(temp.name!=cartQueue[i]){
            temp=temp.next;
        }
        temp.available=0;
       }
    }
    System.out.println("All The books in your cart have been borrowed.Come Again!");
}

    void borrow(){
        Node temp=head;
        System.out.println("Type the code of the book you want to borrow");
        int input=scan.nextInt();
        while(temp.code!=input){
            temp=temp.next;         
        }
        if(temp.available==0){
            scan.nextLine();
            System.out.println("The book is already borrowed.You will now be added in a queue");
            System.out.println("Please enter your registration number");
            String reg=scan.nextLine();
            enQueue(reg);
            System.out.println("You have been added to the queue and You are in the "+(rear+1)+"th position");
            System.out.println("---------------------------------------");
            return;
        }
        System.out.println("The book "+temp.name+" has now been borrowed");
        System.out.println("---------------------------------------");
        temp.available=0;
        scan.nextLine();
    }

    boolean isEmpty() {
        return front==-1;
    }

    boolean isFull(){
        if(front==0 && rear==size-1)
        return true;
        else
         return false;
                }

   void enQueue(String name) {
        if (isFull()) {
            System.out.println("Queue is full");
        } 
        if (front == -1) {
            front = 0;
        }
            rear++;
            userQueue[rear] = name;
    }

     void enQueue1(String name) {
        if (isFull()) {
            System.out.println("Cart is full");
        }if (f == -1) {
            f++;
        }
            r++;
            cartQueue[r] = name;
            System.out.println("Book added to cart: " + name);
            System.out.println("-------------------------------------");
    }

    public static void main(String args[]){
        Library a=new Library();
        a.addBook("Cengage Part-1",101,"Tiwari","math");
        a.addBook("Cengage Part-2",102,"Tiwari","math");
        a.addBook("Cengage Part-3",103,"Tiwari","math");
        a.addBook("Cengage Part-4",104,"Tiwari","math");
        a.addBook("Data Structures & Algorithms",111,"Alfred V Aho","DSA");
        a.addBook("Data Structures & Algorithms",112,"Maria Rukadikar S","DSA");
        a.addBook("Data Structures and Algorithms in Java",113,"Michael T. Goodrich","DSA");
        a.addBook("Concept of Physics",121,"HC Varma","Physics");
        a.addBook("Cengage Physics",122,"Amit","Physics");
        a.addBook("Cengage T",123,"Sharma","Physics");
        a.addBook("Arihant",124,"DC Pandey","Physics");
        System.out.println("+ --------------------------------------------------- +");
        System.out.println("|                                                     |");
        System.out.println("|             LIBRARY MANAGEMENT SYSTEM               |");
        System.out.println("|                                                     |");
        System.out.println("+ --------------------------------------------------- +");
        a.user();
    } 
        
  
}