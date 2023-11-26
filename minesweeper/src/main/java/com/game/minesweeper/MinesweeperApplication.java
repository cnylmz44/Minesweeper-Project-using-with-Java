package com.game.minesweeper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MinesweeperApplication {
	public static void main(String[] args) {
		System.setProperty("java.awt.headless", "false"); //Disables headless
		MinesweeperLevelMenu minesweeperLevelMenu = new MinesweeperLevelMenu();
		SpringApplication.run(MinesweeperApplication.class, args);
	}
}
