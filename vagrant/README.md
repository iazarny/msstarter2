# Development environment

Vagrant project to create development env, which allow to build and run solution on local workstation. 


## Well known issues

 * Sometimes vagrant provisionong is failed, because url to download mvn is changed - fix url

## Build steps
 **IMPORTANT** do not omit clean flag at first build

```
git clone https://github.com/inspire-software/yes-cart.git --depth 30
cd yes-cart
mvn clean install -Pmysql -PpaymentAll -Pdev -DskipTests=true
```
