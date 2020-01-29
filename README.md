# CodeFellowship - Spring Auth

## Collaborators:  Phong

### Problem Domain

Build an app that allows users to create their profile on CodeFellowship.

The site should have a splash page at the root route (/) that contains basic information about the site, as well as a link to the “sign up” page.
An ApplicationUser should have a username, password (will be hashed using BCrypt), firstName, lastName, dateOfBirth, bio, and any other fields you think are useful.
The site should allow users to create an ApplicationUser on the “sign up” page.
Your Controller should have an @Autowired private PasswordEncoder passwordEncoder; and use that to run passwordEncoder.encode(password) before saving the password into the new user.

### Solution

Initialize the Spring app and create a controller class to control routes. Add the routes and create a template within the resources directory. 

## To Run

Run ./gradlew run

In browser, type localhost.com/8080



