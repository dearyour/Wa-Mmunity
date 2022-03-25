요구 패키지 설치
```bash
$ pip install -r requirements.txt
```

flask([공식문서](https://flask.palletsprojects.com/en/2.0.x/))
- python의 마이크로 웹 프레임워크

설치
```bash
$ pip install flask
```

기본 사용법
```python
# app.py
from flask import Flask

app = Flask(__name__)

@app.route('/')
def index():
    return '<p>Hello, World!</p>'

@app.route('/home')
def home():
    return '''
    <h1> h1 제목 </h1>
    <p> p 본문 </p>
    '''

@app.route('/user/<user_name>/<int:user_id>')
def user(user_name, user_id):
    return f'Hello, {user_name} ({user_id})!'

# debug = True 명시해 해당 파일 코드 수정할때마다 Flask 새로고침
if __name__ == '__main__':
    app.run(debug = True)

# $ export FLASK_APP = app
# $ flask run
```


flask-restful([공식문서](https://flask-restful.readthedocs.io/en/latest/index.html))
- REST-API 빌드를 위한 flask의 extension

설치
```bash
$ pip install flask-restful
```

기본 사용법
```python
# api.py
from flask import Flask
from flask_restful import Resource, Api

app = Flask(__name__)
api = Api(app)

# HelloWord에서 get이 실행되면 {'hello': 'world'} 반환
class HelloWorld(Resource):
    def get(self):
        return {'hello': 'world'}

# / url로 요청시 HelloWorld 응답
api.add_resource(HelloWorld, '/')

if __name__ == '__main__':
    app.run(debug=True)

# $ python api.py
```

예제
```python
from flask import Flask, request
from flask_restful import Resource, Api

app = Flask(__name__)
api = Api(app)

todos = {}

class TodoSimple(Resource):
    def get(self, todo_id):
        return {todo_id: todos[todo_id]}

    def put(self, todo_id):
        todos[todo_id] = request.form['data']
        return {todo_id: todos[todo_id]}

api.add_resource(TodoSimple, '/<string:todo_id>')

if __name__ == '__main__':
    app.run(debug=True)

# $ curl http://localhost:5000/todo1 -d "data=Remember the milk" -X PUT
# {"todo1": "Remember the milk"}
# $ curl http://localhost:5000/todo1
# {"todo1": "Remember the milk"}
```


폴더구조
```
bigdata
 ㄴ data
    ㄴ input
    ㄴ output
 ㄴ models
```