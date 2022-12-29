# StoreAPI


This is RestAPI written in SpringBoot

The two entities are Customers and Orders

An Order can only be linked to one Customer

A customer can have multiple orders linked to them

Customers and Orders can be created seperately and then linked via the 'LinkOrderToCustomer' endpoint

A customer can also be created along with their orders

Postman collection: 
[StoreAPI.postman_collection.zip](https://github.com/fr3drick/StoreAPI/files/10319929/StoreAPI.postman_collection.zip)


             +------------+
             | Controller  |
             +------------+
                    |
                    | HTTP request/response
                    |
             +------------+
             |  Service   |
             +------------+
                    |
                    | Business logic
                    |
             +------------+
             | Repository |
             +------------+
                    |
                    | Database access
                    |
             +------------+
             |   Model    |
             +------------+
