FROM nginx:alpine

LABEL authors="David"

# Copy custom nginx configuration first
COPY ./nginx.conf /etc/nginx/conf.d/default.conf

# Copy frontend source files (index.html, css, js)
COPY ./src /usr/share/nginx/html

EXPOSE 80

CMD ["nginx", "-g", "daemon off;"]