# CMP 269: Programming Methods III
#
# Python Exercise 1: Lehman Campus Payment System
#
# The Scenario: "Lehman Campus Payment System"
# The college needs a new system to handle different types of payments (Credit Cards,
# Meal Plans, and Financial Aid). You are tasked with building the class hierarchy.

class PaymentMethod:
    """
    Goal: Practice class definition, __init__, and the self keyword.
    """
    def __init__(self, account_holder, balance):
        self.account_holder = account_holder
        self.balance = balance
        # Using a single underscore denotes a "protected" or internal variable
        self._transaction_count = 0

    def process_payment(self, amount):
        return f"Processing payment of ${amount} for {self.account_holder}"

    def get_payment_status(self):
        return f"{self.account_holder}: ${self.balance}"


class CreditCard(PaymentMethod):
    """
    Goal: Practice Inheritance and super().
    """
    def __init__(self, account_holder, balance, credit_limit):
        # Call the parent class constructor
        super().__init__(account_holder, balance)
        self.credit_limit = credit_limit

    # Override parent method
    def get_payment_status(self):
        base_status = super().get_payment_status()
        return f"{base_status} (Credit Limit: ${self.credit_limit})"


class MealPlan(PaymentMethod):
    """
    Goal: Practice Inheritance and super() - Meal Plan specific logic.
    """
    def __init__(self, account_holder, balance):
        super().__init__(account_holder, balance)

    def get_payment_status(self):
        return f"Meal Plan - {super().get_payment_status()}"


class FinancialAid(PaymentMethod):
    """
    Goal: Practice Inheritance and super() - Financial Aid specific logic.
    """
    def __init__(self, account_holder, aid_amount):
        super().__init__(account_holder, aid_amount)

    def get_payment_status(self):
        return f"Financial Aid - {super().get_payment_status()}"


class SimpleLogger:
    """
    Unrelated class to demonstrate Duck Typing.
    """
    def get_payment_status(self):
        return "LOGGER: Logging payment system activity..."


def exercise_3_polymorphism():
    """
    Goal: Demonstrate Duck Typing.
    Notice how this function doesn't care about the object's class,
    only that it has a 'get_payment_status()' method!
    """
    print("\n--- Exercise 3: Polymorphism (Duck Typing) ---")

    cc = CreditCard("John Lehman", 10.0, 500.0)
    meal = MealPlan("Jane Student", 75.0)
    aid = FinancialAid("Bob Smith", 1000.0)
    logger = SimpleLogger()  # Completely unrelated!

    # We can put completely unrelated objects in a list
    systems = [cc, meal, aid, logger]

    for system in systems:
        # As long as it "quacks" (has get_payment_status()), Python executes it!
        print(system.get_payment_status())


if __name__ == "__main__":
    print("--- Exercise 1 & 2: Classes and Inheritance ---")

    student1 = PaymentMethod("John Doe", 100.0)
    student2 = CreditCard("Jane Smith", 50.0, 1000.0)
    student3 = MealPlan("Bob Brown", 75.0)

    print(student1.get_payment_status())
    print(student2.get_payment_status())
    print(student3.get_payment_status())

    exercise_3_polymorphism()