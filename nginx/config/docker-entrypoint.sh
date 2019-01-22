#!/bin/sh

echo "Generating nginx config..."

python /cg/etc/yaml_to_conf.py && \
cat /cg/etc/nginx.conf