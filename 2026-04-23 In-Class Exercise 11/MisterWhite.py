# CMP 269: Programming Methods III
# Python Introduction Exercises - Mister White

def exercise_1_basics():
    """
    Goal: Practice basic syntax and string formatting.
    Task: Create variables for a course name and a number of students.
    Print a sentence using an f-string.
    """
    # Solution
    course = "Python Programming"
    students = 30
    print(f"The course {course} has {students} students.")


def exercise_2_collections():
    """
    Goal: Manipulate lists and dictionaries.
    Task:
    1. Create a list of 5 colors.
    2. Add a 6th color to the end.
    3. Create a dictionary with keys 'name' and 'gpa'.
    """
    # Solution
    # 1. Create a list of 5 colors
    colors = ["red", "blue", "green", "yellow", "purple"]

    # 2. Add a 6th color to the end
    colors.append("orange")
    print(f"Colors list: {colors}")

    # 3. Create a dictionary with keys 'name' and 'gpa'
    student = {'name': 'Alice', 'gpa': 3.8}
    print(f"Student dict: {student}")


def exercise_3_logic():
    """
    Goal: Use loops and conditionals.
    Task: Iterate through a list of numbers.
    If a number is even, add it to a new list called 'evens'.
    """
    # Solution
    numbers = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
    evens = []

    for num in numbers:
        if num % 2 == 0:
            evens.append(num)

    print(f"Original numbers: {numbers}")
    print(f"Even numbers: {evens}")


if __name__ == "__main__":
    print("--- Exercise 1 ---")
    exercise_1_basics()
    print("\n--- Exercise 2 ---")
    exercise_2_collections()
    print("\n--- Exercise 3 ---")
    exercise_3_logic()