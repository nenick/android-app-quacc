# Fluent Espresso

Target is to hide most of the hamcrest style checks and actions.

### The issue with Espresso and Hamcrest

Hamcrest style is hard to read and not very intuitive to use.
The learning phase is very long when you have not much experience with hamcrest and android layouts.

## The Idea

Add some Page Pattern links ...

The current visible content is called a page.
Each page has specific elements like Drawer, Actionbar, Button, List, ...
Each elements has different actions like click, check, scroll, ...

For example

    loginPage.confirm().checkIsDisabled();
    loginPage.username().enter("MyUserName")
    loginPage.password().enter("*****")
    loginPage.confirm().click()
    loginPage.errormessage().check("Username or password not correct.")