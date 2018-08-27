# digitalpltoon

•	Apache Maven
•	Spring Boot


Sample data to add invoice
endpoint : POST http://localhost:8080/invoices 
add this sample data on the body
	{
 	    "lineItems":[{
        "quantity":2,
        "description":"mca",
        "unitPrice":300
          },
          {
        "quantity":7,
        "description":"lrc",
        "unitPrice":30
          }],
		    "client": "Lucky r",
		    "vatRate": 15,
		    "invoiceDate": "2018-08-26"
		  }
