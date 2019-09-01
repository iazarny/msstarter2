# Msstarter2

    Simple and Minimal starter 2 for spring boot microservices and angular js .
With / without docker and kubernetes (using K3S)

# Run options
 * On local station using regular java applications
 * Inside vagrant box with k8s and docker ( require some adition steps)

#  Build steps
 * For local station just create **resthost** in **hosts** file and point to 127.0.0.1 Examle **	127.0.0.2       resthost **
 * Do following
  
```
     clone https://github.com/iazarny/msstarter2.git
     cd msstarter2
     mvn clean install
     cd springgw
     mvn install fabric8:build fabric8:push
     cd ../springrest
     mvn install fabric8:build fabric8:push
```
  * Run both projects - spring gw and sting rest

# Checks
 * Rest service. Open following urls.

 |URL                             | Result          |
 |--------------------------------|-----------------|
 |http://resthost:22222/          | Greetings from Spring Boot! |
 |http://resthost:22222/api       | Api module presented |
 |http://resthost:22222/api/todos | ```[{"id":1,"title":"Have","completed":false},{"id":2,"title":" some","completed":true},{"id":3,"title":"  fun","completed":false}]```  |

 * Gateway service delegates mostly all calls to resthost:22222
 * So need to to check 

 |URL                              | Result          |
 |---------------------------------|-----------------|
 |http://localhost:20000/api/todos | ```[{"id":1,"title":"Have","completed":false},{"id":2,"title":" some","completed":true},{"id":3,"title":"  fun","completed":false}]```  |

 * At least on **http://localhost:20000/api/todos** you have to see following TODO application

![TODO app] (img1.png)

![TODO App 2](http://github.com/iazarny/msstarter2/img1.png)

![TODO App 3](/img1.png)



