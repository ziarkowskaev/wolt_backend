# GUIDE
In order to run application, write "sbt run" in your terminal.
Application is running on http://localhost:9000/delivery 
You can see there default zero values assigned to variables,
time variable is the time that request was submitted.
In order to test the application you need to run the url address in form seen in this example:

http://localhost:9000/delivery/500/2500/10

route    ->    /delivery/:value/:distance/:number

value variable is the value of the bascket in euro cents
distance variable is the distance of the delivery in meters
number variable is the amount of items that are delivered
