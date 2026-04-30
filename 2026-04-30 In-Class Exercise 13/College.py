class LehmanCourse:
    """A basic course class to demonstrate class definition and self keyword."""

    def __init__(self, course_name, credits):
        self.course_name = course_name
        self.credits = credits
        self._student_count = 0  # "Protected" variable (by convention)

    def enroll_student(self):
        """Increment the student count by 1."""
        self._student_count += 1

    def display_info(self):
        """Display course details and current enrollment."""
        print(f"Course: {self.course_name} | Credits: {self.credits} | Enrolled: {self._student_count}")


# Test Exercise 1
my_course = LehmanCourse("CMP 269", 4)
my_course.display_info()       # Enrolled: 0
my_course.enroll_student()
my_course.enroll_student()
my_course.display_info()       # Enrolled: 2

class LabCourse(LehmanCourse):
    """A specialized course that includes a lab fee."""

    def __init__(self, course_name, credits, lab_fee):
        # Call parent class __init__ to initialize inherited attributes
        super().__init__(course_name, credits)
        self.lab_fee = lab_fee

    def display_info(self):
        """Override parent method to include lab fee."""
        print(f"Course: {self.course_name} | Credits: {self.credits} | Fee: ${self.lab_fee} | Enrolled: {self._student_count}")


# Test Exercise 2
lab_course = LabCourse("CMP 269", 4, 150)
lab_course.display_info()
lab_course.enroll_student()
lab_course.display_info()

class Professor:
    """A professor class with a get_role method."""

    def get_role(self):
        return "Teaching and Research"


class Student:
    """A student class with a get_role method."""

    def get_role(self):
        return "Learning and Coding"


def print_role(person):
    """Standalone function that works with any object having get_role()."""
    # Duck typing: we don't care what type person is, only that it has get_role()
    print(person.get_role())


# Test Exercise 3 - Demonstrate polymorphism via duck typing
prof = Professor()
stud = Student()

print_role(prof)  # Output: Teaching and Research
print_role(stud) # Output: Learning and Coding