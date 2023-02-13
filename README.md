# Inditex exercise

## Selected approach
The chosen solution is a SpringBoot microservice that uses an h2 database for both execution and integration testing.

## Architecture
The code has a hexagonal architecture, separated into use cases (actions), domain and infrastructure. I wanted to separate the domain entity from the SQL entity because this way you could implement other databases (non-relational, for example) and switch between them.

## API
To complete the API, it would be necessary to add the security layer to be able to manage the endpoint accesses. It would also be necessary to add some validations, as well as to extend the possible exceptions of the ErrorHandler.
## Testing
For the testing, I followed the Object Mother pattern. I have introduced unit tests, integration tests and end-to-end tests.

# Acknowledgments
I hope you like my way of programming. I'm sorry I don't have more time, I think it is a long exercise to complete it 100%. Thank you very much for the opportunity. 
