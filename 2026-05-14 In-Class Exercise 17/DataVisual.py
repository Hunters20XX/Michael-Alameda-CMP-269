# CMP 269: Programming Methods III

# In-Class Assignment: Data Visualization and Pytest

import pandas as pd

import matplotlib.pyplot as plt

import seaborn as sns

"""

INSTRUCTIONS:

Part A: Complete the visualization tasks to analyze a mock financial dataset.

Part B: Write testable logic and Pytest assertions to verify your financial math.

"""

# ==========================================

# PART A: VISUALIZATION

# ==========================================

def get_crypto_data():

    """Helper function to load mock crypto data."""

    return pd.DataFrame({

        "Day": [1, 2, 3, 4, 5, 6, 7],

        "Bitcoin": [40000, 42000, 41000, 45000, 44000, 46000, 48000],

        "Ethereum": [2500, 2600, 2550, 2800, 2750, 2900, 3100]

    })

def task_1_trend_line():
    """
    TASK 1: Matplotlib Line Chart
    1. Load the data using get_crypto_data().
    2. Use plt.plot() to chart Bitcoin prices over the 7 days.
    3. Add a title, x-axis label, and y-axis label.
    4. Call plt.show() to render it.
    """
    print("--- Task 1: Building a Trend Line ---")

    df = get_crypto_data()

    # Plot Bitcoin prices over the 7 days
    plt.plot(df["Day"], df["Bitcoin"], marker='o', linewidth=2, color='orange')

    # Add title and axis labels
    plt.title("Bitcoin Price Trend Over 7 Days", fontsize=14, fontweight='bold')
    plt.xlabel("Day", fontsize=12)
    plt.ylabel("Price (USD)", fontsize=12)

    # Add grid for readability
    plt.grid(True, linestyle='--', alpha=0.7)

    plt.tight_layout()
    plt.show()


def task_2_seaborn_comparison():
    """
    TASK 2: Seaborn Bar Chart
    1. Create a simple DataFrame mapping 3 portfolios to their Total Value.
       (e.g., 'Portfolio A': 10000, 'Portfolio B': 15000, 'Portfolio C': 8000)
    2. Use sns.barplot() to display the comparison.
    3. Call plt.show() to render it.
    """
    print("--- Task 2: Seaborn Comparison ---")

    # Create a DataFrame for portfolio values
    portfolio_df = pd.DataFrame({
        "Portfolio": ["Portfolio A", "Portfolio B", "Portfolio C"],
        "Total Value": [10000, 15000, 8000]
    })

    # Use seaborn barplot to display comparison
    plt.figure(figsize=(8, 6))
    sns.barplot(x="Portfolio", y="Total Value", data=portfolio_df, palette="Blues_d")

    # Add title and labels
    plt.title("Portfolio Value Comparison", fontsize=14, fontweight='bold')
    plt.xlabel("Portfolio", fontsize=12)
    plt.ylabel("Total Value (USD)", fontsize=12)

    # Add value labels on bars
    for i, val in enumerate(portfolio_df["Total Value"]):
        plt.text(i, val + 200, f"${val:,}", ha='center', fontsize=11, fontweight='bold')

    plt.tight_layout()
    plt.show()


# ==========================================

# PART B: TESTABLE LOGIC & PYTEST

# ==========================================

def calculate_portfolio_return(initial_value, final_value):
    """Calculate percentage return on investment."""
    if initial_value == 0:
        raise ValueError("Initial value cannot be zero")
    return ((final_value - initial_value) / initial_value) * 100


def calculate_profit_loss(buy_price, sell_price, quantity):
    """Calculate profit or loss from a trade."""
    return (sell_price - buy_price) * quantity


def calculate_compound_interest(principal, rate, time, n=1):
    """
    Calculate compound interest.
    principal: initial investment
    rate: annual interest rate (as decimal, e.g., 0.05 for 5%)
    time: number of years
    n: number of times interest is compounded per year
    """
    return principal * (1 + rate / n) ** (n * time)


