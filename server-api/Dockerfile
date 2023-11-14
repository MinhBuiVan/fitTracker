FROM openjdk:20
LABEL maintainer="fit"
ADD target/fit-tracker-0.0.1-SNAPSHOT.jar fit-tracker.jar
#Trong trường hợp này, chúng ta đang thêm tệp tin fit-tracker-0.0.1-SNAPSHOT.jar từ thư mục target vào image và đổi tên tệp tin thành fit-tracker.jar
ENTRYPOINT ["java", "-jar", "fit-tracker.jar"]
