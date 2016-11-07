package com.company;

import com.sun.javafx.scene.control.skin.VirtualFlow;

import java.util.ArrayList;
import java.util.Random;
import java.util.stream.Collectors;

public class Main {
    static final int SIZE = 10;

    static Room[][] createRooms() {
        Room[][] rooms = new Room[SIZE][SIZE];
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                rooms[row][col] = new Room(row,col);
            }
        }
        return rooms;
    }

    static ArrayList<Room> possibleNeighbors(Room[][] rooms, int row, int col) {
        ArrayList<Room> neighbors = new ArrayList<>();
        if (row < SIZE - 1) {
            neighbors.add(rooms[row+1][col]);
        }
        if (row > 0) {
            neighbors.add(rooms[row-1][col]);
        }
        if (col < SIZE - 1) {
            neighbors.add(rooms[row][col+1]);
        }
        if (col > 0) {
            neighbors.add(rooms[row][col-1]);
        }
        neighbors = neighbors.stream()
                .filter(room -> !room.wasVisited)
                .collect(Collectors.toCollection(ArrayList::new));
        return neighbors;
    }

    static Room randomNeighbor(Room[][] rooms,int row,int col) {
        ArrayList<Room> neighbors = possibleNeighbors(rooms,row,col);
        if (neighbors.size() == 0) {
            return null;
        }
        Random r = new Random();
        int index = r.nextInt(neighbors.size());
        return neighbors.get(index);
    }

    public static void main(String[] args) {
        Room[][] rooms = createRooms();
        for (Room[] row : rooms) {
            System.out.print(" _");
        }
        System.out.println();
        for (Room[] row : rooms) {
            System.out.print("|");
            for (Room room : row) {
                System.out.print("_|");
            }
            System.out.println();
        }
    }
}
