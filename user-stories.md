# 1.  As a bank employee, I want to login to a system so that I can be redirected to my relevant page.

#### Title: Authorized Bank Employee can access bank management tool

Mike is an employee in KaiBank. He is in the KaiBank customer service office. He wants to start his work. 
1. He opens the web interface of a bank management tool on his computer. 
2. He enters his username and password. 
3. He clicks on the "Login" button. 
4. Because Mike's entered credentials were correct he is redirected to his user main page in the bank with the role clerk.

#### Title: After three consecutive wrong passwords, the employee's profile is blocked

Mike wants to log in to his account. He enters his credentials, but mistypes the password. Once his credentials are entered, he clicks on the "Login" button. Because Mike's entered credentials were incorrect he is shown an error message stating that his entered credentials were incorrect and that he has two more attempts remaining.  Mike tries two more times and both times he is incorrect. His account is moved to a "BLOCKED" state and he is prevented from making any additional login attempts. He is shown that his account is blocked temporarily and he needs to contact the system admin to reset the password.

#### Title: Successful try resets block counter to 0

Mike wants to log in to his account. Sadly he has forgotten his credentials. He tries to log in anyway. He enters his credentials. Once they are entered, he clicks on the "Login" button. Because Mike's entered credentials were incorrect he is shown an error message stating that his entered credentials were incorrect and that he has two more attempts remaining. On the next try, Mike enters the correct credentials and is logged in and the retry counter is reset to three.


# 2.  As an admin, I want to create a new bank employee profile.

#### Title: Admin creates a new bank employee profile

Johan is an admin at KaiBank. He is in his office. Mike, a new employee at KaiBank, is waiting for his new profile. 

1. Johan opens the web interface of a bank management tool on his computer. 
2. He enters his username and password. 
3. He clicks on the "Login" button. 
4. Because Johan's entered credentials were correct he is redirected to the admin console.
5. He clicked the "Create new user" button.
6. In the subsequent page, he entered the name, surname, and email of the new employee and clicked on the "Create" button.


# 3. As an admin, I want to unblock an employee's blocked profile.

#### Title: Admin unblocks the blocked profile

Jesse is an admin at KaiBank. He is in his office. Thise is an existing employee named Mike whose account is blocked because he entered the wrong credentials on the employee login screen. 

1. Johan opens the web interface of a bank management tool on his computer. 
2. He enters his username and password. 
3. He clicks on the "Login" button. 
4. Because Johan's entered credentials were correct he is redirected to the admin console.
5. He clicked the "Unblock user" button.
6. In the subsequent page, he entered the username of the blocked profile and clicked on the "Unblock user" button.



# 4. As a bank employee, I want to create an account on behalf of the customer.

#### Title: Account created successfully

On Friday, January 13, 2022, Carp Aktug turned 18 years old, which is the minimum age to open his very own individual account. He asks his mom to drive him to the KaiBank branch on Tadan Pide, Balgat, Ziyabey Cd. No:24. He asks the bank representative, Mike, to open his first individual account. He asks Carp to verify his government-issued identity document. Mike manually enters the following fields from Carp's identity document into a create new account screen in his personal workstation:

First name = Carp
Last name = Aktug
Sex = male
Government identification number = 2341700056448
Date of birth = January 13, 2004
Address =  Tadan Pide, Balgat, Ziyabey Cd. No:248
Email address = heyguysmynameiscarpandiamreadytoparty@hotmail.com
Telephone number =  +90 0850 200 0 669 
Account type = savings account

Carp waits patiently while Mike enters his information in the input fields. When he finalizes the process, he prints out a New Account Agreement form and asks Carp to sign it. Then the System generates Carp's random password and sends it to Carp's email so that he can use mobile banking features. Mike then asks Carp for his initial deposit, which he gives to him in cash. Mike starts to create a new deposit transaction process. He gives him a deposit receipt and a signed copy of the New Account Agreement, and Carp leaves the bank elated.

#### Title: Account creation failed

