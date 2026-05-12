# CMP 269: Programming Methods III
# In-Class Assignment: Stock Data Analysis
import pandas as pd
import numpy as np

"""
INSTRUCTIONS:
You are an analyst at Lehman Capital. You have been handed a messy dataset 
containing a week of trading data. 
Complete the 4 tasks below to clean and analyze the stock.
"""

def get_messy_market_data():
    """Helper function providing the raw data for today's lab."""
    return pd.DataFrame({
        "Date": ["Mon", "Tue", "Wed", "Thu", "Fri"],
        "Open": [200.0, 202.5, np.nan, 201.0, 205.0],
        "Close": [203.0, np.nan, 199.0, 204.5, 208.0],
        "Volume": [1500000, 1800000, 1200000, np.nan, 2100000]
    })


def task_1_data_cleaning():
    """
    TASK 1: Clean the Data
    1. Load the messy data using get_messy_market_data().
    2. Print the number of missing values in each column.
    3. The 'Volume' column is missing Thursday's data. Fill it with a 0.
    4. For the 'Open' and 'Close' columns, drop any row that still has missing data.
    5. Return the cleaned DataFrame.
    """
    print("--- Task 1: Data Cleaning ---")
    df = get_messy_market_data()

    # Check missing values
    print("Missing values per column:")
    print(df.isnull().sum())

    # Fill Volume NaNs with 0
    df['Volume'] = df['Volume'].fillna(0)

    # Drop remaining NaNs
    df = df.dropna()

    # Print and return the cleaned df
    print("\nCleaned DataFrame:")
    print(df)

    return df


def task_2_volatility_filtering(clean_df):
    """
    TASK 2: Filter for Volatility
    1. Accept the clean_df from Task 1.
    2. Create a new column called 'Price_Swing' representing the
       difference between 'Close' and 'Open' (Close - Open).
    3. Filter the DataFrame to only show days where the Price_Swing
       was greater than $2.00 OR less than -$2.00.
    """
    print("\n--- Task 2: Volatility Filtering ---")

    # Calculate Price_Swing
    clean_df['Price_Swing'] = clean_df['Close'] - clean_df['Open']

    # Apply the multi-condition filter (> $2.00 OR < -$2.00)
    volatility_df = clean_df[(clean_df['Price_Swing'] > 2.0) | (clean_df['Price_Swing'] < -2.0)]

    print("Days with Price_Swing > $2.00 OR < -$2.00:")
    print(volatility_df)


def task_3_financial_summary(clean_df):
    """
    TASK 3: Basic Statistics
    1. Accept the clean_df from Task 1.
    2. Use the describe() method on the 'Close' column and print the result.
    3. Manually calculate and print the max 'Volume' for the week.
    """
    print("\n--- Task 3: Financial Summary ---")

    # Print describe() for Close
    print("Close column statistics:")
    print(clean_df['Close'].describe())

    # Print max Volume
    max_volume = clean_df['Volume'].max()
    print(f"\nMax Volume for the week: {max_volume}")


def task_4_algorithmic_metrics(clean_df):
    """
    TASK 4: Moving Averages & Returns
    1. Accept the clean_df from Task 1.
    2. Add a column 'Daily_Return' using pct_change() on the 'Close' column.
    3. Add a column '2_Day_MA' calculating the 2-day rolling mean of the 'Close' column.
    4. Print the final DataFrame.
    """
    print("\n--- Task 4: Algorithmic Metrics ---")

    # Add Daily_Return column
    clean_df['Daily_Return'] = clean_df['Close'].pct_change()

    # Add 2_Day_MA column
    clean_df['2_Day_MA'] = clean_df['Close'].rolling(window=2).mean()

    # Print final DataFrame
    print("Final DataFrame with Daily_Return and 2_Day_MA:")
    print(clean_df)


if __name__ == "__main__":
    # Uncomment these as you progress
    clean_df = task_1_data_cleaning()
    if clean_df is not None:
        task_2_volatility_filtering(clean_df.copy())
        task_3_financial_summary(clean_df.copy())
        task_4_algorithmic_metrics(clean_df.copy())