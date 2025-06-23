FROM node:alpine AS build

WORKDIR /app

COPY package.json .

RUN npm install

COPY . .

RUN npm run build



FROM node:alpine

WORKDIR /app

COPY --from=build /app/dist/ /app/dist/

EXPOSE 5173

COPY package.json .

COPY vite.config.ts .

RUN npm install typescript

EXPOSE 5173

CMD ["npm", "run", "preview"]