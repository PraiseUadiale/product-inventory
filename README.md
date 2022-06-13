

**CONTENTS**

- [Objective](#objective)
- [Tech Stack](#tech-stack)
- [Installation](#installation)


## Objective

Build an inventory tracking web application for a logistics company

Basic CRUD Functionalities:

- Create inventory items
- Edit inventory items
- Delete inventory items
- View a list of inventory items

Additional Feature:

- When deleting, allow deletion comments and undeletion

## Tech Stack

- Backend: Java, SpringBoot, Maven, Spring Batch
- Frontend: Thymeleaf, HTML5, Bootstrap
- Database: H2
- Testing: Junit


Replit: [Site](https://shopify-challenge-2022.praiseuadiale.repl.co/)

## Installation
### Requirements:
- [Docker](https://docs.docker.com/get-docker/)

To run  on your local machine, follow the instructions below:

- To verify Docker is installed Run: 
```
docker --version
```
- Pull Docker images from repository

```
docker pull praiseuadiale/shopify-challenge-2022
```

- Run Image

```
docker run -d -p 8080:8080 --name shopify-challenge praiseuadiale/shopify-challenge-2022
```

On your browser enter url localhost:8080/ and to make use

