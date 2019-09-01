# Msstarter2

    Simple and Minimal starter 2 for spring boot microservices and angular js .
With / without docker and kubernetes (using K3S)

# Run options
 * On local station using regular java applications
 * Inside vagrant box with k8s and docker ( require some adition steps)

Build steps for local station
 * For local station just create **resthost** in **hosts** file and point to 127.0.0.1 Example **	127.0.0.2       resthost **
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
 |http://rest-st-2:22222/          | Greetings from Spring Boot! |
 |http://rest-st-2:22222/api       | Api module presented |
 |http://rest-st-2:22222/api/todos | ```[{"id":1,"title":"Have","completed":false},{"id":2,"title":" some","completed":true},{"id":3,"title":"  fun","completed":false}]```  |

 * Gateway service delegates mostly all calls to rest-st-2:22222
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

   Expose service(s) on some IP addresses  in our case 172.28.128.62 The ip address to access from host machine to vagrant box using tpc/ip. 
Actually **gw-st-2** is enough, because some part it delegates, some part it handle
```
[root@minikube msstarter2]# kubectl get rs -A
NAMESPACE     NAME                   DESIRED   CURRENT   READY   AGE
default       gw-st-2-ddc88dd9       1         1         1       9m2s
default       rest-st-2-7b767bf688   1         1         1       9m2s
kube-system   coredns-b7464766c      1         1         1       37m
kube-system   traefik-5c79b789c5     1         1         1       36m
```

```
kubectl expose rs gw-st-2-ddc88dd9       --port=20000  --target-port=20000 --name=ext-gw-st-2    --external-ip=172.28.128.63
kubectl expose rs rest-st-2-7b767bf688   --port=22222  --target-port=22222 --name=ext-rest-st-2  --external-ip=172.28.128.63

```

# Check steps far  Vagrant	
 The same as for sping app from host machine



