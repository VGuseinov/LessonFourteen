package com.company;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Game {
    private static final char x = 'x';
    private static final char o = 'o';


    public void start() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите размер дроски: ");
        int boardSize = sc.nextInt();
        Board board = new Board(boardSize);
        while (true) {
            board.draw();
            Position userPos = this.userInput(sc);
            try {
                board.placeSymbol(userPos.row, userPos.col, x);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                continue;
            }
            char res = board.checkWin();
            if (res == '*') {
                System.out.println("Ничья");
                break;
            }
            if (res == x) {
                System.out.println("Скайнет повержен");
                break;
            }

            Position botPos = this.botInput(board);
            try {
                board.placeSymbol(botPos.row, botPos.col, o);
            } catch (Exception e) {
                e.printStackTrace();
            }
            res = board.checkWin();
            if (res == '*') {
                System.out.println("Ничья");
                break;
            }
            if (res == o) {
                System.out.println("Человечесто уничтожено");
                break;
            }
        }
        board.draw();
    }

    private Position userInput(Scanner sc) {
        Position pos = new Position();
        System.out.println("Введите номер строки: ");
        pos.row = sc.nextInt() - 1;
        System.out.println("Введите номер столбца: ");
        pos.col = sc.nextInt() - 1;
        return pos;
    }

    private Position botInput(Board b) {
        ArrayList<Position> emptyCells = b.emptyCells();
        Random rand = new Random();
        int pos = rand.nextInt(emptyCells.size());
        return emptyCells.get(pos);
    }
}