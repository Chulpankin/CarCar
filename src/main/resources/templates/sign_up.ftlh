<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <title>Sign Up</title>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css" rel="stylesheet"/>
</head>
<body>
<div class="container">
    <div class="row" style="margin-top: 3rem;">
        <div class="col s12 m6 offset-m3">
            <div class="card">
                <div class="card-content">
                    <span class="card-title center">Sign Up</span>

                    <#if errorMessage??>
                        <div class="card-panel red lighten-2 white-text">${errorMessage}</div>
                    </#if>

                    <form method="post">
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

                        <div class="input-field">
                            <input type="text" id="username" name="username" value="${form.username!}" class="<#if errors?? && errors["username"]??>invalid</#if>"/>
                            <label for="username" class="<#if form.username??>active</#if>">Username</label>
                            <#if errors?? && errors["username"]??>
                                <span class="helper-text red-text">${errors["username"][0]}</span>
                            </#if>
                        </div>

                        <div class="input-field">
                            <input type="email" id="email" name="email" value="${form.email!}" class="<#if errors?? && errors["email"]??>invalid</#if>"/>
                            <label for="email" class="<#if form.email??>active</#if>">Email</label>
                            <#if errors?? && errors["email"]??>
                                <span class="helper-text red-text">${errors["email"][0]}</span>
                            </#if>
                        </div>

                        <div class="input-field">
                            <input type="password" id="password" name="password" class="<#if errors?? && errors["password"]??>invalid</#if>"/>
                            <label for="password">Password</label>
                            <#if errors?? && errors["password"]??>
                                <span class="helper-text red-text">${errors["password"][0]}</span>
                            </#if>
                        </div>

                        <div class="center" style="margin-top: 1rem;">
                            <button type="submit" class="btn waves-effect waves-light">Create Account</button>
                        </div>
                    </form>
                </div>
                <div class="card-action center">
                    <a href="/signIn">Already have an account? Sign In</a>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
<script>
    document.addEventListener('DOMContentLoaded', function () {
        M.updateTextFields();
    });
</script>
</body>
</html>
