# Backend Architecture Details

---

## Table of Contents

- [Class Diagram](#class-diagram)
  - [Bank Implementation](#bank-implementation)
  - [Business Users Implementation](#business-users-implementation)
    + [Enums](#enums)
  - [Business Service Implementation](#business-service-implementation)
- [Object Diagram](#object-diagram)
- [Component Diagram](#component-diagram)
- [Use case Diagrams](#use-case-diagrams)
- [Sequence Diagrams](#sequence-diagrams)

---

### Class Diagram

#### Bank Implementation
The bank model works the same as with real world banks.
So far, the bank has two types of money:
- physical
- virtual

Whenever the bank takes physical money from the customer, it extracts that
amount of money from the bank's account and transfers it to the customer's 
account.

#### Business Implementation
In the first meeting, we discussed how the system should work, and the types of users 
that will use the system.

The team decided to divide the system users into two types:

- `Customer` - person who uses the system.
- `Employee` - person who operates the system.

And there are two types of employees:
- `Admin`
- `Manager`

We defined them by using a `UserRoles` model.

#### Enums
To put a boundary on models, we used several Enumerations.

#### Business Service Implementation
In our second meeting, we discussed how the system service will be implemented.
1. Each `Customer` can have only one `Account` for payments and transfers 
   (initially we were unsure whether we would implement a joint account feature).
2. Each service works with `Transaction` in order to provide traceability of money, 
   and to record all transfers that occurred on the account. 

![](diagrams/class_diagram/SystemModellingProject.png)

---

### Object Diagram
The object diagram is drawn with the help of our class diagram and other diagrams. 
By doing so, I ensured that the logic of our object diagram coincides with that of the 
class diagram and with the actual project code.

![](diagrams/object_diagram/object_diagram.png)

---

### Component Diagram

To showcase the implementation layer of the system, component diagrams, use case diagrams 
and sequence diagrams are shown below:

![](diagrams/component_diagram/component_diagram_transaction.png)

---

### Use case Diagrams
From our user stories ([all user stories](user-stories.md)), we developed the logic of 
our system by constructing Use Case Diagrams as a team. We decided each user story will 
have one use case diagram to make the implementation process easier, and to make it easy 
to grasp the logic of our application.

#### Account Logic
The first aspect of the system we defined was the Account framework.

1) We created the logic where a bank employee creates an account on behalf of the customer.
   ![](diagrams/usecase_diagrams/4_5_6_7/4._Create_an_account_on_behalf_of_the_customer.png)
2) We developed the scenario where a bank employee edits a customer's account details.
   ![](diagrams/usecase_diagrams/4_5_6_7/5_Modify_Account_Details.png)
3) We developed the scenario where the customer edits their own account details.
   ![](diagrams/usecase_diagrams/4_5_6_7/6_Edit_Account_Customer.png)
4) We developed the scenario where the customer closes his or her account.
   ![](diagrams/usecase_diagrams/4_5_6_7/7._close_customer_s_account_usecase.png)

---

#### Transaction Logic
1) Use case diagram for Deposit logic
   ![](diagrams/usecase_diagrams/8_9_11_12/8.DepositUseCase.png)
2) Use case diagram for Withdrawal logic
   ![](diagrams/usecase_diagrams/8_9_11_12/9.WithdrawlUseCase.png)
3) Use case diagram for Transfer logic
   ![](diagrams/usecase_diagrams/8_9_11_12/11.Transfer_to_another_account.png)
4) Use case diagram for Mobile Bank Transfers
   ![](diagrams/usecase_diagrams/8_9_11_12/12.CustomerToCustomerMobileBankTransfer_Usecase.png)
5) Use case diagram for changing the Transfer amount
   ![](diagrams/usecase_diagrams/13_14_15/13.1.Change_transfer_amount.png)

6) Customer deposits money to the bank
   ![](diagrams/usecase_diagrams/13_14_15/13.3.Change_deposit_amount_in_bank.png)
7) Customer cancels a transaction

   ![](diagrams/usecase_diagrams/13_14_15/US14UC.png)

8) Admin sees the transaction logs
   ![](diagrams/usecase_diagrams/13_14_15/15.Admin_see_transtion_logs.png)

---


### Sequence Diagrams

1) As a bank employee, I want to create an account on behalf of the customer.
   ![Create an account on behalf of the customer sequence diagram](diagrams/sequence_diagrams/4.Create_an_account_on_behalf_of_the_customer.png)

2) As a bank employee, I want to modify the customer's account details.
   ![Bank employee modifies profile online banking on customer's behalf sequence diagram](diagrams/sequence_diagrams/5.BankEmployeeModifies_Customer_Details.drawio.png)

   ![Customer modifies profile online banking sequence diagram](diagrams/sequence_diagrams/5.modifying_customers_account_details.drawio.png)

3) As a customer, I want to modify my account details via online banking.

   ![Bank employee closes customer's account sequence diagram](diagrams/sequence_diagrams/6.user_modifying_theirs_account_details.png)

4) As a bank employee, I want to close a customer's account.

   ![Bank employee deposits money on customer's behalf sequence diagram](diagrams/sequence_diagrams/7._Close_customer_s_account.png)

5) As a bank employee, I want to deposit money into a customer's account.

   ![Bank employee withdraws money on customer's behalf sequence diagram](diagrams/sequence_diagrams/8_Deposit.drawio.png)

6) As a bank employee, I want to withdraw money from a customer's account at their request.

   ![Customer ATM withdrawal sequence diagram](diagrams/sequence_diagrams/9_Withdrawal.drawio.png)

7) As a customer, I want to transfer money in a bank branch.

   ![Customer in-branch transfer sequence diagram](diagrams/sequence_diagrams/11_Transfer_to_another_account.png)

8) As a customer, I want to transfer money via mobile banking.

   ![Customer mobile bank transfer sequence diagram](diagrams/sequence_diagrams/12_Mobile_Bank_Transfer.png)

9) As a bank employee, I want to modify a transaction so that I can handle transaction requests.

   ![Bank employee modifies customer's transaction sequence diagram](diagrams/sequence_diagrams/13.ModifyTransaction.png)

10) As a Bank Employee, I want to cancel a customer’s transaction on his or her behalf.

![Bank employee cancels transaction on customer's behalf sequence diagram](diagrams/sequence_diagrams/US14SD.png)

11) As a bank admin, I want to see the transaction logs so that I can know by whom and when the transaction modification request is processed.

![Admin views transaction logs sequence diagram](diagrams/sequence_diagrams/15.Admin_see_transactions.png)

