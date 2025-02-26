<h2 align="center">🌟 Expense Tracking System 🌟</h2>

<p>This Expense Tracking System is a web application developed using <strong>Spring MVC</strong>, <strong>Spring Data JPA</strong>, <strong>HTML</strong>, <strong>CSS</strong>, <strong>Bootstrap</strong> and <strong>MySQL</strong>. The application provides users with a seamless way to manage and track their expenses while incorporating robust features like user authentication, expense management, profile updating, and password recovery.</p>

---

<h3 align="center">📂 Project Structure</h3>
<p>The project is structured into three main layers:</p>
<ol>
    <li><b>DAO Layer:</b> Responsible for the database logic and manages all CRUD (Create, Read, Update, Delete) operations. It facilitates interaction with the MySQL database, ensuring all user and expense data is stored and managed effectively.</li>
    <li><b>Controller Layer:</b> Includes Servlet classes that handle HTTP requests and responses, managing forms submitted by users and directing them to appropriate service methods.</li>
    <li><b>Model Layer:</b> Contains entity classes representing database tables. These classes allow Hibernate to map Java objects to database tables, facilitating efficient database operations.</li>
</ol>

---

<h3 align="center">🌟 Features</h3>

<h4>1. User Account Management</h4>
<ul>
    <li><b>User Registration:</b> New users can register by providing their details, which are securely stored in the database.</li>
    <li><b>Edit User Details:</b> Update personal information such as name, email, contact details, and password.</li>
    <li><b>Remove Account Permanently:</b> Delete accounts and all related data from the database for privacy.</li>
    <li><b>View User Details:</b> Users can verify and manage their account information.</li>
    <li><b>Login and Authentication:</b> Validates user credentials for secure login.</li>
    <li><b>Forgot Password:</b> Helps users reset their password via a secure process.</li>
</ul>
