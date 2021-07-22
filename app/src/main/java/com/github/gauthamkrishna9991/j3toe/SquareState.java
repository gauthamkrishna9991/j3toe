package com.github.gauthamkrishna9991.j3toe;

public enum SquareState {
    NONE, X, O;
    @Override
    public String toString() {
        if (this == SquareState.NONE) {
            return " ";
        } else if (this == SquareState.X) {
            return "X";
        }
        return "O";
    }
}
