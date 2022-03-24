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