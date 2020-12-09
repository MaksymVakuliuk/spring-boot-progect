
# spring-boot-project
* [Short Description](#description)   
* [Project Structure](#structure)
* [Setup Guide](#setup)
* [Authors](#authors)
# <a name="description"></a> Short Description
This project represents the simple REST-ful application. 
The following models are used: Product, Review, User, UserRole.
### Functions available for all users:
  * access to h2 console
  * get all products
  * get product by id
  * get all reviews
  * inject data from scv to database
  + inject test data from scv to database
### Functions available for not authorized users:
  * login
  * register
### Functions available for authorized users:
  * logout
### Functions available for users with "ADMIN" role:
  * get all most used words in reviews
  * get most active users 
  * get most commented products
  * delete any review
### Functions available for users with "USER" role:
  * leave a comment
  * update own comments
# <a name="description"></a> Project Structure
  * java 11
  * maven 4.0.0
  * spring boot
  * lombok
  * junit 4.13
  * common-csv
  * validation api
  * spring swagger2
  * springfox swagger ui
  * h2 database
# <a name="description"></a> Setup Guide
##### To run this project you need to have installed:
* Java 11
* maven 4.0.0
##### Setup project
* Add this project to your IDE as Maven project.
* Configure java sdk in ProjectStructure
* Run method main() in DemoApplication.class.
# <a name="authors"></a> Authors
Maksym Vakuliuk