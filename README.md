
# Digital Warranty Vault

A simple yet powerful Spring Boot application to manage warranties, including features such as adding, retrieving, and deleting warranties. The application provides an API to interact with warranty data and allows easy management of warranties. Additionally, it sends **email alerts** when a warranty is nearing its expiration date.

## Features

- **Add a warranty**: Save warranty information into the system.
- **Retrieve a warranty**: Get warranty details by ID.
- **Get all warranties**: Fetch a list of all stored warranties.
- **Delete a warranty**: Remove warranty details by ID.
- **Exception handling**: Handle errors such as missing warranties gracefully with proper HTTP responses.
- **Email Alerts for Expiration**: Send an email notification when a warranty is nearing its expiration date (e.g., 30 days before).

## Technology Stack

- **Backend**: Java 17, Spring Boot
- **Database**: MySQL
- **API**: RESTful API
- **Other libraries**:
    - Spring Data JPA
    - Lombok (for reduced boilerplate code)
    - Spring Boot Starter Web
    - Spring Boot Starter Test (for unit testing)
    - Spring Boot Starter Mail (for email notifications)

## Setup

### Prerequisites

Ensure you have the following tools installed:

- **Java 17** or higher
- **MySQL** (or access to a MySQL database)
- **Maven** (for managing dependencies and building the project)
- **Gmail Account** (for sending email notifications)

### MySQL Configuration

1. **Install MySQL**: If you don't have MySQL installed, download and install it from the official [MySQL website](https://dev.mysql.com/downloads/).

2. **Create a Database**:
   After MySQL is installed, create a database named `warranty_vault`:

   ```sql
   CREATE DATABASE warranty_vault_db;
   ```

3. **Create a User** (optional): If you want to use a specific MySQL user, you can create one and grant it permissions:

   ```sql
   CREATE USER 'warranty_user'@'localhost' IDENTIFIED BY 'password';
   GRANT ALL PRIVILEGES ON warranty_vault.* TO 'warranty_user'@'localhost';
   FLUSH PRIVILEGES;
   ```

### Clone the Repository

Clone this repository to your local machine:

```bash
git clone https://github.com/yourusername/digital-warranty-vault.git
```

### Configure MySQL Connection

In your application’s `application.properties`, add the following MySQL connection settings:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/warranty_vault
spring.datasource.username=warranty_user
spring.datasource.password=password
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.hibernate.ddl-auto=update
spring.jpa.database-platform=org.hibernate.dialect.MySQL8Dialect
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
```

### Configure Email for Alerts

1. **Gmail Setup**: To send email notifications for warranty expiration alerts, configure your Gmail SMTP settings in `application.properties`. You will need to use an app password instead of your regular Gmail password.

2. **Add the following email settings to `application.properties`**:

   ```properties
   spring.mail.host=smtp.gmail.com
   spring.mail.port=587
   spring.mail.username=your-email@gmail.com
   spring.mail.password=your-app-password
   spring.mail.properties.mail.smtp.auth=true
   spring.mail.properties.mail.smtp.starttls.enable=true
   ```

   **Note**: For Gmail, you need to [generate an app password](https://myaccount.google.com/apppasswords) instead of using your regular Gmail password.

### Build the Project

Navigate to the project directory and run the following Maven command to build the project:

```bash
mvn clean install
```

### Run the Application

To run the application, use the following command:

```bash
mvn spring-boot:run
```

By default, the application will start at `http://localhost:8080`.

## API Endpoints

### 1. **POST /warranties**

Save a new warranty.

**Request body**:
```json
{
    "productName": "Apple iPhone",
    "brandName": "Apple",
    "purchaseDate": "2023-01-10",
    "expiryDate": "2026-01-10",
    "invoiceUrl": "http://example.com/invoice_apple",
    "email": "your.address@gmail.com"
}
```

**Response**:
```json
{
    "id": 15,
    "productName": "Apple iPad",
    "brandName": "Apple",
    "purchaseDate": "2023-01-10",
    "expiryDate": "2026-01-10",
    "invoiceUrl": "http://example.com/invoice_apple"
}
```

### 2. **GET /warranties/{id}**

Retrieve a warranty by its ID.

**Response**:
```json
{
    "id": 15,
    "productName": "Apple iPad",
    "brandName": "Apple",
    "purchaseDate": "2023-01-10",
    "expiryDate": "2026-01-10",
    "invoiceUrl": "http://example.com/invoice_apple"
}
```

### 3. **GET /warranties**

Get a list of all warranties.

**Response**:
```json
[
    {
        "id": 9,
        "productName": "Laptop",
        "brandName": "HP",
        "purchaseDate": "2023-03-01",
        "expiryDate": "2025-05-27",
        "invoiceUrl": "http://example.com/invoice_laptop"
    },
    {
        "id": 12,
        "productName": "Samsung TV",
        "brandName": "Samsung",
        "purchaseDate": "2023-04-20",
        "expiryDate": "2025-04-20",
        "invoiceUrl": "http://example.com/invoice_samsung"
    },
    {
        "id": 13,
        "productName": "Apple iPhone",
        "brandName": "Apple",
        "purchaseDate": "2023-01-10",
        "expiryDate": "2026-01-10",
        "invoiceUrl": "http://example.com/invoice_apple"
    }
]
```

### 4. **DELETE /warranties/{id}**

Delete a warranty by its ID.

**Response**:
```json
{
  "message": "Warranty {id} deleted successfully!"
}
```

## Warranty Expiration Alerts

The system sends email notifications when a warranty is nearing its expiration date. The application checks for warranties whose expiration date is approaching (e.g., 30 days before the expiration date) and sends an email to the registered email address of the user when adding the warranty.

### Steps for Email Alert:

- The `WarrantyExpiryService` runs periodically to check for warranties nearing expiration.
- If a warranty's expiration date is close, an email is sent to the email address provided by the user when adding the warranty.
- The `EmailService` is responsible for sending the email notifications.

## Exception Handling

The application handles errors gracefully by returning appropriate HTTP responses:

- **404 Not Found**: If a warranty is not found by ID, the API returns a `404` status with a descriptive error message.

  Example:
  ```json
  {
    "message": "Warranty not found with id: 999"
  }
  ```

## Running Tests

To run unit tests and ensure everything is working correctly, use:

```bash
mvn test
```

## Contributing

We welcome contributions to the Digital Warranty Vault! To contribute:

1. Fork the repository.
2. Create a feature branch (`git checkout -b feature-branch`).
3. Commit your changes (`git commit -am 'Add new feature'`).
4. Push to the branch (`git push origin feature-branch`).
5. Open a Pull Request.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Acknowledgements

- Spring Boot - for easy development of Java-based applications.
- Lombok - for reducing boilerplate code.
- MySQL - for managing relational data.
- Spring Data JPA - for integrating Spring with MySQL.
- Spring Boot Starter Mail - for sending email notifications.