def calculate_portfolio_allocation(total_value, allocations):
    """
    Calculate portfolio allocation percentages.
    allocations: dict of {asset_name: value}
    """
    if total_value <= 0:
        raise ValueError("Total value must be positive")

    allocations_dict = {}
    for asset, value in allocations.items():
        percentage = (value / total_value) * 100
        allocations_dict[asset] = round(percentage, 2)

    return allocations_dict


# ==========================================

# PYTEST ASSERTIONS (run with: pytest filename.py -v)

# ==========================================

import pytest


class TestFinancialCalculations:

    def test_calculate_portfolio_return_positive(self):
        """Test positive return calculation."""
        result = calculate_portfolio_return(10000, 12000)
        assert result == 20.0

    def test_calculate_portfolio_return_negative(self):
        """Test negative return calculation."""
        result = calculate_portfolio_return(10000, 8000)
        assert result == -20.0

    def test_calculate_portfolio_return_zero(self):
        """Test zero return calculation."""
        result = calculate_portfolio_return(10000, 10000)
        assert result == 0.0

    def test_calculate_portfolio_return_zero_initial(self):
        """Test error when initial value is zero."""
        with pytest.raises(ValueError, match="Initial value cannot be zero"):
            calculate_portfolio_return(0, 10000)

    def test_calculate_profit_loss_profit(self):
        """Test profit calculation."""
        result = calculate_profit_loss(50, 75, 10)
        assert result == 250

    def test_calculate_profit_loss_loss(self):
        """Test loss calculation."""
        result = calculate_profit_loss(75, 50, 10)
        assert result == -250

    def test_calculate_profit_loss_break_even(self):
        """Test break-even calculation."""
        result = calculate_profit_loss(50, 50, 10)
        assert result == 0

    def test_calculate_compound_interest_annual(self):
        """Test compound interest with annual compounding."""
        result = calculate_compound_interest(1000, 0.05, 2, n=1)
        assert round(result, 2) == 1102.50

    def test_calculate_compound_interest_monthly(self):
        """Test compound interest with monthly compounding."""
        result = calculate_compound_interest(1000, 0.05, 1, n=12)
        assert round(result, 2) == 1051.16

    def test_calculate_compound_interest_zero_rate(self):
        """Test compound interest with zero rate."""
        result = calculate_compound_interest(1000, 0, 5, n=1)
        assert result == 1000

    def test_calculate_portfolio_allocation(self):
        """Test portfolio allocation percentages."""
        result = calculate_portfolio_allocation(10000, {"Stocks": 6000, "Bonds": 4000})
        assert result == {"Stocks": 60.0, "Bonds": 40.0}

    def test_calculate_portfolio_allocation_single_asset(self):
        """Test allocation with single asset (100%)."""
        result = calculate_portfolio_allocation(5000, {"Crypto": 5000})
        assert result == {"Crypto": 100.0}

    def test_calculate_portfolio_allocation_invalid_total(self):
        """Test error with zero or negative total value."""
        with pytest.raises(ValueError, match="Total value must be positive"):
            calculate_portfolio_allocation(0, {"Stocks": 1000})
        with pytest.raises(ValueError, match="Total value must be positive"):
            calculate_portfolio_allocation(-100, {"Stocks": 1000})


if __name__ == "__main__":
    # Uncomment to test visuals during development
    # task_1_trend_line()
    # task_2_seaborn_comparison()

    # Run pytest if executed directly
    import sys
    if len(sys.argv) > 1 and sys.argv[1] == "--test":
        pytest.main([__file__, "-v"])
    else:
        print("=" * 50)
        print("CMP 269: Data Visualization & Pytest")
        print("=" * 50)
        print("\nTo run visualizations:")
        print("  Uncomment task calls in main block")
        print("\nTo run tests:")
        print("  pytest filename.py -v")
        print("  OR: python filename.py --test")
        print("=" * 50)