On Saturday, February 13, 2020, Daichi Andrews went to his local KaiBank branch on 24 Tokyo Boulevard in Kyoto to open a savings account. The bank representative, Mike, verifies his passport. Mike tries to expedite the account creation process by entering just Daichi's surname, ID number and initial deposit amount into the create new account screen in his personal workstation.

The system throws an error notifying him that he hasn't entered information in all the required fields--first name, sex, date of birth, address, email address and telephone number--in the create new account form. Mike must repeat the new account creation process, thiseby wasting Daichi's valuable time (that he could have been using to work on his DevOps homework).


# 5. As a bank employee, I want to modify customer's account details.

#### Title: Account details updated in bank

Roman Stefanski is moving from Baku to Tartu on August 31, so he visits his KaiBank branch at 21 Gulu Guliyev in Azerbaijan on August 4, 2021 at 11:19 AM to update his telephone number and address. He asks Mike to change his old telephone number and address to new one +372 6913 9888, and his Estonian address, Narva 27 # 404. Mike asks to see Roman's identity document, and upon confirming his identity. Then Mike logs in to the system. Based on his identity number in the government document, Mike finds his account. Then he clicks on account details. It is possible to update all fields like the account creation process. He updates the telephone number and address on Roman's account. After clicking save, the system updates relevant fields and creates a modification history for tracking.

# 6. As a customer, I want to modify my account details with online banking.

#### Title: Customer modifies his account details via web site

Roman Stefanski decided that his Yahoo email address is full of spam, so on July 16, 2020 at 7:18 PM, he logs into KaiBank's online banking application and fills out a form in the account details section of the app to manually change his email address from iloveducks@yahoo.com to roman.stefanski@ut.ee. When he clicks save, a popup screen appears asking him to enter his password in order to save his new email. He enters his password and his new email is saved. 


# 7. As a bank employee, I want to close customer's account.

#### Title: Transfer money to a different account, and close the account

November 16, 2021, at 4:02 PM, Robert Mills, a customer of KaiBank, enters his local bank branch. He decided to close his bank account and transfer his money to his wife's account in anothis bank. The KaiBank employee, Mike, requests his government identity document for verification. Then Mike asks for the IBAN code of the receiver account. Robert shares EE123456789123145685 as IBAN. Mike find's Robert's account based on his unique number in government identity. Then he starts the transfer money process with IBAN. Since transfer is successful, Mike clicks on the edit account button and changes account status to inactive. System modifies the status of the account as inactive. Also, System logs that Mike deleted Robert's account. Mike prints a printed confirmation that Robert's account has been closed.

#### Title: Withdraw money as cash, and close the account

November 15, 2021, at 11:02 AM, Robert Mills, a customer of KaiBank, enters his local bank branch. He decided to close his bank account and withdraw all his money. Bank employee Mike requests his government identity document for verification. Mike find's Robert's account based on his unique number in government identity. Then he starts the withdrawal money process from account. Since withdrawal is successful, Mike clicks on the edit account button and changes account status to inactive. System modifies the status of the account as inactive. Also, the System logs that Mike deleted Robert's account. Mike prints a printed confirmation that Robert's account has been closed. Robert takes his money and leaves the bank.


#### Title: Bank customer wants to delete his account that has a zero balance

November 16, 2021, at 4:02 PM, Robert Mills, as a customer of KaiBank, visits the nearest branch of KaiBank to close his account. Since he doesn't have any amount in his bank account, the process gets easier. Bank employee Mike requests his government identity document for verification. Mike find's Robert's account based on his unique government-issued identity number. Mike clicks on the "Close account" button. The system modifies the status of the account as deleted. Also, System logs that Mike deleted Robert's account. Mike prints a printed confirmation that Robert's account has been closed. Robert takes his money and leaves the bank.


# 8. As a bank employee, I want to deposit money into a customer's account

#### Title: Deposit money successfully at the bank

