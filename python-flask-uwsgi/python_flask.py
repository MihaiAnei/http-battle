import random

from flask import Flask, request
from flask_restful import Api, Resource

app = Flask(__name__)
api = Api(app)


class Benchmark(Resource):
    def get(self):
        return random.randint(0, 999), 200


api.add_resource(Benchmark, "/benchmark")


if __name__ == "__main__":
    app.run(host="0.0.0.0", port=8080, debug=False)
