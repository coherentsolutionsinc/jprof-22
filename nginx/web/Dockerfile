FROM openresty/openresty:alpine-fat

WORKDIR /demo

RUN /usr/local/openresty/luajit/bin/luarocks install lua-resty-jwt

EXPOSE 8080

COPY . /demo

ENTRYPOINT ["/demo/docker-entrypoint.sh"]
