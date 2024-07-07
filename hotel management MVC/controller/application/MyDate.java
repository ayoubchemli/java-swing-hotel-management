package controller.application;

import java.util.regex.Pattern;
import java.time.LocalDate; // Import LocalDate class


public class MyDate {
    private int day;
    private int month;
    private int year;

    public MyDate(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return String.format("%02d/%02d/%04d", day, month, year);
    }

    public boolean isLeapYear() {
        if (year % 4 == 0) {
            if (year % 100 == 0) {
                return year % 400 == 0;
            } else {
                return true;
            }
        }
        return false;
    }

    public int daysInMonth() {
        switch (month) {
            case 2:
                return isLeapYear() ? 29 : 28;
            case 4:
            case 6:
            case 9:
            case 11:
                return 30;
            default:
                return 31;
        }
    }

    public boolean isValidDate() {
        if (year < 1 || month < 1 || month > 12 || day < 1 || day > daysInMonth()) {
            return false;
        }
        return true;
    }

    public MyDate addDays(int days) {
        MyDate newDate = new MyDate(day, month, year);
        newDate.incrementDays(days);
        return newDate;
    }
    
    public void incrementDays(int days) {
        day += days;
        while (day > daysInMonth()) {
            day -= daysInMonth();
            month++;
            if (month > 12) {
                month = 1;
                year++;
            }
        }
    }

    public void decrementDays(int days) {
        day -= days;
        while (day < 1) {
            month--;
            if (month < 1) {
                month = 12;
                year--;
            }
            day += daysInMonth();
        }
    }

    public int compareTo(MyDate otherDate) {
        if (this.year != otherDate.year) {
            return Integer.compare(this.year, otherDate.year);
        } else if (this.month != otherDate.month) {
            return Integer.compare(this.month, otherDate.month);
        } else {
            return Integer.compare(this.day, otherDate.day);
        }
    }

    public static MyDate parseDate(String dateString) {
        Pattern pattern = Pattern.compile("^\\d{2}/\\d{2}/\\d{4}$");
        if (!pattern.matcher(dateString).matches()) {
            throw new IllegalArgumentException("Invalid date format. Use DD/MM/YYYY format.");
        }
        String[] parts = dateString.split("/");
        int day = Integer.parseInt(parts[0]);
        int month = Integer.parseInt(parts[1]);
        int year = Integer.parseInt(parts[2]);
        return new MyDate(day, month, year);
    }

        // Method to check if two dates are equal
        public boolean equals(MyDate otherDate) {
            return this.year == otherDate.year && this.month == otherDate.month && this.day == otherDate.day;
        }
    
        // Method to calculate the difference in days between two dates
        public int differenceInDays(MyDate otherDate) {
            int daysInBetween = 0;
            MyDate currentDate = new MyDate(this.day, this.month, this.year);
    
            while (!currentDate.equals(otherDate)) {
                currentDate.incrementDays(1);
                daysInBetween++;
            }
    
            return daysInBetween;
        }
    
        // Method to format the date as a string in a custom format
        public String format(String format) {
            format = format.replaceAll("DD", String.format("%02d", day));
            format = format.replaceAll("MM", String.format("%02d", month));
            format = format.replaceAll("YYYY", String.format("%04d", year));
            return format;
        }
    
        // Method to get the day of the week for the date (0 for Sunday, 1 for Monday, ..., 6 for Saturday)
        public int getDayOfWeek() {
            int y = year - (month < 3 ? 1 : 0);
            int m = month + (month < 3 ? 12 : 0);
            return (y + y / 4 - y / 100 + y / 400 + (13 * m + 8) / 5 + day) % 7;
        }

        // Method to check if the current date is before another date
    public boolean isBefore(MyDate otherDate) {
        if (this.year < otherDate.year) {
            return true;
        } else if (this.year == otherDate.year && this.month < otherDate.month) {
            return true;
        } else if (this.year == otherDate.year && this.month == otherDate.month && this.day < otherDate.day) {
            return true;
        }
        return false;
    }

    // Method to check if the current date is after another date
    public boolean isAfter(MyDate otherDate) {
        return !this.equals(otherDate) && !this.isBefore(otherDate);
    }

    // Method to calculate the age based on the current date and the birth date
    public int calculateAge(MyDate birthDate) {
        int age = this.year - birthDate.year;
        if (this.month < birthDate.month || (this.month == birthDate.month && this.day < birthDate.day)) {
            age--;
        }
        return age;
    }

    // Method to increment the date by one day
    public void incrementDay() {
        day++;
        if (day > daysInMonth()) {
            day = 1;
            incrementMonth();
        }
    }

    // Method to decrement the date by one day
    public void decrementDay() {
        day--;
        if (day < 1) {
            decrementMonth();
            day = daysInMonth();
        }
    }

    // Method to increment the date by one month
    public void incrementMonth() {
        month++;
        if (month > 12) {
            month = 1;
            year++;
        }
    }

    // Method to decrement the date by one month
    public void decrementMonth() {
        month--;
        if (month < 1) {
            month = 12;
            year--;
        }
    }

    // Method to increment the date by one year
    public void incrementYear() {
        year++;
    }

    // Method to decrement the date by one year
    public void decrementYear() {
        year--;
    }

    // Method to get the current date
    public static MyDate getCurrentDate() {
        LocalDate currentDate = LocalDate.now();
        return new MyDate(currentDate.getDayOfMonth(), currentDate.getMonthValue(), currentDate.getYear());
    }

    // Method to check if the current date is equal to the actual date
    public boolean isCurrentDate() {
        MyDate currentDate = getCurrentDate();
        return this.equals(currentDate);
    }

    // Method to check if the current date is before the actual date
    public boolean isBeforeCurrentDate() {
        MyDate currentDate = getCurrentDate();
        return this.isBefore(currentDate);
    }

    // Method to check if the current date is after the actual date
    public boolean isAfterCurrentDate() {
        MyDate currentDate = getCurrentDate();
        return this.isAfter(currentDate);
    }

    // Method to calculate the difference in days between the current date and the actual date
    public int differenceInDaysFromCurrentDate() {
        MyDate currentDate = getCurrentDate();
        return this.differenceInDays(currentDate);
    }

    // Method to increment the current date by one day
    public static MyDate incrementCurrentDateByOneDay() {
        MyDate currentDate = getCurrentDate();
        currentDate.incrementDay();
        return currentDate;
    }

    // Method to decrement the current date by one day
    public static MyDate decrementCurrentDateByOneDay() {
        MyDate currentDate = getCurrentDate();
        currentDate.decrementDay();
        return currentDate;
    }
}
