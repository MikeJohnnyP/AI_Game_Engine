package com.game.animation;

public class State {
	public static final int IDLE = 1 << 0;
	public static final int JUMP_START = 1 << 1;
	public static final int JUMP_END = 1 << 2;
	public static final int DEAD = 1 << 3;
	public static final int RUN = 1 << 4;
	public static final int ATTACK = 1 << 5;
}