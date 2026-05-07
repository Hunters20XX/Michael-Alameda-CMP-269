import pandas as pd

"""

INSTRUCTIONS:

Complete the following 4 tasks using the pandas library. 

Run your script frequently to see how the DataFrames look in the console!

"""

def task_1_series_creation():
    """

    TASK 1: Create a Series

    1. Create a dictionary mapping 4 Lehman building names to their floor counts.

       (e.g., "Gillet": 4, "Carman": 3, "Music": 3, "Library": 4)

    2. Convert this dictionary into a Pandas Series.

    3. Print the Series.

    """

    print("--- Task 1: Building Series ---")

    # Create a dictionary mapping building names to floor counts
    buildings = {
        "Gillet": 4,
        "Carman": 3,
        "Music": 3,
        "Library": 4
    }

    # Convert dictionary to a Pandas Series
    building_series = pd.Series(buildings)

    # Print the Series
    print(building_series)


def task_2_dataframe_creation():
    """

    TASK 2: Create a DataFrame

    1. Create a dictionary of lists containing data for at least 3 courses:

       - 'CourseCode': ['CMP168', 'CMP269', 'CMP338']

       - 'Credits': [4, 4, 4]

       - 'Enrolled': [25, 30, 20]

    2. Convert this into a Pandas DataFrame.

    3. Print the DataFrame.

    """

    print("\n--- Task 2: Course DataFrame ---")

    # Create a dictionary of lists with course data
    courses = {
        'CourseCode': ['CMP168', 'CMP269', 'CMP338'],
        'Credits': [4, 4, 4],
        'Enrolled': [25, 30, 20]
    }

    # Convert to Pandas DataFrame
    df = pd.DataFrame(courses)

    # Print the DataFrame
    print(df)


def task_3_data_manipulation():
    """

    TASK 3: Filtering and Math

    1. Using the same data from Task 2, create the DataFrame here again.

    2. Filter the DataFrame to only show courses with more than 20 students enrolled.

    3. Calculate and print the total number of students across ALL courses (use the .sum() method).

    """

    print("\n--- Task 3: Filtering and Math ---")

    # Create the same DataFrame as in Task 2
    courses = {
        'CourseCode': ['CMP168', 'CMP269', 'CMP338'],
        'Credits': [4, 4, 4],
        'Enrolled': [25, 30, 20]
    }
    df = pd.DataFrame(courses)

    # Filter to show only courses with more than 20 students enrolled
    filtered_df = df[df['Enrolled'] > 20]
    print("Courses with more than 20 students enrolled:")
    print(filtered_df)

    # Calculate total students across ALL courses
    total_students = df['Enrolled'].sum()
    print(f"\nTotal students across all courses: {total_students}")


def task_4_csv_integration():
    """

    TASK 4: The Pandas CSV Advantage

    1. Create a simple DataFrame representing stock data (Symbols and Prices).

    2. Use df.to_csv('stocks.csv', index=False) to save it.

    3. Use pd.read_csv('stocks.csv') to read it back into a new variable called df_loaded.

    4. Print df_loaded to prove it worked!

    """

    print("\n--- Task 4: Easy CSV I/O ---")

    # Create a simple DataFrame representing stock data
    stocks = {
        'Symbol': ['AAPL', 'GOOGL', 'MSFT', 'AMZN'],
        'Price': [175.50, 140.25, 380.00, 145.75]
    }
    df = pd.DataFrame(stocks)
    print("Original DataFrame:")
    print(df)

    # Save to CSV (without index)
    df.to_csv('stocks.csv', index=False)
    print("\nSaved to 'stocks.csv'")

    # Read it back into a new variable
    df_loaded = pd.read_csv('stocks.csv')
    print("\nDataFrame loaded from CSV:")
    print(df_loaded)


if __name__ == "__main__":
    # Uncomment these as you work through the assignment
    task_1_series_creation()
    task_2_dataframe_creation()
    task_3_data_manipulation()
    task_4_csv_integration()