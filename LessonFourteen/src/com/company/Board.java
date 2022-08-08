package com.company;

import java.util.ArrayList;

public class Board {
    private int boardSize;
    private static final char empty = '.';
    private char[][] board;
    private int usedCellsCount;

    public Board(int size) {
        this.boardSize = size;
        this.board = new char[boardSize][boardSize];
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                this.board[i][j] = empty;
            }
        }
    }

    public void placeSymbol(int row, int col, char sym) throws PositionException, AlreadyUsedException {
        if (row < 0 || col < 0 || row >= boardSize || col >= boardSize) {
            throw new PositionException();
        }

        if (this.board[row][col] != empty) {
            throw new AlreadyUsedException();
        }

        this.board[row][col] = sym;
        this.usedCellsCount++;
    }

    public void draw() {
        System.out.print(" ");
        for (int i = 0; i < boardSize; i++){
            System.out.print(" " + (i + 1) + " ");
        }
        System.out.println();
        for (int i = 0; i < boardSize; i++) {
            System.out.print(i + 1);
            for (int j = 0; j < boardSize; j++) {
                System.out.print(" " + this.board[i][j] + " ");
            }
            System.out.println();
        }
    }

    // '-' - игра продолжается
    // 'x'/'o' - выйграл человек/выйграл скайнет
    // '*' - ничья
    public char checkWin() {
        // проверека по горизонтали
        for (int i = 0; i < boardSize; i++) {
            if (this.checkCells(this.board[i])) {
                return this.board[i][0];
            }
        }

        // проверка по вертикали
        for (int i = 0; i < boardSize; i++) {
            char[] cells = new char[boardSize];
            for (int j = 0; j < boardSize; j++) {
                cells[j] = this.board[j][i];
            }
            if (this.checkCells(cells)) {
                return cells[0];
            }
        }

        // проверка первой диагонали
        char[] cells = new char[boardSize];
        for (int i = 0; i < boardSize; i++) {
            cells[i] = this.board[i][i];
        }
        if (this.checkCells(cells)) {
            return cells[0];
        }

        // проверка вторую диагональ
        for (int i = 0; i < boardSize; i++) {
            cells[i] = this.board[i][boardSize - i - 1];
        }
        if (this.checkCells(cells)) {
            return cells[0];
        }

        // проверка на ничью
        if (this.usedCellsCount == boardSize * boardSize) {
            return '*';
        }
        return '-';
    }


    private boolean checkCells(char[] cells) {
        for (int i = 0; i < cells.length - 1; i++) {
            if (cells[i] == empty || cells[i] != cells[i + 1]) {
                return false;
            }
        }
        return true;
    }

    public ArrayList<Position> emptyCells() {
        ArrayList<Position> list = new ArrayList<>();
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                if (this.board[i][j] == empty) {
                    Position p = new Position();
                    p.row = i;
                    p.col = j;
                    list.add(p);
                }
            }
        }
        return list;
    }
}