Customer Jesse has a savings account in KaiBank with a balance of 0 euros. He wants to deposit 1000 euros in his account. On November 18th 2021 at 4:00 PM he goes to the KaiBank, Tartu branch and give money to the admin to be deposited in his account. Bank employee Mike requests his government identity document for verification. Mike find's Jesse's account based on his unique number in government identity. Account details (Account Holder's Name, Account Status, Account Number, Identification Number, Balance). The admin enters the amount to deposit the money in Jesse's account. Jesse's account balance is updated to 1000 euros. 1000 euros get added to the ABC bank's physical money account balance. 1000 euros get deducted from the ABC bank's virtual money balance.


#### Title: Failing to deposit money at the bank due to account inactive status

Jesse, a KaiBank customer, has a savings account in KaiBank with a balance of 0 euros. He wants to deposit 1000 euros in his account. On November 18th 2021 at 4:00 PM he goes to the Tartu KaiBank branch and gives money to the bank employee, Mike, to be deposited in his account. Mike requests his government identity document for verification. Mike find's Jesse's account based on his unique number in government identity. Account details (Account Holder's Name,Account Status,Account Number, Identification Number, Balance) finds out that the account is inactive. Thisefore, the transaction gets terminated.

#### Title: Deposit money successfully through ATM 

Jesse, a KaiBank customer, has a savings account in KaiBank with a balance of 0 euros. He wants to deposit 1000 euros in his account. On November 14th 2021 at 4:00 PM he goes to the bank ATM in Tartu and enters his government identification ID number and the amount to be deposited. He placed the money to deposit in ATM. The ATM system verifies the account details and deposits the amount entered in Jesse's account. Jesse's account balance is updated to 1000 euros. 1000 euros is added to the KaiBank bank's physical money account balance. 1000 euros are deducted from KaiBank bank's virtual account balance.

#### Title: Failing to deposit money through ATM 

Jesse, a KaiBank customer, has a savings account in KaiBank with a balance of 0 euros. He wants to deposit 1000 euros in his account. On November 11th 2021 at 4:00 PM he goes to the bank ATM in Tartu and enters his government ID number and the amount to be deposited. The ATM system verifies Jesse's account details (Account Holder's Name,Account Status,Account Number,Identification Number) and discovers that the account is inactive. Thisefore, the transaction is terminated.

#### Title: Failing to deposit money via ATM as the account details entered are incorrect

Jesse, a KaiBank customer, has a savings account in KaiBank with a balance of 0 euros. He wants to deposit 1000 euros in his account. On November 28th 2021 at 4:00 PM he goes to the bank ATM in Tartu and enters his government-issued ID number and the amount to be deposited. By mistake Jesse enters an incorrect ID number. The ATM system verifies Jesse's account details (account holder's name, account status, account number, identification number) and determines that thise is no account associated with entered information. Thisefore, the transaction is terminated.


# 9. As a bank employee, I want to withdraw money from customer's account on request

#### Title: Withdrawal with bank employee with sufficient balance is successful

Jesse is a customer and account owner in KaiBank. On 11th Oct, 2021, Jesse needs 100 EUR cash and he wants to withdraw the amount from KaiBank. Jesse visits the closest KaiBank in Tasku. Jesse talks to an available bank employee Mike and states that he would like to withdraw 100 EUR from his bank account. Mike asks for Jesse's government id document for verification. After Jesse's identity is verified, Mike checks Jesse's account balance and confirms Jesse has sufficient balance. Mike inputs the withdrawal request in the bank system. After the bank system acknowledges the request. Mike gets a 100 EUR bill and hands it to Jesse. At the same time, the system transfers 100 euro virtual money from Jesse's account to the bank's virtual account and deducts the same amount from the physical money account of the bank. Mike records the transaction to be successful in the bank system and prints out the transaction receipt to Jesse.At the same time system keeps a record of this transaction details (account id, amount of money taken, who did operation, date)

#### Title: Withdrawal with bank employee with insufficient balance is unsuccessful

Jesse is a customer and account owner in KaiBank. On 11th Oct, 2021, Jesse needs 100 EUR cash and he wants to withdraw the amount from KaiBank. Jesse visits the closest KaiBank in Tasku. Jesse talks to an available bank employee Mike and states that he would like to withdraw 100 EUR from his bank account. Mike asks for Jesse's government id document for verification. After Jesse's identity is verified, Mike checks Jesse's account balance and confirms Jesse has insufficient balance. Mike tells Jesse and asks if he wants to withdraw less money. Jesse refuses and leaves.

