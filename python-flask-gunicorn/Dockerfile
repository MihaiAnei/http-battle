FROM python:3.7-alpine

ADD . /app

WORKDIR /app

RUN pip install -r requirements.txt && \
    pip install gunicorn

CMD [ "gunicorn", "-c", "gunicorn-conf.py", "python-flask:app" ]