FROM amazonlinux
RUN yum install -y gcc-c++
COPY . .
RUN g++ prog1.cpp -o prog1
RUN g++ prog2.cpp -o prog2
CMD ./prog1
