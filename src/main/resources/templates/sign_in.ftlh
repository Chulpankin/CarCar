<#import "/spring.ftl" as spring />
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Вход</title>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css" rel="stylesheet"/>
</head>
<body>
<div class="container" style="margin-top: 3rem;">
    <div class="row">
        <div class="col s12 m6 offset-m3">
            <div class="card">
                <div class="card-content">
                    <span class="card-title center">Вход</span>
                    <#if error??>
                        <div class="card-panel red lighten-2 white-text">Неверные данные</div>
                    </#if>
                    <#if oauthError??>
                        <div class="card-panel red lighten-2 white-text">
                            OAuth error: ${oauthError}
                        </div>
                    </#if>
                    <form method="post">
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

                        <div class="input-field">
                            <input id="username" name="username" type="text" required>
                            <label for="username">Логин</label>
                        </div>

                        <div class="input-field">
                            <input id="password" name="password" type="password" required>
                            <label for="password">Пароль</label>
                        </div>

                        <div class="center" style="margin-top: 1rem;">
                            <button type="submit" class="btn waves-effect waves-light">Войти</button>
                        </div>
                    </form>
                    <div class="center" style="margin-top: 1.5rem;">
                        <a href="/auth/google" class="btn red darken-1">Войти через Google</a>
                    </div>
                </div>
                <div class="card-action center">
                    <a href="/signUp">Нет аккаунта? Зарегистрируйтесь</a>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
<script>M.updateTextFields();</script>
</body>
</html>
