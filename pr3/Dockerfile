FROM alpine
RUN apk add g++
COPY . .
RUN g++ pr3.cpp -masm=intel -o pr3
CMD ./pr3
