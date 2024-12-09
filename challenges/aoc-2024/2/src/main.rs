use std::fs;

fn log(message: &str) {
    println!("[{}] {}", chrono::offset::Local::now(), message);
}

fn get_safe_reports(reports: String) {
    let mut safe_reports: i16 = 0;

    for (report_number, report) in reports.split("\n").enumerate() {
        log(&*format!("Report: {}", report));

        let mut previous: i16 = 0;
        let mut is_increasing = false;
        let mut is_decreasing = false;
        let mut has_set_direction = false;
        let mut has_failed = false;

        for (result_number, result) in report.split(" ").enumerate() {
            let local_result = result.parse::<i16>().unwrap();

            if result_number == 0 {
                previous = local_result;
                continue
            }

            if previous == local_result {
                log("Previous was the same as current.");
                has_failed = true;
                break;
            }

            let upper_limit = previous + 3;
            let lower_limit = previous - 3;

            if local_result > upper_limit || local_result < lower_limit {
                log(&*format!("Range is too great. {} is outside {} or {}", local_result, upper_limit, lower_limit));
                has_failed = true;
                break;
            } else {
                //log("Within Range");

                if has_set_direction == false {
                    log("Have not yet determined direction");

                    if local_result > previous {
                        log("Is Increasing");
                        is_increasing = true;
                        has_set_direction = true;
                    } else if local_result < previous {
                        log("Is Decreasing");
                        is_decreasing = true;
                        has_set_direction = true;
                    }
                } else {
                    if is_increasing && local_result < previous {
                        log("No longer increasing!");
                        has_failed = true;
                        break;
                    } else if is_decreasing && local_result > previous {
                        log("No longer decreasing!");
                        has_failed = true;
                        break;
                    }
                }
            }

            previous = local_result;
        }

        log("");

        if !has_failed {
            log(&*format!("Determined this report to be safe: {}\n", report));
            safe_reports += 1;
        } else {
            log(&*format!("Determined this report to be unsafe: {}\n", report))
        }
    }

    log(&*format!("Safe Reports: {}", safe_reports));
}

fn main() {
    let file_contents = fs::read_to_string("input.txt")
        .expect("Should have been able to read the 'input.txt' file");

    log(&*file_contents);

    get_safe_reports(file_contents)
}
