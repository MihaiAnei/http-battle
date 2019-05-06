from flask import Flask, request
from flask_restful import Resource, Api
from typing import List

app = Flask(__name__)
api = Api(app)

class Benchmark(Resource):
    def post(self):
        numbers: List[int] = request.get_json(force=True)
        # Because sets are not JSON serializable
        return list(set(numbers)), 200

api.add_resource(Benchmark, '/benchmark')


if __name__ == '__main__':
    app.run(host="0.0.0.0", port=8080, debug=False)
