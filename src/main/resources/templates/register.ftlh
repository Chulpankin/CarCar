<#-- src/main/resources/templates/register.ftlh -->
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Register</title>
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
                    <span class="card-title">Регистрация</span>

                    <#-- Вывод ошибок валидации -->
                    <#if bindingResult??>
                        <#list bindingResult.allErrors as err>
                            <div class="card-panel red lighten-4 red-text text-darken-4">
                                ${err.defaultMessage}
                            </div>
                        </#list>
                    </#if>

                    <form action="/register" method="post">
                        <div class="input-field">
                            <input id="email" name="email" type="email" required value="${userForm.email!}">
                            <label for="email">Email</label>
                        </div>
                        <div class="input-field">
                            <input id="username" name="username" type="text" required value="${userForm.username!}">
                            <label for="username">Username</label>
                        </div>
                        <div class="input-field">
                            <input id="password" name="password" type="password" required>
                            <label for="password">Password</label>
                        </div>
                        <button class="btn waves-effect waves-light" type="submit">Register</button>
                    </form>

                    <div class="section">
                        Уже есть аккаунт? <a href="/login">Войти</a>
                    </div>

                </div>
            </div>
        </div>
    </div>
</div>
<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
</body>
</html>
