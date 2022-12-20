package project;

public class Customer {
        private String name;
        private int age;
        private String contact_info;
        private String email_address;
        private String account_number;
        private int priority_status;

        public Customer()
        {
            this.name = "";
            this.age = 0;
            this.contact_info = "";
            this.email_address = "";
            this.account_number = "";
            this.priority_status = 0;
        }

        public Customer(String name, int age, String contact_info, String email_address, String account_number, int priority_status)
        {
            this.name = name;
            this.age = age;
            this.contact_info = contact_info;
            this.email_address = email_address;
            this.account_number = account_number;
            this.priority_status = priority_status;
        }

        public String getName()
        {
            return name;
        }
        public void setName(String name)
        {
            this.name = name;
        }

        public int getAge()
        {
            return age;
        }
        public void setAge(int age)
        {
            this.age = age;
        }

        public String getContact_info()
        {
            return contact_info;
        }
        public void setContact_info(String contact_info)
        {
            this.contact_info = contact_info;
        }

        public String getEmail_address()
        {
            return email_address;
        }
        public void setEmail_address(String email_address)
        {
            this.email_address = email_address;
        }

        public String getAccount_number()
        {
            return account_number;
        }
        public void setAccount_number(String account_number)
        {
            this.account_number = account_number;
        }

        public int getPriority_status()
        {
            return priority_status;
        }
        public void setPriority_status(int priority_status)
        {
            this.priority_status = priority_status;
        }

        public void display() {
            System.out.print("Name: ");
            System.out.println(name);

            System.out.print("Age: ");
            System.out.println(age);

            System.out.print("Phone number ");
            System.out.println(contact_info);

            System.out.print("Email: ");
            System.out.println(email_address);

            System.out.print("Account Number: ");
            System.out.println(account_number);

            System.out.print("Priority Status: ");
            System.out.println(priority_status);
        }


        // project.Customer creating their account with custom password
        public String createCustomerAccountWithPass(String pass)
        {
            CustomerDB obj = new CustomerDB();
            return obj.insertCustomerWithPassword(this, pass);
        }



        // edit one field
        public void editCustomerAccountField(String id, String field, String new_value) {
            CustomerDB obj = new CustomerDB();
            obj.updateField(id,field,new_value);
        }


        // project.Customer logging in - returns true or false
        public boolean customerLogin(String id, String pass) {
            CustomerDB obj = new CustomerDB();
            return obj.customerLogin(id, pass);
        }

        // project.Customer changing password

        // project.Customer changing password
        public boolean changePassword(String id, String old_pass, String new_pass) {
            CustomerDB obj = new CustomerDB();
            return obj.changeCustomerPassword(id, old_pass, new_pass);
        }

        // Changing password without comparing
        public void changePassword(String id, String new_pass) {
            CustomerDB obj = new CustomerDB();
            obj.changeCustomerPassword(id, new_pass);
        }

        // Filling project.Customer object from db
        public void getCustomerDetails(String id) {
            CustomerDB obj = new CustomerDB();
            Customer temp = obj.getCustomer(id);

            this.setName(temp.getName());
            this.setEmail_address(temp.getEmail_address());
            this.setAccount_number(temp.getAccount_number());
            this.setAge(temp.getAge());
            this.setContact_info(temp.getContact_info());
            this.setPriority_status(temp.getPriority_status());
        }

        // resetting customer object
        public void clear() {
            this.name = "";
            this.age = 0;
            this.contact_info = "";
            this.email_address = "";
            this.account_number = "";
            this.priority_status = 0;
        }

        // provide email, get ID
        public String getCustomerIDbyEmail(String email) {
            CustomerDB obj = new CustomerDB();
            return obj.getCustomerIDbyEmail(email);
        }

        // provide ID, get email
        public String getCustomerEmailByID(String id) {
            CustomerDB obj = new CustomerDB();
            return obj.getCustomerEmailByID(id);
        }

        // check if the email is already registered (returns true if it does)
        public static boolean checkIfEmailRegistered(String email) {
            CustomerDB obj = new CustomerDB();
            return !(obj.getCustomerByEmail(email).getName().isEmpty());
        }
    }
