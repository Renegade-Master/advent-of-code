use std::fs::File;
use std::io::{BufRead, BufReader, stderr};
use std::process;
use std::collections::HashMap;

use log::{debug, error, info, warn};

fn get_cipher_legend() -> HashMap<&'static str, u32> {
    return HashMap::from([
        ("A", 1), ("X", 1), // Rock
        ("B", 2), ("Y", 2), // Paper
        ("C", 3), ("Z", 3), // Scissors
    ]);
}


fn get_round_points(opponent: &str, player: &str) -> (u32, u32) {
    let legend = get_cipher_legend();
    let mut player_win: u32; // Lose/Draw/Win 0/1/2

    if (opponent == "A") {
        debug!("Opponent played Rock");

        if (player == "X") {
            debug!("Player played Rock");
            player_win = 3
        } else if (player == "Y") {
            debug!("Player played Paper");
            player_win = 6
        } else {
            debug!("Player played Scissors");
            player_win = 0
        }

    } else if (opponent == "B") {
        debug!("Opponent played Paper");
        if (player == "X") {
            debug!("Player played Rock");
            player_win = 0
        } else if (player == "Y") {
            debug!("Player played Paper");
            player_win = 3
        } else {
            debug!("Player played Scissors");
            player_win = 6
        }

    } else {
        debug!("Opponent played Scissors");
        if (player == "X") {
            debug!("Player played Rock");
            player_win = 6
        } else if (player == "Y") {
            debug!("Player played Paper");
            player_win = 0
        } else {
            debug!("Player played Scissors");
            player_win = 3
        }
    }

    let player_score = player_win + legend.get(player).unwrap();

    return (0, player_score);
}


fn get_total_score(reader: BufReader<File>) {

    for (index, line) in reader.lines().enumerate() {
        let line = line.unwrap();
        debug!("{}", line);

        let round_hands: Vec<&str> = line.split(' ').collect();

        let round_points = get_round_points(round_hands[0], round_hands[1]);

        let player_points = round_points.1;
    }
}


fn read_file() -> BufReader<File> {
    info!("Reading data file...");

    let file_data = File::open("res/input.txt").unwrap();
    let file_reader = BufReader::new(file_data);

    return file_reader;
}


fn fail_with_error(err: &str) {
    eprintln!("{}", err);
    process::exit(0x0100);
}


fn main() {
    println!("Advent of Code Challenge - Day 2!");
    env_logger::init();

    let data: BufReader<File> = read_file();
    get_total_score(data);
}
