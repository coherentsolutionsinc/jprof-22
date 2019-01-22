FROM python:2

WORKDIR /cg/etc/

RUN pip install pyyaml jinja2

COPY . /cg

ENTRYPOINT ["/cg/docker-entrypoint.sh"]