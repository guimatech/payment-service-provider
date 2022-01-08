FROM node:10
RUN mkdir /usr/src/app
WORKDIR /usr/src/app
COPY package*.json ./
RUN npm install
COPY . .
EXPOSE 3000
EXPOSE 9229
CMD [ "node", "." ]