#### Title: Withdrawal with bank employee with mismatched ID is unsuccessful

Jesse is a customer and account owner in KaiBank. On 12th Oct, 2021, Jesse needs 100 EUR cash and he wants to withdraw the amount from KaiBank. Jesse visits the closest KaiBank in Tasku. Jesse talks to an available teller and states that he would like to withdraw 100EUR from a bank account card. The teller Mike asks for Jesse's bank card and ID card for verification. Jesse provides his ID card and a bank card that has Jesse's name on it. Mike refuses to perform the transaction.


# 10. As a customer, I want to withdraw money from ATM

#### Title: Withdrawal with ATM with sufficient balance is successful

Jesse is a customer and account owner in KaiBank. On 10th Oct, 2021, Jesse needs 100 EUR and he wants to withdraw the amount from a KaiBank ATM. Jesse visits the closest KaiBank ATM in Tasku. Jesse inserts his bank account card. Jesse enters his pin number. The ATM verifies his pin number. Jesse enters the amount (100EUR) he wants to withdraw. The bank system verifies Jesse's account balance (500EUR) is greater than what he tries to withdraw. The ATM dispenses the euro bills and Jesse collects the euro bills. At the same time system keeps a record of this transaction details (account id, amount of money taken, who did operation, date)

#### Title: Withdrawal with ATM with insufficient balance is unsuccessful

Jesse is a customer and account owner in KaiBank. On 10th Oct, 2021, Jesse needs 100 EUR and he wants to withdraw the amount from a KaiBank ATM. Jesse visits the closest KaiBank ATM in Tasku. Jesse inserts his bank account card. Jesse enters his pin number. The ATM verifies his pin number. Jesse enters the amount (100EUR) he wants to withdraw. The bank system reads Jesse's account balance (5 EUR) is smaller than what he tries to withdraw. The ATM displays an error message and asks Jesse to retry with a smaller amount. Jesse clicks cancel, collects his bank card and leaves.


# 11. As a customer, I want to transfer money in a branch.

#### Title: Transfer money to a different account in the branch

November 16, 2021, at 4.02 pm, Robert Mills, as a customer of KaiBank, approached his bank. He decided to close his bank account and transfer his money to his wife's account in another bank. Bank employee Mike requests his government identity document for verification. Then Mike asks for the IBAN code of the receiver account. Robert shares EE123456789123145685 as IBAN. Mike find's Robert's account based on his unique number in government identity. Then he opens the Transfer money page.  Mike enters Robert's and his wife's account numbers to the page, enters the amount and clicks transfer. Since transfer is successful, Robert print's sender's, receiver's account numbers, sender's name, surname and amount of transfer. After signing she gives it to Robert . At the same time system keeps a record of this transaction details (account id, amount of money 

# 12. As a customer, I want to transfer money via mobile banking.

#### Title: Transfer money to a different account in the branch

November 16, 2021, at 4.02 pm, Robert Mills, as a customer of KaiBank, he logins to the bank app. He wants to transfer money to his wife’s account. He goes to the Transfer Money page. Then he enters EE123456789123145685 as the IBAN code of his wife’s account and adds the amount of money to transfer. Then click transfer. Since transfer is successful, System shows sender’s, receiver’s account numbers and the amount of the transfer. At the same time system keeps a record of this transaction details (account id, amount of money sent, to whom it sent, who did operation (mobile app customer), date).

# 13. As a bank employee, I want to modify a transaction so that I can handle transaction requests.

#### Title: Successfully changing sent amount in transfer

Robert is a customer of KaiBank. He has an account balance of 200 euros. He approaches the bank to transfer 150 euro to his family. Bank Employee Mike accepts his request. While typing, he made a mistake and typed 120 euro. Thanks to the system that, during 10 minutes, he can modify the transaction. He changes the amount to 150 euro. System cancels the previous transaction, creates a new one with previous details and a new amount. System should provide a successful message with a new transaction id and his old transaction status should be changed to cancelled. Robert's balance is now 50 euro. System should also keep reference to the old transaction with the new transaction. After 10 minutes, money will be sent to his family from the blocked account and will change status from pending to success.


