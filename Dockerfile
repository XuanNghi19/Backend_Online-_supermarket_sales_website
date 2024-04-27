#Sử dụng maven và java 17
FROM maven:3.8.4-openjdk-17-slim AS build

#Dùng thư mục app trong container để làm việc
WORKDIR /app

#Copy thư mục BACKEND_ONLINE_SUPERMARKET_SALES_WEBSITE vào thư mục /app trong container
COPY ./ /app

#Chạy maven trong container
RUN mvn package -f /app/pom.xml

#Dùng java jdk 17
FROM openjdk:17-slim  

#Thư mục làm việc app trong container 
WORKDIR /app

#Sử dụng file jar đã build trong container 
COPY --from=build /app/target/*.jar app.jar
COPY --from=build /app/uploads uploads

EXPOSE 8080
CMD ["java","-jar","app.jar"]

