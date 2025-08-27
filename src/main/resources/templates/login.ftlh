<#-- src/main/resources/templates/login.ftlh -->
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <!-- Materialize CSS -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css" rel="stylesheet"/>
    <style>
        body { display: flex; height: 100vh; align-items: center; justify-content: center; background: #f5f5f5; }
        .card { padding: 20px; }
    </style>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col s12 m6 offset-m3">
            <div class="card">
                <div class="card-content">
                    <span class="card-title">Вход</span>

                    <#-- Показываем сообщение об успешной регистрации -->
                    <#if param.registered??>
                        <div class="card-panel green lighten-4 green-text text-darken-4">
                            Регистрация прошла успешно. Войдите, пожалуйста.
                        </div>
                    </#if>

                    <form action="/login" method="post">
                        <div class="input-field">
                            <input id="username" name="username" type="text" required>
                            <label for="username">Username</label>
                        </div>
                        <div class="input-field">
                            <input id="password" name="password" type="password" required>
                            <label for="password">Password</label>
                        </div>
                        <button class="btn waves-effect waves-light" type="submit">Login</button>
                    </form>

                    <div class="section">
                        <a class="btn blue lighten-1" href="/oauth2/authorization/google">
                            <i class="material-icons left">login</i>Google Login
                        </a>
                    </div>
                    <div class="section">
                        Нет аккаунта? <a href="/register">Зарегистрироваться</a>
                    </div>

                </div>
            </div>
        </div>
    </div>
</div>
<!-- Materialize JS -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
</body>
</html>
