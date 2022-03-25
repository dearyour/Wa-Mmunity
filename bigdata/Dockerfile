FROM python:3.9.6
WORKDIR /test
COPY . .
RUN pip install -r requirements.txt

EXPOSE 5000

CMD python ./app.py