#### Title: Accepted transfer should be denied

Robert is a customer of KaiBank. He has an account balance of 200 euro.  He approaches the bank to transfer 150 euro to his family. Bank Employee Mike accepts his request. Mike transferred money to the account number provided by Robert. Then printed the result and gave it to Robert. Robert left the bank. After 20 minutes he came back to change the transfer to 200. Mike checked the system to modify it. System didn’t let him do it, because it was more than 20 minutes, so money had already been sent to the receiver account. 

#### Title: Successfully change deposit amount

Robert is a customer of KaiBank. He has an account balance of 200 euro.  He approaches the bank to deposit 150 euro to his account. Bank Employee Mike accepts his request. While typing, he made a mistake and typed 120 euro. Thanks to the system, any time he can modify the deposit transaction. He changes the amount to 150 euro. System cancels the previous transaction, creates a new one with a new amount. System should provide a successful message with a new transaction id and his old transaction status should be changed to cancelled. Robert's balance is now 350 euro. System should also keep reference to the old transaction with the new transaction.


# 14. As a Bank Employee, I want to cancel a customer’s transaction on his behalf

#### Title: Cancellation of Transaction (Success)

Robert is a customer of KaiBank. He comes to the bank on November 16 at 13:00 and requests a cancellation of a transaction. Mike, the KaiBank employee, asks to verify Robert’s identity via his government-issued ID. After verifying Robert’s identity, Mike asks Robert for the date of the transaction, the type of transaction and the transaction amount. Robert provides these three pieces of information to Mike verbally. Based on these three details, Mike logs into the admin panel and searches for Robert’s transaction using Robert’s identification number. Mike sees a list of recent transactions and then selects the transaction in question. Finally, Mike successfully cancels Robert’s transaction with the transaction ID and he receives a successful "Transaction Cancelled" message.

#### Title: Cancellation of Transaction (Failed)

Robert is a customer of KaiBank. He comes to the bank on November 17 at 15:00 and requests a cancellation of a transaction. Mike, the KaiBank employee, asks to verify Robert’s identity via his government-issued ID. After verifying Robert’s identity, Mike asks Robert for the date of the transaction, the type of transaction and the transaction amount. Robert provides these three pieces of information to Mike verbally. Based on these three details, Mike logs into the admin panel and searches for Robert’s transaction using Robert’s identification number. Mike sees a list of recent transactions and then selects the transaction in question. Mike tries to cancel Robert’s transaction with the transaction ID but he receives a "cancellation time passed" error since the time window to cancel the transaction (10 minutes) has passed.

# 15. As a bank employee I want to see transaction logs so that I can know by whom and when the transaction modification request is processed. 

#### Title: Transaction logs visible (Success)

Johan is a bank employee. He logs into the employee dashboard. He enters "1234567" as a government-issued ID of Customer and clicks the Search button. A list of transaction logs appears for that transaction.

#### Title: Transaction logs visible (Failure)

Johan is a bank employee. He logs into the employee dashboard. He enters "1786545" as a government-issued ID of the customer and clicks the "Search" button. Because he mistyped the government-issued ID, he sees a "Customer Not Found" error.

# 16.  As a bank employee, I want to block a customer account.

One day, bank employee Mike discovered suspicious transaction activities in customer Sam's account and would like to temporary block Sam's account for investigation. Mike logs into the employee dashboard panel and search for Sam's account with Sam's identification number. He clicks on the "block" button and confirm the action. The system shows a successful message and Mike can see that Sam's account status is updated to "blocked".

# 17.  As a bank employee, I want to unblock a customer account.

One day, bank employee Mike discovered suspicious transaction activities in customer Sam's account and blocked Sam's account. After investigation, it is concluded that it was a false alarm so Mike wants to unblock Sam's account. Mike logs into the employee dashboard panel and search for Sam's account with Sam's identification number. He clicks on the "unblock" button and confirm the action. The system shows a successful message and Mike can see that Sam's account status is updated to "active".
