# Etap buildowania
FROM node:20 AS build
WORKDIR /app
COPY . .
RUN npm install
RUN npm run build

# Etap serwowania statycznego buildu
FROM nginx:alpine
COPY --from=build /app/dist /usr/share/nginx/html

# Kopiujemy domyślny nginx config
COPY nginx.conf /etc/nginx/conf.d/default.conf

EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]
