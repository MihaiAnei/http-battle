FROM python:3.7-alpine

ADD requirements.txt /app/requirements.txt

WORKDIR /app

RUN pip install -r requirements.txt

ADD . /app

CMD [ "gunicorn", "-c", "gunicorn-conf.py", "python-flask:app" ]