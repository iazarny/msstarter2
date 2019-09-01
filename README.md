# Msstarter2

    Simple and Minimal starter 2 for spring boot microservices and angular js .
With / without docker and kubernetes (using K3S)

# Run options
 * On local station using regular java applications
 * Inside vagrant box with k8s and docker ( require some adition steps)

#  Build steps for local station
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

![TODO App](/img1.png)

#  Build steps for Vagrant
```
cd vagrant
vagrant up
vagrant ssh

sudo su -
git clone https://github.com/iazarny/msstarter2.git
cd msstarter2
mvn clean install
cd springgw
mvn install fabric8:build fabric8:push
cd ../springrest
mvn install fabric8:build fabric8:push
cd ..
kubectl create -f k8s/deploy-all.yml

```
## Expose to external IP address

   Expose service(s) on some IP addresses  in our case 172.28.128.3. The ip address to access from host machine to vagrant box using tpc/ip. 
Actually **st-wg-2** is enough, because some part it delegates, some part it handle

```
kubectl expose deployment gw-st-2     --port=20000  --target-port=20000 --name=ext-gw-st-2    --external-ip=172.28.128.3
kubectl expose deployment rest-st-2   --port=22222  --target-port=22222 --name=ext-rest-st-2  --external-ip=172.28.128.57

```

# Check steps far  Vagrant	
 The same as for sping app from host machine



