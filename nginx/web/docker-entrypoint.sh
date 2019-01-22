#!/bin/sh

export JWT_SECRET=$(cat "/demo/key.pub")

echo "Starting nginx..."
/usr/local/openresty/bin/openresty -g 'daemon off;' -c /demo/nginx.conf