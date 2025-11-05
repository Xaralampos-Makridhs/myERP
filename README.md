# myERP

myERP is a simple yet powerful desktop application (Java/Swing) for managing core business resources in a small-to-medium-sized enterprise. It provides an intuitive, tab-based interface for handling customers, products, and orders.

## Features

### Customer Management
- **Add customers:** Store information such as name, email, phone number, company name, VAT number, and address.
- **Remove customers:** Delete customers from the database.
- **Status and notes:** Track the status of each customer and maintain per-customer notes.

### Product Management
- **Add products:** Register new products with properties like name, description, price, and available stock.
- **Remove products:** Delete products from your inventory.
- **Update product information:** Change product price and stock levels, activate/discontinue products, and apply discounts.
- **Product status tracking:** Monitor whether products are active or discontinued.

### Order Management
- **Add orders:** Create new orders by selecting existing customers and available products (with specific quantities).
- **View orders:** Orders are displayed in a table with details such as customer, status, and total amount.
- **Complete and cancel orders:** Mark orders as completed or cancelled.
- **Order summaries:** View the summary and updated timestamps for each order.

### Interface & Experience
- **Tabbed UI:** Switch easily between Customers, Products, and Orders using a clean tab-based GUI.
- **Instant table updates:** All relevant tables update automatically when adding, removing, or updating entries.
- **Input validation:** Ensures valid data entry for prices, stock, and email fields.

## How to Use

1. **Launch the program.**
2. **Navigate using the top tabs**: Customers, Products, Orders.
3. **Use action buttons** (`Add`, `Remove`, `Complete`, `Cancel`) within each tab to manage your resources.
4. **Dialog boxes** prompt for details as you perform each action.

## Technologies

- Java 11+
- Swing (GUI framework)
- No database required (in-memory data management)

---
