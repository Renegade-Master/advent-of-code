from datetime import datetime


def log(message: str, enabled: bool = True):
    if enabled: print(f"[{datetime.now()}] {message}")

def get_similarity(list_1: list, list_2: list) -> int:
    total_similarity = 0

    for i in range(len(list_1)):
        point_a = list_1[i]

        count = list_2.count(point_a)
        similarity = int(point_a) * count

        total_similarity += similarity

    return total_similarity

def get_distance(list_1: list, list_2: list) -> int:
    total_distance = 0

    for i in range(len(list_1)):
        point_a = int(list_1[i])
        point_b = int(list_2[i])

        distance = abs(point_a - point_b)

        log(f"Point A: {point_a}, Point B: {point_b}", False)
        log(f"Distance: {distance}", False)

        total_distance += distance

    return total_distance

def sort_lists(list_1: list, list_2: list) -> (list,list):
    return sorted(list_1), sorted(list_2)

def split_list(content: str) -> (list,list):
    col_1 = []
    col_2 = []

    for a in content.splitlines():
        log(f"Reading: {a}", False)

        col_1.append(a.split('   ')[0])
        col_2.append(a.split('   ')[1])

    return col_1, col_2

def solve():
    content: str

    with open("input.txt") as file_input:
        content = file_input.read()

    log(content, False)

    column_a, column_b = split_list(content)
    log(f"Column A: {column_a}", False)
    log(f"Column B: {column_b}", False)

    column_a, column_b = sort_lists(column_a, column_b)
    log(f"Column A sorted: {column_a}", False)
    log(f"Column B sorted: {column_b}", False)

    log(f"Total distance:   {get_distance(column_a, column_b)}")
    log(f"Total similarity: {get_similarity(column_a, column_b)}")

## Main
if __name__ == '__main__':
    solve()
