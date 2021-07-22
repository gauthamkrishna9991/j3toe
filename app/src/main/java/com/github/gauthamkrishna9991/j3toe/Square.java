package com.github.gauthamkrishna9991.j3toe;

public class Square {
    private SquareState state;

    public Square() {
        this.state = SquareState.NONE;
    }

    public SquareState getState() {
        return state;
    }

    public boolean updateState(Player player) {
        if (this.state == SquareState.NONE) {
            this.state = player.playerState;
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return this.state.toString();
    }
}
