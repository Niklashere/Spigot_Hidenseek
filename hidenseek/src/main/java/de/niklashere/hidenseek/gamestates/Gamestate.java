package de.niklashere.hidenseek.gamestates;

public enum Gamestate {
	Lobby, Full, WarmUp, Ingame, End;

	private static Gamestate state;

	public static void setState(Gamestate state) {
		Gamestate.state = state;
	}

	public static boolean isState(Gamestate state) {
		if (Gamestate.state == state) {
			return true;
		}
		return false;
	}

	public static Gamestate getState() {
		return state;
	}
}
