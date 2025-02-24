# Mega City Cab

Mega City Cab is a complete cab booking application that allows customers to book rides, view bookings, and get help guidelines. Admins can manage cars, bookings, customers, drivers, and help guidelines.

## Features

- **Customer Features:**
  - Register and login with email verification using OTP.
  - Make new bookings.
  - View past bookings.
  - Access help guidelines.

- **Admin Features:**
  - Manage cars (add, update, delete).
  - Manage bookings (view all bookings, add, update, delete).
  - Manage customers (view all customers, update, delete).
  - Manage drivers (add, update, delete).
  - Manage help guidelines (add, update, delete).

## Technology Stack

- **Backend:**
  - Java Servlet
  - JDBC
  - MySQL

- **Frontend:**
  - JSP
  - HTML/CSS

## Project Structure

```
Mega_City_Cab/
├── src/
│   ├── com/example/mega_city_cab/
│   │   ├── dao/
│   │   │   ├── AdminDAO.java
│   │   │   ├── BookingDAO.java
│   │   │   ├── CarDAO.java
│   │   │   ├── CustomerDAO.java
│   │   │   ├── DriverDAO.java
│   │   │   ├── HelpDAO.java
│   │   ├── model/
│   │   │   ├── Admin.java
│   │   │   ├── Booking.java
│   │   │   ├── Car.java
│   │   │   ├── Customer.java
│   │   │   ├── Driver.java
│   │   │   ├── Help.java
│   │   ├── service/
│   │   │   ├── AdminService.java
│   │   │   ├── BookingService.java
│   │   │   ├── CarService.java
│   │   │   ├── CustomerService.java
│   │   │   ├── DriverService.java
│   │   │   ├── HelpService.java
│   │   │   ├── OtpService.java
│   │   ├── servlet/
│   │   │   ├── AdminServlet.java
│   │   │   ├── BookingServlet.java
│   │   │   ├── CarServlet.java
│   │   │   ├── CustomerServlet.java
│   │   │   ├── DeleteCustomer.java
│   │   │   ├── DriverServlet.java
│   │   │   ├── HelpServlet.java
│   │   │   ├── LoginServlet.java
│   │   ├── util/
│   │       ├── DBConnection.java
├── web/
│   ├── WEB-INF/
│   │   ├── web.xml
│   ├── META-INF/
│   │   ├── context.xml
│   ├── jsp/
│   │   ├── addbooking.jsp
│   │   ├── addcar.jsp
│   │   ├── addCustomer.jsp
│   │   ├── adddriver.jsp
│   │   ├── addhelp.jsp
│   │   ├── admin.jsp
│   │   ├── adminaddbooking.jsp
│   │   ├── billdetails.jsp
│   │   ├── customer.jsp
│   │   ├── editBooking.jsp
│   │   ├── editCar.jsp
│   │   ├── editCustomer.jsp
│   │   ├── editdriver.jsp
│   │   ├── edithelp.jsp
│   │   ├── helpmanage.jsp
│   │   ├── index.jsp
│   │   ├── login.jsp
│   │   ├── logout.jsp
│   │   ├── managebooking.jsp
│   │   ├── managecar.jsp
│   │   ├── managecustomer.jsp
│   │   ├── managedriver.jsp
│   │   ├── otp_verification.jsp
│   │   ├── register.jsp
│   │   ├── viewbookings.jsp
│   │   ├── viewhelp.jsp
├── lib/
│   ├── mysql-connector-java-8.0.23.jar
├── README.md
```

## Setup Instructions

### Prerequisites

- Java Development Kit (JDK) 8 or higher
- Apache Tomcat 8.5 or higher
- MySQL Database
- IDE (IntelliJ IDEA, Eclipse, etc.)

### Database Setup

1. Create a MySQL database named `Mega_City_Cab`.
2. Run the following SQL script to create the necessary tables and insert the default admin user:

```sql
CREATE DATABASE Mega_City_Cab;

USE Mega_City_Cab;

-- Admin table
CREATE TABLE admin (
    admin_id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    password VARCHAR(50) NOT NULL
);

-- Customer table
CREATE TABLE customer (
    customer_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    address VARCHAR(200) NOT NULL,
    nic VARCHAR(20) NOT NULL UNIQUE,
    phone VARCHAR(15) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(50) NOT NULL
);

-- Car table
CREATE TABLE car (
    car_id INT AUTO_INCREMENT PRIMARY KEY,
    model VARCHAR(50) NOT NULL,
    license_plate VARCHAR(20) NOT NULL UNIQUE,
    availability BOOLEAN NOT NULL DEFAULT TRUE
);

-- Driver table
CREATE TABLE driver (
    driver_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    contact VARCHAR(15) NOT NULL,
    license_number VARCHAR(20) NOT NULL UNIQUE,
    car_id INT,
    availability BOOLEAN NOT NULL DEFAULT TRUE,
    FOREIGN KEY (car_id) REFERENCES car(car_id)
);

-- Booking table
CREATE TABLE booking (
    booking_id INT AUTO_INCREMENT PRIMARY KEY,
    customer_id INT,
    driver_id INT,
    car_id INT,
    destination VARCHAR(200) NOT NULL,
    payment_method ENUM('cash', 'card') NOT NULL,
    distance_km DOUBLE NOT NULL,
    total_price DOUBLE NOT NULL,
    booking_date DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (customer_id) REFERENCES customer(customer_id),
    FOREIGN KEY (driver_id) REFERENCES driver(driver_id),
    FOREIGN KEY (car_id) REFERENCES car(car_id)
);

-- Help table
CREATE TABLE help (
    help_id INT AUTO_INCREMENT PRIMARY KEY,
    guideline TEXT NOT NULL
);

-- Insert default admin
INSERT INTO admin (username, password) VALUES ('admin', 'admin123');
```

### Project Setup

1. Clone the repository:

```bash
git clone https://github.com/Kulan66/Mega_City_Cab.git
```

2. Open the project in your IDE.

3. Configure the database connection in `DBConnection.java`:

```java
private static final String URL = "jdbc:mysql://127.0.0.1:3306/Mega_City_Cab";
private static final String USER = "root";
private static final String PASSWORD = "your_password";
```

4. Add the MySQL Connector library to your project. If you are using Maven, add the following dependency to your `pom.xml`:

```xml
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <version>8.0.23</version>
</dependency>
```

5. Deploy the project to your Apache Tomcat server.

6. Start the server and open your web browser. Navigate to `http://localhost:8080/Mega_City_Cab` to access the application.

## Usage

- **Admin:**
  - Login using the credentials set in the `admin` table.
  - Manage cars, bookings, customers, drivers, and help guidelines.

- **Customer:**
  - Register and verify your email using OTP.
  - Login and make new bookings.
  - View past bookings and help guidelines.

## Contributing

Contributions are welcome! Please fork the repository and create a pull request with your changes.

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

## Contact

For any questions or support, please reach out to [Kulan66](https://github.com/Kulan66).
