FROM python:3.7-alpine

ADD requirements.txt /app/requirements.txt

WORKDIR /app

RUN apk update \
    && apk add --virtual build-dependencies \
        build-base \
        musl-dev \
        linux-headers \
    && apk add pcre-dev \
    && pip install -r requirements.txt \
    && apk del build-dependencies \
    && rm -rf /var/cache/apk/*

RUN addgroup -S uwsgi && adduser -S uwsgi -G uwsgi

ADD . /app

RUN chown -R uwsgi:uwsgi /app

USER uwsgi

EXPOSE 8080

CMD [ "uwsgi", "--ini", "uwsgi.ini" ]