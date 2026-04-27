
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package project;

import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Tuna-Miray
 */
public class Problem2 {

    public static void main(String[] args) throws IOException {
        File f = new File("names.txt");
        Scanner scanner = new Scanner(f);
        Scanner input = new Scanner(System.in);
        DoubleLinkedList list = new DoubleLinkedList();
        Random random = new Random();

        System.out.print("Enter the number of hubs:");
        int M = input.nextInt();
        System.out.print("Enter a message without space:");
        String mesaj = input.next();

        int counter = 0;
        while (scanner.hasNextLine()) { //saymak
            scanner.nextLine();
            counter++;
        }

        String[] names = new String[counter];
        int i = 0;
        scanner = new Scanner(f);
        while (scanner.hasNextLine()) {  // eklemek
            names[i] = scanner.nextLine();
            i++;
        }

        String[] selectedstartPlayers = new String[30];
        boolean[] kontrol = new boolean[names.length];
        int count = 0;
        while (count < 30) {
            int r = random.nextInt(names.length);
            if (kontrol[r] == false) {
                selectedstartPlayers[count] = names[r];
                kontrol[r] = true;
                count++;
            }
        }

        for (int j = 0; j < 30; j++) {
            list.add(selectedstartPlayers[j]);
        }

        int b = 0;
        while (b < M) {
            Node current = list.first;
            int k = random.nextInt(30) + 1;
            for (int j = 1; j < k; j++) {
                current = current.next;
            }
            if (current.isHub == false) {
                current.isHub = true;
                current.data = current.data + "*";
                b++;
            }
        }

        Node startPlayer = list.first;
        int rand = random.nextInt(30) + 1;
        for (int j = 1; j < rand; j++) {
            startPlayer = startPlayer.next;
        }

        int directionnum = random.nextInt(2);
        String direction = "";
        boolean rightorleft = true;
        if (directionnum == 0) {
            direction = "left";
            rightorleft = false;
        } else {
            direction = "right";
            rightorleft = true;
        }

        startPlayer.Letter(mesaj);
        System.out.println("Randomly generating a direction: " + direction);
        System.out.println("Randomly choosing a student: " + startPlayer.data);

        boolean end = false;
        while (end != true) {
            int k = random.nextInt(5) + 1;
            int rightorleftcounter = 0;
            int sabitsayi = k;
            while (rightorleftcounter != sabitsayi) {
                if (rightorleft == true) {
                    for (int j = 0; j < k; j++) {
                        if (startPlayer.next == null) {
                            k = k - j;
                            rightorleft = !rightorleft;
                            break;
                        } else {
                            startPlayer = startPlayer.next;
                            rightorleftcounter++;
                        }
                    }
                }
                if (rightorleft == false) {
                    for (int j = 0; j < k; j++) {
                        if (startPlayer.prev == null) {
                            k = k - j;
                            rightorleft = !rightorleft;
                            break;
                        } else {
                            startPlayer = startPlayer.prev;
                            rightorleftcounter++;
                        }
                    }

                }

                startPlayer.Letter(mesaj);
            }
            System.out.println("Randomly generating the value of k: " + k);
            if (startPlayer.isHub == true) {
                rightorleft = !rightorleft;
                System.out.println("\nHUB STUDENT: " + startPlayer.data);
                System.out.print("Enter the new message: ");
                String mesaj1 = input.next();
                mesaj = mesaj1;
            }
            System.out.println(list);
            System.out.println("Current Student: " + startPlayer.data);

            startPlayer.isVisited = true;

            Node visited = list.first;
            int totalCount = 0;
            while (visited != null) {
                if (visited.isVisited == true) {
                    totalCount++;
                }
                visited = visited.next;
            }
            if (totalCount == 30) {
                end = true;
            }

        }
        System.out.println("\nTHE OUTPUT");
        System.out.println(list);
    }

    static class Node {

        String data;
        Node next;
        Node prev;
        boolean isHub;
        int commonLetterCounter;
        boolean isVisited;

        Node(String data) {
            this.data = data;
            this.next = null;
            this.prev = null;
            this.isHub = false;
            this.commonLetterCounter = 0;
            this.isVisited = false;
        }

        public int Letter(String mesaj) {
            for (int i = 0; i < data.length(); i++) {
                for (int j = 0; j < mesaj.length(); j++) {
                    if (data.charAt(i) == mesaj.charAt(j)) {
                        commonLetterCounter++;
                    }
                }
            }
            return commonLetterCounter;
        }

    }

    static class DoubleLinkedList {

        Node first;
        Node last;

        public void add(String name) {
            Node newNode = new Node(name);
            if (last == null) {
                last = newNode;
            } else {
                newNode.next = first;
                first.prev = newNode;
            }
            first = newNode;
        }

        @Override
        public String toString() {
            String s = "";
            Node tmp = first;
            while (tmp != null) {
                s += tmp.data + " " + tmp.commonLetterCounter + "<->";
                tmp = tmp.next;
            }
            s += "Null";
            return s;
        }

    }
}
