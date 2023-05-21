package Interface;

import Application.Dictionary;

import java.util.Scanner;

public class CLI {
    public void i()
    {
        Dictionary dictionary = null;
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to the Dictionary program!");
        System.out.println("Please select an option:");
        System.out.println("1. O(N^2) Perfect Hashing");
        System.out.println("2. O(N) Perfect Hashing");
        System.out.println("3. Exit");
        int choice = sc.nextInt();
        System.out.println("Please enter the hash table initial size");
        int size = sc.nextInt();

        switch (choice) {
            case 1 -> dictionary = new Dictionary("Hash_N2",size);
            case 2 -> dictionary = new Dictionary("Hash_N",size);
            case 3 -> System.exit(0);
            default -> {
                System.out.println("Invalid choice!");
                i();
            }
        }
        do{
            System.out.println("Please select an option:");
            System.out.println("1. Insert a word");
            System.out.println("2. Delete a word");
            System.out.println("3. Search a word");
            System.out.println("4. Search multiple words");
            System.out.println("5. Batch insert");
            System.out.println("6. Batch delete");
            System.out.println("7. Return to choose Hashing type");
            System.out.println("8. Rehashing times");
            System.out.println("9. Exit");
            try {
                choice = sc.nextInt();
            }
            catch (Exception e)
            {
                System.out.println("Invalid choice!");
                sc.nextLine();
                continue;
            }
            switch (choice) {
                case 1 -> {
                    System.out.println("Please enter the word:");
                    sc.nextLine();
                    String word = sc.nextLine();
                    assert dictionary != null;
                    if (dictionary.insert_word(word))
                        System.out.println(word + " : " + "Word inserted successfully!");
                    else {
                        if(dictionary.get_no_elements() == size)
                            System.out.println(word + " : " + "Size exceeded!!");
                        else
                            System.out.println(word + " : "+"word is already exist!");
                    }
                }
                case 2 -> {
                    System.out.println("Please enter the word:");
                    sc.nextLine();
                    String word = sc.nextLine();
                    assert dictionary != null;
                    if (dictionary.delete_word(word))
                        System.out.println(word + " : " +"Word deleted successfully!");
                    else
                        System.out.println(word + " : " + "Word does not exist!");

                }
                case 3 -> {
                    System.out.println("Please enter the word:");
                    sc.nextLine();
                    String word = sc.nextLine();
                    assert dictionary != null;
                    if (dictionary.search_word(word))
                        System.out.println(word + " : " +"Word found!");
                    else
                        System.out.println(word + " : " +"Word not found!");
                }
                case 4 -> {
                    System.out.println("Please enter the file name:");
                    String fname = sc.next();
                    assert dictionary != null;
                    try {
                        dictionary.search_multiword(fname);
                    } catch (RuntimeException e) {
                        System.out.println("File not found!");
                    }
                }
                case 5 -> {
                    System.out.println("Please enter the file name:");
                    String fname = sc.next();
                    assert dictionary != null;
                    try {
                        int count = dictionary.Batch_Insert(fname);
                        System.out.println("Number of successfully added words = "+count);
                    } catch (RuntimeException e) {
                        System.out.println("File not found!");
                        System.out.println(e);
                    }
                }
                case 6 -> {
                    System.out.println("Please enter the file name:");
                    String fname = sc.next();
                    assert dictionary != null;
                    try {
                        int count = dictionary.Batch_Delete(fname);
                        System.out.println("Number of successfully added words = "+count);
                    } catch (RuntimeException e) {
                        System.out.println("File not found!");
                    }
                }
                case 7 -> i();
                case 8 -> {
                    assert dictionary != null;
                    System.out.println("Number of re-hashing = "+dictionary.getrebuild());
                }
                case 9 -> System.exit(0);
                default -> {
                    System.out.println("Invalid choice!");
                }
            }
        }while (true);
    }
}
