FROM python:3.7-alpine

COPY . /app

WORKDIR /app

RUN apk add --update --no-cache build-base \
    && pip install -r requirements.txt \
    && apk del build-base

EXPOSE 8080

CMD ["uvicorn", "main:app", "--host", "0.0.0.0", "--port", "8080", "--log-level", "warning"]