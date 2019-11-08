from fastapi import FastAPI

app = FastAPI()


@app.get("/benchmark", status_code=200)
def read_root():
    return {}
