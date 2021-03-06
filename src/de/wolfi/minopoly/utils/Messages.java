package de.wolfi.minopoly.utils;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

public enum Messages {

	COMMUNITY_FIELD_ENTER(
			"$0 hat ein Gemeinschaffts Feld betreten!\n\n\nOh was ist denn das? Es sieht aus als w�rde sich hier ein Minispiel verstecken!"), EVENT_FIELD_ENTER(
					"$0 hat ein Ereigniss Feld betreten!"), JAIL_FIELD_ENTER(
							"$0 stattet dem Gef�ngnis einen Besuch ab."), MINIGAME_SELECTED(
									"$0 hat das Minispiel $1 gefunden."), MINIGAME_DEATH(
											"$0 ist in $1 gestorben."), MINIGAME_GOT_KILLED(
													"$0 wurde in $1 von $2 get�tet!"), MINIGAME_KILL(
															"Du hast $0 in $1 get�tet!"), MINIGAME_WIN(
																	"Das Minispiel $0 wurde von $1 gewonnen!"), MONEY_GAIN(
																			"Du hast $0 " + Messages.Econemy
																					+ " bekommen. ($1)"), MONEY_PAYD(
																							"Du hast $0 "
																									+ Messages.Econemy
																									+ " bezahlt. ($1)"), MONEY_TRANSFER_GAIN(
																											"Du hast $0 "
																													+ Messages.Econemy
																													+ " von $1 bekommen. ($2)"), MONEY_TRANSFER_SENT(
																															"Du hast $0 "
																																	+ Messages.Econemy
																																	+ " an $1 bezahlt. ($2)"), FIELD_ENTERED(
																																			"$0 betrat $1"),

	OTHER_FIELD_ENTERED("$0 ist auf ein Feld von $1 getreten! �l($2)"), FIELD_BOUGHT(
			"$0 hat das Feld $1 gekauft!"), FIELD_SOLD("$0 hat das Feld $1 verkauft!"),FIELD_PROPERTY_MOVED(
					"$0 hat das Feld $1 $2 �berlassen!"), POLICE_FIELD_ENTER(
					"$0 betrat das Feld der Polizei und wurde daf�r ins Gef�ngnis geschickt."), START_FIELD_BYPASS(
							"$0 ist �ber Los gezogen."), TELEPORT("Du wurdest teleportiert."), MOVE_STARTED(
									"$0 bewegt sich nun $1 Felder."), MOVE_FINISHED(
											"Der Zug von $0 wurde beendet."), PLAYER_ROLLED_THE_DICE(
													"$0 hat eine $1 und eine $2 gew�rfelt!"), COMMAND_WRONG_WORLD(
															"�cDu befindest dich nicht in einer Minopoly Welt!"), COMMAND_NO_ARGUMENTS(
																	"�cEs werden Mindestens(!) $0 Argumente ben�tigt!"), COMMAND_NO_PLAYER(
																			"�c$0 ist kein valider Spieler!"), MONEY_GLOBAL_PAID(
																					"$0 " + Messages.Econemy
																							+ " wurden an $1 �berwiesen. ($2)"), MONEY_GLOBAL_GOT(
																									"$0 " + Messages.Econemy
																											+ " wurden bei $1 abgebucht. ($2)"), MONEY_GLOBAL_TRANSFER(
																													"$0 " + Messages.Econemy
																															+ " wurden von $1 auf das Konto von $2 �berwiesen. ($3)"), FIGURE_SELECTED(
																																	"$0 hat sich Figur $1 ausgesucht. o/"), FIGURE_ALREADY_TAKEN(
																																			"�cDie Figur $0 ist bereits von jemandem Ausgew�hlt!"), TRIPPLE_JAILED(
																																					"$0 hat 3 mal nacheinander einen Pasch gew�rfelt. \nDas riecht verd�chtig nach cheaten. Ab in den Knast!"), JAIL_EXIT(
																																							"$0 hat es geschafft durch einen Pasch das Gef�ngniss zu verlassen."), JAIL_EXIT_FAILED(
																																									"Du hast noch $0 Versuche einen Pasch zu W�rfeln");

	public static String Prefix = "�0[�1Minopoly�0] �a";
	public static final String Econemy = "Katzenbabys";
	private String txt;

	private Messages(String txt) {
		this.txt = txt;
	}

	private String createMessage(Object... target) {
		StringBuilder tmp = new StringBuilder();
		tmp.append(Messages.Prefix);
		boolean rplc = false;
		for (char c : this.txt.toCharArray()) {
			if (c == '$') {
				rplc = true;
				continue;
			}
			if (rplc) {
				tmp.append(target[c - '0']);
				rplc = false;
				continue;
			}
			tmp.append(c);
		}
		return tmp.toString();
	}

	public void broadcast(Object... target) {
		Bukkit.broadcastMessage(createMessage(target));
	}

	public void send(CommandSender player, Object... target) {
		player.sendMessage(createMessage(target));
	}
}
